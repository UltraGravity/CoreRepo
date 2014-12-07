package Physics;

import Objects.Buzzsaw;
import Objects.Ice;
import Objects.Tire;
import Objects.WoodBox;
import Objects.SafeZone;
import Objects.GroundBlock;
import Objects.Item;
import Objects.MainCharacter;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;

public class WorldUtils
{
	MyGame myGame;
	Array groundChain;
	GroundBlock groundBlock;

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
		def.friction = item.getFriction();
		def.restitution = item.getRestitution();
		def.isSensor = (item instanceof SafeZone);
		if (item instanceof Buzzsaw)
		{
			body.setGravityScale(0);
			float randX = (float) (10 - (Math.random() * 20));
			float randY = (float) (10 - (Math.random() * 20));
			System.out.println(randY);
			body.applyLinearImpulse(randX, randY, 0, 0, true);
			body.setAngularVelocity(10);
		}
		if(item instanceof Tire){
			body.setAngularVelocity((float) (5 - (Math.random() * 5)));
		}
		body.createFixture(def);
		body.setUserData(item);
	}

	public void combineGroundBlocks(World world)
	{
		// world.step(60, Constants.VELOCITY_ITERATIONS,
		// Constants.POSITION_ITERATIONS);
		System.out.println("Combining the ground" + world.getContactCount());
		Array<Body> bodies = new Array<Body>();
		for (int i = 0; i < world.getBodyCount(); i++)
		{
			world.getBodies(bodies);
		}
		for (int i = 0; i < bodies.size; i++)
		{
			for (int k = i + 1; k < bodies.size; k++)
			{
				Fixture fixA = bodies.get(i).getFixtureList().get(0);
				Fixture fixB = bodies.get(k).getFixtureList().get(0);
				if (fixA.getBody().getUserData() instanceof GroundBlock
						&& fixB.getBody().getUserData() instanceof GroundBlock)
				{

					System.out.println("2 Grounds");
					PolygonShape shapeA = (PolygonShape) fixA.getShape();
					Vector2[] vertPointsA = new Vector2[shapeA.getVertexCount()];
					for (int j = 0; j < shapeA.getVertexCount(); j++)
					{
						Vector2 v = new Vector2();
						shapeA.getVertex(j, v);
						vertPointsA[j] = fixA.getBody().getWorldPoint(v);
//						System.out.print(vertPointsA[j].toString());
					}
					System.out.println("");
					PolygonShape shapeB = (PolygonShape) fixB.getShape();
					Vector2[] vertPointsB = new Vector2[shapeB.getVertexCount()];
					for (int j = 0; j < shapeB.getVertexCount(); j++)
					{
						Vector2 v = new Vector2();
						shapeB.getVertex(j, v);
						vertPointsB[j] = fixB.getBody().getWorldPoint(v);
//						System.out.print(vertPointsB[j].toString());
					}
					Array<Vector2> cs = new Array<Vector2>();
//					System.out.println("Creating a new chain");
					for (int j = 0; j < vertPointsA.length; j++)
					{
						for (int l = 0; l < vertPointsB.length; l++)
						{
//							System.out.println(vertPointsA[j].toString()
//									+ vertPointsB[l].toString());
							if (vertPointsA[j].dst(vertPointsB[l]) < .02f)
							{
								cs.add(vertPointsA[j]);
								System.out.println("Added vert to chain");
							}
						}
					}

					if (cs.size > 0)
					{
						System.out.println("Creating a new Chain");
						Vector2[] csVerts = new Vector2[cs.size];
						for (int j = 0; j < csVerts.length; j++)
						{
							csVerts[j] = cs.get(j);
						}
						ChainShape chain = new ChainShape();
						chain.createChain(csVerts);

						BodyDef bodyDef = new BodyDef();
						bodyDef.type = BodyType.StaticBody;
						bodyDef.position.set(0, 0);
						Body body = world.createBody(bodyDef);
						FixtureDef def = new FixtureDef();
						def.shape = groundBlock.getShape();
						def.density = groundBlock.getDensity();
						def.friction = groundBlock.getFriction();
						def.restitution = groundBlock.getRestitution();

						body.createFixture(def);
						body.setUserData(groundBlock);
					}
				}
			}
		}

	}

}
