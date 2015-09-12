package asteroids;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class HighScore implements Comparable{
	static String fileName = "HighScores";
	String name;
	int score;
	
	//CONSTRUCTOR
	public HighScore(String name, int score){
		this.name = name;
		this.score = score;
	}

	//COMPARE_TO METHOD
	public int compareTo(Object o) {
		HighScore newScore = (HighScore)o;
		if (this.score > newScore.score){
			return -1;
		}
		if (this.score < newScore.score){
			return 1;
		}
		return 0;
	}
	
	//method that checks the new score against the five highest scores, returns true or false
	public static boolean isHighScore(int score){
		//read in the first five scores
		Scanner inputStream = null;
		try{
			inputStream = new Scanner(new File(fileName));
		} catch(Exception e){
			System.out.println(e.getMessage());
		}

		ArrayList<HighScore> scoreList = new ArrayList<>();
		int nextScore;
		String name;
		for(int i = 0; i < 5; i++){
			nextScore = inputStream.nextInt();
			name = inputStream.nextLine().trim();
			scoreList.add(new HighScore(name, nextScore));
		}
		inputStream.close();
		//check new score against the current top five. return true when new score is greater than one of them:
		for(int i = 0; i < scoreList.size(); i++){
			if(score > scoreList.get(i).score){
				return true;
			}
		}
		return false;
	}
	
	/*
	 * method that reads in the five highest scores and draws them to the screen*/
//	public static void topFive(Graphics brush){
//		//use an inputStream to read in the values
//		Scanner inputStream = null;
//		try{
//			inputStream = new Scanner(new File(fileName));
//		} catch(Exception e){
//			System.out.println(e.getMessage());
//		}
//		
//		//store them in an array
//		HighScore[] highScore = new HighScore[5];
//		String name;
//		int score;
//		for(int i = 0; i < 5; i++){
//			score = inputStream.nextInt();
//			name = inputStream.nextLine();
//			highScore[i] = new HighScore(name, score);
//		}
//		inputStream.close();
//		
//		
//		//Title:
//		brush.setColor(Color.blue);
//		brush.setFont(new Font("Courier New", Font.BOLD, 72));
//		brush.drawString("High Scores:", 175, 100);
//
//		
//		//set font
//		brush.setColor(Color.white);
//		double fontSize = 44;
//		brush.setFont(new Font("Courier New", Font.BOLD, (int)fontSize));
//		
//		//draw them to the screen, starting from 0 and ending at 5
//		for(int i =0; i < 5; i++){
//			String str = highScore[i].score + " " + highScore[i].name;
//			brush.drawString(str, 175, 200 + (int)(i*(fontSize*1.5)));
//		}
//	}
	
	
	/*
	 * method that takes the new high score and stores it in the highScores file such that all scores are listed from least to greatest*/
	public static void storeScore(int newScore){
		//input window for number of apples
		String nameString = JOptionPane.showInputDialog("Congratulations!  You got a high score!  Please enter your name:") + "";

		if (nameString.equals("null") || nameString.equals("")){
			nameString = "Anonymous";
		}
		
		//read in all entries from file, storing them as HighScore objects
				Scanner inputStream = null;
				try{
					inputStream = new Scanner(new File(fileName));
				} catch(Exception e){
					System.out.println(e.getMessage());
				}

				ArrayList<HighScore> scoreList = new ArrayList<>();
				int nextScore;
				String name;
				while(inputStream.hasNextLine()){
					nextScore = inputStream.nextInt();
					name = inputStream.nextLine().trim();
					scoreList.add(new HighScore(name, nextScore));
				}
				
				//add the new entry
				scoreList.add(new HighScore(nameString, newScore));
				//sort them
				Collections.sort(scoreList);

				//write them back to the file
				PrintWriter outputStream = null;
				try{
					outputStream = new PrintWriter(new File(fileName));
				} catch(Exception e){
					System.out.println(e.getMessage());
				}
				
				for(int i = 0; i < scoreList.size(); i++){
					outputStream.print(scoreList.get(i).score + " ");
					outputStream.println(scoreList.get(i).name);
				}
				inputStream.close();
				outputStream.close();
	}

}
