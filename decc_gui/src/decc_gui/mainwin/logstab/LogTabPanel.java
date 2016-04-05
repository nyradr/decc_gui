package decc_gui.mainwin.logstab;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * JPanel for the log tab
 * @author nyradr
 */
public class LogTabPanel extends JPanel{
	
	private LogTextArea lta;
	
	public LogTabPanel(){
		super();
		this.setLayout(new BorderLayout());
		
		lta = new LogTextArea();
		this.add(lta, BorderLayout.CENTER);
		
	}
}
