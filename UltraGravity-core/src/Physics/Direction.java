package Physics;

import com.badlogic.gdx.math.Vector2;

public class Direction
{
	public static final Vector2 UP = new Vector2(0, Constants.GRAVITY);
	public static final Vector2 DOWN = new Vector2(0, -Constants.GRAVITY);
	public static final Vector2 LEFT = new Vector2(-Constants.GRAVITY, 0);
	public static final Vector2 RIGHT = new Vector2(Constants.GRAVITY, 0);

}