package function;

import java.io.IOException;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) throws IOException {
		JFrame obj = new JFrame();
		Gameplay gameplay  = new Gameplay();
		obj.setBounds(10, 10, 900, 725);
		obj.add(gameplay);
		obj.setResizable(true);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
