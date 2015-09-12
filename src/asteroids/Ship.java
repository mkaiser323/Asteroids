package asteroids;

import java.awt.*;
import java.awt.event.*;
import java.awt.KeyEventDispatcher;

public class Ship extends Polygon implements KeyListener {
	boolean upKey, downKey, leftKey, rightKey, spaceBar, shift;
	int lastCrash;
	public static final double ACCELERATION = .3;
	public static final int INITIAL_SPEED = 7;
	double speed = INITIAL_SPEED; 
	double slowingFactor = .5;

	
	//CONSTRUCTOR
	public Ship(Point[] shipPoints, Point shipLocation, double rotation) {
		super(shipPoints, shipLocation, rotation);
	}
	
	
	//KEY_LISTENER METHODS
	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		switch (keyCode){
		case KeyEvent.VK_UP:
			upKey = true;
			break;
		case KeyEvent.VK_DOWN:
			downKey = true;
			break;
		case KeyEvent.VK_LEFT:
			leftKey = true;
			break;
		case KeyEvent.VK_RIGHT:
			rightKey = true;
			break;
		case KeyEvent.VK_SHIFT:
			shift = true;
			rotation = 270;
			break;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode){
		case KeyEvent.VK_UP:
			speed = INITIAL_SPEED;
			upKey = false;
			break;
		case KeyEvent.VK_DOWN:
			downKey = false;
			break;
		case KeyEvent.VK_LEFT:
			leftKey = false;
			break;
		case KeyEvent.VK_RIGHT:
			rightKey = false;
			break;
		case KeyEvent.VK_SHIFT:
			shift = false;
			break;
		}
	}
	
	public void keyTyped(KeyEvent e) {}//not used
	
	//PAINT METHOD
	public void paint(Graphics brush, boolean beingHit, int timer, double lives, boolean myShip){
		
        if(beingHit && myShip){
            brush.setColor(Asteroids.shipHitColor);//flicker effect
        } 

        brush.setColor(Asteroids.shipColor);
        brush.fillPolygon(Ship.getXPoints(getPoints()), Ship.getYPoints(getPoints()), getPoints().length);
        checkBounds();
        //lastCrash = damage;

	}
	
	//move methods used for demo
	public void demo(String demo){
		if(demo.equals("clockWise")){
			this.rotation += 5;
		}
		if(demo.equals("counterClockWise")){
			this.rotation -= 5;
		}

		if(demo.equals("forward")){
			this.position.y += speed * Math.sin(Math.toRadians(rotation));
			this.position.x += speed * Math.cos(Math.toRadians(rotation));			
		}
		
		if(demo.equals("left")){
			position.x += 5;
		}
		if(demo.equals("right")){
			position.x -= 5;
		}
	}
	//MOVE METHOD 
	public void move(){
		if(upKey){
			this.position.y += speed * Math.sin(Math.toRadians(rotation));
			this.position.x += speed * Math.cos(Math.toRadians(rotation));	
			speed += ACCELERATION;//acceleration effect
		}
		if(downKey){
			this.position.y -= 10 * Math.sin(Math.toRadians(rotation));
			this.position.x -= 10 * Math.cos(Math.toRadians(rotation));	
		}
		if(leftKey){
			if(shift){
				this.position.y += speed * Math.cos(Math.toRadians(rotation));
				this.position.x += speed * Math.sin(Math.toRadians(rotation));	
			} else {
				rotation -= 10;
			}

		}
		if(rightKey){
			if(shift){
				this.position.y -= speed * Math.cos(Math.toRadians(rotation));
				this.position.x -= speed * Math.sin(Math.toRadians(rotation));
			} else {
				rotation += 10;
			}
		}
	}
	
	  public void checkBounds(){
		  
	      if(position.y < 0){
	        	position.y = Game.height;
	        }
	        if(position.y > Game.height){
	        	position.y = 0;
	        }
	        if(position.x < 0){
	        	position.x = Game.width;
	        }
	        if(position.x > Game.width){
	        	position.x = 0;
	        }
		  
//	      if(position.y < 0 + Asteroids.SHIP_SIZE/2){//top border
//	      	position.y = 0 + Asteroids.SHIP_SIZE/2;//Game.height - Asteroids.SHIP_SIZE;
//	      }
//	      if(position.y >= Game.height - 3*Asteroids.SHIP_SIZE/2){//bottom border
//	      	position.y = Game.height - 3*Asteroids.SHIP_SIZE/2;
//	      }
//	      if(position.x <= 0){//left border
//	      	position.x = Game.width - Asteroids.SHIP_SIZE;
//	      }
//	      if(position.x > Game.width){//right border
//	      	position.x = 0;
//	      }
	  }


}