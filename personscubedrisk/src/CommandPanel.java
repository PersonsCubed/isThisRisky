import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;

import javax.swing.*;

import java.io.IOException;

@SuppressWarnings("serial")
public class CommandPanel extends JPanel{

	private String gameOutput="Game output blablabla" ;
	private String userInput;
	static JTextArea textArea = new JTextArea();
	static String textDisplayed;
	
	public CommandPanel() throws IOException{
		
		Dimension size = getPreferredSize();
        size.width = 320;
        setPreferredSize(size);
        setBorder(BorderFactory.createLineBorder(Color.black, 20));

	    JLabel enterCommandLabel = new JLabel("Enter command: ");
	    enterCommandLabel.setForeground(Color.WHITE);
	    enterCommandLabel.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
	    enterCommandLabel.setBackground(SystemColor.controlHighlight);
		
		JTextPane userInputTextPane = new JTextPane();
		userInputTextPane.setPreferredSize(new Dimension(2000, 23));
		
		JButton userInputButton = new JButton("send");
		userInputButton.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		
		JLabel ouputLabel = new JLabel("Ouput: ");
		ouputLabel.setForeground(Color.WHITE);
		ouputLabel.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		ouputLabel.setBackground(SystemColor.controlHighlight);
		
		JLabel gameOutputLabel = new JLabel(gameOutput);
		gameOutputLabel.setForeground(Color.RED);
		gameOutputLabel.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		gameOutputLabel.setBackground(SystemColor.controlHighlight);
		
		textArea.setEditable(false); // display only
		textArea.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		JScrollPane scrollingTextArea = new JScrollPane(textArea);
		System.out.println("making panels");
		scrollingTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollingTextArea.setBackground(SystemColor.controlHighlight);
		scrollingTextArea.setForeground(Color.BLACK);
		
		userInputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	gameOutput = userInputTextPane.getText();
            	userInputTextPane.setText("");
            	gameOutputLabel.setText(gameOutput);	
            }
            
        });
		
		setLayout(new GridBagLayout());        
        GridBagConstraints gc = new GridBagConstraints();
        
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.weightx = 0.5;
        gc.weighty = 0.5;
        
        gc.gridx = 0;
        gc.gridy = 0;
        add(enterCommandLabel, gc);
       
        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(userInputTextPane, gc);
        
        gc.gridx = 1;
        gc.gridy = 0;
        add(userInputButton, gc);
        
        gc.gridx = 0;
        gc.gridy = 2;
        add(ouputLabel, gc);
        
        gc.gridx = 0;
        gc.gridy = 3;
        gc.weighty =50;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.fill = GridBagConstraints.BOTH;


        add(scrollingTextArea, gc);
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