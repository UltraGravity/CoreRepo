package Screens;

import Dialog.LevelEditDialog;
import FileIO.LevelFile;
import Objects.GridImage;
import Objects.ThePlane;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.Viewport;

public class LevelEditorScreen extends GenericScreen {

  int                     selectedBlock = 0;
  BitmapFont              font;
  Stage                   stage;
  Skin                    buttonSkin;
  Table                   mapTable;
  Table                   toolTable;
  Table                   levelGrid;
  ScrollPane              scrollPane;
  TextButtonStyle         textButtonStyle;
  TextureAtlas            buttonAtlas;

  GridImage               cell[];
  public ImageButtonStyle selectedStyle;

  Label                   ultraGravity;
  LabelStyle              ultraGravityFont;

  LevelFile               levelFile;
  ThePlane                thePlane;

  float                   currentScaleX = screenWidth;
  float                   currentScaleY = screenHeight;

  public LevelEditorScreen(MyGame myGame) {
    super(myGame);
  }

  public void render(float delta) {
    Gdx.gl.glClearColor(0, .25f, .25f, 1);
    Gdx.gl.glClearColor(.65f, .65f, .65f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();
    batch.setProjectionMatrix(camera.combined);

    stage.act();

    batch.begin();
    // scrollPane.debug();
    // scrollPane.debugAll();
    // mapTable.debug();
    // mapTable.debugTable();
    // toolTable.debug();
    // toolTable.debugTable();
    // levelGrid.debug();
    // levelGrid.debugTable();
    stage.draw();
    batch.end();
  }

  public void show() {
    stage = new Stage();
    stage.getViewport().setCamera(camera);

    Gdx.input.setInputProcessor(stage);
    Gdx.input.setCatchBackKey(true);

    mapTable = new Table(); // Holds the ScrollPane
    levelGrid = new Table(); // Holds all the items you place
    toolTable = new Table(myGame.assetLoader.menuButtonSkin); // Holds all the tools
    scrollPane = new ScrollPane(levelGrid);
    mapTable.setHeight(screenHeight);
    mapTable.setWidth(screenWidth);
    mapTable.add(scrollPane).height(screenHeight - screenHeight / 7);
    mapTable.top();

    selectedStyle = myGame.assetLoader.blankBlockStyle;

    toolTable.add(myGame.assetLoader.backButton).size(screenHeight / 12).padLeft(screenWidth / 100)
        .padRight(screenWidth / 100);
    toolTable.add(myGame.assetLoader.groundButton).size(screenHeight / 12).padLeft(screenWidth / 100)
        .padRight(screenWidth / 100);
    toolTable.add(myGame.assetLoader.boxButton).size(screenHeight / 12).padLeft(screenWidth / 100)
        .padRight(screenWidth / 100);
    toolTable.add(myGame.assetLoader.safeZoneButton).size(screenHeight / 12).padLeft(screenWidth / 100)
        .padRight(screenWidth / 100);
    toolTable.add(myGame.assetLoader.blankButton).size(screenHeight / 12).padLeft(screenWidth / 100)
        .padRight(screenWidth / 100);
    toolTable.add(myGame.assetLoader.saveButton).size(screenHeight / 12).padLeft(screenWidth / 100)
        .padRight(screenWidth / 100);
    toolTable.add(myGame.assetLoader.loadButton).size(screenHeight / 12).padLeft(screenWidth / 100)
        .padRight(screenWidth / 100);
    toolTable.add(myGame.assetLoader.playButton).size(screenHeight / 12).padLeft(screenWidth / 100)
        .padRight(screenWidth / 100);

    toolTable.setBackground("Button-Pressed");
    // toolTable.pack();
    toolTable.center().bottom();

    int index = 0;
    thePlane = new ThePlane(20, 10);
    // world = new World(new Vector2(0,-10), true);
    cell = new GridImage[thePlane.getXSize() * thePlane.getYSize()];
    createGrid(cell);

    mapTable.row();
    mapTable.add(toolTable).bottom();

    addListeners(levelGrid);

    stage.addActor(mapTable);

    LevelEditDialog dialog = new LevelEditDialog(myGame, "Load A File?", myGame.assetLoader.uiSkin);
    dialog.show(stage);

    // This might not be working because the action listener is set to the stage.
    InputProcessor backProcessor = new InputAdapter() {
      public boolean keyDown(int keycode) {
        if ((keycode == Keys.BACK) || (keycode == Keys.BACKSPACE)) {
          myGame.changeToMainMenuScreen();
        }
        return false;
      }
    };

    stage.addListener(new ActorGestureListener() {

      public void pinch(InputEvent event, Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1,
          Vector2 pointer2) {
        // System.out.println(initialPointer1);
        //
        //
        // float origDist = (float)(Math.sqrt(Math.pow(initialPointer1.x - initialPointer2.x, 2) +
        // Math.pow(initialPointer1.y - initialPointer2.y, 2)));
        // float newDist = (float)(Math.sqrt(Math.pow(pointer1.x - pointer2.x, 2) + Math.pow(pointer1.y - pointer2.y,
        // 2)));
        //
        //
        // if (newDist > origDist) // Add a little bit of error in there
        // {
        // currentScaleX += newDist;
        // currentScaleY += newDist;
        // mapTable.setScale(currentScaleX);
        // }
        // if (newDist < origDist)
        // {
        // currentScaleX -= newDist;
        // currentScaleY -= newDist;
        // mapTable.setScale(currentScaleX);
        // }
        //
        // //System.out.println(origDist + ",  " + (origDist + (origDist * .20)) + ",  " + newDist);
        //
        //
        // mapTable.pack();
      }
    });
  }

  private void addListeners(Table levelGrid2) {
    levelGrid.addListener(new ChangeListener() {
      public void changed(ChangeEvent event, Actor actor) {
        GridImage image = (GridImage) actor;
        image.setStyle(selectedStyle);

        if (selectedStyle == myGame.assetLoader.blankBlockStyle) {
          image.cellValue = 0;
        }
        if (selectedStyle == myGame.assetLoader.groundBlockStyle) {
          image.cellValue = 1;
        }
        if (selectedStyle == myGame.assetLoader.boxBlockStyle) {
          image.cellValue = 2;
        }
        if (selectedStyle == myGame.assetLoader.safeZoneBlockStyle) {
          image.cellValue = 3;
        }
      }
    });
  }

  private void createGrid(GridImage[] cell2) {
    int index = 0;
    for (int y = 0; y < thePlane.getYSize(); y++) {
      for (int x = 0; x < thePlane.getXSize(); x++) {
        cell[index] = new GridImage(myGame.assetLoader.blankBlockStyle);
        levelGrid.add(cell[index]).size(screenHeight / 8);// .size(screenHeight/7);
        index++;
      }
      levelGrid.row();
    }

  }

  public String getLastLevel() {
    String level = "";
    level = levelFile.LoadLevel(levelFile.getLastLevelName());
    return level;
  }

  public String getLevelString() {

    String level = Integer.toString(thePlane.getXSize()) + "," + Integer.toString(thePlane.getYSize()) + ":";
    for (Actor A : levelGrid.getChildren()) {
      GridImage item = (GridImage) A;
      System.out.println(item.cellValue);
      level = level + item.cellValue;

    }
    return level;
  }

  public void save() {
    String fileName = "";
    levelFile = new LevelFile(myGame);
    fileName = levelFile.getNextLevelName();
    String level = getLevelString();
    System.out.println(level);
    System.out.println(fileName);
    levelFile.SaveLevel(fileName, level);
  }

  public void load(String file) {
    levelGrid.clearChildren();
    levelFile = new LevelFile(myGame);
    String level = levelFile.LoadLevel(file);
    
    int i = 0;
    String xSizeString = "";
    String ySizeString = "";
    int index = 0;

    while (!String.valueOf(level.charAt(i)).equals(",")) {
      xSizeString = xSizeString + String.valueOf(level.charAt(i));
      i++;
    }
    int x = Integer.parseInt(xSizeString);
    System.out.println(x);

    i = xSizeString.length() + 1;
    while (!String.valueOf(level.charAt(i)).equals(":")) {
      ySizeString = ySizeString + String.valueOf(level.charAt(i));
      i++;
    }
    int y = Integer.parseInt(ySizeString);
    System.out.println(y);

    thePlane.setSize(x, y);
    System.out.println(thePlane.getXSize());
    System.out.println(thePlane.getYSize());
    cell = new GridImage[x * y];
    System.out.println("New grid created " + (x * y));
    createGrid(cell);

    i = xSizeString.length() + ySizeString.length() + 2;
    System.out.println(index);

    levelGrid.reset();
    while (y > 0) {
      while (x > 0) { // The x and y loops are here to help place in a grid
        int nextInt = level.charAt(i);
        if (nextInt == '0') {
          System.out.print(" " + 0 + " ");
          cell[index] = new GridImage(myGame.assetLoader.blankBlockStyle);
          levelGrid.add(cell[index]).size(screenHeight / 8);// .size(screenHeight/7);
          // add blank space
        }
        if (nextInt == '1') {
          System.out.print(" " + 1 + " ");
          cell[index] = new GridImage(myGame.assetLoader.groundBlockStyle);
          levelGrid.add(cell[index]).size(screenHeight / 8);
          // add ground block
        }
        if (nextInt == '2') {
          System.out.print(" " + 2 + " ");
          cell[index] = new GridImage(myGame.assetLoader.boxBlockStyle);
          levelGrid.add(cell[index]).size(screenHeight / 8);
          // add crate
        }
        if (nextInt == '3') {
          System.out.print(" " + 3 + " ");
          cell[index] = new GridImage(myGame.assetLoader.safeZoneBlockStyle);
          levelGrid.add(cell[index]).size(screenHeight / 8);
          // add character
        }
        index++;
        i++;
        x--;
      }
      System.out.println();
      levelGrid.row();
      x = thePlane.getXSize();
      y--;
    }
    addListeners(levelGrid);
  }

  public void hide() {
    System.out.println("Disposing Level Editor Screen");
    save();
    this.dispose();
  }

  public void dispose() {
    super.dispose();
    stage.dispose();
  }

  public void pause() {}

  public void resume() {}

  public void resize(int width, int height) {}

}
