package levelEditor;

import java.util.ArrayList;

public class LevelEditerArray{
  int xSize;    //sets the level size in terms of editor grid
  int ySize;
  Object[][] levelArray = new Object[xSize][ySize];  //List of objects to enter into the editor Array
  
  
  public LevelEditerArray(Object[][] levelArray) {
    this.levelArray = levelArray;
  }

}
