import java.util.*;
import java.io.*;

public class Player implements Serializable {

	private int px = 0;
	private int py = 0;
	private int gold = 0;
	public static final int startingHealth = 100;
	private int health = startingHealth;
	int[][] level = new int[5][5];


	public Player(int posx, int posy, int g, int h, int[][] l){
		this.px = posx;
		this.py = posy;
		this.gold = g;
		this.health = h;
		this.level = l;
	}

	public int getX(){
		return px;
	}
	public void setX(int i){
		px = i;
	}

	public int getY(){
		return py;
	}

	public void setY(int i){
		py = i;
	}

	public int getGold(){
		return gold;
	}
	public void setGold(int i){
		gold = i;
	}

	public int getHealth(){
		return health;
	}

	public void setHealth(int i){
		health = i;
	}

	public int[][] getLevel(){
		return level;
	}

	public void setLevel(int[][] l){
		level = l;
	}
	// reads the input file containing the level.
	public void levelReader(String s) {
		try{
			Scanner sc = new Scanner(new File(s));
			//Puts read data into 2D array
			for(int i=0; i<5; i++){
				for(int j=0; j<5; j++){
					level[i][j] = sc.nextInt();
				}
			}
			sc.close();
		}catch(Exception e){
			System.out.println("Error while reading input file!");
		}
	}

}
