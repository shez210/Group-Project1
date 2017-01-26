import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;

public class Menu{
	public JMenuBar menuBar;
	public JMenu menu;
	public JMenuItem save, load;
	public Player player;
	public Board board;
	public JProgressBar health;
	public JLabel currentgold;

	public Menu(Player p, Board b, JProgressBar j, JLabel cg){
		player = p;
		board = b;
		health = j;
		currentgold = cg;
	}
	
	public JMenuBar menuBar(){

			JMenuBar menuBar = new JMenuBar();
			JMenu file = new JMenu("File");
			file.setMnemonic(KeyEvent.VK_A);
			JMenu settings = new JMenu("Settings");
			settings.setMnemonic(KeyEvent.VK_A);
			menuBar.add(file);
			
			JMenuItem save = new JMenuItem("Save", new ImageIcon("save.png"));
			save.setMnemonic(KeyEvent.VK_D);
			save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	try{
            	File outFile = new File("save.data");
            	FileOutputStream outFileStream = new FileOutputStream(outFile);
            	ObjectOutputStream outObjectStream = new ObjectOutputStream(outFileStream);
            	Player player2 = new Player(player.getX(), player.getY(), player.getGold(), player.getHealth(), player.getLevel());
				System.out.println("X "+player2.getX());
				System.out.println("Y "+player2.getY());
            	System.out.println("Gold "+player2.getGold());
            	System.out.println("Health "+player2.getHealth());
            	outObjectStream.writeObject(player2);
            	//info.setText("Game has been saved successfully!");
    			outObjectStream.close();
            }catch(Exception e){
            	System.out.println("Error saving");
            }
            }
       		});

	        JMenuItem load = new JMenuItem("Load", new ImageIcon("load.png"));
	        load.setMnemonic(KeyEvent.VK_D);
	        load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	try{
            	File inFile = new File("save.data");
            	FileInputStream inFileStream = new FileInputStream(inFile);
            	ObjectInputStream inObjectStream = new ObjectInputStream(inFileStream);

            	Player player2 = (Player) inObjectStream.readObject();
         
            	player.setX(player2.getX());
            	player.setY(player2.getY());
            	player.setHealth(player2.getHealth());
            	player.setGold(player2.getGold());
            	player.setLevel(player2.getLevel());
            	inObjectStream.close();
            	//info.setText("Game loaded successfully");
            	//health.setValue(player.getHealth());
            	//currentgold.setText(""+player.getGold()+"");
            	board.repaint();
            }catch(Exception e){
            	System.out.println("Error loading");
            }
            }
       		});

			JMenuItem tutorial = new JMenuItem("Tutorial", new ImageIcon("tutorial.png"));
			tutorial.setMnemonic(KeyEvent.VK_D);
			tutorial.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					Main n = new Main();
				}
			}
			);

	        JMenuItem exit = new JMenuItem("Exit");
	        exit.setMnemonic(KeyEvent.VK_D);
	        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	System.exit(0);
            }
       		});
	        file.add(save);
	        file.add(load);
	        file.addSeparator();
	        file.add(exit);
			file.add(tutorial);
	        return menuBar;
		}


}