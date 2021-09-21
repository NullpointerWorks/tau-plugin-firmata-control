package com.nullpointerworks.taup.firmatacontrol.model;

import java.io.IOException;

import org.firmata4j.IODeviceEventListener;
import org.firmata4j.Pin;
import org.firmata4j.Pin.Mode;

import com.axtron.tau.model.mainboard.firmata.IFirmata;

/**
 * firmata proxy delegate to ensure firmata commands are thread-safe
 * @author Michiel
 */
public class FirmataSyncProxy implements IFirmata 
{
	private final IFirmata firmata;
	
	public FirmataSyncProxy(IFirmata f)
	{
		firmata = f;
	}
	
	@Override
	public synchronized boolean connect(int location) throws IOException 
	{
		return firmata.connect(location);
	}

	@Override
	public synchronized void addEventListener(IODeviceEventListener firmataConnection) 
	{
		firmata.addEventListener(firmataConnection);
	}

	@Override
	public synchronized void start() throws IOException 
	{
		firmata.start();
	}

	@Override
	public synchronized void ensureInitializationIsDone() throws InterruptedException 
	{
		firmata.ensureInitializationIsDone();
	}

	@Override
	public synchronized void stop() throws IOException 
	{
		firmata.stop();
	}

	@Override
	public synchronized Pin getPin(int pin) 
	{
		return firmata.getPin(pin);
	}

	@Override
	public synchronized void setMode(int pin, Mode mode) throws IOException 
	{
		firmata.setMode(pin, mode);
	}

	@Override
	public synchronized void setPin(int pin, int state) throws IOException 
	{
		firmata.setPin(pin, state);
	}

	@Override
	public synchronized long getValue(int pin)
	{
		return firmata.getValue(pin);
	}
}
