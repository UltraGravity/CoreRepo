package levelEditor;

import java.util.ArrayList;

public class LevelEditerArray{
  int xSize;    //sets the level size in terms of editor grid
  int ySize;
  ArrayList<Objects> levelArray;  //List of objects to enter into the editor Array
  
  
  public LevelEditerArray(ArrayList<Objects> levelArray) {
    this.levelArray = levelArray;
  }

}
