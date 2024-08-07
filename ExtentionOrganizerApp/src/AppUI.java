import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


import javax.swing.Timer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AppUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Controller control;
	JTextArea log;
	JScrollPane scroller;
	JPanel panel1,panel2;
	JButton filechoice;
	String path;
	ButtonListener buttEvent;
	JMenuBar menu;
    JMenu helpMenu;
	public AppUI(Controller c)
	{
		this.control=c;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(1,2));
		this.setSize(500,500);
		
		
		
		buttEvent=new ButtonListener();
		
		panel1=new JPanel();
		panel2=new JPanel();
		
		menu=new JMenuBar();
		
	    helpMenu = new JMenu("Help");
	    
	    JMenuItem creditWindow=new JMenuItem("Show credits");
	    creditWindow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new creditFrame();
			}
		});
	    
	    JMenuItem useWindow=new JMenuItem("How to use");
	    useWindow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new helpFrame();
				
			}
		});
	    
	    helpMenu.add(useWindow);
	    helpMenu.add(creditWindow);
	    
	    menu.add(helpMenu);
	    
	    this.setJMenuBar(menu);
	    
		//panel1 will have the log and the filechoocer. 
		panel1.setLayout(new GridLayout(2,1));
		log=new JTextArea();
		log.setEditable(false);
		log.setLineWrap(true);
		log.setWrapStyleWord(true);
		log.setVisible(true);
		panel1.add(log);
		panel1.setVisible(true);
		
		filechoice=new JButton("Select Directory");
		filechoice.setBorder(BorderFactory.createLineBorder(Color.green,2));
		filechoice.setVisible(true);
		filechoice.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Color og=filechoice.getBackground();
				Timer time=new Timer(250,new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						filechoice.setBackground(og);
						
					}
				});
				filechoice.setBackground(Color.green);
				time.start();
				time.setRepeats(false);
				JFileChooser fc=new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int ret=fc.showOpenDialog(AppUI.this);
				if(ret==JFileChooser.APPROVE_OPTION)
				{
					path=fc.getSelectedFile().getPath();
					updateLog(path);
					createButtons(control.listExtentions(path));
				}
				
			}
		});
		panel1.add(filechoice);
		
		
		panel2=new JPanel();
		panel2.setBorder(BorderFactory.createLineBorder(Color.blue,2));
		panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
		scroller=new JScrollPane(panel2,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setVisible(true);
		this.add(panel1);
		this.add(scroller);
		this.setTitle("File Organizer");
		this.repaint();
		this.setVisible(true);
	}
	
	
	private void createButtons(String items)
	{
		panel2.removeAll();
		String itemsSeparate[]=items.split(";");
		for(String ext:itemsSeparate)
		{
			JButton b;
			ImageIcon icon;
			try
			{
				icon=new ImageIcon(getClass().getResource(ext+".png"));
			}
			catch (NullPointerException e) {
				icon=new ImageIcon(getClass().getResource("unknown.png"));
			}
			
			b=new JButton(ext,icon);
			b.setVisible(true);
			b.addActionListener(buttEvent);
			panel2.add(b);
		}
		panel2.revalidate();
		panel2.repaint();
		
	}
	
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String ext=((JButton) e.getSource()).getText();
			String name=JOptionPane.showInputDialog("Give name of new directory");
			if(name==null)
			{
				return;
			}
			
			panel2.remove((JButton) e.getSource());
			panel2.revalidate();
			panel2.repaint();
			control.moveToCreate(path, name, ext);
			updateLog(path,ext,name);
		}
       
    }
	
	private void updateLog(String s)
	{
		log.append("[Entry "+LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a")).toString()+"] Selected Directory "+s.substring(s.lastIndexOf("\\")+1)+'\n');
	}
	
	private void updateLog(String s,String ex,String name)
	{
		log.append("[Entry "+LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a")).toString()+"] Selected extentions ."+ex+" of folder "+s.substring(s.lastIndexOf("\\")+1)
				+" and moved to directory "+name+'\n');
	}
	 
}
