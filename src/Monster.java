import javax.swing.*;
import java.util.Random;

public class Monster{

	public int mhealth = 100;
	public Monster(){
	}

	public int generateMonsterDmg(){
		Random ran = new Random();
		int mdamage = ran.nextInt(10 - 1 + 1) + 1;
		return mdamage;
	}

	public int generatePlayerDmg(){
		Random ran = new Random();
		int mdamage = ran.nextInt(30 - 10 + 1) + 10;
		return mdamage;
	}

	public int generateGold(){
		Random ran = new Random();
		int gold = ran.nextInt(20 - 10 + 1) + 10;
		return gold;
	}

	public int generateFlee(){
		Random ran = new Random();
		int flee = ran.nextInt(2) + 1;
		return flee;
	}

	public int generateWhere(){
		Random ran = new Random();
		int where = ran.nextInt(4) + 1;
		return where;
	}

	public void fight(JLabel info, Player p, JLabel cgold){
		int mdmg = generateMonsterDmg();
		int pdmg = generatePlayerDmg();
		info.setText("You have dealt "+pdmg+" and received "+mdmg+" damage back!");
		p.setHealth(p.getHealth() - mdmg);
		mhealth = mhealth - pdmg;
		
		if(p.getHealth() < 0){
			info.setText("You have died!");
		}else if(mhealth < 0){
			int gold = generateGold();
			p.setGold(p.getGold() + gold);
			cgold.setText(""+p.getGold()+"");
			info.setText("You have slain the monster and found "+gold+" gold!");
			p.level[p.getY()][p.getX()] = 0;
		}
	}

	public void flee(JLabel info, Player p){
		int chanceOfFleeing = generateFlee();
		int chanceOfWhere = generateWhere();
		System.out.println(chanceOfWhere);
		int monsterDmg = generateMonsterDmg();
		System.out.println("Chance of Fleeing "+chanceOfFleeing);			
		//fleeing unsuccessful
		if (chanceOfFleeing == 1) {
			p.setHealth(p.getHealth() - monsterDmg);
			info.setText("Monster caught you dealing "+monsterDmg+" damage!");
			if (p.getHealth() < 0) {
				info.setText("Monster caught you dealing "+monsterDmg+" damage. It was lethal, you're dead!");
			}
		}
		else{
			//fleeing north
			if (chanceOfWhere == 1) {
				if (p.getY() != 0) {
					p.setY(p.getY() - 1);
				}else{
					p.setY(p.getY() + 1);
				}
			}
			//fleeing south
			if (chanceOfWhere == 2) {
				if (p.getY() != 4) {
					p.setY(p.getY() + 1);	
				} else {
					p.setY(p.getY() - 1);
				}
			}
			//fleeing west
			if (chanceOfWhere == 3) {
				if (p.getX() != 0) {
					p.setX(p.getX() - 1);
				}else {
				p.setX(p.getX() + 1);		
				}
			}
			//fleeing east
			if (chanceOfWhere == 4) {
				if (p.getX() != 4) {	
					p.setX(p.getX() + 1);
				}else {
				p.setX(p.getX() - 1);
				}
			}
		}
	}
}