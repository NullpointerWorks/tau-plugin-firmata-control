package com.nullpointerworks.taup.firmatacontrol.control;

import static com.axtron.tau.util.pinout.MEGA328.*;

import java.io.IOException;

import org.firmata4j.Pin;

import com.axtron.tau.control.interfaces.Command;
import com.axtron.tau.model.log.ILogger;
import com.axtron.tau.model.log.LogResult;
import com.axtron.tau.model.mainboard.firmata.IFirmata;

public class PinSetupCommand implements Command 
{
	private IFirmata firmata;
	private ILogger log;
	
	public PinSetupCommand(IFirmata f, ILogger l) 
	{
		firmata = f;
		log = l;
	}
	
	@Override
	public void onExecute() 
	{
		Pin a0 = firmata.getPin(A0);
		Pin a1 = firmata.getPin(A1);
		Pin a2 = firmata.getPin(A2);
		Pin a3 = firmata.getPin(A3);
		Pin a4 = firmata.getPin(A4);
		Pin a5 = firmata.getPin(A5);
		
		Pin d2 = firmata.getPin(D2);
		Pin d3 = firmata.getPin(D3);
		Pin d4 = firmata.getPin(D4);
		Pin d5 = firmata.getPin(D5);
		Pin d6 = firmata.getPin(D6);
		Pin d7 = firmata.getPin(D7);
		
		try 
		{
			a0.setMode(Pin.Mode.ANALOG);
			a1.setMode(Pin.Mode.ANALOG);
			a2.setMode(Pin.Mode.ANALOG);
			a3.setMode(Pin.Mode.ANALOG);
			a4.setMode(Pin.Mode.ANALOG);
			a5.setMode(Pin.Mode.ANALOG);
			
			d2.setMode(Pin.Mode.OUTPUT);
			d3.setMode(Pin.Mode.OUTPUT);
			d4.setMode(Pin.Mode.OUTPUT);
			d5.setMode(Pin.Mode.OUTPUT);
			d6.setMode(Pin.Mode.OUTPUT);
			d7.setMode(Pin.Mode.OUTPUT);
		} 
		catch (IOException e) 
		{
			log.println(LogResult.ERROR, "Error setting up pinmodes! Check connection to the Firmata divice.");
			e.printStackTrace();
			return;
		}
		
		log.println(LogResult.LOG, "Pinmodes have been set. Firmata ready.");
	}
}
