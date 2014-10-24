package Objects;

public class World {
  int worldY = 6;
  int worldX = 12;

  Item[][] World = new Item[worldX][worldY];
  
  public World(int editWorldX, int editWorldY) 
  {
    this.worldX = editWorldX;
    this.worldY = editWorldY;
    
    World = new Item[editWorldX][editWorldY];
  }
  public void setWorld() {
    
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
  
  public void addItem(int item, int x, int y) 
  {
    if(item == 0) {
      Box box = new Box(x,y);
      World[x][y] = box;
    }
    if(item == 1) {
      Character character = new Character(x,y);
      World[x][y] = character;
    }
    if(item == 2) {
      GroundBlock ground = new GroundBlock(x,y);
      World[x][y] = ground;
    }

  }
  
  public void removeItem(int x, int y) {
    World[x][y] = null;
  }
  
//  public Item[][] getArray() {
//    return World;
//  }
  

  
}
