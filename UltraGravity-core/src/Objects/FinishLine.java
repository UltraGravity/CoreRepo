package Objects;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class FinishLine extends Item{
  private FinishLine finish;

//  Texture texture = new Texture(Gdx.files.internal("data/box.png"));
  
  public FinishLine(MyGame myGame, int x, int y)
  {
    super(myGame, x, y);
    texture = myGame.assetLoader.crate;
    shape = new PolygonShape();
    shape.setAsBox(100, 100);
  }
  
  
  public FinishLine getFinish()
  {
    return this.finish;
  }

  private void setFinish(int x, int y) 
  {
    this.x = x;
    this.y = y;
  }
  
  private void setLocation(int x, int y) 
  {
    this.x = x;
    this.y = y;
  }  
  
  public TextureRegion getTexture() 
  {
    return texture;
  }
  

}
