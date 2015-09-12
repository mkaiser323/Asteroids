
package asteroids;

import java.awt.*;
import java.util.ArrayList;


public class Asteroid extends Polygon {

	public Asteroid(Point[] points, Point location, double rotation) {
		super(points, location, rotation);
	}
	
	//paint method
	public void paint(Graphics brush, int i){
        brush.setColor(Asteroids.asteroidColor);
        brush.fillPolygon(Asteroid.getXPoints(getPoints()), Asteroid.getYPoints(getPoints()), getPoints().length);
        
        brush.setColor(Asteroids.asteroidOutlineColor);
        brush.drawPolygon(Asteroid.getXPoints(getPoints()), Asteroid.getYPoints(getPoints()), getPoints().length);

        infiniteScreen();
	}
	


	//move method
	public static final double INITIAL_SPEED = 2;
	public static double speed = INITIAL_SPEED;
	double standardOffset = .5;
	public void move(int i){

		switch(i%7){
		case 0://mostly horizontal 
			position.x += standardOffset * 3;
			position.y += speed + .2;
			rotation += speed*.5;
			break;
		case 1://mostly vertical
			position.x += standardOffset *2;
			position.y += speed + .4;
			rotation += speed;
			break;
		case 2://south east
			position.x += standardOffset;
			position.y += speed + .4;
			rotation -= speed*2;
			break;
		case 3://north east
			position.x -= standardOffset;
			position.y += speed;
			break;
		case 4://mostly horizontal
			position.x -= standardOffset;
			position.y += speed;
			break;
		case 5://mostly vertical
			position.x -= standardOffset * -5;
			position.y += speed;
			rotation -= speed;
			break;
		case 6://south east
			position.x -= standardOffset;
			position.y += speed;
			rotation += speed;
			break;
		case 7://north east
			position.x -= standardOffset;
			position.y += speed;
			break;
		}
		/**
		 * 10*Math.sin(Math.toRadians(count * 10));//
		 * 10*Math.cos(Math.toRadians(count * 10));//
		 */
	}
	
	

	public void hover(int i){
		switch(i){
		case 0://horizontal
			position.x += 1 + .1 * i;
			position.y += .05 * (i + 1);
			break;
		case 1://southeast
			position.x += .5 + .1 * i;
			position.y += .4 + .1 * i;
			break;
		case 2://northwest
			position.x -= .1 + .1 * i;
			position.y -= .4 + .1 * i;
			break;
		case 3://not used
			position.x += .5 + .1 * i;
			position.y -= .2 + .1 * i;
			break;
		}
	}
}
