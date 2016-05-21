import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
public class Game<Item> extends JFrame implements ActionListener {
	public static JFrame game = new Game();
	//instance of the GameWords class
	GameWords gameWords = new GameWords();

	//Arraylists for game
	ArrayList <Shape> shapeArray = new <Shape> ArrayList();//initial gui
	ArrayList <JButton> jbuttonArray = new ArrayList<JButton>();//button array
	ArrayList<Shape> gameShapeArray = new <Shape> ArrayList();//game shapes array

	//the key clicked on the gui is saved
	private static Character keyPressed;
	
	//total moves the player has
	private Integer moves = 20;

	//panel for gui interface
	DrawPanel guiP= new DrawPanel();

	//turn count variable
	private int turn = 0;
	
	
	//Jpanels
	JPanel letterPanel = new JPanel(); //letter panel
	JPanel buttonPanel = new JPanel(new FlowLayout (FlowLayout.CENTER)); // button panel
	JPanel labelPanel = new JPanel(); //label panel
	
	//JButtons
	JButton newGame = new JButton("New Game"); //new game
	JButton closeButton = new JButton("Close Game");
	
	//JLabels
	JLabel wordLabel = new JLabel(gameWords.getSB());
	JLabel movesLabel = new JLabel("Number of moves left: " + moves.toString());

	//boolean wrong
	private Boolean won = false;

	//constructor 
	Game(){
		//call the init gui method
		createInitView();

		//add button to panel
		buttonPanel.add(newGame);
		buttonPanel.add(closeButton);
		buttonPanel.add(movesLabel);

		labelPanel.add(wordLabel, new FlowLayout(FlowLayout.RIGHT,10,10));

		//actionlistener to new Game and close buttons
		newGame.addActionListener(this);
		closeButton.addActionListener(this);
	}

	/**
	 * Method that creates the initial view of the game including the panels, shapes, and buttons
	 * 
	 */
	private void createInitView() 
	{
		//create new initial shapes
		shapeArray.add(new NewLine(80,100,80,350)); 
		shapeArray.add(new NewLine(70,350,90,350));
		shapeArray.add(new NewLine(80,100,170,80));
		shapeArray.add(new NewLine(170,80,170,140));

		super.setLayout(new BorderLayout(10, 10));
		//add panels to the jframe
		super.add(BorderLayout.CENTER,guiP);
		super.add(BorderLayout.SOUTH, buttonPanel);
		super.add(BorderLayout.EAST, letterPanel);
		super.add(BorderLayout.NORTH, labelPanel);

		//array button call
		populateButtonArray();	
		//call to populate Game shapes array method
		populateGameShapesArray();
	}

	/**
	 * Next turn in the game. Each click on a letter in the gui
	 * runs this method. 
	 * @param e Action event parameter
	 */
	private void turn(ActionEvent e){
		for(int i = 0; i < jbuttonArray.size(); i++){
			if (e.getSource() == jbuttonArray.get(i)){

				keyPressed = jbuttonArray.get(i).getText().charAt(0);
				jbuttonArray.get(i).setEnabled(false);
				gameWords.letterInWord();
				wordLabel.setText(gameWords.getSB());
				movesLabel.setText("Number of moves left: " + reduceMoves());
				gameLost();
				wonGame();
			}	
		}
	}
	/**
	 * Action performed method for mouse clicking events
	 * @param action event
	 */
	public void actionPerformed(ActionEvent event){
		String s = null;

		turn(event); //user click letters

		if (event.getSource()== newGame){
			restart();
		}
		else if (event.getSource() == closeButton){
			System.exit(0);
		}
	}


	/**
	 * method used to restart the game using the "New Game" button
	 */
	public void restart(){
		dispose();
		game = new Game();
		game.setSize(600, 500);
		game.setVisible(true);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setTitle("Hangman");
	}
	//get method for returning keyPressed variable
	public static Character returnKeyPressed(){
		return keyPressed;
	}
	
	/**
	 * Method used to populate the button array from 'a' to 'z'.
	 * The method also adds action listener, as well as adds the buttons to letterPanel.
	 */
	public void populateButtonArray(){
		for(Character i = 'a'; i<='z'; ++i){
			jbuttonArray.add(new JButton(i.toString()));
		}
		letterPanel.setLayout(new GridLayout(10, 0,2,5));
		for (int i = 0 ;i<jbuttonArray.size();i++){
			jbuttonArray.get(i).addActionListener(this);
			letterPanel.add(jbuttonArray.get(i));
		}	
	}
	/**
	 * Method that checks whether the player has won. If so, the JOptionPane
	 * message is displayed and the method returns true;
	 * @return boolean true if player has won, else false
	 */
	private Boolean wonGame(){
		char chars = 0;
		//check if there is no asterix in the stringbuilder
		for (int i = 0; i < gameWords.getStringBuilderWord().length(); i++){
			chars = gameWords.getStringBuilderWord().charAt(i);
			if (chars == '*'){
				return false;
			}
			chars = 0;
		}
		for (JButton button: jbuttonArray){
			button.setEnabled(false);}
			JOptionPane.showMessageDialog(null, "You won", "Hangman",JOptionPane.INFORMATION_MESSAGE);
			won = true;
		return true;
	}
	
	private void gameLost(){
		if (moves == 0){
			JOptionPane.showMessageDialog(null, "You ran out of moves.", "Hangman", JOptionPane.INFORMATION_MESSAGE);
			for (JButton button: jbuttonArray){
				button.setEnabled(false);
			}
		}
		//calls the drawInGameShapes at every turn specified in this method.
		drawInGameShapes();

	}


	/**
	 * Populate the game shapes array with shapes that are used to draw while playing
	 * the game.
	 */
	private void populateGameShapesArray(){
		gameShapeArray.add(new NewOval(160,140,20,20)); //draw head
		gameShapeArray.add(new NewLine(170,160,170,230)); //body line
		gameShapeArray.add(new NewLine(170,175,150,190)); //left hand
		gameShapeArray.add(new NewLine(170, 175, 190, 190)); //right hand
		gameShapeArray.add(new NewLine(170, 230, 150, 250));//left leg
		gameShapeArray.add(new NewLine(170, 230, 190, 250)); //right leg

	}

	/*public void NewLine(){
		private int x1, x2, y1, y2;

		//NewLine(int x1, int y1, int x2, int y2){
			//this. x1= x1;
			//this.y1 = y1;
			//this.x2 = x2;
			//this.y2 = y2;

		}*/

	/**
	 * Inner class to show to paint to the panel
	 */
	class DrawPanel extends JPanel{
		public void paint(Graphics g){
			//draw first shapes when program starts
			for (int i = 0; i < shapeArray.size();i++){
				shapeArray.get(i).draw(g);
			}

			//set newgame color
			newGame.setForeground(Color.BLUE);
			closeButton.setForeground(Color.BLUE);

			//sets border
			labelPanel.setBorder(new TitledBorder("Hangman"));
			letterPanel.setBorder(new TitledBorder("Select a Letter"));
			
			
			//calls the draw Shape method that
			drawShape(g); 
		}

	}
	/**
	 * Method used to draw the shapes of hangman as the game progresses. Works with the drawShape method
	 * to draw the shapes as the game progresses.
	 */
	void drawInGameShapes(){
		
		switch (moves){
		case 16:
			repaint();
			break;
		case 13:
			repaint();
			break;
		case 10:
			repaint();
			break;
		case 7:
			repaint();
			break;
		case 3:
			repaint();
			break;
		case 0:
			repaint();
			break;
		}
	}
	/**
	 * Used in the class paint method to draw the shapes as the games progress.
	 * @param g 
	 */
	void drawShape(Graphics g){
		switch (moves){
		case 16:
			gameShapeArray.get(0).draw(g);
			break;
		case 13:
			for (int i = 0; i <= 1; i++){
				gameShapeArray.get(i).draw(g); }
			break;
		case 10:
			for (int i = 0; i <= 2; i++)
				gameShapeArray.get(i).draw(g);
			break;
		case 7:
			for (int i = 0; i <= 3; i++)
				gameShapeArray.get(i).draw(g);
			break;
		case 3:
			for (int i = 0; i <= 4; i++)
				gameShapeArray.get(i).draw(g);
			break;
		case 0:
			if(won == false){
				for (int i = 0; i <= 5; i++)
					gameShapeArray.get(i).draw(g);}
			else{
				for (int i = 0; i < 5; i++){
					gameShapeArray.get(i).draw(g); }
			}
			break;
		}
	}
	
	
	/**
	 * reduces then number of moves
	 * @return moves variable decremented by 1
	 */
	public Integer reduceMoves(){
		return --moves;
	}


}
