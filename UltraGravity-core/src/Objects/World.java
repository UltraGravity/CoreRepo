package Objects;

import com.APAAAEAIA.UltraGravity.MyGame;

public class World {
  int worldY = 6;
  int worldX = 12;

  Item[][] World = new Item[worldX][worldY];
  
  MyGame myGame;
  
  public World(int editWorldX, int editWorldY) 
  {
    this.worldX = editWorldX;
    this.worldY = editWorldY;
    
    World = new Item[editWorldX][editWorldY];
  }
  public void setWorld() {
    System.out.println("Setting to world");
    for(int i = 0; i < worldX; i++) {
      addItem(2,i,0);
    }
    for(int i = 0; i < worldX; i++) {
      addItem(2,i,5);
    }
    for(int i = 0; i < worldY; i++) {
      addItem(2,0,i);
    }
    for(int i = 0; i < worldY; i++) {
      addItem(2,11,i);
    }
    addItem(1,1,1);
    addItem(0,3,3);
    
  }
  
  public Item[][] getWorld(){
    return World;
  }
  
  public void addItem(int item, int x, int y) 
  {
    if(item == 0) {
      Box box = new Box(myGame, x,y);
      World[x][y] = box;
    }
    if(item == 1) {
      Character character = new Character(myGame, x,y);
      World[x][y] = character;
    }
    if(item == 2) {
      GroundBlock ground = new GroundBlock(myGame, x,y);
      World[x][y] = ground;
    }

  }
  
  public void removeItem(int x, int y) {
    World[x][y] = null;
  }
  
  public int getXSize() {
    return worldX;
  }
  
  public int getYSize() {
    return worldY;
  }
  
//  public Item[][] getArray() {
//    return World;
//  }
  

  
}
