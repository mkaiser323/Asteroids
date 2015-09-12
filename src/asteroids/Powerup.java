package asteroids;

import java.awt.*;
import java.util.ArrayList;


public class Powerup extends Polygon {

	public Powerup(Point[] points, Point location, double rotation) {
		super(points, location, rotation);
	}
	
	//paint method
	public void paint(Graphics brush){
        brush.fillPolygon(Asteroid.getXPoints(getPoints()), Asteroid.getYPoints(getPoints()), getPoints().length);
        brush.drawOval((int)position.x-10, (int)position.y-10, 26, 26);
//        brush.drawPolygon(Asteroid.getXPoints(getPoints()), Asteroid.getYPoints(getPoints()), getPoints().length);

        /**
         * optional text on asteroids*/
//        brush.setFont(new Font("Arial", Font.BOLD, 16));
//        brush.setColor(Color.black);
//        brush.drawString(i + "", (int)position.x + 5, (int)position.y + 15);
//        brush.setColor(Color.gray);
        infiniteScreen();
	}
	


	//move method
	public static double frequency = 2;
	public static double Altitude = 2;
	double standardOffset = .15;
	int timer = 0;
	public void move(int i){
		timer +=5;
		switch(i){
		case 0://sine wave
			position.x += frequency;
			position.y += Altitude *Math.sin(Math.toRadians(timer));
			break;
//		case 1://mostly vertical
//			position.x += standardOffset * (i + 1);
//			position.y += speed + .1 * i;
//			break;
//		case 2://south east
//			position.x += standardOffset + .1 * i;
//			position.y += speed + .1 * i;
//			break;
//		case 3://north east
//			position.x += standardOffset + .1 * i;
//			position.y += speed + .1 * i;
//			break;
//		case 4://mostly horizontal
//			position.x -= standardOffset + .1 * i;
//			position.y += speed + .5* i;
//			break;
//		case 5://mostly vertical
//			position.x -= standardOffset * (i + 1);
//			position.y += speed + .1 * i;
//			break;
//		case 6://south east
//			position.x -= standardOffset + .1 * i;
//			position.y += speed + .1 * i;
//			break;
//		case 7://north east
//			position.x -= standardOffset + .1 * i;
//			position.y += speed + .1 * i;
//			break;
		}
//		rotation += i * speed;
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
