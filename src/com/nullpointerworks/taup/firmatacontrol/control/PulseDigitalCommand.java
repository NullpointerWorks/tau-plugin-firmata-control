package com.nullpointerworks.taup.firmatacontrol.control;

import com.axtron.tau.control.interfaces.ActionCommand;
import com.axtron.tau.model.log.ILogger;
import com.axtron.tau.model.mainboard.firmata.IFirmata;
import com.nullpointerworks.taup.firmatacontrol.view.ControlPanel;

public class PulseDigitalCommand implements ActionCommand 
{
	private PulseThreadCommand pulse;
	
	public PulseDigitalCommand(IFirmata f, ILogger l, ControlPanel v, final int pin)
	{
		pulse = new PulseThreadCommand(f,l,v,pin);
	}
	
	@Override
	public void onExecute() 
	{
		if (!pulse.isRunning())
		{
			pulse.begin();
		}
		else
		{
			pulse.stop();
		}
	}
}
