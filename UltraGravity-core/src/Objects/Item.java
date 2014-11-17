package Objects;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Item
{

	int gravity;
	int x;
	int y;

	World world;
	MovingItem movingItem;
	public MyGame myGame;
	PolygonShape shape;
	BodyDef bodyDef;

	public Item(MyGame myGame, int x, int y)
	{
		this.myGame = myGame;
		this.x = x;
		this.y = y;
	}

	public BodyDef getBodyDef()
	{
		return bodyDef;
	}

	public PolygonShape getShape()
	{
		return shape;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

}
