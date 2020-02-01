 import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics; 
import java.awt.Graphics2D;
import java.util.LinkedList;

import javax.swing.*; 
 
 public class PaintScreen extends JPanel 
 { 
	private LinkedList<Point> coordinates;
	private String informationText = "0";
	public int squareSize = 0;
	public int factor = 0;
	Font font;
	
	public PaintScreen(int scaleFactor)
	{
	    font = new Font("Arial", Font.PLAIN, 20);
		coordinates = new LinkedList<Point>();
	    this.squareSize = scaleFactor;
	    this.factor = scaleFactor;
	}
	
    protected void paintComponent( Graphics g ) 
    { 
        super.paintComponent( g );
//        paintComponent(g, )
    	  Graphics2D g2 = (Graphics2D) g; 
      g2.setColor(Color.BLUE);
    	System.out.println(coordinates.size());
      for (Point p : coordinates) {
          g2.drawRect(p.x*factor, p.y*factor, squareSize, squareSize);
      }
      g2.setFont(font);
      g2.setColor(Color.RED);
      g2.drawString("age: "+informationText, 30, 30);
    }
    
    public void setCoordinates(LinkedList<Point> coordinates)
    {
    	this.coordinates = coordinates;
    }
    
    public void setInformationText(String text)
    {
    	this.informationText = text;
    }
}