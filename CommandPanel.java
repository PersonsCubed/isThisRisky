import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.io.IOException;

@SuppressWarnings("serial")
public class CommandPanel extends JPanel{

	private String gameOutput="Game output blablabla" ;
	private String userInput;

	public CommandPanel() throws IOException {
		
		Dimension size = getPreferredSize();
        size.width = 320;
        setPreferredSize(size);
        setBorder(BorderFactory.createLineBorder(Color.black, 20));


	    JLabel enterCommandLabel = new JLabel(" Enter command: ");
	    enterCommandLabel.setForeground(Color.WHITE);
	    enterCommandLabel.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
	    enterCommandLabel.setBackground(SystemColor.controlHighlight);
		
		JTextPane userInputTextPane = new JTextPane();
		userInput=userInputTextPane.getText();
		userInputTextPane.setPreferredSize( new Dimension( 200, 23 ) );
		
		JButton userInputButton = new JButton("send");
		userInputButton.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		
		userInputButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	userInput= userInputTextPane.getText();
            }
            
        });
		
		
		JLabel ouputLabel = new JLabel("Ouput: ");
		ouputLabel.setForeground(Color.WHITE);
		ouputLabel.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		ouputLabel.setBackground(SystemColor.controlHighlight);
		
		JLabel gameOutputLabel = new JLabel(gameOutput);
		gameOutputLabel.setForeground(Color.RED);
		gameOutputLabel.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		gameOutputLabel.setBackground(SystemColor.controlHighlight);
		
		setLayout(new GridBagLayout());        
        GridBagConstraints gc = new GridBagConstraints();
        
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.weightx = 0.5;
        gc.weighty = 0.5;
       
        gc.gridx = 1;
        gc.gridy = 0;
        add(enterCommandLabel, gc);
       
        gc.weightx = 0;
        gc.gridx = 1;
        gc.gridy = 1;
        add(userInputTextPane, gc);
        
        gc.gridx = 2;
        gc.gridy = 1;
        add(userInputButton, gc);
        
        gc.gridx = 1;
        gc.gridy = 2;
        add(ouputLabel, gc);
        
        gc.gridx = 1;
        gc.gridy = 3;
        gc.weighty = 40;
        add(gameOutputLabel, gc);		
	}

	public String get_gameOutput(){
		return gameOutput;
	}
	
	public void change_gameOutput(String s){
		s=gameOutput;
	}
	
	public String get_userInput(){
		return userInput;
	}
}
