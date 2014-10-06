package Objects;

public class Box {
  int x;
  int y;
  int angle;
  int xSpeed;
  int ySpeed;
  int rotSpeed;
  private Box box;
  
  public Box(int x, int y, int angle, int xSpeed, int ySpeed, int rotSpeed) {
    this.x = x;
    this.y = y;
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
    this.rotSpeed = rotSpeed;
    this.angle = angle;
  }
  
  public Box getBox(){
    return this.box;
  }

  private void setBox(int x, int y, int angle, int xSpeed, int ySpeed, int rotSpeed) {
    this.x = x;
    this.y = y;
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
    this.rotSpeed = rotSpeed;
    this.angle = angle;
  }
  
  private void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  private void setSpeed(int xSpeed, int ySpeed) {
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
  }
  
  private void setAngularData(int angle, int rotSpeed) {
    this.angle = angle;
    this.rotSpeed = rotSpeed;
  }
  

}
