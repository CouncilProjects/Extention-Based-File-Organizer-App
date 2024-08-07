import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class helpFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public helpFrame() {
		getContentPane().setLayout(new GridLayout(2, 1));
		JEditorPane pane = new JEditorPane();
		pane.setContentType("text/html");
		pane.setText("<html>" + "This app allows you to organize your files based on their extensions.<br>"
				+ "First, choose a directory by pressing the <span style=\"color: green;\">Choose Directory</span> button.<br>"
				+ "After you select a directory, a <span style=\"color: blue;\">list</span> with all the types of extensions present in the directory will be shown.<br>"
				+ "After you select an extension, you will be prompted to give a name for the new directory.<br>"
				+ "The app then makes a new directory inside the chosen directory and will move all the files of the selected extension there.<br>"
				+ "The <span style=\"color: orange;\">Allow Append</span> button when active places the files in the given directory"
				+ " even if it already exists.<br>When off it will fail to move them if a directory with that name already exists"
				+ "</html>");
		pane.setEditable(false);


		pane.setVisible(true);
		getContentPane().add(pane);

		JPanel dicContain = new JPanel();
		dicContain.setLayout(null);
		JLabel disclamer = new JLabel("Disclaimer");
		disclamer.setHorizontalAlignment(SwingConstants.CENTER);
		disclamer.setBackground(Color.GRAY);
		disclamer.setBorder(BorderFactory.createLineBorder(Color.orange, 2));
		disclamer.setBounds(10, 100, 70, 40);
		disclamer.setToolTipText(
				"<html>The software is provided \"as is\", without warranty of any kind, express or implied, <br>including but not limited to the warranties of merchantability, fitness for a particular purpose and noninfringement. <br>In no event shall the authors or copyright holders be liable for any claim, damages or other liability, <br>whether in an action of contract, tort or otherwise, arising from, out of or in connection with the software or the use or other dealings in the software.</html>");
		dicContain.add(disclamer);
		dicContain.setBackground(Color.WHITE);
		dicContain.setVisible(true);
		
		this.add(dicContain);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setTitle("Use");

		this.setSize(800, 500);
		this.setResizable(false);
		this.setVisible(true);
	}
}
