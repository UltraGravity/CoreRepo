package Objects;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Item {

  int gravity;
  int x;
  int y;
  int angle;
  int xSpeed;
  int ySpeed;
  int rotSpeed;
  int xAccel;
  int yAccel;
  int rotAccel;
  
  World world;
  public MyGame myGame;
  public TextureRegion texture;
  
  public Item(MyGame myGame, int x, int y) 
  {
    this.myGame = myGame;
    this.x = x;
    this.y = y;
    TextureRegion texture = new TextureRegion();
//    this.xSpeed = xSpeed;
//    this.ySpeed = ySpeed;
//    this.rotSpeed = rotSpeed;
//    this.angle = angle;
  }
  
  
  public void update(boolean phys) {
    if (phys) {
      updatePhysics();
    }
    else {
      updateEditor();
    }
  }

  public void updateEditor() {
    
  }
  
  public void updatePhysics() {
    if (gravity == 0) {  // direction towards home button when home button is in right hand
      xAccel++;
    }
    if (gravity == 1) { // up when home button is in right hand
      yAccel--;
    }
    if (gravity == 2) { // left of standard
      xAccel--;
    }
    if (gravity == 3) { // down of standard
      yAccel++;
    }
    xSpeed = xSpeed + xAccel;
    ySpeed = ySpeed + yAccel;

    x = x + xSpeed;
    y = y + ySpeed;
  }

  public void draw() {

  }

}
