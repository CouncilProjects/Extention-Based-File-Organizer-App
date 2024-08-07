import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
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
		JEditorPane label = new JEditorPane();
		label.setContentType("text/html");
		label.setText("<html>" + "This app allows you to organize your files based on their extensions.<br>"
				+ "First, choose a directory by pressing the <span style=\"color: green;\">Choose Directory</span> button.<br>"
				+ "After you select a directory, a <span style=\"color: blue;\">list</span> with all the types of extensions present in the directory will be shown.<br>"
				+ "After you select an extension, you will be prompted to give a name for the new directory.<br>"
				+ "The app then makes a new directory inside the chosen directory and will move all the files of the selected extension there."
				+ "<br><br> <span style=\"color: red;\">Warning</span><br>"
				+ "This app does not check for already existing directories before creating new ones." + "</html>");
		label.setEditable(false);

		label.addHyperlinkListener(new HyperlinkListener() {

			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					try {
						Desktop.getDesktop().browse(e.getURL().toURI());
					} catch (IOException | java.net.URISyntaxException ex) {

					}
				}

			}
		});

		label.setVisible(true);
		getContentPane().add(label);

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
		getContentPane().add(dicContain);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setTitle("Use");

		this.setSize(500, 500);
		this.setVisible(true);
	}
}
