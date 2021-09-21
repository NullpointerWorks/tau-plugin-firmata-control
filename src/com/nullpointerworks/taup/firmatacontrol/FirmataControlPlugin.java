package com.nullpointerworks.taup.firmatacontrol;

import javax.swing.JPanel;

import static com.axtron.tau.util.pinout.MEGA328.*;

import com.axtron.tau.control.interfaces.ActionCommand;
import com.axtron.tau.control.interfaces.Command;
import com.axtron.tau.control.interfaces.RunnableCommand;
import com.axtron.tau.model.log.ILogger;
import com.axtron.tau.model.mainboard.firmata.IFirmata;
import com.axtron.tau.model.plugin.TestPlugin;
import com.axtron.tau.util.Relation;
import com.axtron.tau.util.Version;
import com.nullpointerworks.taup.firmatacontrol.control.PinSetupCommand;
import com.nullpointerworks.taup.firmatacontrol.control.PulseDigitalCommand;
import com.nullpointerworks.taup.firmatacontrol.control.ToggleDigitalCommand;
import com.nullpointerworks.taup.firmatacontrol.control.UpdateAnalogPinsCommand;
import com.nullpointerworks.taup.firmatacontrol.model.FirmataSyncProxy;
import com.nullpointerworks.taup.firmatacontrol.view.ControlPanel;

public class FirmataControlPlugin extends TestPlugin
{
	public FirmataControlPlugin() {}
	
	@Override
	public String getName()
	{
		return "Firmata Control";
	}
	
	@Override
	public String getDescription() 
	{
		return 	  "Version    : "+getVersion().getStringFull() + "\n\n"
				+ "This plugin allows for controlling the Firmata device on the mainboard. You can toggle, pulse, and read analog values.";
	}
	
	@Override
	public Version getVersion()
	{
		return new Version(1,0,0,"alpha");
	}
	
	@Override
	public Relation getRelation() 
	{
		return Relation.UNKNOWN;
	}
	
	@Override
	public JPanel getTestInterface() 
	{
		IFirmata mFirmata = new FirmataSyncProxy( this.getFirmata() );
		ILogger mLog = this.getLogger();
		
		ControlPanel vPanel = new ControlPanel();
		
		Command cSetup = new PinSetupCommand(mFirmata, mLog);
		RunnableCommand cAnalogUpdate = new UpdateAnalogPinsCommand(mFirmata, vPanel);
		ActionCommand cToggleD2 = new ToggleDigitalCommand(mFirmata, mLog, vPanel, D2);
		ActionCommand cToggleD3 = new ToggleDigitalCommand(mFirmata, mLog, vPanel, D3);
		ActionCommand cToggleD4 = new ToggleDigitalCommand(mFirmata, mLog, vPanel, D4);
		ActionCommand cToggleD5 = new ToggleDigitalCommand(mFirmata, mLog, vPanel, D5);
		ActionCommand cToggleD6 = new ToggleDigitalCommand(mFirmata, mLog, vPanel, D6);
		ActionCommand cToggleD7 = new ToggleDigitalCommand(mFirmata, mLog, vPanel, D7);
		ActionCommand cPulseD2 = new PulseDigitalCommand(mFirmata, mLog, vPanel, D2);
		ActionCommand cPulseD3 = new PulseDigitalCommand(mFirmata, mLog, vPanel, D3);
		ActionCommand cPulseD4 = new PulseDigitalCommand(mFirmata, mLog, vPanel, D4);
		ActionCommand cPulseD5 = new PulseDigitalCommand(mFirmata, mLog, vPanel, D5);
		ActionCommand cPulseD6 = new PulseDigitalCommand(mFirmata, mLog, vPanel, D6);
		ActionCommand cPulseD7 = new PulseDigitalCommand(mFirmata, mLog, vPanel, D7);
		
		vPanel.setToggleD2Command(cToggleD2);
		vPanel.setToggleD3Command(cToggleD3);
		vPanel.setToggleD4Command(cToggleD4);
		vPanel.setToggleD5Command(cToggleD5);
		vPanel.setToggleD6Command(cToggleD6);
		vPanel.setToggleD7Command(cToggleD7);
		vPanel.setPulseD2Command(cPulseD2);
		vPanel.setPulseD3Command(cPulseD3);
		vPanel.setPulseD4Command(cPulseD4);
		vPanel.setPulseD5Command(cPulseD5);
		vPanel.setPulseD6Command(cPulseD6);
		vPanel.setPulseD7Command(cPulseD7);
		
		cSetup.onExecute();
		Thread t = new Thread(cAnalogUpdate);
		t.start();
		
		return vPanel;
	}
}
