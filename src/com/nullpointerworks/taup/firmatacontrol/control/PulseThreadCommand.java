package com.nullpointerworks.taup.firmatacontrol.control;

import static com.axtron.tau.util.pinout.LOGICSTATE.HIGH;
import static com.axtron.tau.util.pinout.LOGICSTATE.LOW;

import java.io.IOException;

import org.firmata4j.Pin;

import com.axtron.tau.control.interfaces.RunnableCommand;
import com.axtron.tau.model.log.ILogger;
import com.axtron.tau.model.log.LogResult;
import com.axtron.tau.model.mainboard.firmata.IFirmata;
import com.nullpointerworks.taup.firmatacontrol.view.ControlPanel;
import com.nullpointerworks.util.Convert;

public class PulseThreadCommand implements RunnableCommand 
{
	private IFirmata firmata;
	private ILogger log;
	private ControlPanel control;
	private int pinnumber;
	private boolean running;
	
	public PulseThreadCommand(IFirmata f, ILogger l, ControlPanel v, final int pin)
	{
		firmata = f;
		log = l;
		control = v;
		pinnumber = pin;
		stop();
	}
	
	public synchronized boolean isRunning()
	{
		return running;
	}
	
	public synchronized void begin()
	{
		running = true;
		Thread t = new Thread(this);
		t.start();
	}
	
	public synchronized void stop()
	{
		running = false;
	}
	
	// do not synchronize
	@Override
	public void onExecute() 
	{
		control.setDisplayPulse(true, pinnumber);
		
		Pin d 			= firmata.getPin(pinnumber);
		long state 		= d.getValue();
		
		float sleep 	= Convert.toFloat( control.getPulseWidth() );
		float duty 		= 0.01f * Convert.toFloat( control.getDutyCycle() );
		boolean repeat 	= control.getRepeatState();
		
		long highSleep 	= (long)( sleep*duty );
		long lowSleep 	= (long)(sleep) - highSleep;
		
		while(isRunning())
		{
			// do HIGH time
			state = toggle(state);
			try 
			{
				d.setValue(state);
			} 
			catch (IOException e) 
			{
				log.println(LogResult.ERROR, "Error setting the value of pin D"+pinnumber+"!");
				e.printStackTrace();
				return;
			}
			control.setDisplayDigital(state != 0, pinnumber);
			delay_ms(highSleep);
			
			// do LOW time
			state = toggle(state);
			try 
			{
				d.setValue(state);
			} 
			catch (IOException e) 
			{
				log.println(LogResult.ERROR, "Error setting the value of pin "+pinnumber+"!");
				e.printStackTrace();
				return;
			}
			control.setDisplayDigital(state != 0, pinnumber);
			delay_ms(lowSleep);
			
			if (!repeat) stop();
		}
		
		control.setDisplayPulse(false, pinnumber);
	}
	
	private long toggle(long s)
	{
		if (s==HIGH) 
			return LOW;
		return HIGH;
	}
	
	private void delay_ms(long milli)
	{
		try 
		{
			Thread.sleep(milli);
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
}
