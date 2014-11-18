package Physics;

import com.badlogic.gdx.math.Vector2;

public class Direction
{
	private static float gravity = 150;

	public static final Vector2 UP = new Vector2(0, gravity);
	public static final Vector2 DOWN = new Vector2(0, -gravity);
	public static final Vector2 LEFT = new Vector2(-gravity, 0);
	public static final Vector2 RIGHT = new Vector2(gravity, 0);

}