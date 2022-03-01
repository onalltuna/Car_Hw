//DO NOT CHANGE OR DELETE ANYTHING HERE
//YOU DO NOT HAVE TO UNDERSTAND THIS PART
//Turn back to Comp131_HW3_S2020.java file and write your code there
/*
 * This class extends the GCompound class in order to create
 * the map for the game. It consists of a list of GObjects
 * which are considered the obstacles in the game.
 * 
 */
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import acm.graphics.*;


public class GameMap extends GCompound{
	
	private double width = 1080;
	private double height = 10800;
	private List<GObject> obstacles;
	/*
	 * Constructor of the class, the class is created given the width
	 * and height of the game map
	 */
	public GameMap(int width, int height) {
		this.width = width;
		this.height = height;
		addBackground();
		obstacles = new ArrayList<>();
	}
	/*
	 * Method for adding the background of the map.
	 * It consists of a GREct 	
	 */
	private void addBackground() {
		GRect background = new GRect(width, height);
		background.setFilled(true);
		background.setFillColor(Color.GRAY);
		this.add(background);
	}
	
	/*
	 * Method for adding GObjects to the lists of obstacles in the map
	 */
	public void addObstacle(GObject obstacle, double x, double y) {
		this.add(obstacle, x, y);
		obstacles.add(obstacle);
	}
	
	/*
	 * Method which  returns the list of all the obstacles in the map
	 */
	public List<GObject> getAllObstacles(){
		return this.obstacles;
	}
	
	/*
	 * (non-Javadoc)
	 * @see acm.graphics.GObject#getHeight()
	 */
	@Override
	public double getHeight() {
		return height;
	}

	/*
	 * (non-Javadoc)
	 * @see acm.graphics.GObject#getSize()
	 */
	@Override
	public GDimension getSize() {
		return new GDimension(width, height);
	}
	/*
	 * (non-Javadoc)
	 * @see acm.graphics.GObject#getWidth()
	 */
	@Override
	public double getWidth() {
		return width;
	}	
}
