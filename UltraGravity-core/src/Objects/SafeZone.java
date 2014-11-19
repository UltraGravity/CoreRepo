package Objects;

import Physics.Constants;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class SafeZone extends Item
{

	public SafeZone(MyGame myGame, int x, int y)
	{
		super(myGame, x, y);
		texture = myGame.assetLoader.safeZone;
		sprite = new Sprite(texture);
		setShape();
	}
	
	public void setShape()
	{
		shape.setAsBox(myGame.screenHeight/(Constants.SIZE_SCALE*2), myGame.screenHeight/(Constants.SIZE_SCALE*2));
	}
	
	public TextureRegion getTexture() {
		return texture;
	}

}
