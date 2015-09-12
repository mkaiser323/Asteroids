package asteroids;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.File;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 * Separate class to separate screen-related methods from the rest of the Asteroids code
 * @author mahedikaiser
 *
 */
public class Screen extends Asteroids {


	private static void drawStars(Graphics brush){
		
		//stars
		int x_distanceBetweenStars = 90, y_distanceBetweenStars = 200, starSize = 2;
		int x, y;
		movement++;
		if(asteroidsList.isEmpty() ){
			movement+=3;
		}
		if(movement > y_distanceBetweenStars ){
			movement = 0;
		}  
		for( x = 0; x < Game.width; x += x_distanceBetweenStars){
			brush.setColor(Color.white);
			if(x % (2 * x_distanceBetweenStars) == 0){
				y = -3*y_distanceBetweenStars / 2;
			} else {
				y = -y_distanceBetweenStars;
			}
			for(; y < Game.height; y+= y_distanceBetweenStars){
				
				//COLORS:
				if( x == x_distanceBetweenStars * 3 && y == y_distanceBetweenStars * 1 ){
					brush.setColor(Asteroids.shipColor);
				}
				if( x == x_distanceBetweenStars * 5 && y == y_distanceBetweenStars * 2){
					brush.setColor(Color.orange);
				}
				if( x == x_distanceBetweenStars * 8 && y == 3*y_distanceBetweenStars / 2){
					brush.setColor(Color.red);
				}
				if( x == x_distanceBetweenStars * 4 && y == y_distanceBetweenStars / 2){
					brush.setColor(Color.cyan);
				}
							
				brush.fillOval(x + x_distanceBetweenStars / 2, y + movement, starSize, starSize);
			}
		}
	}
	
	//draw background
	public static void drawBackground(Graphics brush){
		brush.setColor(Color.black);
		brush.fillRect(0, 0, width, height);
		drawStars(brush);
	}
	
	//Welcome screen 
	public static void welcomeScreen(Graphics brush){
		welcomeScreenAsteroids(asteroidsBehind, brush);

		//Title
		String welcomeMessage = "Asteroids";
		brush.setColor(Asteroids.shipColor);
		brush.setFont(new Font("Courier New", Font.BOLD, 144));

		FontMetrics fm = brush.getFontMetrics();
		int x_string = (width - fm.stringWidth(welcomeMessage)) / 2;
		brush.drawString(welcomeMessage, x_string, 14*height/27);
		brush.drawRect(-1, 3*height/10, width + 1,   height/3);
		
		String enterPrompt = "Press [Enter] to continue.";
		brush.setColor(Color.white);		
		brush.setFont(new Font("Courier New", Font.BOLD, 32));
		fm = brush.getFontMetrics();
		x_string = (width - fm.stringWidth(enterPrompt)) / 2;
		if( timer % 80 >= 40){//flicker effect
			brush.drawString(enterPrompt, x_string, 13*height/16);	
		}
		
		welcomeScreenAsteroids(asteroidsInFront, brush);
	}
	/*
	 * Main menu
	 * 
	 * this screen will be the first to show up after the welcome screen goes away
	 * it will also show up on the pause screen, the only difference being the 
	 * blinking continue prompt on the top
	 * 
	 * the offset variable allows me to adjust the y value of the text each time I invoke the method
	 */
	public static void menu(Graphics brush){
		//title: "MAIN MENU"
		brush.setColor(Asteroids.shipColor);
		brush.setFont(new Font("Courier New", Font.BOLD, 72));
		int title_y = 200;
		brush.drawString("Main Menu", 200, title_y);
		
		int fontSize = 33;
		int topGap = 50;
		int x = 95;
		brush.setColor(Color.white);
		//Menu options:
		//l for learn how to play-- M button to return from there to main menu
		brush.setFont(new Font("Courier New", Font.BOLD, fontSize));
		brush.drawString("Press [L] to learn how to play", 
				x, (int)(title_y + topGap + fontSize*1.5) );

		
		//n for new game-- M button to return to main menu
		brush.drawString("Press [N] to start a new game", 
				x, (int)(title_y + topGap + 2*fontSize*1.5) );

		
		//h for high scores-- M button to return to main menu
		brush.drawString("Press [H] to view high scores", 
				x, (int)(title_y + topGap + 3*fontSize*1.5) );
	}
	
	public static void highScores(Graphics brush){
		//use an inputStream to read in the values
		Scanner inputStream = null;
		try{
			inputStream = new Scanner(new File(fileName));
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		//store them in an array
		HighScore[] highScore = new HighScore[5];
		String name;
		int score;
		for(int i = 0; i < 5; i++){
			score = inputStream.nextInt();
			name = inputStream.nextLine();
			highScore[i] = new HighScore(name, score);
		}
		inputStream.close();
		
		
		//Title:
		brush.setColor(Asteroids.shipColor);
		brush.setFont(new Font("Courier New", Font.BOLD, 72));
		brush.drawString("High Scores:", 145, 150);

		
		//set font
		brush.setColor(Color.white);
		double fontSize = 44;
		brush.setFont(new Font("Courier New", Font.BOLD, (int)fontSize));
		
		//draw them to the screen, starting from 0 and ending at 5
		for(int i =0; i < 5; i++){
			String str = highScore[i].score + " " + highScore[i].name;
			brush.drawString(str, 155, 220 + (int)(i*(fontSize*1.5)));
		}
		
		brush.setColor(Color.gray);
		//press m to return to the main menu
		brush.setFont(new Font("Courier New", Font.BOLD, 24));
		brush.drawString("Press [M] to return to the Main Menu", 135, 55);
	}	
	//how to play
	static boolean clockWise = true, left = true;
	public static void howToPlay(Graphics brush){
		brush.setColor(Color.gray);
		//press m to return to the main menu
		brush.setFont(new Font("Courier New", Font.BOLD, 24));
		brush.drawString("Press [M] to return to the Main Menu", 135, 55);
		
		//TEXT DEMO:
		brush.setColor(Color.white);
		brush.setFont(new Font("Courier New", Font.BOLD, 24));
		brush.drawString("The [Left] & [Right] arrow keys are used to turn.", 60, 100);

		brush.drawString("The [Up] arrow key moves you forward.", 140, 250);
		
		brush.drawString("Holding down the [Shift] key while pressing the ", 60, 385);
		brush.drawString("[Left] & [Right] arrow keys moves you sideways.", 65, 415);
		
		brush.drawString("Oh, and you use the [Spacebar] to shoot.", 130, 550);
	
		//VISUAL DEMO:
		//L + R
		brush.setColor(Asteroids.shipColor);
		Asteroids.demoShip1.paint(brush, false, timer, lives, false);
		if(Asteroids.demoShip1.rotation > 280){
			clockWise = false;
		}
		if(Asteroids.demoShip1.rotation < -280){
			clockWise = true;
		}
		if(clockWise){
			Asteroids.demoShip1.demo("clockWise");
		} else {
			Asteroids.demoShip1.demo("counterClockWise");
		}
		
		//forward
		demoShip2.paint(brush, false, timer, lives, false);
		demoShip2.demo("forward");
		
		//SHIFT & L + R
		demoShip3.paint(brush, false, timer, lives, false);
		
		if(demoShip3.position.x > 700){
			left = false;
		}
		if(demoShip3.position.x < 100){
			left = true;
		}
		if(left){
			demoShip3.demo("left");
		} else {
			demoShip3.demo("right");
		}
	}
	
	//New Game
	public static void newGame(Graphics brush){
		score += .01;
		Asteroid.speed += .0005;
		if(Asteroid.speed > 8){
			Asteroid.speed = 4;
			score += 50;
		}
		//asteroids
		//only paint asteroids when list is not empty
		if(!asteroidsList.isEmpty()){
			brush.setColor(Color.gray);
			paintAsteroids(asteroidsList, brush);
		} else {
			//reload asteroidArrayList when empty
			reloadAsteroids(brush);
		}

		displayStats(brush);

		//ship
		shoot(brush);
		brush.setColor(Asteroids.shipColor);
		myShip.paint(brush, beingHit, timer, lives, true);

		myShip.move();

		if(asteroidWasHit(brush)){
			brush.setColor(Color.cyan);
			brush.drawOval(
					(int)bullet.getPoints()[3].x-50, 
					(int)bullet.getPoints()[3].y-50, 100, 100);
		}
	}
	
	
	 //Game over screen
	static boolean scoreUpdated = false;
	public static void gameOver(Graphics brush){
		
		//game over
		String gameOver = "GAME OVER";
		brush.setColor(Asteroids.shipColor);
		brush.setFont(new Font("Courier New", Font.BOLD, 128));
		FontMetrics fm = brush.getFontMetrics();	
		int x_string = (width - fm.stringWidth(gameOver)) / 2;
		brush.drawString(gameOver, x_string, 5*height/16);

		
		//SCORE
		if(!scoreUpdated){
			updateHighScore();
			scoreUpdated = true;
		}
		brush.setFont(new Font("Courier New", Font.BOLD, 64));
		brush.setColor(Color.gray);
		fm = brush.getFontMetrics();
		
		highScoreMessage = "High Score: " + getHighScore(); 
		x_string = (width - fm.stringWidth(scoreMessage)) / 2;
		brush.drawString(highScoreMessage, x_string, 17*height/32);

		scoreMessage = "Your Score: " + (int)score; 
		x_string = (width - fm.stringWidth(scoreMessage)) / 2;
		brush.drawString(scoreMessage, x_string, 21*height/32);
		
		
		//new game prompt
		String enterPrompt = "Press [Enter] to play again";

		if( timer % 80 >= 40){//flicker effect
			brush.setColor(Color.black);
		} else {
			brush.setColor(Color.white);
		}		brush.setFont(new Font("Courier New", Font.BOLD, 32));
		fm = brush.getFontMetrics();
		x_string = (width - fm.stringWidth(enterPrompt)) / 2;
		brush.drawString(enterPrompt, x_string, 13*height/16);
		
		playGame = false;
		if(enter){
			setDefaults(brush);
			playGame = true;
			scoreUpdated = false;
		}

	}
}
