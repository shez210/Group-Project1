
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Board extends Canvas {

	public BufferedImage sprite = null;
	public Player player;
	private int blocksize = 65;
	private int playersize = 50;
	private int arc = 30;
	private static final Color BLOCKCOLOR = Color.BLACK;
	private static final Color BLOCKCOLOR2 = Color.WHITE;
	private static final Color PLAYERCOLOR = Color.BLACK;
	
	public Board(Player p){
		player = p;
		loadImages();
	}

	private void loadImages() {
		try {
		    sprite = ImageIO.read(new File("char.png"));
		} catch (IOException e) {
		}
	}
	
	private void drawBoard(Graphics2D g2d){
		int row=0;
		int column=0;
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				g2d.setColor(BLOCKCOLOR);
				g2d.drawRoundRect(blocksize*column,blocksize*row,blocksize,blocksize,arc,arc);
				if(column == 4){
					column=0;
				}else{
					column++;
				}
			}
			row++;
		}
	}

	private void drawPlayer(Graphics2D g2d){
		g2d.drawImage(sprite, (blocksize*player.getX())+(blocksize/8), (blocksize*player.getY())+(blocksize/8), playersize, playersize,null);
	}


	public void paint(Graphics g){	
		//super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		drawBoard(g2d);
		drawPlayer(g2d);

		Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
	}		
}
