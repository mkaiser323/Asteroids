package asteroids;

/*
CLASS: Asteroids
DESCRIPTION: Extending Game, Asteroids is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.
Original code by Dan Leyzberg and Art Simon
 */


import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;

class Asteroids extends Game implements KeyListener{

	int i = 0;
	static int movement;
	int startedWaitingAt;
	private boolean  welcomeScreen;
	protected static boolean playGame;
	public static int timer;
	private static int highScore;
	private String livesLeft;
	protected static String scoreMessage;
	protected static String highScoreMessage;
	private String speedMessage;
	private static boolean gameIsOver;
	public static double score;
	protected static double lives;
	private static double damage = 50;
	private static double powerup;
	public static final int SHIP_SIZE = 50;
	protected static boolean enter;
	private boolean paused;
	private boolean mainMenu = false;
	public static String fileName = "HighScores";
	static Color shipColor = Color.blue, shipHitColor = Color.red, bulletColor = Color.cyan, 
	healthBarColor = Color.blue, healthBarColor2 = Color.orange, healthBarColor3 = Color.red, healthBarBGColor = Color.white, 
	asteroidColor = Color.gray, asteroidOutlineColor = Color.black;

	//SHIP
	//points
	static double thickness = 10;
	static Point nose = new Point(SHIP_SIZE, SHIP_SIZE/2),
	 rightWing = new Point(0, SHIP_SIZE),
	 rightWingInner = new Point(0, SHIP_SIZE-thickness),
	 center = new Point(5*SHIP_SIZE/8, SHIP_SIZE/2),
	 leftWingInner = new Point(0 , thickness),
	 leftWing = new Point(0, 0);	
	
	//shape:
	static Point[] shape = {nose, rightWing, rightWingInner, center, leftWingInner, leftWing};
	
	static Point shipLocation = new Point(Game.width / 2, Game.height / 2),
			demoShip1Location = new Point(400, 150),
			demoShip2Location = new Point(400, 300),
			demoShip3Location = new Point(400, 460);
	
	
	static double rotation = 270.0;
	static Ship myShip = new Ship(shape, shipLocation, rotation),
			demoShip1 = new Ship(shape, demoShip1Location, 0),
			demoShip2 = new Ship(shape, demoShip2Location, 0),
			demoShip3 = new Ship(shape, demoShip3Location, 270);

		
	//ASTEROIDS
	
	//medium sized asteroids
	static Point point1 = new Point(0,17),
			point2 = new Point(16,0),
			point3 = new Point(28,0),
			point4 = new Point(36,10),
			point5 = new Point(40,20),
			point6 = new Point(28,35),
			point7 = new Point(15,38),
			point8 = new Point(4,30),
			point9 = new Point(20, 20);
	static Point[] asteroidShape = {point1,point2,point3,point4,point5,point6,point7,point8};
	
	//slightly larger asteroids
	static Point largepoint1 = new Point(0,25),
			largepoint2 = new Point(22,0),
			largepoint3 = new Point(45,0),
			largepoint4 = new Point(50,15),
			largepoint5 = new Point(60,30),
			largepoint6 = new Point(50,60),
			largepoint7 = new Point(20,45),
			largepoint8 = new Point(6,45),
			largepoint9 = new Point(35, 35);
	static Point[] largeasteroidShape = {largepoint1,largepoint2,largepoint3,largepoint4,largepoint5,largepoint6,largepoint7,largepoint8};
	
	static Point location1 = new Point(-50, -10),
			location2 = new Point(75, -50),
			location3 = new Point(150, -5),
			location4= new Point(200, -25),
			location5 = new Point(700, -50),
			location6 = new Point(500, 0),
			location7 = new Point(650, -50),
			location8 = new Point(600, -30),
			location9 = new Point(400, -10),
			location10 = new Point(300, -45);
	static Point[] locations = {location1, location2, location3, location4, location5, location6, location7, location8};
	
	static Asteroid asteroid1 = new Asteroid(largeasteroidShape, location1, 0),
			asteroid2 = new Asteroid(largeasteroidShape, location2, 0),
			asteroid3 = new Asteroid(largeasteroidShape, location3, 0),
			asteroid4 = new Asteroid(asteroidShape, location4, 0),
			asteroid5 = new Asteroid(largeasteroidShape, location5, 0),	
			asteroid6 = new Asteroid(asteroidShape, location6, 0),	
			asteroid7 = new Asteroid(largeasteroidShape, location7, 0),	
			asteroid8 = new Asteroid(largeasteroidShape, location8, 0),
			asteroid9 = new Asteroid(asteroidShape, location9, 0),
			asteroid10 = new Asteroid(largeasteroidShape, location10, 0);
	static Asteroid[] asteroids = {asteroid1, asteroid2, asteroid3, asteroid4, asteroid5, 
			asteroid6, asteroid7, asteroid8, asteroid9, asteroid10};
	
	//arrayList of Asteroids
	static ArrayList<Asteroid> asteroidsList = new ArrayList<>();
	
	
	//Asteroids for welcome screen
	//in front of text:
	static Point loc1 = new Point(-1, 200),//moving horizontally behind the title
			loc2 = new Point(-10, 120),//moving southeast
			loc3 = new Point(270, 800);//moving northwest
	//behind text:
	static Point loc4 = new Point(300, 200),//horizontal
			loc5 = new Point(0, 400),//SE
			loc6 = new Point(600, 400);//NW

	static Asteroid a1 = new Asteroid(asteroidShape, loc1, 0),
			a2 = new Asteroid(asteroidShape, loc2, 0),
			a3 = new Asteroid(asteroidShape, loc3, 0),
			a4 = new Asteroid(asteroidShape, loc4, 0),
			a5 = new Asteroid(asteroidShape, loc5, 0),
			a6 = new Asteroid(asteroidShape, loc6, 0);

	static Asteroid[] asteroidsBehind = {a1, a2, a3};
	static Asteroid[] asteroidsInFront = {a4, a5, a6};
	
	//BULLET
	//arrow shape
	Point tip = new Point(15, 5),
			rightCorner = new Point(4, 10),
			innerRight = new Point(6,6),
			tail = new Point(0,5),
			innerLeft = new Point(6,4),
			leftCorner = new Point(4,0);
	Point[] arrow = {tip, rightCorner, innerRight, tail, innerLeft, leftCorner};
	
	//laser shape
	public static final int BULLET_WIDTH = 2;
	public static final int BULLET_LENGTH = 75;
	static Point s1 = new Point(0,0),
			s2 = new Point(0,BULLET_WIDTH),
			s3 = new Point(BULLET_LENGTH,BULLET_WIDTH),
			s4 = new Point(BULLET_LENGTH,0);
	static Point[] laser ={s1,s2,s3,s4};
	static Point shipTip = new Point(600, 400);
	static Bullet bullet = new Bullet(laser, shipTip, myShip.rotation);
	
	//POWERUPS
	static int powerupSize = 5;
	static Point right1 = new Point(2*powerupSize, 0),
			right2 = new Point(2*powerupSize, 1*powerupSize),
			right3 = new Point(3*powerupSize, 1*powerupSize),
			right4 = new Point(3*powerupSize, 2*powerupSize),
			right5 = new Point(2*powerupSize, 2*powerupSize),
			right6 = new Point(2*powerupSize, 3*powerupSize),
			left1 = new Point(1*powerupSize, 3*powerupSize),
			left2 = new Point(1*powerupSize, 2*powerupSize),
			left3 = new Point(0, 2*powerupSize),
			left4 = new Point(0, 1*powerupSize),
			left5 = new Point(1*powerupSize, 1*powerupSize),
			left6 = new Point(1*powerupSize, 0);
	static Point[] plusShape = {right1, right2, right3, right4, right5, right6, 
			left1, left2, left3, left4, left5, left6};
	static Point topLeft = new Point(200, 150), 
			topRight = new Point(600, 150),
			bottomLeft = new Point(200, 450),
			bottomRight = new Point(600, 450);
	static Powerup health1 = new Powerup(plusShape, topLeft, 270),
			health2= new Powerup(plusShape, topRight, 270),
			health3 = new Powerup(plusShape, bottomLeft, 270),
			health4 = new Powerup(plusShape, bottomRight, 270);
	static Powerup[] powerups = {health1, health2, health3, health4};
	static ArrayList<Powerup> healthList = new ArrayList<>();
	static int health_i = 0;

	//CONSTRUCTOR
	public Asteroids() {
		super("Asteroids!",800,600);
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(myShip);
		this.addKeyListener(this);
		addKeyListener(bullet);
		welcomeScreen = true;
		playGame = false;
		//put contents of Asteroids into arrayList:
		for(int i = 0; i < asteroids.length; i++ ){
			asteroidsList.add(asteroids[i]);			
		}
		for(int i = 0;i < powerups.length; i++){
			healthList.add(powerups[i]);
		}
		gameIsOver = false;
		timer = 1;
		lives = 5;
		score = 0;
		enter = false;
		paused = false;
		beingHit = false;

	}

	static int healthTimer = 0;
	boolean rightAfterWelcomeScreen = true;
	//PAINT METHOD
	public void paint(Graphics brush) {
		this.addKeyListener(this);
		timer++;
		healthTimer++;
		if(timer == 800){
			timer = 0;
		}
		Screen.drawBackground(brush);	
		//welcome screen
		if(welcomeScreen){
			Screen.welcomeScreen(brush);
			bullet.isFired = false;
			if(enter){
				welcomeScreen = false;//hide welcome screen
				mainMenu = true;
				playGame = true;
				paused = true;
			}
		}
		
		
		if(playGame){
			if(!paused){//unpaused
				Screen.newGame(brush);
				if(p){
					paused = true;
					rightAfterWelcomeScreen = false;
				}
				if(lives <= 0){//conditions required to end game
					gameIsOver = true;
				}
				mainMenu = true;
			}
			if (paused) {//paused
				if(mainMenu){
					Screen.menu(brush);
				}
				
				//********************MENU OPTIONS:********************//
				
				//----------Press [L] to Learn how to play----------//
				if(l && !highScores){
					mainMenu = false;//hide menu
					howToPlay = true;//display tutorial
				}
				if(howToPlay){
					Screen.howToPlay(brush);
				}
				//----------Press [N] to start a new game----------//
				if(n){
					setDefaults(brush);
					mainMenu = false;//hide menu
					paused = false;//exit from pause
					playGame = true;
				}
				
				//----------Press [H] to view high scores----------//
				if(h && !howToPlay){					
					mainMenu = false; //hide menu
					highScores = true;
				}
				if(highScores){
					Screen.highScores(brush);
				}
				if(m){//returning to the main menu
					highScores = false;//hide high scores
					howToPlay = false;//hide tutorial
					mainMenu = true;//return to main menu
				}

				if(!rightAfterWelcomeScreen && mainMenu){
					brush.setColor(Color.white);
					brush.setFont(new Font("Courier New", Font.BOLD, 32));
					if( timer % 80 >= 40){//blinking effect
						brush.drawString("Paused. Press [C] to Continue", 115, height - 100);
					}
					if(c){//if c is pressed, hide menu and resume game
						mainMenu = false; 
						paused = false;
					}
				}
			} 
		}

		
		//game over screen

		if(gameIsOver ){
			bullet.isFired = false;
			playGame = false;
			Screen.gameOver(brush);
		}
	}

	//MAIN METHOD
	public static void main (String[] args) {
		Asteroids a = new Asteroids();
		a.repaint();
	}
	
	//************************************************PRIVATE METHODS****************************************************************//
	
	//checks for collisions
	private static boolean shipWasHit(Graphics brush){
		for(int a = 0; a < asteroidsList.size(); a ++){
			if(Polygon.collided(myShip, asteroidsList.get(a)) && timer >= 500){
				return true;				
			}
		}
		return false;//return false by default
	}
	//check for collisions between bullet and asteroids
	protected static boolean asteroidWasHit(Graphics brush){
		for(int a = 0; a < asteroidsList.size(); a++){
			if(a >= asteroidsList.size()){
				a = asteroidsList.size() - 1;
			}					
			if(a >= 0){
				for(int i = 0; i < bullet.getPoints().length; i++){
					if (	(bullet.isFired &&  
								(asteroidsList.get(a).contains(bullet.getPoints()[i]))
								|| (bullet.isFired && (asteroidsList.get(a).contains(myShip.getPoints()[0])))
							)
						){//if asteroid a is hit
						asteroidsList.remove(a);
						score += 5;
						return true;
					}

				}
			}
		}
		return false;//return false by default
	}
	
	private static boolean gotHealth(){
		for(int shipPoints = 0; shipPoints < myShip.getPoints().length; shipPoints++){
			for(int h = 0; h < healthList.size(); h++){
				for(int i = 0; i < healthList.get(h).getPoints().length; i++){
					if(myShip.contains(healthList.get(h).getPoints()[i]) 
							|| healthList.get(h).contains(myShip.getPoints()[shipPoints])){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	protected static void shoot(Graphics brush){
		brush.setColor(bulletColor);
		if(bullet.isFired){
			brush.fillPolygon(bullet.getX(bullet.getPoints()), bullet.getY(bullet.getPoints()), bullet.getPoints().length);
			bullet.shoot();
			
		} 
		if(!bullet.isFired){
			bullet.position.x = myShip.getPoints()[0].x + 25*Math.cos(Math.toRadians(myShip.rotation)) -15;
			bullet.position.y = myShip.getPoints()[0].y + 25* Math.sin(Math.toRadians(myShip.rotation));
			bullet.rotation = myShip.rotation;
		}
	}
	

	//paints all asteroids in the arrayList, moving them differently depending on which asteroid is being moved
	protected static void paintAsteroids(ArrayList<Asteroid> asteroidsList, Graphics brush){
		if(!asteroidsList.isEmpty()){ //move asteroids only if there are asteroids to move
			for(int i = 0; i < asteroidsList.size(); i++){
				//brush.setColor(Color.gray);
				if(i >= asteroidsList.size()){
					i = asteroidsList.size() - 1;
				}
				if(i>=0){
					asteroidsList.get(i).paint(brush, i);
					asteroidsList.get(i).move(i);
				}
			}
		} else { //if there are no more asteroids, reset their locations

		}

	}
	
	protected static void reloadAsteroids(Graphics brush){
			for(int i = 0; i < asteroids.length; i++ ){
				asteroidsList.add(asteroids[i]);
			}
			for(int i = 0; i < asteroidsList.size(); i++ ){
				asteroidsList.get(i).setLocation(new Point( 4 + i*95, i*5 - 50));
			}
	}
	
	//paints asteroids for the welcome screen
	protected static void welcomeScreenAsteroids(Asteroid[] asteroids, Graphics brush){
		for(int i = 0; i < asteroids.length; i ++){
			//brush.setColor(Color.DARK_GRAY);
			asteroids[i].paint(brush, i);
			asteroids[i].hover(i);
		}
	}
	
	protected static int getHighScore(){
		Scanner inputStream = null;
		try{
			inputStream = new Scanner(new File(fileName));
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		int highScore;
		if(inputStream.hasNext()){
			highScore = inputStream.nextInt();
		} else {
			highScore = 0;
		}
		inputStream.close();
		return highScore;
	}
	protected static void updateHighScore(){
		highScore = getHighScore();
		if (HighScore.isHighScore((int)score)){
			highScore = (int)score;
			HighScore.storeScore(highScore);
		}
	}
	static boolean beingHit = false;
	static boolean gettingHealth = false;
	protected static void displayStats(Graphics brush){
		//deduct lives if hit by asteroid
		if(shipWasHit(brush) ){
			if(!beingHit){

				lives -= damage ;//lives only decremented the first time, before
				//beingHit is changed to true
			}
			beingHit = true;
		} else {//once the ship is no longer in contact with the block
			//beingHit is set back to false so that another separate hit
			//will be able to deduct a life
				beingHit = false;
		}
		
//		//powerups
//		if(lives <= 2 && healthTimer >= 1000){
//			brush.setColor(green);
//			healthList.get(health_i).paint(brush);
//			healthList.get(health_i).move(0);
//		}
//		if(gotHealth() ){
//			if(!gettingHealth && lives <= 3){
//				healthList.remove(health_i);
//				lives++;
//				healthTimer = 0;
//				health_i++;
//				if(health_i >= healthList.size()){
//					for(int i = 0;i < powerups.length; i++){
//						healthList.add(powerups[i]);
//					}
//					health_i = 0;
//				}
//				int x, y;
//				if(myShip.position.x <= 400){
//					x = 600;
//				} else {
//					x = 200;
//				}
//				if(myShip.position.y <= 300){
//					y = 450;
//				} else {
//					y = 150;
//				}
//				healthList.get(health_i).position = new Point(x, y);
//				gettingHealth = true;
//			} else {
//				gettingHealth = false;
//			}
//		}
		brush.setColor(Color.white);
		
		//display health bar
		healthBar(brush);
	}
	
	private static void healthBar(Graphics brush){

		int lives_percentage = (int)((lives / Game.width) * 100);
		if(lives > 0){
			
			//background
			brush.setColor(healthBarBGColor);
			brush.fillRect(5, 5, Game.width-10, 30);
			
			//health
			
			brush.setColor(healthBarColor);
			if(lives_percentage <= 25){
				brush.setColor(healthBarColor2);
			}
			if(lives_percentage <= 15){
				brush.setColor(healthBarColor3);
			}
			brush.fillRect(5, 5, (int)lives-10, 30);
		}
		
		scoreMessage = "Score: " + (int)score;
		brush.setFont(new Font("Courier New", Font.BOLD, 20));
		brush.setColor(Color.black);
		brush.drawString("Health: " + lives_percentage + "%", 20, 25);
		brush.drawString(scoreMessage, 350, 25);
		brush.drawString("Press [P] to Pause", 545, 25);
//		powerup(brush);
	}
	
//	private static void powerup(Graphics brush){
//		
//		if(lives > 0){
//			
//			//background
//			brush.setColor(Color.black);
//			brush.fillRect(5, 35, Game.width-10, 5);
//			//powerup
//			if(powerup < Game.width-10){
//				powerup+=2;
//				brush.setColor(Color.cyan);
//			} else {
//				if( timer % 20 >= 10){//flicker effect
//				brush.setColor(Color.black);
//				} else {
//					brush.setColor(Color.cyan);
//				}
//				
//			}
//			brush.fillRect(5, 35, (int)powerup, 5);
//			//border
//			brush.setColor(Color.black);
//			brush.drawRect(5, 35, Game.width-10, 5);
//		}
//	}
	
	protected static void setDefaults(Graphics brush){
		gameIsOver = false;
		beingHit = false;
		timer = 1;
		lives = Game.width;
		powerup = 0;
		score = 0;
		Asteroid.speed = Asteroid.INITIAL_SPEED;
		asteroidsList.clear();
		reloadAsteroids(brush);
		myShip.setLocation(new Point(Game.width / 2,Game.height/2));
		myShip.rotation = 270;
	}
	

	//KEY_LISTENER METHODS-------------------------------------------------------------------------------------------//
	public void keyTyped(KeyEvent e) {}

	boolean highScores = false, n = false, m = false, l = false,
			p = false, c = false, h = false, howToPlay = false, 
			spaceBar = false;
;
	int b = 0;
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch(keyCode){
		case KeyEvent.VK_ENTER:
			enter = true;
			break;
		case KeyEvent.VK_P:
			p = true;
			break;
		case KeyEvent.VK_C:
			c = true;
			break;
		case KeyEvent.VK_H:
			h = true;
			break;

		case KeyEvent.VK_N:
			n = true;
			break;
		case KeyEvent.VK_M:
			m = true;
			break;
		case KeyEvent.VK_L:
			l = true;
			break;		
		}
	}

	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch(keyCode){
		case KeyEvent.VK_ENTER:
			enter = false;
			break;
		case KeyEvent.VK_P:
			p = false;
			break;
		case KeyEvent.VK_C:
			c = false;
			break;
		case KeyEvent.VK_H:
			h = false;
			break;

		case KeyEvent.VK_N:
			n = false;
			break;
		case KeyEvent.VK_M:
			m = false;
			break;
		case KeyEvent.VK_L:
			l = false;
			break;
		}		
	}
}