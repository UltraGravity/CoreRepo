package Objects;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Box extends MovingItem{
  private Box box;
  

//  Texture texture = new Texture(Gdx.files.internal("data/box.png"));
  
  public Box(MyGame myGame, int x, int y)
  {
    super(myGame, x, y);
    texture = myGame.assetLoader.crate;
    shape = new PolygonShape();
    shape.setAsBox(100, 100);
  }
  
  
  public Box getBox()
  {
    return this.box;
  }

  private void setBox(int x, int y, int angle, int xSpeed, int ySpeed, int rotSpeed) 
  {
    this.x = x;
    this.y = y;
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
    this.rotSpeed = rotSpeed;
    this.angle = angle;
  }
  
  private void setLocation(int x, int y) 
  {
    this.x = x;
    this.y = y;
  }
  
  private void setSpeed(int xSpeed, int ySpeed) 
  {
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
  }
  
  private void setAngularData(int angle, int rotSpeed) 
  {
    this.angle = angle;
    this.rotSpeed = rotSpeed;
  }
  
  public int getAngle()
  {
    return angle;
  }
  
  public int getXSpeed()
  {
    return xSpeed;
  }
  
  public int getYSpeed()
  {
    return ySpeed;
  }
  
  public int getRotSpeed()
  {
    return rotSpeed;
  }
  
  
  public TextureRegion getTexture() 
  {
    return texture;
  }
  

}
