import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class InfoPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private static final int TEXT_AREA_HEIGHT = 10;
	private static final int CHARACTER_WIDTH = 34;
	private JTextPane userInputTextPane = new JTextPane();

	JTextArea textArea = new JTextArea(TEXT_AREA_HEIGHT, CHARACTER_WIDTH);
	JScrollPane scrollPane = new JScrollPane(textArea);
	DefaultCaret caret = (DefaultCaret)textArea.getCaret();
	
	InfoPanel () {
		userInputTextPane.setPreferredSize(new Dimension(200, 23));
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		textArea.setBorder(BorderFactory.createLoweredBevelBorder());
		textArea.setEditable(false); // display only
		textArea.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		textArea.setForeground(Color.red);
		textArea.setBackground(Color.black);

		JScrollPane scrollingTextArea = new JScrollPane(textArea);
		scrollingTextArea.setBorder(BorderFactory.createLoweredBevelBorder());
		scrollingTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		/////uses GidBagLayout to set the layout of the main JPanel//////

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		gc.gridx = 0;
		gc.gridy = 3;
		gc.weighty = 50;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.fill = GridBagConstraints.BOTH;
		add(scrollingTextArea, gc);
		return;
	}
	
	public void addText (String text) {
		textArea.setText(textArea.getText()+"\n"+text);
	}

}
