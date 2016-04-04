package decc_gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import decc_gui.convwin.Message;
import decc_gui.convwin.MessagesBox;
import decc_gui.convwin.TextEntry;

/**
 * Conversation window between 2 peers in DECC
 * @author nyradr
 *
 */
public class ConvWin extends JFrame{
	
	MessagesBox messbox;
	TextEntry textEntry;
	
	public ConvWin(){
		super();
		
		this.setSize(400,  500);
		
		buildUi();
	}
	
	private void buildUi(){
		
		this.getContentPane().setLayout(new BorderLayout());
		
		messbox = new MessagesBox();
		this.getContentPane().add(messbox, BorderLayout.CENTER);
		
		textEntry = new TextEntry();
		this.getContentPane().add(textEntry, BorderLayout.SOUTH);
		
		
		
	}
}
