/* 
 * Copyright (C) 2014, Sylvester Saracevas
 * 
 */
import java.util.Random;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Loot{
	
	public Loot(){
	}

	public int giveGold(){
		Random random = new Random();
		return random.nextInt(30 - 10 + 1) + 10;
	}
	
	public int giveHealth(){
		Random random = new Random();
		return random.nextInt(20 - 5 + 1) + 5;
	}

	public void chanceOfTreasure(Player p, JLabel g, JLabel info){
		Random ran = new Random();
		int n = ran.nextInt(2);
		int gold = giveGold();
		int health = giveHealth();
		//System.out.println(n);
		if(n == 1){
			p.setGold(p.getGold() + gold);
			g.setText(""+p.getGold()+"");
			info.setText("You have found a bag with "+gold+" gold!");
		}else{
			int test = (p.getHealth() + health);
			if(test >= 100){
				p.setHealth(100);
				info.setText("You have found an HP Potion, your health is full!");
			}else{
				p.setHealth(p.getHealth() + health);
				info.setText("You have found an HP Potion, it restored "+health+" health!");
			}
		}
	}
}
