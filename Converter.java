import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
public class Converter extends JFrame{
	JFrame f;
	JMenuBar menu;
	JPanel panel;
	TextArea textarea;
	
	public Converter(){
		gui();
	}
	public void gui(){
		f=new JFrame();
		panel=new JPanel();
		menu=new JMenuBar();
		textarea=new TextArea(42,190);
		
		
		JMenu file=new JMenu("File");
		menu.add(file);
		JMenuItem newfile=new JMenuItem("New");
		file.add(newfile);
		
		newfile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				new Converter();
			}
		});
		JMenuItem open=new JMenuItem("Open");
		file.add(open);
		open.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				JFileChooser chooser=new JFileChooser();
				int resValue=chooser.showOpenDialog(null);
				if(resValue==JFileChooser.APPROVE_OPTION){
					try{
					File f=chooser.getSelectedFile();
					FileInputStream fin= new FileInputStream(f);
					int i=0;
					StringBuilder str=new StringBuilder("");
					while((i=fin.read())!=-1){
						str=str.append(Character.toString((char)i));
						}
						String s=str.toString();
						textarea.setText(s);
					}
					catch(Exception e){
						System.out.println(e);
					}
				}
				else if(resValue==JFileChooser.CANCEL_OPTION){
						chooser.cancelSelection();
				}
			}
		});
		
		JMenuItem save=new JMenuItem("Save");
		file.add(save);
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
			JFileChooser chooser=new JFileChooser();
			int resValue=chooser.showSaveDialog(Converter.this);
			if(resValue==JFileChooser.APPROVE_OPTION){
				File fileToSave = chooser.getSelectedFile();
				try{
			 String name=chooser.getName(fileToSave)+".txt"; 
			 String f=fileToSave.getAbsolutePath();
			 File file=new File(f,name);
			 File actualFile=new File(f);
			 actualFile.createNewFile();
			 FileOutputStream fout=new FileOutputStream(actualFile);
			 String data=textarea.getText();
			 byte []b=data.getBytes();
			 fout.write(b);
			 fout.close();
					}
					catch(Exception e){
					
					}
				}
				
			}
		});
		JMenuItem exit=new JMenuItem("Exit");
		file.add(exit);
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				System.exit(0);
			}
		});
		
		JMenu hello=new JMenu("Help");
		menu.add(hello);
		JMenuItem about=new JMenuItem("About");
		hello.add(about);
		about.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				textarea.setText("Made by Anurag Sharma");
			}
		});
		
		
		
		panel.setSize(800,800);
		panel.add(textarea);
		f.setJMenuBar(menu);
		f.add(panel);
		f.setSize(1366,728);
		f.setVisible(true);
		f.setLayout(null);
		f.setDefaultCloseOperation(f.DISPOSE_ON_CLOSE);
	}

public static void main(String []args){
new Converter();
	}
}
