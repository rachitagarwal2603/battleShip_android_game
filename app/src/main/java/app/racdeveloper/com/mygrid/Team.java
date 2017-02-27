package app.racdeveloper.com.mygrid;

public class Team {

	private int gridMap[][];
	
	private int numShipsAlive;
	private int numShipsDead;
	
	public Team() 
	{
		//initialize all values on creation of object
		gridMap = new int[Env.GRID_LENGTH][Env.GRID_WIDTH];
		numShipsAlive = Env.NUM_SHIPS_START;
		numShipsDead = 0;
		
		//init the whole grid with whitespaces
		for(int i=0;i<Env.GRID_LENGTH;i++) 
			for(int j=0;j<Env.GRID_WIDTH;j++)
				gridMap[i][j] = Env.EMPTY_SPACE; 
			
	
		
	}
	
	public void setShipAt(int xMarker, int yMarker, int type) 
	{
		gridMap[xMarker][yMarker] = type;
	}
	
	//Setter methods
	
	void shipDied(int x, int y) { numShipsAlive--; numShipsDead++; gridMap[x][y]= Env.SHIP_DEAD; }
	
	//Getter methods
	public int getShipAtGrid(int x, int y)
	{
		return gridMap[x][y];
	}
	
	public int getAliveShips(){
		return numShipsAlive;
	}
	
	public int getDeadShips(){
		return numShipsDead;
	}
}

