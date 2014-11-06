package Screens;

import Loaders.GameAssets;
import Objects.GridImage;
import Objects.Item;
import Objects.ThePlane;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class GameScreen extends GenericScreen
{
  String levelString;

	World world; 
	LevelEditorScreen levelEditor;
	Item[][] stuff;
	ThePlane thePlane;
	
	public GameScreen(MyGame myGame, String levelString) 
	{
		super(myGame);
		this.levelString = levelString;
	}


	private enum GameState {PLAY, PAUSE, GAMEOVER};
	public GameState gameState = GameState.PLAY;
	
	public void render(float delta) 
	{
	  Gdx.gl.glClearColor(0, .25f, .25f, 1);
    Gdx.gl.glClearColor(.65f, .65f, .65f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	public void resize(int width, int height) 
	{
		
	}

	public void show()
	{
	    System.out.println("beginning");
	    fillThePlane(levelString);
//	    fillWorld(levelString);
	    System.out.println("Plane Filled");
	    thePlane.fillWorld();
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
	
	private void fillThePlane(String gridString){
	  this.levelString = gridString;
	  int i = 0;
    String xSizeString = "";
    String ySizeString = "";
    while (!String.valueOf(levelString.charAt(i)).equals(",")) {
      xSizeString = xSizeString + String.valueOf(levelString.charAt(i));
      i++;
    }
    int x = Integer.parseInt(xSizeString);
    System.out.println(x);

    i = xSizeString.length() + 1;
    while (!String.valueOf(levelString.charAt(i)).equals(":")) {
      ySizeString = ySizeString + String.valueOf(levelString.charAt(i));
      i++;
    }
    int y = Integer.parseInt(ySizeString);
    System.out.println(y);
    thePlane = new ThePlane(x * 100, y * 100);
//    thePlane.setSize(x * 100, y * 100);
    System.out.println(thePlane.getXSize());
    System.out.println(thePlane.getYSize());
    
    i = xSizeString.length() + ySizeString.length() + 2;

    int xIndex = x;
    
    while(y>0) {
      while(x>0) {
        int nextInt = levelString.charAt(i);
        if (nextInt == '1') {
          thePlane.addItem(1, x*100, y*100);
        }
        if (nextInt == '2') {
          thePlane.addItem(2,x*100,y*100);
         }
        if(nextInt == '3') {
          thePlane.addItem(3,x*100,y*100);
        }
        i++;
        x--;
       }
      x = xIndex;
      y--;
    }
	}
	
	
	
	//TODO use below to set the level based on the string from either a file or the level editor
  
  
}
