package FileIO;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class LevelFile {
  MyGame myGame;

  public LevelFile(MyGame myGame) {
    this.myGame = myGame;
  }

  public void SaveLevel(String fileName, String world) {
    // BufferedWriter writer = new BufferedWriter(new FileWriter("Levels/" + fileName));
    FileHandle file = Gdx.files.local("Levels/" + fileName);
    System.out.println(world);
    file.writeString(world, false);
    System.out.println("File Saved");

  }

  public String LoadLevel(String fileName) {
    FileHandle file = Gdx.files.local("Levels/" + fileName);
    if(file.exists()) {
      System.out.println("The file exists");
    }
    else {
      System.out.println("No file exists");
    }
    String level = file.readString();
    System.out.println(level);
    return level;
  }
  
  public String getLastLevelName() {
    boolean nameFound = false;
    String fileName = "";
    int fName = 0;
    while (!nameFound)
    {
      fileName = "CL_" + Integer.toString(fName) + ".txt";
      System.out.println("Checking if " + fileName
          + " is an available name");
      FileHandle file = Gdx.files.local("Levels/" + fileName);
      if (file.exists())
      {
        System.out.println("File existed");
        fName++;
      }
      else
      {
        fName--;
        fileName = "CL_" + Integer.toString(fName) + ".txt";
        System.out.println(fileName + " is being loaded");
        nameFound = true;
      }
    }
    return fileName;
  }
  
  public String getNextLevelName() {
    boolean nameFound = false;
    String fileName = "";
    int fName = 0;
    while (!nameFound)
    {
      fileName = "CL_" + Integer.toString(fName) + ".txt";
      System.out.println("Checking if " + fileName
          + " is an available name");
      FileHandle file = Gdx.files.local("Levels/" + fileName);
      if (file.exists())
      {
        System.out.println("File existed");
        fName++;
      }
      else
      {
        System.out.println(fileName + " is being created");
        nameFound = true;
      }
    }
    return fileName;
  }
}
