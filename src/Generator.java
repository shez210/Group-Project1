

//imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Generates the map and all of its features
public class Generator extends JPanel implements ActionListener {
	
	public JProgressBar pbar;
	public Board board;
	public Player player;
	public Loot loot;
	public Trap trap;
	public Monster monster;
	public JPanel sidebar, control, fight, i;
	public JButton north, east, south, west, fightbutton, fleebutton;
	public JLabel currentgold, info;
	public int mhealth;

	public Menu menu;
	
	public Generator() {
		//player = new Player();
		board = new Board(player);
		loot = new Loot();
		trap = new Trap();
		menu = new Menu(player, board, pbar, currentgold);
		drawPanel();
		startPosition();
	}

	public void drawPanel() {
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		add(drawBoard(), BorderLayout.CENTER);
		add(sidebar(), BorderLayout.SOUTH);
	}

	public JPanel drawBoard(){
		JPanel b = new JPanel();
		b.setLayout(new BoxLayout(b, BoxLayout.Y_AXIS));
		b.setPreferredSize(new Dimension(326,326));
		b.add(board);
		return b;
	}

	public JPanel sidebar() {
		sidebar = new JPanel();
		fight = fightButtons();
		control = controlButtons();
		sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
		sidebar.add(information());
		sidebar.add(stats());
		sidebar.add(control);
		return sidebar;
	}

	public JPanel information() {
		i = new JPanel();
		info = new JLabel(" ");
		i.add(info);
		return i;
	}

	//creates the buttons and puts them into a panel
	public JPanel stats() {
		JPanel stats = new JPanel();
		stats.setLayout(new FlowLayout());
		JLabel health = new JLabel("Health ");
		pbar = new JProgressBar(0,100);
		pbar.setStringPainted(true);
		pbar.setForeground(Color.RED);
		pbar.setValue(player.getHealth());
		JLabel gold = new JLabel("Gold ");
		currentgold = new JLabel(""+player.getGold()+"");
		stats.add(health);stats.add(pbar);stats.add(gold);stats.add(currentgold);
		return stats;
	}

	public JPanel controlButtons() {
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());
		north = new JButton("UP");
		north.addActionListener(this);
		east = new JButton("RIGHT");
		east.addActionListener(this);
		south = new JButton("DOWN");
		south.addActionListener(this);
		west = new JButton("LEFT");
		west.addActionListener(this);
		p.add(north);p.add(south);p.add(west);p.add(east);
		return p;
	}

	public JPanel fightButtons() {
		JPanel f = new JPanel();
		f.setLayout(new FlowLayout());
		fightbutton = new JButton("FIGHT");
		fightbutton.addActionListener(this);
		fleebutton = new JButton("FLEE");
		fleebutton.addActionListener(this);
		f.add(fightbutton);f.add(fleebutton);
		return f;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==north){
			if(player.getY() != 0){
				int newcoordinate = player.getY() - 1;
				player.setY(newcoordinate);
				checkMonster();
				board.repaint();
			}
		}
		if(e.getSource()==east){
			if(player.getX() != 4){
				int newcoordinate = player.getX() + 1;
				player.setX(newcoordinate);
				checkMonster();
				board.repaint();
			}
		}
		if(e.getSource()==south){
			if(player.getY() != 4){
				int newcoordinate = player.getY() + 1;
				player.setY(newcoordinate);
				checkMonster();
				board.repaint();
			}
		}
		if(e.getSource()==west){
			if(player.getX() != 0){
				int newcoordinate = player.getX() - 1;
				player.setX(newcoordinate);
				checkMonster();
				board.repaint();
			}
		}
		if(e.getSource()==fleebutton){
				monster.flee(info, player);
				board.repaint();
		}
		if(e.getSource()==fightbutton){
				monster.fight(info, player, currentgold);
				board.repaint();
		}
		
		//empty
		if(player.level[player.getY()][player.getX()] == 0){
			updateControlButtons();
			updateHealth();
			currentgold.setText(""+player.getGold()+"");
		}
		//MONSTER
		if(player.level[player.getY()][player.getX()] == 1){
			if(player.getHealth() < 0){
					disableFight();
			}else{
				updateFightButtons();
			}
			updateHealth();
		}
		//LOOT
		if(player.level[player.getY()][player.getX()] == 2){
			updateControlButtons();
			loot.chanceOfTreasure(player, currentgold, info);
			updateHealth();
			player.level[player.getY()][player.getX()] = 0;
		}
		//TRAP
		if(player.level[player.getY()][player.getX()] == 3){
			updateControlButtons();
			trap.trap(player, info);
			if(player.getHealth() <= 0){
				disableControl();
			}
			updateHealth();
			//player.level[player.getY()][player.getX()] = 0;
		}
	}

	public void updateControlButtons(){
		sidebar.remove(fight);
		sidebar.add(control);
		sidebar.revalidate();
		sidebar.repaint();
	}
	public void updateFightButtons(){
		sidebar.remove(control);
		sidebar.add(fight);
		sidebar.revalidate();
		sidebar.repaint();
	}
	public void updateHealth(){
		pbar.setValue(player.getHealth());
	}
	public void checkMonster(){
		if(player.level[player.getY()][player.getX()] == 1){
			info.setText("You have encountered a monster!");
			monster = new Monster();
		}
	}

	public void disableFight(){
		fightbutton.setEnabled(false);
		fleebutton.setEnabled(false);
		sidebar.revalidate();
		sidebar.repaint();
	}

	public void disableControl(){
		north.setEnabled(false);
		east.setEnabled(false);
		south.setEnabled(false);
		west.setEnabled(false);
		sidebar.revalidate();
		sidebar.repaint();
	}

	public void startPosition(){
		//MONSTER
		if(player.level[player.getY()][player.getX()] == 0){
			currentgold.setText(""+player.getGold()+"");
			updateHealth();
		}
		if(player.level[player.getY()][player.getX()] == 1){
			checkMonster();
			if(player.getHealth() < 0){
					disableFight();
			}else{
				updateFightButtons();
			}
			updateHealth();
		}
		//LOOT
		if(player.level[player.getY()][player.getX()] == 2){
			updateControlButtons();
			loot.chanceOfTreasure(player, currentgold, info);
			updateHealth();
			player.level[player.getY()][player.getX()] = 0;
		}
		//TRAP
		if(player.level[player.getY()][player.getX()] == 3){
			updateControlButtons();
			trap.trap(player, info);
			if(player.getHealth() <= 0){
				disableControl();
			}
			updateHealth();
			//player.level[player.getY()][player.getX()] = 0;
		}
	}
}
