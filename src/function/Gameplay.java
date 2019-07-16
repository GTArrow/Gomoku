package function;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
//import java.util.HashMap;
//import java.util.ArrayList;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener, ActionListener, MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int b = 1;
	private final int w = 2;
	
	private ImageIcon black;
	private ImageIcon white;
	private ImageIcon blackW;
	private ImageIcon whiteW;
	
//	HashMap<Integer, ArrayList<Integer>> hmap = new HashMap<Integer, ArrayList<Integer>>();
//	private ArrayList<Integer> xmouse = new ArrayList<Integer>();
//	private ArrayList<Integer> ymouse = new ArrayList<Integer>();
	private int [][] mouse = new int[22][17];
	
	private int playerCounter = 1;
	private int realx;
	private int realy;
	
	private boolean player1 = false;
	private boolean player2 = false;
	private boolean whiteWin = false;
	private boolean blackWin = false;
	
	public Gameplay() throws IOException{
		addMouseListener(this);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}
	
	public void paint(Graphics g)  {
		setBackground(Color.white);
		//draw the game border
		g.setColor(Color.black);
		g.drawRect(40, 40, 840 , 640);
		//draw the lines
		for (int i =2; i*40<880;i++) {
			g.drawLine(i*40, 40, i*40, 680);
		}
		for (int j =2; j*40<680;j++) {
			g.drawLine(40, j*40, 880, j*40);
		}
	}
	
	
	//draw pieces
	public void drawPieces(int x, int y) {
		Graphics g = getGraphics();
		if (player1) {
			black = new ImageIcon("res/black.png");
			black.paintIcon(this, g, x, y);	
		}
		if (player2) {
			white = new ImageIcon("res/white.png");
			white.paintIcon(this, g, x, y);	
		}
		
	}
	
	//draw winning window
	public void drawWin() {
		Graphics g = getGraphics();
		if (whiteWin) {
			whiteW = new ImageIcon("res/whiteWin.png");
			whiteW.paintIcon(this, g, 240, 250);
		}
		if (blackWin) {
			blackW = new ImageIcon("res/blackWin.png");
			blackW.paintIcon(this, g, 240, 250);
		}
	}
	
	
	public int paintX(int x) {
		int paintx = 0;
		for (int i=0; i<22; i++) {
			if (i*40+20<x && x<(i+1)*40+20) {
				paintx = i*40+20;
				return paintx;
			}
		}
		
		return -100;
	}
	public int paintY(int y) {
		int painty = 0;
		for (int i=0; i<17; i++) {
			if (i*40+20<y && y<(i+1)*40+20) {
				painty = i*40+20;
				return painty;
			}
		}
		
		return -100;
	}
//	public boolean inList(int x, ArrayList<Integer> y) {
//		for(int i=0; i<y.size(); i++) {
//			if (i== x) {
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	public boolean check(int x, int y, HashMap<Integer, ArrayList<Integer>> hmp ) {
//		if (!hmp.containsKey(x)) {
//			return true;
//		}
//		else if(inList(y, hmp.get(x))) {
//			return false;
//		}
//		return true;
//		
//	}
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		realx =  paintX(e.getX()); 
//		realy =  paintY(e.getY()); 
//		if (check(realx, realy, hmap)) {
//			hmap.put(realx, realy);
//			System.out.println(hmap.size());
//			System.out.println(hmap.get(realx));
//			if (playerCounter%2==1) {
//				player1 = true;
//				player2 = false;
//				playerCounter+=1;
//				drawPieces(realx, realy);
//			}else {
//				player1 = false;
//				player2 = true;
//				playerCounter+=1;
//				drawPieces(realx, realy);
//			}	
//		}
//		// TODO Auto-generated method stub
//		
//	}
	public boolean check(int x, int y,  int[][] mouse ) {
		if (mouse[toIndex(x)][toIndex(y)]!=0) {
			return false;

		}
	return true;
}
	
//	public int fiveIn (ArrayList<Integer> a) {
//		int countB = 1;
//		int countW = 1;
//		for (int i =0; i<a.size(); i+=2) {
//			if(i+2 > a.size()) {
//				break;
//			}
//			if (a.get(i)==a.get(i+2)) {
//				countB+=1;
//			}else {
//				countB =1;
//			}
//			if (a.get(i+1)==a.get(i+3)) {
//				countW+=1;
//			}else {
//				countW =1;
//			}
//		}
//		if (countB>=5) {
//			return 0;
//		}
//		if (countW>=5) {
//			return 1;
//		}
//		return 2;
//	}
	
	
	public boolean winVertical(int[][] mouse) {
		int count = 0;
		for(int i=0; i<mouse.length;i++ ) {
			for(int j=0; j<mouse[i].length-1; j++) {
				if((mouse[i][j]==w || mouse[i][j] == b) && mouse[i][j]==mouse[i][j+1]) {
					count+=1;
				}else {
					count=0;
				}
				if (count>=4) {
					if (mouse[i][j+1] == w) {
						whiteWin = true;
						blackWin = false;
						return true;
						}
					if (mouse[i][j+1] == b) {
						blackWin = true;
						whiteWin = false;
						return true;
						}
					}
			}
		}
		return false;
	}
	
	public boolean winHorizontal(int[][] mouse) {
		int count = 0;
		for(int i=0; i<mouse[0].length;i++ ) {
			for(int j=0; j<mouse.length-1; j++) {
				if((mouse[j][i]==w || mouse[j][i] == b) && mouse[j][i]==mouse[j+1][i]) {
					count+=1;
				}else{
					count=0;
				}
				if (count>=4) {
					if (mouse[j+1][i] == w) {
						whiteWin = true;
						blackWin = false;
						return true;
						}
					if (mouse[j+1][i] == b) {
						blackWin = true;
						whiteWin = false;
						return true;
						}
					}
			}
		}
		return false;
	}
	
	public boolean winDiagRight(int[][] mouse) {
		ArrayList <ArrayList<Integer>> diag = new ArrayList <ArrayList<Integer>>();
		diag = TurnDiag.diagRightOrder(mouse);
		int count = 0;
		for(int i=0; i<diag.size();i++ ) {
			for(int j=0; j<diag.get(i).size()-1; j++) {
				if((diag.get(i).get(j)==w || diag.get(i).get(j) == b) && diag.get(i).get(j)==diag.get(i).get(j+1)) {
					count+=1;
				}else {
					count=0;
				}
				if (count>=4) {
					if (diag.get(i).get(j+1) == w) {
						whiteWin = true;
						blackWin = false;
						return true;
						}
					if (diag.get(i).get(j+1) == b) {
						blackWin = true;
						whiteWin = false;
						return true;
						}
					}
			}
		}
		return false;
	}
	
	public boolean winDiagLeft(int[][] mouse) {
		ArrayList <ArrayList<Integer>> diag = new ArrayList <ArrayList<Integer>>();
		diag = TurnDiag.diagLeftOrder(mouse);
		int count = 0;
		for(int i=0; i<diag.size();i++ ) {
			for(int j=0; j<diag.get(i).size()-1; j++) {
				if((diag.get(i).get(j)==w || diag.get(i).get(j) == b) && diag.get(i).get(j)==diag.get(i).get(j+1)) {
					count+=1;
				}else {
					count=0;
				}
				if (count>=4) {
					if (diag.get(i).get(j+1) == w) {
						whiteWin = true;
						blackWin = false;
						return true;
						}
					if (diag.get(i).get(j+1) == b) {
						blackWin = true;
						whiteWin = false;
						return true;
						}
					}
			}
		}
		return false;
	}
	
	public boolean win(int[][] mouse) {
		if(winVertical(mouse) || winHorizontal(mouse) || winDiagLeft(mouse) || winDiagRight(mouse)) {
			drawWin();
			return true;
			
		}
		return false;
	}
		
	public int toIndex(int real) {
		return (real-20)/40;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(!win(mouse)) {
			realx =  paintX(e.getX()); 
			realy =  paintY(e.getY()); 
			if (check(realx, realy, mouse)) {
				if (playerCounter%2==1) {
					mouse[toIndex(realx)][toIndex(realy)]=b;
					player1 = true;
					player2 = false;
					playerCounter+=1;
					drawPieces(realx, realy);
				}else {
					mouse[toIndex(realx)][toIndex(realy)]=w;
					player1 = false;
					player2 = true;
					playerCounter+=1;
					drawPieces(realx, realy);
				}	
			}
			
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
