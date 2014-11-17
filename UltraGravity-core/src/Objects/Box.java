package Objects;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Box extends MovingItem
{
	private Box box;
	TextureRegion texture = myGame.assetLoader.box;

	public Box(MyGame myGame, int x, int y)
	{
		super(myGame, x, y);
		 shape.setAsBox(100, 100);
	}

}
