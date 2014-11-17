package Physics;

import Objects.Item;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class WorldUtils
{

	public World createWorld()
	{
		System.out.println("Creating a new world!");
		World world = new World(Direction.DOWN, true);
		return world;
	}

	public void changeWorldGravity(Vector2 gravity, World world)
	{
		world.setGravity(gravity);
	}

	public Body createItemInWorld(Item item, World world)
	{
		BodyDef bodyDef = item.getBodyDef();
		bodyDef.position.set(new Vector2(item.getX(), item.getY()));
		Body body = world.createBody(bodyDef);
		PolygonShape shape = item.getShape();
		body.createFixture(shape, 0f);
		shape.dispose();
		return body;
	}

}
