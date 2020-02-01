import java.util.HashMap;
import java.util.LinkedList;


public class Universe {
	public Cell cells[][];
	LinkedList<Cell> livingCells;
	int size; 					// invariant size > 0
	int age;						// invariant age > 0
	HashMap<Cell, LinkedList<Cell>> surrounding;
	LinkedList<Cell> toDie;
	LinkedList<Cell> toLive;
	
	
	public Universe(int size)
	{
		this.size = size;
		livingCells = new LinkedList<Cell>();
		this.cells = new Cell[size][size];
		surrounding = new HashMap<Cell, LinkedList<Cell>>();//(LinkedList<Cell>[]) new LinkedList[livingCells.size()];
		
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
			{
				this.cells[i][j] = new Cell(new Point(i,j), false);
				surrounding.put(this.cells[i][j], new LinkedList<Cell>());
			}
	}
	


	public Universe(int size, LinkedList<Cell> livingCells)
	{
		this.livingCells = livingCells;
		this.size = size;
	}
	
	public void reset()
	{
		livingCells.clear();

		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				this.cells[i][j].isAlive = false;
		this.age = 0;
	}
	
	public void iterate()
	{
		this.age++;
		toDie = new LinkedList<Cell>(); 
		toLive = new LinkedList<Cell>();
		
//		for(Cell c : livingCells)
//			c.printStatus();

		
		for (Cell c : livingCells) {
//			System.out.println(c.pos.x+" "+c.pos.y);
			if (!checkLivingCellRules(surrounding.get(c)))
				toDie.add(c);
			checkDeadCellRule(surrounding.get(c));
		}

		//System.out.println("toDie: ");
		for (Cell c : toDie) {
			//c.printStatus();
			editCell(c.pos.x, c.pos.y, false);
		}
		//System.out.println("toLive: ");
		for(Cell c : toLive) {
			//c.printStatus();
			editCell(c.pos.x, c.pos.y, true);
		}
			
	}
	
	private void addSurroundingCells(LinkedList<Cell> surrounding, Point pos)
	{
		int n = this.size;
		
		int x = pos.x;
		int y = pos.y;
		int mx = pos.x-1;
		int tx = mx < 0 ? n-1 : mx;
		int my = pos.y-1;
		int ty = my < 0 ? n-1 : my;
		int px = (pos.x+1) %n;
		int py = (pos.y+1) %n;
		
		cells[px][y].numNeighbours++;
		cells[x][py].numNeighbours++;
		cells[tx][y].numNeighbours++;
		cells[x][ty].numNeighbours++;
		cells[px][ty].numNeighbours++;
		cells[tx][py].numNeighbours++;
		cells[px][py].numNeighbours++;
		cells[tx][ty].numNeighbours++;
		
		
		surrounding.add(cells[px][y]);
		surrounding.add(cells[x][py]);
		surrounding.add(cells[tx][y]);
		surrounding.add(cells[x][ty]);
		surrounding.add(cells[px][ty]);
		surrounding.add(cells[tx][py]);
		surrounding.add(cells[px][py]);
		surrounding.add(cells[tx][ty]);
		
//		System.out.println("Surrounding");
//		for (Cell c : surrounding) {
//			c.printStatus();
//		}
	}
	private boolean checkLivingCellRules(LinkedList<Cell> surrounding)
	{
		int n = 0;
		for (Cell c : surrounding) {
			n = c.isAlive ? n+1:n;
		}
	//	System.out.println("checkLivingCellRules:"+n);
		if (n == 3 || n == 2) 
			return true;
		else
			return false;
	}
	
	private void checkDeadCellRule(LinkedList<Cell> surrounding)
	{
		for (Cell c : surrounding) {
			if(c.numNeighbours == 3)
				toLive.add(new Cell(c.pos, true));
		}
	}
	public LinkedList<Cell> getLivingCells()
	{
		return livingCells;
	}

	public void editCell(int x, int y, boolean isAlive)
	{
		if (isAlive)
		{
			if(this.cells[x][y].isAlive) // O(1)
				return;
			livingCells.addLast(this.cells[x][y]); // O(1)
			surrounding.put(this.cells[x][y], new LinkedList<Cell>()); // O(1)
			addSurroundingCells(surrounding.get(this.cells[x][y]), this.cells[x][y].pos); // O(1)
		}
			
		else
		{
			LinkedList<Cell> toRemove = new LinkedList<Cell>();
			if(this.cells[x][y].isAlive) {// O(1)
				for (Cell d : surrounding.get(this.cells[x][y])) // O(1)
						d.numNeighbours--;
				livingCells.remove(this.cells[x][y]); // O(n)
			}
		}
		this.cells[x][y].isAlive = isAlive;
	}
}
