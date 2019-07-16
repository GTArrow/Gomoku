package function;

import java.util.ArrayList;

public class TurnDiag {
	static final int col= 17;
	static final int row = 22;
	
	public static int min(int a, int b) {
		return (a<b)?a:b;
	}
	public static int max(int a, int b) {
		return (a>b)?a:b;
	}
	public static int min3(int a, int b, int c) {
		return min(min(a,b),c);
	}
	
	public static ArrayList <ArrayList <Integer>> diagRightOrder(int mouse[][]){
		ArrayList <ArrayList <Integer>> dia  = new ArrayList <ArrayList <Integer>>();
		for (int line =1; line <=(row+col-1); line++) {
			dia.add(new ArrayList<Integer>());
			int start_col = max(0, line-row);
			int count  = min3(line, (col - start_col), row);
			for(int j=0; j< count; j++) {
				dia.get(line-1).add(mouse[min(row,line)-j-1][start_col+j]);
			}
		}
		return dia;
	}
	
	public static ArrayList <ArrayList <Integer>> diagLeftOrder(int mouse[][]){
		ArrayList <ArrayList <Integer>> dia  = new ArrayList <ArrayList <Integer>>();
		for (int line =1; line <=(row+col-1); line++) {
			dia.add(new ArrayList<Integer>());
			int start_col = col-1-max(0, line-row);
			int count  = min3(line, (start_col+1), row);
			for(int j=0; j< count; j++) {
				dia.get(line-1).add(mouse[min(row,line)-j-1][start_col-j]);
			}
		}
		return dia;
	}

}
