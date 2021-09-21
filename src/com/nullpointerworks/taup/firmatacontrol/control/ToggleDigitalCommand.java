package com.nullpointerworks.taup.firmatacontrol.control;

import static com.axtron.tau.util.pinout.MEGA328.HIGH;
import static com.axtron.tau.util.pinout.MEGA328.LOW;

import java.io.IOException;

import org.firmata4j.Pin;

import com.axtron.tau.control.interfaces.ActionCommand;
import com.axtron.tau.model.log.ILogger;
import com.axtron.tau.model.log.LogResult;
import com.axtron.tau.model.mainboard.firmata.IFirmata;
import com.nullpointerworks.taup.firmatacontrol.view.ControlPanel;

public class ToggleDigitalCommand implements ActionCommand 
{
	private IFirmata firmata;
	private ILogger log;
	private ControlPanel control;
	private int pinnumber;
	
	public ToggleDigitalCommand(IFirmata f, ILogger l, ControlPanel v, final int pin)
	{
		firmata = f;
		log = l;
		control = v;
		pinnumber = pin;
	}
	
	@Override
	public void onExecute() 
	{
		Pin d = firmata.getPin(pinnumber);
		long state = d.getValue();
		
		if (state>0) state = LOW;
		else state = HIGH;
		
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
		
		delay_ms(10);
	}
	
	private void delay_ms(int milli)
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
