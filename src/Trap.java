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

public class Trap{
	
	public Trap(){
	}

	public void trap(Player p, JLabel info){
		Random ran = new Random();
		int damage = ran.nextInt(20 - 5 + 1) + 5;
		
		if((p.getHealth() - damage) < 0){
			p.setHealth(0);
			info.setText("Stepping on a trap dealt lethal damage! Game Over!");
		}else{
			p.setHealth(p.getHealth() - damage);
			info.setText("You stepped on a trap and received "+damage+" damage!");
		}
	}
}