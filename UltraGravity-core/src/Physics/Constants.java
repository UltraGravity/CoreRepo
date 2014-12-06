package Physics;

import com.badlogic.gdx.Gdx;

public class Constants
{
	public final static float TIME_STEP = 1/60f;
	public final static int VELOCITY_ITERATIONS = 6;
	public final static int POSITION_ITERATIONS = 2;
	
//	public final static int SIZE_SCALE = 8;
	
	public final static float SIZE_SCALE = (float) 1.95;	 	//meters for each grid block
	public final static float GROUND_SCALE = 2;
	public final static int OBJECT_SCALE = 2;
	public final static int GRID_TO_WORLD = 2 * OBJECT_SCALE;
	
	
	public final static float GRAVITY = 10;

}
