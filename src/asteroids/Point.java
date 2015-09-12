package asteroids;

/*
CLASS: Point
DESCRIPTION: Ah, if only real-life classes were this straight-forward. We'll
             use 'Point' throughout the program to store and access 
             coordinates.
Original code by Dan Leyzberg and Art Simon
*/

class Point implements Cloneable {
  double x,y;
  public Point(double inX, double inY) { 
	  x = inX; 
	  y = inY; 
	  
  }
  
  public Point clone() {
	  return new Point(x, y);
  }
  
  public Point adjustPoint(int x, int y){
	  return new Point(this.x + x, this.y + y);
	  
  }
  
}