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
}
