public class Cell {
	public Point pos;
	public boolean isAlive;
	public int numNeighbours;
	
	public Cell(Point pos, boolean isAlive)
	{
		this.isAlive = isAlive;
		this.pos = pos;
	}
	
	public void printStatus()
	{
		System.out.println(pos.x+ " "+pos.y+" "+isAlive+" "+numNeighbours);
	}
}
