
import java.awt.Graphics; 
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*; 

public class GameOfLife
{ 

  public static void main( String[] args ) 
  {
    Controller c = new Controller();
    c.run();
  }
}