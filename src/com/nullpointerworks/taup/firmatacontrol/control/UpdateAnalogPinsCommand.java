package com.nullpointerworks.taup.firmatacontrol.control;

import org.firmata4j.Pin;

import static com.axtron.tau.util.pinout.MEGA328.*;
import com.axtron.tau.control.interfaces.RunnableCommand;
import com.axtron.tau.model.mainboard.firmata.IFirmata;
import com.nullpointerworks.taup.firmatacontrol.view.ControlPanel;
import com.nullpointerworks.util.Convert;

public class UpdateAnalogPinsCommand implements RunnableCommand 
{
	private IFirmata firmata;
	private ControlPanel view;
	private boolean run;
	
	public UpdateAnalogPinsCommand(IFirmata f, ControlPanel v)
	{
		firmata = f;
		view = v;
	}
	
	@Override
	public void onExecute() 
	{
		run = true;
		float vref = 0f;
		
		Pin a0 = firmata.getPin(A0);
		Pin a1 = firmata.getPin(A1);
		Pin a2 = firmata.getPin(A2);
		Pin a3 = firmata.getPin(A3);
		Pin a4 = firmata.getPin(A4);
		Pin a5 = firmata.getPin(A5);
		
		while(run)
		{
			vref = Convert.toFloat( view.getReferenceVoltage() );
			
			long a0v = a0.getValue();
			view.setDisplayAnalog(a0v, toVoltage(a0v, vref), A0);
			
			long a1v = a1.getValue();
			view.setDisplayAnalog(a1v, toVoltage(a1v, vref), A1);
			
			long a2v = a2.getValue();
			view.setDisplayAnalog(a2v, toVoltage(a2v, vref), A2);
			
			long a3v = a3.getValue();
			view.setDisplayAnalog(a3v, toVoltage(a3v, vref), A3);
			
			long a4v = a4.getValue();
			view.setDisplayAnalog(a4v, toVoltage(a4v, vref), A4);
			
			long a5v = a5.getValue();
			view.setDisplayAnalog(a5v, toVoltage(a5v, vref), A5);
			
			sleep(250);
		}
	}
	
	private float toVoltage(long adc, float vref)
	{
		float d = (float)adc / (float)1024;
		return d * vref;
	}
	
	private void sleep(long millies)
	{
		try 
		{
			Thread.sleep(millies);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
}
