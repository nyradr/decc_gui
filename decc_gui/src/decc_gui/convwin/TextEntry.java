package decc_gui.convwin;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

class TextEntry extends JPanel{
	
	JTextField entry;
	JButton send;
	
	ITextEntry clb;
	
	public TextEntry(ITextEntry clb){
		super();
		this.clb = clb;
		
		this.setLayout(new FlowLayout());
		
		entry = new JTextField(25);
		entry.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		entry.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				
				switch (e.getKeyCode()){
					case KeyEvent.VK_ENTER:
						onEntry();
						break;
						
					default:
						break;
				}
			}
		});
		
		this.add(entry);
		
		send = new JButton("Send");
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onEntry();
			}
		});
		this.add(send);
		
	}
	
	private void onEntry(){
		String txt = entry.getText();
		entry.setText("");
		
		clb.onEntry(txt);
	}
}
