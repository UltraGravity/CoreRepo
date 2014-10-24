package Objects;

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
