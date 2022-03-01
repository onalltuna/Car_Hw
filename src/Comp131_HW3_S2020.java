/* Student Name: Tuna Onal
 * This program creates a Graphics Program which consists of 
 * 3 lanes, a car and obstacles on the lanes
 * The aim of the game here to move the car on the game map so that
 * can reach the finish line without hitting the obstacles on the lanes.
 * The obstacles are randomly generated on the lanes, and the user can navigate the
 * car using the keyboard. 
 */
import java.awt.Color;
import java.awt.event.KeyEvent;

import acm.graphics.*;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class Comp131_HW3_S2020 extends GraphicsProgram {
	
	public void run() {
		addKeyListeners(); //Do not change or delete this line
		
		//You can call your helper methods here
		//YOUR CODES START HERE
		addRoadBorders();
		addLaneSeparationLines();
		addRoadLines();
		addFinishLine();
		addRandomObstacles();
	
		//YOUR CODE ENDS HERE
		
		car = createCar(); //Do not change or delete this line
		
		//You can add car to map, handle the movement of the map and end of the game here.
		//YOUR CODE STARTS HERE
		add(car, (APPLICATION_WIDTH - car.getWidth())/2, APPLICATION_HEIGHT - 2 * car.getHeight()); 
		mapMover();
		
	}
		//YOUR CODE ENDS HERE
	
	/*
	 * Method which draws the Road Borders on the game map
	 */
	private void addRoadBorders() {
		//YOUR CODE STARTS HERE
		GRect borderR = new GRect(ROAD_BORDER_WIDTH, ROAD_HEIGHT);
		GRect borderL = new GRect(ROAD_BORDER_WIDTH, ROAD_HEIGHT);
		borderR.setFilled(true);
		borderR.setFillColor(Color.white);
		borderL.setFilled(true);
		borderL.setFillColor(Color.white);
		gameMap.add(borderR, APPLICATION_WIDTH-ROAD_BORDER_WIDTH, 0);
		gameMap.add(borderL, 0, 0);
		//YOUR CODE ENDS HERE
	}
	
	/*
	 * Method which draws the lines separating the lanes on the road
	 */
	private void addLaneSeparationLines(){
		//YOUR CODE STARTS HERE
		int t = (ROAD_HEIGHT / (LANE_SEPARATION_HEIGHT + LANE_SEPARATION_SPACE));
		for(int i = 0; i < t; i++) {
			GRect seperator = new GRect(LANE_SEPARATION_WIDTH, LANE_SEPARATION_HEIGHT);
			seperator.setFilled(true);
			seperator.setFillColor(Color.white);
			gameMap.add(seperator, (APPLICATION_WIDTH-2*ROAD_BORDER_WIDTH)/3 + 2*LANE_SEPARATION_WIDTH -LANE_SEPARATION_WIDTH/2 -3, (i+1)*(LANE_SEPARATION_HEIGHT + LANE_SEPARATION_SPACE)-LANE_SEPARATION_HEIGHT);
		}
		for(int i = 0; i < t; i++) {
			GRect seperator = new GRect(LANE_SEPARATION_WIDTH, LANE_SEPARATION_HEIGHT);
			seperator.setFilled(true);
			seperator.setFillColor(Color.white);
			gameMap.add(seperator, 2*(APPLICATION_WIDTH-2*ROAD_BORDER_WIDTH)/3 + 2*LANE_SEPARATION_WIDTH -LANE_SEPARATION_WIDTH/2+2, (i+1)*(LANE_SEPARATION_HEIGHT + LANE_SEPARATION_SPACE)-LANE_SEPARATION_HEIGHT);
		}
		//YOUR CODE ENDS HERE
	}
	
	/*
	 * Methods which draws the lines on the road
	 */
	private void addRoadLines() {
		//YOUR CODE STARTS HERE
		int t = ROAD_HEIGHT / (ROAD_LINE_HEIGHT + ROAD_LINE_SEPARATION);
		for(int i = 0; i < t; i++) {
			GRect roadLine = new GRect(ROAD_LINE_WIDTH, ROAD_LINE_HEIGHT);
			roadLine.setFilled(true);
			roadLine.setFillColor(Color.white);
			gameMap.add(roadLine, ((APPLICATION_WIDTH-2*ROAD_BORDER_WIDTH)/3 + 2*LANE_SEPARATION_WIDTH)/2+ROAD_LINE_WIDTH/4, (i+1)*(ROAD_LINE_HEIGHT + ROAD_LINE_SEPARATION)-ROAD_LINE_SEPARATION);
		}
		for(int i = 0; i < t; i++) {
			GRect roadLine = new GRect(ROAD_LINE_WIDTH, ROAD_LINE_HEIGHT);
			roadLine.setFilled(true);
			roadLine.setFillColor(Color.white);
			gameMap.add(roadLine, (3*(APPLICATION_WIDTH-2*ROAD_BORDER_WIDTH)/3 + 2*LANE_SEPARATION_WIDTH)/2+ROAD_LINE_WIDTH/4, (i+1)*(ROAD_LINE_HEIGHT + ROAD_LINE_SEPARATION)-ROAD_LINE_SEPARATION);
		}
		for(int i = 0; i < t; i++) {
			GRect roadLine = new GRect(ROAD_LINE_WIDTH, ROAD_LINE_HEIGHT);
			roadLine.setFilled(true);
			roadLine.setFillColor(Color.white);
			gameMap.add(roadLine, (5*(APPLICATION_WIDTH-2*ROAD_BORDER_WIDTH)/3 + 2*LANE_SEPARATION_WIDTH)/2+ROAD_LINE_WIDTH/4, (i+1)*(ROAD_LINE_HEIGHT + ROAD_LINE_SEPARATION)-ROAD_LINE_SEPARATION);
		}
		
		//YOUR CODE ENDS HERE	
	}
	/*
	 * Method which draws the finish line
	 */
	private void addFinishLine() {
		//YOUR CODE STARTS HERE
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < FINISH_LINE_SQUARE_NUM; j++) {
				 GRect square = new GRect(FINISH_LINE_SQUARE_SIZE,FINISH_LINE_SQUARE_SIZE);
				gameMap.add(square, (j*FINISH_LINE_SQUARE_SIZE)+ROAD_BORDER_WIDTH,i*FINISH_LINE_SQUARE_SIZE);
				square.setFilled(true);
				square.setFillColor(Color.white);
				if ((i + j) % 2 == 1) {
					square.setFillColor(Color.black);
				}
			    
			}
		}
		
		//YOUR CODE ENDS HERE	
	}
	/*
	 * Method which draws the rectangle obstacles randomly
	 * on the road 
	 */
	private void addRandomObstacles() {
		//YOUR CODE STARTS HERE
		int t = ROAD_HEIGHT/(2*ROAD_LINE_SEPARATION+2*ROAD_LINE_HEIGHT);
		int a = 0;
		int a2 = 0;
		for (int i=0; i<t; i++) {
			if (i == 0) {
			  a = 1;
			  a2 = 0;
			} else {
				a = 0;
				a2 = 1;
			}
			int n = rgen.nextInt(1,3);
			int k = rgen.nextInt(1,3);
			while(n == k) {
				k = rgen.nextInt(1,3);
			}
			Color b = rgen.nextColor();
			Color c = rgen.nextColor();
			
			if (n == 1 && k == 2) {
				GRect obstacle = createObstacle(LANE_WIDTH,OBSTACLE_HEIGHT,b);
				GRect obstacle2 = createObstacle(LANE_WIDTH,OBSTACLE_HEIGHT,c);
				gameMap.addObstacle(obstacle,ROAD_BORDER_WIDTH,i*2*(ROAD_LINE_SEPARATION+ROAD_LINE_HEIGHT) + a*LENGTH_SAFE_ZONE_END + a2*LENGTH_SAFE_ZONE_END);
				gameMap.addObstacle(obstacle2,ROAD_BORDER_WIDTH +LANE_WIDTH+LANE_SEPARATION_WIDTH,i*2*(ROAD_LINE_SEPARATION+ROAD_LINE_HEIGHT) + a*LENGTH_SAFE_ZONE_END + a2*LENGTH_SAFE_ZONE_END);
				
			} else if (n == 1 && k == 3) {
				GRect obstacle = createObstacle(LANE_WIDTH,OBSTACLE_HEIGHT,b);
				GRect obstacle2 = createObstacle(LANE_WIDTH,OBSTACLE_HEIGHT,c);
				gameMap.addObstacle(obstacle,ROAD_BORDER_WIDTH,i*2*(ROAD_LINE_SEPARATION+ROAD_LINE_HEIGHT) + a*LENGTH_SAFE_ZONE_END + a2*LENGTH_SAFE_ZONE_END);
				gameMap.addObstacle(obstacle2,ROAD_BORDER_WIDTH +2*LANE_WIDTH+2*LANE_SEPARATION_WIDTH,i*2*(ROAD_LINE_SEPARATION+ROAD_LINE_HEIGHT) + a*LENGTH_SAFE_ZONE_END + a2*LENGTH_SAFE_ZONE_END);
				
			} else if (n == 2 && k == 1) {
				GRect obstacle = createObstacle(LANE_WIDTH,OBSTACLE_HEIGHT,b);
				GRect obstacle2 = createObstacle(LANE_WIDTH,OBSTACLE_HEIGHT,c);
				gameMap.addObstacle(obstacle,ROAD_BORDER_WIDTH,i*2*(ROAD_LINE_SEPARATION+ROAD_LINE_HEIGHT) + a*LENGTH_SAFE_ZONE_END + a2*LENGTH_SAFE_ZONE_END);
				gameMap.addObstacle(obstacle2,ROAD_BORDER_WIDTH +LANE_WIDTH+LANE_SEPARATION_WIDTH,i*2*(ROAD_LINE_SEPARATION+ROAD_LINE_HEIGHT) + a*LENGTH_SAFE_ZONE_END + a2*LENGTH_SAFE_ZONE_END);
				
			} else if (n == 2 && k == 3) {
				GRect obstacle = createObstacle(LANE_WIDTH,OBSTACLE_HEIGHT,b);
				GRect obstacle2 = createObstacle(LANE_WIDTH,OBSTACLE_HEIGHT,c);
				gameMap.addObstacle(obstacle,ROAD_BORDER_WIDTH + 2*LANE_WIDTH + 2*LANE_SEPARATION_WIDTH ,i*2*(ROAD_LINE_SEPARATION+ROAD_LINE_HEIGHT) + a*LENGTH_SAFE_ZONE_END + a2*LENGTH_SAFE_ZONE_END);
				gameMap.addObstacle(obstacle2,ROAD_BORDER_WIDTH +LANE_WIDTH+LANE_SEPARATION_WIDTH,i*2*(ROAD_LINE_SEPARATION+ROAD_LINE_HEIGHT) + a*LENGTH_SAFE_ZONE_END + a2*LENGTH_SAFE_ZONE_END);
				
			} else if (n == 3 && k == 1) {
				GRect obstacle = createObstacle(LANE_WIDTH,OBSTACLE_HEIGHT,b);
				GRect obstacle2 = createObstacle(LANE_WIDTH,OBSTACLE_HEIGHT,c);
				gameMap.addObstacle(obstacle,ROAD_BORDER_WIDTH,i*2*(ROAD_LINE_SEPARATION+ROAD_LINE_HEIGHT) + a*LENGTH_SAFE_ZONE_END + a2*LENGTH_SAFE_ZONE_END);
				gameMap.addObstacle(obstacle2,ROAD_BORDER_WIDTH +2*LANE_WIDTH+2*LANE_SEPARATION_WIDTH,i*2*(ROAD_LINE_SEPARATION+ROAD_LINE_HEIGHT) + a*LENGTH_SAFE_ZONE_END + a2*LENGTH_SAFE_ZONE_END);
				

			} else if (n == 3 && k == 2) {
				 GRect obstacle = createObstacle(LANE_WIDTH,OBSTACLE_HEIGHT,b);
				 GRect obstacle2 = createObstacle(LANE_WIDTH,OBSTACLE_HEIGHT,c);
				gameMap.addObstacle(obstacle,ROAD_BORDER_WIDTH + 2*LANE_WIDTH + 2*LANE_SEPARATION_WIDTH ,i*2*(ROAD_LINE_SEPARATION+ROAD_LINE_HEIGHT) + a*LENGTH_SAFE_ZONE_END + a2*LENGTH_SAFE_ZONE_END);
				gameMap.addObstacle(obstacle2,ROAD_BORDER_WIDTH +LANE_WIDTH+LANE_SEPARATION_WIDTH,i*2*(ROAD_LINE_SEPARATION+ROAD_LINE_HEIGHT) + a*LENGTH_SAFE_ZONE_END + a2*LENGTH_SAFE_ZONE_END);
					
			}
		}
		//YOUR CODE ENDS HERE
	}
	
	/*
	 * Method which draws a single rectangle simulating an obstacles 
	 * on the road
	 */
	
	private GRect createObstacle(int width, int height, Color fillColor) {
		GRect obstacle = null;
		
		//YOUR CODE STARTS HERE
		obstacle = new GRect(LANE_WIDTH, OBSTACLE_HEIGHT);
		obstacle.setFilled(true);
		obstacle.setFillColor(fillColor);
		return obstacle;
	}
		
		//YOUR CODE ENDS HERE
	/**
	 * Changing this method is bonus and left to you, you can skip it or work for the bonus points
	 * We strongly encourage you to do everything else first and work on this afterwards
	 * Method which draws a GCompound simulating the car 
	 */
	private GCompound createCar() {
		GCompound car = new GCompound();
		GRect a = new GRect(40 ,80);
		a.setFilled(true);
		a.setFillColor(Color.YELLOW);
		car.add(a);
		return car;
	}
	/*
	 * Method which is called when the games has finished wither by successfully
	 * traveling the road without hitting any obstacles or when completed the games when
	 * the car hits an obstacle.
	 */
	
	private void finishGame(){
		//YOUR CODE STARTS HERE
		if (s == 1) {
			removeAll();
			GLabel srry = new GLabel("Sorry, you lost the game :(");
			add(srry, (getWidth() - srry.getWidth())/2, (getHeight() - srry.getHeight())/2);
			
		} else if (s == 2) {
			removeAll();
			GLabel cong = new GLabel("Congratulations!!!");
			add(cong, (getWidth() - cong.getWidth())/2, (getHeight() - cong.getHeight())/2);
		}
	}
			
		//YOUR CODE ENDS HERE
		
	// Feel free to write any helpers you want to here
	// YOUR CODE STARTS HERE
int s;

	// This method moves the map and decides if there is a collision
	private void mapMover() {
		 while (true) {
				gameMap.move(0, dy);
				pause(PAUSE_TIME);
				double x = car.getX();
				double y = car.getY();
				double z = gameMap.getY();
				double z2 = z + 6476.0;
				
				GPoint localPoint = gameMap.getLocalPoint(x,y);
				GPoint localPoint2 = gameMap.getLocalPoint(x + 40,y + 80);
				GPoint localPoint3 = gameMap.getLocalPoint(x + 40,y);
				GPoint localPoint4 = gameMap.getLocalPoint(x,y + 80);
				GPoint localPoint5 = gameMap.getLocalPoint(x,y+30);
				GPoint localPoint6 = gameMap.getLocalPoint(x+40,y+30);
				GPoint localPoint7 = gameMap.getLocalPoint(x,y+60);
				GPoint localPoint8 = gameMap.getLocalPoint(x+40,y+60);
				
				GObject h = gameMap.getElementAt(localPoint);
				GObject h2 = gameMap.getElementAt(localPoint2);
				GObject h3 = gameMap.getElementAt(localPoint3);
				GObject h4 = gameMap.getElementAt(localPoint4);
				GObject h5 = gameMap.getElementAt(localPoint5);
				GObject h6 = gameMap.getElementAt(localPoint6);
				GObject h7 = gameMap.getElementAt(localPoint7);
				GObject h8 = gameMap.getElementAt(localPoint8);
				
				if (gameMap.getAllObstacles().contains(h)||gameMap.getAllObstacles().contains(h2)||gameMap.getAllObstacles().contains(h3)||gameMap.getAllObstacles().contains(h4)||gameMap.getAllObstacles().contains(h5)||gameMap.getAllObstacles().contains(h6)||gameMap.getAllObstacles().contains(h7)||gameMap.getAllObstacles().contains(h8)) {
					s = 1;
					finishGame();
					break;
							
				} else if (z2 + 2.0*37.0 >= 7114.0) {
					s = 2;
					finishGame();
					break;
				}
		 }
		
	}
	// YOUR CODE ENDS HERE
	
	
	//////////// DO NOT CHANGE ANYTHING BELOW THIS LINE!!! ////////////////////
	// Declaring the class constant variables
	// These constants are explained in the pdf
	private static final int LANE_WIDTH = 360;
	private static final int ROAD_HEIGHT = 7200;
	private static final int ROAD_BORDER_WIDTH = 30;
	private static final int LANE_SEPARATION_WIDTH = 15;
	private static final int LANE_SEPARATION_HEIGHT = 40;
	private static final int LANE_SEPARATION_SPACE = 20;
	private static final int ROAD_LINE_WIDTH = 20;
	private static final int ROAD_LINE_HEIGHT = 180;
	private static final int ROAD_LINE_SEPARATION = 100;
	private static final int OBSTACLE_HEIGHT = 30;
	private static final int LENGTH_SAFE_ZONE_START = 360;
	private static final int LENGTH_SAFE_ZONE_END = 3 * ROAD_LINE_SEPARATION / 2 + ROAD_LINE_HEIGHT - OBSTACLE_HEIGHT/2;

	private static final int FINISH_LINE_SQUARE_NUM = 30;
	private static final int FINISH_LINE_SQUARE_SIZE = (3 * LANE_WIDTH + 2 * LANE_SEPARATION_WIDTH) / FINISH_LINE_SQUARE_NUM;
	
	private static final int APPLICATION_HEIGHT = ROAD_HEIGHT / 10;
	private static final int APPLICATION_WIDTH = 3 * LANE_WIDTH + 2 * ROAD_BORDER_WIDTH + 2 * LANE_SEPARATION_WIDTH;

	private static final int PAUSE_TIME = 20;
	
	/* Private instance variables */
	private final RandomGenerator rgen = RandomGenerator.getInstance();
	
	// declaring the global variables of the class
	int dx = 12;	
	int dy = 4; 
	private GameMap gameMap;
	private GCompound car;
	/*
	 * Method use to initialize the GameMap class 
	 * (non-Javadoc)
	 * @see acm.program.GraphicsProgram#init()
	 */
	public void init() {
		setProgramSize();
		gameMap = new GameMap(APPLICATION_WIDTH, ROAD_HEIGHT); 
		add(gameMap, 0 , - 0.9 * ROAD_HEIGHT );
		setBackground(Color.GREEN);
	}
	/*
	 * Method for adding listeners to the Key Event for left and right 
	 * navigation of the car
	 * (non-Javadoc)
	 * @see acm.program.Program#keyPressed(java.awt.event.KeyEvent)
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(car.getX() > gameMap.getX())
			gameMap.move(dx, 0);
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(car.getX() + car.getWidth() < gameMap.getX() + gameMap.getWidth()) {
				gameMap.move(-dx, 0);	
			}
		}
	}
	/*
	 * Method for setting the size of the Graphics Program
	 */
	private void setProgramSize() {
		this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	}
}
