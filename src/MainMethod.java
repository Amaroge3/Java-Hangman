import java.awt.Canvas;

import javax.swing.JFrame;


public class MainMethod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game.game.setVisible(true);
		Game.game.setSize(600, 500);
		Game.game.setDefaultCloseOperation(Game.game.EXIT_ON_CLOSE);
		Game.game.setTitle("Hangman");
		int number = 5;
		StringBuilder sb = new StringBuilder("12345");
		
		
		sb.deleteCharAt(1);
		sb.insert(1, "4");
		System.out.println(sb);
	
	}

}
