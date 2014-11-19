package Objects;

import Physics.Constants;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MainCharacter extends MovingItem
{
	
	public MainCharacter(MyGame myGame, int x, int y)
	{
		super(myGame, x, y);
		texture = myGame.assetLoader.character;
		sprite = new Sprite(texture);
		setShape();
	}
	
	public void setShape()
	{
		shape.setRadius(Constants.SIZE_SCALE);
	}
	
	public TextureRegion getTexture() {
		return texture;
	}

}
