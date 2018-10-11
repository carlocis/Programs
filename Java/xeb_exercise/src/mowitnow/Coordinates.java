package mowitnow;

public class Coordinates {

	private int x_position;
	private int y_position;
	private char orientation;
	
	public Coordinates(int x_position, int y_position, char orientation) {
		this.x_position = x_position;
		this.y_position = y_position;
		this.orientation = orientation;
	}
	
	// I created another constructor so that I can define coordinates which do not have
	// orientation (like the right top corner)
	public Coordinates(int x_position, int y_position) {
		this.x_position = x_position;
		this.y_position = y_position;
	}
	
	public int getX_position() {
		return x_position;
	}
	public int getY_position() {
		return y_position;
	}
	public char getOrientation() {
		return orientation;
	}
	public void setX_position(int x_position) {
		this.x_position = x_position;
	}
	public void setY_position(int y_position) {
		this.y_position = y_position;
	}
	public void setOrientation(char orientation) {
		this.orientation = orientation;
	}
	
	
	
}
