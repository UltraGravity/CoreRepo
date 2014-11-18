package Objects;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class SafeZone extends Item
{

	public SafeZone(MyGame myGame, int x, int y)
	{
		super(myGame, x, y);
		texture = myGame.assetLoader.safeZone;

		shape.setAsBox(100, 100);
	}
	
	public void setShape()
	{
		shape.setAsBox(100, 100);
	}
	
	public TextureRegion getTexture() {
		return texture;
	}

}
