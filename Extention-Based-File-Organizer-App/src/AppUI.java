import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.Timer;

public class AppUI extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Controller control;
	JTextArea log;
	JScrollPane scrollerButton,scrollerLog;
	JPanel panel1, buttonList, mainButtons;
	JButton filechoice;
	String path;
	ButtonListener buttEvent;
	JMenuBar menu;
	JMenu helpMenu;
	JToggleButton appendB;
	JLabel label;

	public AppUI(Controller c) 
	{
		this.control = c;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(1, 2));
		this.setSize(500, 500);

		buttEvent = new ButtonListener();

		buttonList = new JPanel();

		menu = new JMenuBar();

		helpMenu = new JMenu("Help");

		JMenuItem creditWindow = new JMenuItem("Show credits");
		creditWindow.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				new creditFrame();
			}
		});

		JMenuItem useWindow = new JMenuItem("How to use");
		useWindow.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				new helpFrame();

			}
		});

		helpMenu.add(useWindow);
		helpMenu.add(creditWindow);

		menu.add(helpMenu);

		this.setJMenuBar(menu);

		// panel1 will have the log and the directory Selection button.
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2, 1));

		log = new JTextArea();
		log.setEditable(false);
		log.setLineWrap(true);
		log.setWrapStyleWord(true);
		log.setVisible(true);
		scrollerLog = new JScrollPane(log,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel1.add(scrollerLog);

		mainButtons = new JPanel();
		panel1.add(mainButtons);
		mainButtons.setLayout(new BorderLayout(0, 0));

		filechoice = new JButton("Select Directory");
		filechoice.setBorder(BorderFactory.createLineBorder(Color.green, 2));
		filechoice.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{

				Color og = filechoice.getBackground();
				Timer time = new Timer(250, new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						filechoice.setBackground(og);

					}
				});
				filechoice.setBackground(Color.green);
				time.start();
				time.setRepeats(false);
				JFileChooser fc = new JFileChooser();
				// Show the user only directories
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				// Make the selection window appear in the middle
				int ret = fc.showOpenDialog(null);
				if (ret == JFileChooser.APPROVE_OPTION) 
				{
					path = fc.getSelectedFile().getPath();
					updateLog(path);
					createButtons(control.listExtentions(path));
				}

			}
		});
		filechoice.setVisible(true);
		mainButtons.add(filechoice, BorderLayout.CENTER);

		appendB = new JToggleButton("Allow Appends");
		appendB.setBorder(BorderFactory.createLineBorder(Color.orange,2));
		appendB.setSelected(false);
		appendB.setForeground(Color.red);
		appendB.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (appendB.isSelected()) 
				{
					appendB.setForeground(Color.green);
				} 
				else 
				{
					appendB.setForeground(Color.red);
				}

			}
		});
		mainButtons.add(appendB, BorderLayout.WEST);

		label = new JLabel("<HTML>Read How To Use<br>If using the App for the first time</HTML>");
		
		mainButtons.add(label, BorderLayout.NORTH);

		panel1.setVisible(true);

		// Panel 2 will have the list of buttons
		buttonList = new JPanel();
		buttonList.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
		buttonList.setLayout(new BoxLayout(buttonList, BoxLayout.Y_AXIS));
		// the scroller will put buttonList (Button list) in a scrollable view.
		scrollerButton = new JScrollPane(buttonList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollerButton.setVisible(true);

		getContentPane().add(panel1);

		getContentPane().add(scrollerButton);
		this.setTitle("File Organizer");
		this.repaint();
		this.setVisible(true);
	}

	private void createButtons(Set<String> extensions) 
	{
		buttonList.removeAll();
		if (extensions == null) // control failed while reading the directory.
		{
			updateLogFail1();
			return;
		}

		for (String ext : extensions) {
			JButton b;
			ImageIcon icon;
			URL iconURL = getClass().getResource(ext + ".png");
			if (iconURL != null) // if Resource URL is found then the extension does have an icon.
			{
				icon = new ImageIcon(iconURL);
			} 
			else 
			{
				try 
				{
					icon = new ImageIcon(getClass().getResource("unknown.png")); // If it does not have an icon put the general one.
																					
				} 
				catch (NullPointerException e) 
				{
					icon = null;
				}

			}
			// In the event of the icon being null it still will not create problems
			b = new JButton(ext, icon);
			b.setVisible(true);
			b.addActionListener(buttEvent);
			buttonList.add(b);
		}
		buttonList.revalidate();
		buttonList.repaint();

	}

	class ButtonListener implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String ext = ((JButton) e.getSource()).getText();
			String name = JOptionPane.showInputDialog("Give name of new directory");
			if (name == null)// if name==null then user selected cancel
			{
				return;
			}

			// Remove button from list only if it is a success
			if (control.moveToCreate(path, name, ext, appendB.isSelected()) == 0) 
			{
				buttonList.remove((JButton) e.getSource());
				buttonList.revalidate();
				buttonList.repaint();
				updateLog(path, ext, name);
			}
			else 
			{
				updateLogFail2();
			}

		}

	}

	private void updateLogFail1() 
	{
		log.append("[Entry " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a")).toString()
				+ "] An error was encountered while reading directory contents");
	}

	private void updateLogFail2() 
	{
		log.append("[Entry " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a")).toString()
				+ "] An error was encountered while making new directory.Check for duplicate name or allow appends");
	}

	private void updateLog(String s) 
	{
		log.append("[Entry " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a")).toString()
				+ "] Selected Directory " + s.substring(s.lastIndexOf("\\") + 1) + '\n');
	}

	private void updateLog(String s, String ex, String name) 
	{
		log.append("[Entry " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a")).toString()
				+ "] Selected extentions ." + ex + " of directory " + s.substring(s.lastIndexOf("\\") + 1)
				+ " and moved to directory " + name + '\n');
	}

}
