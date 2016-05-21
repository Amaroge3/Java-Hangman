import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;


public class GameWords {
	
	Game game;
	private StringBuilder word = new StringBuilder();
	
	//variable to hold the word chosen from textfile
	private String randWord = "";
	
	//new array list to hold the words contained in file
	ArrayList  <String> fileRArray = new <String> ArrayList();
		
	
	GameWords(){ 
		readFromFile();
		selectRandomWord();
	}
    
	/**
	 * Method to check if the key clicked on gui interface is in the word
	 * chosen to guess.
	 */
	public void letterInWord(){
		for (int i = 0; i < randWord.length(); i++){
			if (Game.returnKeyPressed().equals(randWord.charAt(i))){
				word.deleteCharAt(i);
				word.insert(i, Game.returnKeyPressed());
			}
		}//for loop
		
	}
    
	/**
	 * Method to generate a random number from a range (inclusive)
	 * @param first integer number
	 * @param second integer number range up to (inclusive)
	 * @return a random integer
	 */
	private int generateRandomBetween(int first, int second){
		Random rand = new Random();
		int random = rand.nextInt(second + 1) + first;
		return random;
	}
	
	/**
	 * Method to read words from file
	 */
	private void readFromFile(){
		try{
			
			FileReader fileReader = new FileReader("src/text");
			BufferedReader reader = new BufferedReader(fileReader);
				
				String line = null;
				while ((line = reader.readLine()) != null){
					fileRArray.add(line);
                }
				reader.close();
				}
				catch(Exception ex){
					ex.printStackTrace();
	}
	}
	
	/**
	 * selects a random word and assigns it to the string variable randWord
	 */
	private void selectRandomWord(){
		//randWord = null;
		randWord = fileRArray.get(generateRandomBetween(0,fileRArray.size() - 1));
		randWord = randWord.toLowerCase();
		//call to append letters to the StringBuilder
		appendLetters();
	}
	
	/**
	 * Method to return a string of the StringBuilder variable "word"
	 * @return the word variable toString
	 */
	public String getSB(){
		return word.toString();
	}
	
	public StringBuilder getStringBuilderWord(){ 
		return word; 
		}
	//append *'s to the String Builder variable
	private void appendLetters(){
		for(int i = 0; i < randWord.length(); i++){
			word.append("*");
		}	
	}
}
