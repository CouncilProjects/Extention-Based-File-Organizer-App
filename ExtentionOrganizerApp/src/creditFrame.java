import java.awt.Desktop;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;

import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class creditFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public creditFrame() {
		JEditorPane label = new JEditorPane();
		//Set the type to HTML so it displays it correctly
		label.setContentType("text/html");
		//Awful HTML but whatever
		//ALL CREDITS ARE SHOWN AS INSTRUCTED PER THE ATTRIBUTION INSTRUCTIONS
		label.setText("<html> <b>This App was created in JAVA</b><br><u>List of icon creators</u>"
				+ "<br><a href=\"https://www.freepik.com/icons/jpeg\">Icon by iconixar</a>"
				+ "<br><a href=\"https://icons8.com/icon/XEnbmdky0kzu/cisco-packet-tracer\">Cisco Packet Tracer</a> icon by <a href=\"https://icons8.com\">Icons8</a>"
				+ "<br><a href=\"https://icons8.com/icon/D2Hi2VkJSi33/html-5\">HTML</a> icon by <a href=\"https://icons8.com\">Icons8</a>"
				+ "<br><a href=\"https://www.flaticon.com/free-icons/zip-format\" title=\"zip format icons\">Zip format icons created by Smashicons - Flaticon</a>"
				+ "<br><a href=\"https://www.flaticon.com/free-icons/powerpoint\" title=\"powerpoint icons\">Powerpoint icons created by Roman Káčerek - Flaticon</a>"
				+ "<br><a href=\"https://www.flaticon.com/free-icons/jpg\" title=\"jpg icons\">Jpg icons created by Smashicons - Flaticon</a>"
				+ "<br><a href=\"https://www.flaticon.com/free-icons/question-mark\" title=\"question mark icons\">Question mark icons created by Fathema Khanom - Flaticon</a>"
				+ "<br><a href=\"https://www.flaticon.com/free-icons/mp4\" title=\"mp4 icons\">Mp4 icons created by Freepik - Flaticon</a>"
				+ "<br><a href=\"https://www.flaticon.com/free-icons/document\" title=\"document icons\">Document icons created by Freepik - Flaticon</a>"
				+ "<br><a href=\"https://www.flaticon.com/free-icons/office365\" title=\"office365 icons\">Office365 icons created by Tinti Nodarse - Flaticon</a>"
				+ "<br> <a href=\"https://www.flaticon.com/free-icons/java\" title=\"java icons\">Java icons created by Freepik - Flaticon</a>"
				+ "<br> <a href=\"https://www.flaticon.com/free-icons/pdf\" title=\"pdf icons\">Pdf icons created by Smashicons - Flaticon</a>"
				+ "<br> <a href=\"https://www.flaticon.com/free-icons/txt-file\" title=\"txt file icons\">Txt file icons created by Smashicons - Flaticon</a>"
				+ "<br> <a href=\"https://www.flaticon.com/free-icons/png\" title=\"png icons\">Png icons created by Freepik - Flaticon</a>"
				+ "<br> <a href=\"https://www.flaticon.com/free-icons/mp3\" title=\"mp3 icons\">Mp3 icons created by Freepik - Flaticon</a>"
				+ "<br> <a href=\"https://www.flaticon.com/free-icons/chip\" title=\"chip icons\">Chip icons created by Freepik - Flaticon</a>"
				+ "<br> <a href=\"https://www.flaticon.com/free-icons/webp\" title=\"webp icons\">Webp icons created by juicy_fish - Flaticon</a>"
				+ "<br> <a href=\"https://www.flaticon.com/free-icons/computer\" title=\"computer icons\">Computer icons created by Freepik - Flaticon</a>"
				+ "<br> <a href=\"https://www.flaticon.com/free-icons/gif-file\" title=\"gif file icons\">Gif file icons created by surang - Flaticon</a>"
				+ "<br> <a href=\"https://www.flaticon.com/free-icons/files-and-folders\" title=\"files and folders icons\">Files and folders icons created by Shahryar MInhas - Flaticon</a>"
				+ "<br> <a href=\"https://www.flaticon.com/free-icons/json-file\" title=\"json file icons\">Json file icons created by Smashicons - Flaticon</a>"
				+ "<br> <a href=\"https://www.flaticon.com/free-icons/mkv\" title=\"mkv icons\">Mkv icons created by prettycons - Flaticon</a>"
				+ "<br> <a href=\"https://www.flaticon.com/free-icons/letter-c\" title=\"letter c icons\">Letter c icons created by riajulislam - Flaticon</a>"
				+ "<br> <a href=\"https://www.flaticon.com/free-icons/python\" title=\"python icons\">Python icons created by Freepik - Flaticon</a>"
				+ "</html>");
		label.setEditable(false);
		
		//Make links clickable
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
		
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setTitle("Credits");
		this.add(label);
		this.setSize(500, 600);
		this.setResizable(false);
		this.setVisible(true);
	}
}
