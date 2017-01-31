package game;

/**
 * Created by faisals1 on 25/01/2017.
 */
import java.util.*;
import java.io.*;

public class PlayerInformation {
	private int px = 0;
	private int py = 0;
	private int gold = 0;
	public static final int startingHealth = 100;
	private int health = startingHealth;
	int[][] level = new int[5][5];

	public PlayerInformation(int posx, int posy, int g, int h, int[][] l) {
		this.px = posx;
		this.py = posy;
		this.gold = g;
		this.health = h;
		this.level = l;
	}

	public int getX() {
		return px;
	}

	public void setX(int i) {
		px = i;
	}

	public int getY() {
		return py;
	}

	public void setY(int i) {
		py = i;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int i) {
		gold = i;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int i) {
		health = i;
	}

	public int[][] getLevel() {
		return level;
	}
}
