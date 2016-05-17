package decc_gui.convwin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import decc.DeccInstance;
import decc.ICom;

/**
 * Conversation window between 2 peers in DECC
 * @author nyradr
 *
 */
public class ConvWin extends JFrame implements ITextEntry{
	
	private DeccInstance decc;
	private ICom conv;
	
	private MessagesBox messbox;
	private TextEntry textEntry;
	
	public ConvWin(DeccInstance decc, ICom conv){
		super();
		
		this.decc = decc;
		this.conv = conv;
		
		this.setSize(400,  500);
		
		buildUi();
	}
	
	private void buildUi(){
		
		this.setTitle(conv.getTargetName());
		this.getContentPane().setLayout(new BorderLayout());
		
		messbox = new MessagesBox();
		this.getContentPane().add(messbox, BorderLayout.CENTER);
		
		textEntry = new TextEntry(this);
		this.getContentPane().add(textEntry, BorderLayout.SOUTH);
	}

	@Override
	public void onEntry(String txt) {
		if(txt.length() > 0){
			decc.send(conv.getComid(), txt);
			messbox.addMessage(txt, MessagesBox.SIDE_LEFT);
		}
	}
	
	/**
	 * Display received message
	 * @param mess message to display
	 */
	public void receiveMessage(String mess){
		if(mess.length() > 0)
			messbox.addMessage(mess, MessagesBox.SIDE_RIGHT);
	}
	
	/**
	 * Get the comid of the conversation
	 * @return
	 */
	public String getComid(){
		return conv.getComid();
	}
}
