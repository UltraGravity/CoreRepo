package Objects;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
	TextureRegion texture;

	public Item(MyGame myGame, int x, int y)
	{
		this.myGame = myGame;
		this.x = x;
		this.y = y;
		this.shape = new PolygonShape();
	}

	public BodyDef getBodyDef()
	{
		return bodyDef;
	}

	public PolygonShape getShape()
	{
		return shape;
	}
	
	public TextureRegion getTextureRegion() {
		return texture;
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
