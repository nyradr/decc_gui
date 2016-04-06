package decc_gui.mainwin.logstab;

import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Text area for events logs
 * @author nyradr
 *
 */
public class LogTextArea extends JTextArea{
	
	public LogTextArea(){
		super(5, 20);				// temporary size	
		this.setEditable(false);
	}
	
	/**
	 * Log text into the text area on a new line
	 * @param txt text to add
	 */
	public void log(String txt){
		if(this.getText().length() > 0)
			this.append("\n");
			
		this.append(txt);
	}
}
