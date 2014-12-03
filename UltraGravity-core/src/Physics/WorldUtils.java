package Physics;

import Objects.Box;
import Objects.SafeZone;
import Objects.GroundBlock;
import Objects.Item;
import Objects.MainCharacter;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class WorldUtils
{
	MyGame myGame;

	public WorldUtils(MyGame myGame)
	{
		this.myGame = myGame;
	}

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

	public void createItemInWorld(Item item, World world)
	{
		BodyDef bodyDef = item.getBodyDef();
		bodyDef.position.set(new Vector2(item.getX(), item.getY()));
		Body body = world.createBody(bodyDef);
		// Shape shape = item.getShape();
		// body.createFixture((Shape) shape, item.getDensity());
		FixtureDef def = new FixtureDef();
		def.shape = item.getShape();
		def.density = item.getDensity();
		def.isSensor = (item instanceof SafeZone);
		body.createFixture(def);
		body.setUserData(item);
	}

}
