package Screens;

import Loaders.GameAssets;
import Objects.Item;
import Objects.World;

import com.APAAAEAIA.UltraGravity.MyGame;

public class GameScreen extends GenericScreen
{

	GameAssets gameAssets;
	World world;
	
	public GameScreen(MyGame myGame, GameAssets gameAssets) 
	{
		super(myGame);
		this.gameAssets = gameAssets;
	}


	private enum GameState {PLAY, PAUSE, GAMEOVER};
	public GameState gameState = GameState.PLAY;
	
	public void render(float delta) 
	{
    System.out.println("beginning");
    world = new World(6,12);
    world.setWorld();
    System.out.println("World is Set");
    Item[][] stuff = world.getWorld();
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

}
