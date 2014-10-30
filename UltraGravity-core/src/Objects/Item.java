package Objects;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Item {

  int gravity;
  int x;
  int y;

  
  World world;
  MovingItem movingItem;
  public MyGame myGame;
  public TextureRegion texture;
  
  public Item(MyGame myGame, int x, int y) 
  {
    this.myGame = myGame;
    this.x = x;
    this.y = y;
    TextureRegion texture = new TextureRegion();

  }

  public void draw() {

  }

  public TextureRegion getTexture() {
    return this.texture;
  }
  
  public int getX() 
  {
    return x;
  }
  
  public int getY()
  {
    return y;
  }
  
  public void update(int gravity) {
    this.gravity = gravity;
    movingItem.update(gravity);
  }

}
