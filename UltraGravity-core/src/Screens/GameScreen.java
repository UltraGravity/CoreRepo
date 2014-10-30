package Screens;

import Loaders.GameAssets;
import Objects.GridImage;
import Objects.Item;
import Objects.ThePlane;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class GameScreen extends GenericScreen
{
  String levelString;

	World world; 
	LevelEditorScreen levelEditor;
	Item[][] stuff;
	ThePlane thePlane;
	
	public GameScreen(MyGame myGame) 
	{
		super(myGame);
	}


	private enum GameState {PLAY, PAUSE, GAMEOVER};
	public GameState gameState = GameState.PLAY;
	
	public void render(float delta) 
	{
	  
	}

	public void resize(int width, int height) 
	{
		
	}

	public void show()
	{
	    System.out.println("beginning");
	    setWorld(getLevelString(true));
	    world = new World(new Vector2(0,-10),true);
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
  
  public String getLevelString(Boolean editor)
  {
    String level = levelEditor.getLevelString();
    setWorld(level);
    System.out.println(level);
    return level;
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
    
    thePlane.setSize(x, y);
    System.out.println(thePlane.getXSize());
    System.out.println(thePlane.getYSize());
    System.out.println("New grid created " + (x*y));
    
    i = xSizeString.length() + ySizeString.length() + 2;
    System.out.println(index);
    
    thePlane.setBounds(x * 100, y * 100);
    
      while(y > 0) {
        while(x > 0) {   //The x and y loops are here to help place in a grid
          int nextInt = level.charAt(i);
          if(nextInt == '0') {
            System.out.print(" " + 0 + " ");
            //add blank space
          }
          if(nextInt == '1') {
            System.out.print(" " + 1 + " ");
            thePlane.addItem(1, x * 100, y * 100);
            //add ground block
          }
          if(nextInt == '2') {
            System.out.print(" " + 2 + " ");
            thePlane.addItem(2, x * 100, y * 100);
            //add crate
          }
          if(nextInt == '3') {
            System.out.print(" " + 3 + " ");
            thePlane.addItem(3, x * 100, y * 100);
            //add character
          }
          index++;
          i++;
          x--;
        }
        System.out.println();
        x = thePlane.getXSize();
        y--;
      }
      
  }

}
