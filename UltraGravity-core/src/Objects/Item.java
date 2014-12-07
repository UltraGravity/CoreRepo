package Objects;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class Item
{

	int gravity;
	int x;
	int y;

	float density;
	float friction = 1f;
	float restitution;

	World world;
	MovingItem movingItem;
	public MyGame myGame;
	Shape shape;
	BodyDef bodyDef;
	TextureRegion texture;
	Sprite sprite;

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

	public Shape getShape()
	{
		return shape;
	}

	public TextureRegion getTextureRegion()
	{
		return texture;
	}

	public Sprite getSprite()
	{
		return sprite;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public float getDensity()
	{
		return density;
	}

	public float getFriction()
	{
		return friction;
	}

	public float getRestitution()
	{
		return restitution;
	}

}
