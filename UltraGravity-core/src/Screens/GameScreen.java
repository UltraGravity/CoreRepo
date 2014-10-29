package Screens;

import Loaders.GameAssets;
import Objects.GridImage;
import Objects.Item;
import Objects.World;

import com.APAAAEAIA.UltraGravity.MyGame;

public class GameScreen extends GenericScreen
{
  String levelString;

	World world; 
	Item[][] stuff;
	
	public GameScreen(MyGame myGame) 
	{
		super(myGame);
	}


	private enum GameState {PLAY, PAUSE, GAMEOVER};
	public GameState gameState = GameState.PLAY;
	
	public void render(float delta) 
	{
    for (int x = 0; x < world.getXSize(); x++) {
      for (int y = 0; y < world.getYSize(); y++) {
        Item nextItem = stuff[x][y];
        if(nextItem != null) {
          batch.draw(nextItem.getTexture(), nextItem.getX(), nextItem.getY());
        }
      }
    }
	}

	public void resize(int width, int height) 
	{
		
	}

	public void show()
	{
	    System.out.println("beginning");
	    world = new World(6,12);
	    System.out.println("World is Set");
	    Item[][] stuff = world.getWorld();
	}

	public void hide() 
	{
		
	}

	public void pause() 
	{
		
	}

	public void resume() 
	{
		
	}


	public void dispose() 
	{
		/*
		 * It is very important that everything created is disposed of properly when it is no longer needed.
		 * I find it best to explicitly set everything equal to null so the garbage collector knows it can
		 * remove the stuff from memory. 
		 * 
		 * Calling super.dispose() will get rid of the built in variables, but it is important that anything
		 * that is uniquely created in this class be disposed.
		 */
	
		super.dispose();
	}
	
	
	
	//TODO use below to set the level based on the string from either a file or the level editor
  public void setLevel(String level) {
    levelString = level;
  }
  
  public void setWorld(String level) {
    int i =0;  
    String xSizeString = "";
    String ySizeString = "";
    int index = 0;
    
    while(!String.valueOf(level.charAt(i)).equals(",")) {
      xSizeString = xSizeString + String.valueOf(level.charAt(i));
      i++;        
      }
    int x = Integer.parseInt(xSizeString);
    System.out.println(x);
    
    i = xSizeString.length() + 1;
    while(!String.valueOf(level.charAt(i)).equals(":")) {
      ySizeString = ySizeString + String.valueOf(level.charAt(i));
      i++;        
      }
    int y = Integer.parseInt(ySizeString);
    System.out.println(y);
    
    world.setSize(x, y);
    System.out.println(world.getXSize());
    System.out.println(world.getYSize());
    System.out.println("New grid created " + (x*y));
    
    i = xSizeString.length() + ySizeString.length() + 2;
    System.out.println(index);
    
      while(y > 0) {
        while(x > 0) {   //The x and y loops are here to help place in a grid
          int nextInt = level.charAt(i);
          if(nextInt == '0') {
            System.out.print(" " + 0 + " ");
            //add blank space
          }
          if(nextInt == '1') {
            System.out.print(" " + 1 + " ");
            world.addItem(1, x * 100, y * 100);
            //add ground block
          }
          if(nextInt == '2') {
            System.out.print(" " + 2 + " ");
            world.addItem(2, x * 100, y * 100);
            //add crate
          }
          if(nextInt == '3') {
            System.out.print(" " + 3 + " ");
            world.addItem(3, x * 100, y * 100);
            //add character
          }
          index++;
          i++;
          x--;
        }
        System.out.println();
        x = world.getXSize();
        y--;
      }
      
  }

}
