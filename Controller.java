import java.awt.Dimension;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Controller  implements KeyListener, MouseMotionListener{
	Universe u;
	JFrame f;
	PaintScreen p;
	int size = 500;
	int scaleFactor = 2;
	
	public void run()
	{
		int screenSize = size * scaleFactor;
	    u = new Universe(size);
	    	    
	    p = new PaintScreen(scaleFactor);
	    p.setPreferredSize(new Dimension(screenSize+5, screenSize+5));
	    f = new JFrame(); 
	    f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); 
	     
	    f.setResizable(true);
	    f.add( p ); 
	    f.addKeyListener(this);
	    p.addMouseMotionListener(this);
	    f.setLocationRelativeTo(null);
	    f.pack();
	    f.setVisible( true ); 
	    f.setAlwaysOnTop(true);
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == '+')
		{
			u.iterate();
			LinkedList<Cell> ll = u.getLivingCells();
			LinkedList<Point> lp = new LinkedList<Point>();
			for (Cell c : ll) {
				lp.add(c.pos);
			}
			
			p.setCoordinates(lp);
			p.setInformationText(new Integer(this.u.age).toString());
			f.repaint();
			
		}
		else if (e.getKeyChar() == '-')
			System.exit(0);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println(e.getX()+" "+e.getY());
		//System.out.println(e.getX()/scaleFactor+" "+e.getY()/scaleFactor);

		if(SwingUtilities.isLeftMouseButton(e))
			u.editCell(e.getX()/scaleFactor, e.getY()/scaleFactor, true);
		else
			u.editCell(e.getX()/scaleFactor, e.getY()/scaleFactor, false);
		
		LinkedList<Cell> ll = u.getLivingCells();
		LinkedList<Point> lp = new LinkedList<Point>();

		for (Cell c : ll)
		{
			lp.add(c.pos);
		}

		p.setCoordinates(lp);
		p.setInformationText(new Integer(this.u.age).toString());
		f.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	} 
}

