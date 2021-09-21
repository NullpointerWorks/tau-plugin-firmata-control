package com.nullpointerworks.taup.firmatacontrol.view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.axtron.tau.view.awt.AbsoluteLayout;
import com.nullpointerworks.taup.firmatacontrol.view.swing.JTextFieldFilter;
import com.nullpointerworks.util.Convert;

public class ControlPanel extends JPanel 
{
	private static final long serialVersionUID = -9042183946142834151L;
	
	private final Color COLOR_HIGH = new Color(255,100,100);
	private final Color COLOR_LOW = new Color(150,150,150);
	
	private JButton jbToggleD2;
	private JButton jbToggleD3;
	private JButton jbToggleD4;
	private JButton jbToggleD5;
	private JButton jbToggleD6;
	private JButton jbToggleD7;
	
	private JButton jbPulseD2;
	private JButton jbPulseD3;
	private JButton jbPulseD4;
	private JButton jbPulseD5;
	private JButton jbPulseD6;
	private JButton jbPulseD7;
	
	private JPanel jpMarkerD2;
	private JPanel jpMarkerD3;
	private JPanel jpMarkerD4;
	private JPanel jpMarkerD5;
	private JPanel jpMarkerD6;
	private JPanel jpMarkerD7;
	
	private JTextField jtfPulseWidth;
	private JTextField jtfDutyCycle;
	private JCheckBox jcbRepeat;
	private JTextField jtfAREF;
	
	private JTextField jtfAnalogADC0;
	private JTextField jtfAnalogADC1;
	private JTextField jtfAnalogADC2;
	private JTextField jtfAnalogADC3;
	private JTextField jtfAnalogADC4;
	private JTextField jtfAnalogADC5;
	
	private JTextField jtfAnalogV0;
	private JTextField jtfAnalogV1;
	private JTextField jtfAnalogV2;
	private JTextField jtfAnalogV3;
	private JTextField jtfAnalogV4;
	private JTextField jtfAnalogV5;
	
	public ControlPanel()
	{
		// digital UI
		
		jbToggleD2 = new JButton();
		jbPulseD2 = new JButton();
		jpMarkerD2 = new JPanel();
		
		jbToggleD3 = new JButton();
		jbPulseD3 = new JButton();
		jpMarkerD3 = new JPanel();
		
		jbToggleD4 = new JButton();
		jbPulseD4 = new JButton();
		jpMarkerD4 = new JPanel();
		
		jbToggleD5 = new JButton();
		jbPulseD5 = new JButton();
		jpMarkerD5 = new JPanel();
		
		jbToggleD6 = new JButton();
		jbPulseD6 = new JButton();
		jpMarkerD6 = new JPanel();
		
		jbToggleD7 = new JButton();
		jbPulseD7 = new JButton();
		jpMarkerD7 = new JPanel();
		
		JLabel jlblPulseWidth = new JLabel("Pulsewidth (ms)");
		jlblPulseWidth.setLocation(15, 180);
		jlblPulseWidth.setSize(100, 20);
		jlblPulseWidth.setPreferredSize(jlblPulseWidth.getSize());
		
		jtfPulseWidth = new JTextField();
		jtfPulseWidth.setLocation(120, 180);
		jtfPulseWidth.setSize(80, 20);
		jtfPulseWidth.setPreferredSize(jtfPulseWidth.getSize());
		jtfPulseWidth.setDocument( new JTextFieldFilter( JTextFieldFilter.NUMERIC ) );
		jtfPulseWidth.setText("1000");
		
		JLabel jlblDutyCycle = new JLabel("Duty cycle (%)");
		jlblDutyCycle.setLocation(15, 205);
		jlblDutyCycle.setSize(100, 20);
		jlblDutyCycle.setPreferredSize(jlblDutyCycle.getSize());
		
		jtfDutyCycle = new JTextField();
		jtfDutyCycle.setLocation(120, 205);
		jtfDutyCycle.setSize(80, 20);
		jtfDutyCycle.setPreferredSize(jtfDutyCycle.getSize());
		jtfDutyCycle.setDocument( new JTextFieldFilter( JTextFieldFilter.FLOAT ) );
		jtfDutyCycle.setText("50");
		jtfDutyCycle.addKeyListener( new KeyAdapter()
		{
			public void keyReleased(KeyEvent e) 
			{
				if (jtfDutyCycle.getText().length()==0) return;
				float v = Convert.toFloat( jtfDutyCycle.getText() );
				if (v > 100f) jtfDutyCycle.setText("100");
			}
		} );
		
		JLabel jlblRepeat = new JLabel("Repeat");
		jlblRepeat.setLocation(15, 230);
		jlblRepeat.setSize(100, 20);
		jlblRepeat.setPreferredSize(jlblRepeat.getSize());
		
		jcbRepeat = new JCheckBox();
		jcbRepeat.setLocation(120, 230);
		jcbRepeat.setSize(20, 20);
		jcbRepeat.setPreferredSize(jcbRepeat.getSize());
		
		JPanel jpDigital = new JPanel();
		jpDigital.setBorder( BorderFactory.createTitledBorder("Digital Control") );
		jpDigital.setLayout( new AbsoluteLayout() );
		jpDigital.setLocation(10, 10);
		jpDigital.setSize(220, 260);
		jpDigital.setPreferredSize(jpDigital.getSize());
		makeDigitalUI("D2", jbToggleD2, jbPulseD2, jpMarkerD2, jpDigital, 20);
		makeDigitalUI("D3", jbToggleD3, jbPulseD3, jpMarkerD3, jpDigital, 45);
		makeDigitalUI("D4", jbToggleD4, jbPulseD4, jpMarkerD4, jpDigital, 70);
		makeDigitalUI("D5", jbToggleD5, jbPulseD5, jpMarkerD5, jpDigital, 95);
		makeDigitalUI("D6", jbToggleD6, jbPulseD6, jpMarkerD6, jpDigital, 120);
		makeDigitalUI("D7", jbToggleD7, jbPulseD7, jpMarkerD7, jpDigital, 145);
		jpDigital.add(jlblPulseWidth);
		jpDigital.add(jtfPulseWidth);
		jpDigital.add(jlblDutyCycle);
		jpDigital.add(jtfDutyCycle);
		jpDigital.add(jlblRepeat);
		jpDigital.add(jcbRepeat);
		
		// analog UI
		
		jtfAnalogADC0 = new JTextField();
		jtfAnalogV0 = new JTextField();
		
		jtfAnalogADC1 = new JTextField();
		jtfAnalogV1 = new JTextField();
		
		jtfAnalogADC2 = new JTextField();
		jtfAnalogV2 = new JTextField();
		
		jtfAnalogADC3 = new JTextField();
		jtfAnalogV3 = new JTextField();
		
		jtfAnalogADC4 = new JTextField();
		jtfAnalogV4 = new JTextField();
		
		jtfAnalogADC5 = new JTextField();
		jtfAnalogV5 = new JTextField();
		
		JLabel jlblAREF = new JLabel("Analog REF (V)");
		jlblAREF.setLocation(15, 180);
		jlblAREF.setSize(80, 20);
		jlblAREF.setPreferredSize(jlblAREF.getSize());
		
		jtfAREF = new JTextField();
		jtfAREF.setLocation(110, 180);
		jtfAREF.setSize(70, 20);
		jtfAREF.setPreferredSize(jtfAREF.getSize());
		jtfAREF.setDocument( new JTextFieldFilter( JTextFieldFilter.FLOAT ) );
		jtfAREF.setText("4.000");
		/*
		jtfAREF.addKeyListener( new KeyAdapter()
		{
			public void keyReleased(KeyEvent e) 
			{
				if (jtfAREF.getText().length()==0) return;
				float v = Convert.toFloat( jtfAREF.getText() );
				if (v > 5f) jtfAREF.setText("5.000");
			}
		} );//*/
		
		JPanel jpAnalog = new JPanel();
		jpAnalog.setBorder( BorderFactory.createTitledBorder("Analog Input") );
		jpAnalog.setLayout( new AbsoluteLayout() );
		jpAnalog.setLocation(240, 10);
		jpAnalog.setSize(200, 260);
		jpAnalog.setPreferredSize(jpAnalog.getSize());
		makeAnalogUI("A0", jtfAnalogADC0, jtfAnalogV0, jpAnalog, 20);
		makeAnalogUI("A1", jtfAnalogADC1, jtfAnalogV1, jpAnalog, 45);
		makeAnalogUI("A2", jtfAnalogADC2, jtfAnalogV2, jpAnalog, 70);
		makeAnalogUI("A3", jtfAnalogADC3, jtfAnalogV3, jpAnalog, 95);
		makeAnalogUI("A4", jtfAnalogADC4, jtfAnalogV4, jpAnalog, 120);
		makeAnalogUI("A5", jtfAnalogADC5, jtfAnalogV5, jpAnalog, 145);
		jpAnalog.add(jlblAREF);
		jpAnalog.add(jtfAREF);
		
		// panel UI
		
		//this.setLayout( new BorderLayout() );
		this.setLayout( new AbsoluteLayout() );
        this.setSize(600, 500);
        this.setPreferredSize(this.getSize());
        this.add(jpDigital);
        this.add(jpAnalog);
	}
	
	private void makeDigitalUI(String label, JButton toggle, JButton pulse, JPanel jpMarker, JPanel panel, int height)
	{
		JLabel jlbl = new JLabel(label);
		jlbl.setLocation(15, height);
		jlbl.setSize(20, 20);
		jlbl.setPreferredSize(jlbl.getSize());
		
		jpMarker.setBounds(40,height, 20,20);
		jpMarker.setPreferredSize(jpMarker.getSize());
		jpMarker.setBackground( COLOR_LOW );
		
		toggle.setText("Toggle");
		toggle.setLocation(65, height);
		toggle.setSize(70, 20);
		toggle.setPreferredSize(toggle.getSize());
		
		pulse.setText("Pulse");
		pulse.setLocation(140, height);
		pulse.setSize(60, 20);
		pulse.setPreferredSize(pulse.getSize());
		
		panel.add(jlbl);
		panel.add(toggle);
		panel.add(pulse);
		panel.add(jpMarker);
	}
	
	private void makeAnalogUI(String label, JTextField adc, JTextField voltage, JPanel panel, int height)
	{
		JLabel jlbl = new JLabel(label);
		jlbl.setLocation(15, height);
		jlbl.setSize(20, 20);
		jlbl.setPreferredSize(jlbl.getSize());
		
		adc.setLocation(40, height);
		adc.setSize(60, 20);
		adc.setPreferredSize(adc.getSize());
		adc.setText("0");
		
		voltage.setLocation(110, height);
		voltage.setSize(70, 20);
		voltage.setPreferredSize(voltage.getSize());
		voltage.setText("0");
		
		panel.add(jlbl);
		panel.add(adc);
		panel.add(voltage);
	}
	
	public void setToggleD2Command(ActionListener cmd) {jbToggleD2.addActionListener(cmd);}
	public void setToggleD3Command(ActionListener cmd) {jbToggleD3.addActionListener(cmd);}
	public void setToggleD4Command(ActionListener cmd) {jbToggleD4.addActionListener(cmd);}
	public void setToggleD5Command(ActionListener cmd) {jbToggleD5.addActionListener(cmd);}
	public void setToggleD6Command(ActionListener cmd) {jbToggleD6.addActionListener(cmd);}
	public void setToggleD7Command(ActionListener cmd) {jbToggleD7.addActionListener(cmd);}

	public void setPulseD2Command(ActionListener cmd) {jbPulseD2.addActionListener(cmd);}
	public void setPulseD3Command(ActionListener cmd) {jbPulseD3.addActionListener(cmd);}
	public void setPulseD4Command(ActionListener cmd) {jbPulseD4.addActionListener(cmd);}
	public void setPulseD5Command(ActionListener cmd) {jbPulseD5.addActionListener(cmd);}
	public void setPulseD6Command(ActionListener cmd) {jbPulseD6.addActionListener(cmd);}
	public void setPulseD7Command(ActionListener cmd) {jbPulseD7.addActionListener(cmd);}

	public void setDisplayDigital(boolean state, int pin)
	{
		JPanel marker;
		switch(pin)
		{
		case 2: marker = jpMarkerD2; break;
		case 3: marker = jpMarkerD3; break;
		case 4: marker = jpMarkerD4; break;
		case 5: marker = jpMarkerD5; break;
		case 6: marker = jpMarkerD6; break;
		case 7: marker = jpMarkerD7; break;
		default: return;
		}
		
		if (state) marker.setBackground( COLOR_HIGH );
		else marker.setBackground( COLOR_LOW );
	}
	
	public void setDisplayPulse(boolean state, int pin)
	{
		JButton marker;
		switch(pin)
		{
		case 2: marker = jbPulseD2; break;
		case 3: marker = jbPulseD3; break;
		case 4: marker = jbPulseD4; break;
		case 5: marker = jbPulseD5; break;
		case 6: marker = jbPulseD6; break;
		case 7: marker = jbPulseD7; break;
		default: return;
		}
		
		if (state) marker.setText("Stop");
		else marker.setText("Pulse");
	}
	
	public synchronized void setDisplayAnalog(long value, float val, int pin)
	{
		JTextField adc;
		switch(pin)
		{
		case 14: adc = jtfAnalogADC0; break;
		case 15: adc = jtfAnalogADC1; break;
		case 16: adc = jtfAnalogADC2; break;
		case 17: adc = jtfAnalogADC3; break;
		case 18: adc = jtfAnalogADC4; break;
		case 19: adc = jtfAnalogADC5; break;
		default: return;
		}
		
		JTextField v;
		switch(pin)
		{
		case 14: v = jtfAnalogV0; break;
		case 15: v = jtfAnalogV1; break;
		case 16: v = jtfAnalogV2; break;
		case 17: v = jtfAnalogV3; break;
		case 18: v = jtfAnalogV4; break;
		case 19: v = jtfAnalogV5; break;
		default: return;
		}
		
		adc.setText(""+value);
		v.setText( String.format("%.3f", val) );
	}
	
	public String getPulseWidth()
	{
		return jtfPulseWidth.getText();
	}
	
	public String getDutyCycle()
	{
		return jtfDutyCycle.getText();
	}
	
	public boolean getRepeatState()
	{
		return jcbRepeat.isSelected();
	}
	
	public String getReferenceVoltage() 
	{
		String t = jtfAREF.getText();
		if (t.length()==0) return "0";
		return t;
	}
}
