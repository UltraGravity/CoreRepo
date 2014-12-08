package Screens;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Orientation;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen extends GenericScreen
{

	Stage stage;
	BitmapFont font;
	Skin skin;
	Table table;
	TextureAtlas buttonAtlas;
	TextButtonStyle textButtonStyle;
	TextButton levelSelectButton;
	TextButton levelEditorButton;
	TextButton optionsButton;
	Label ultraLabel;
	Label gravityLabel;
	LabelStyle ultraGravityFont;

	float rotation;

	public MainMenuScreen(MyGame myGame)
	{
		super(myGame);
	}

	public void render(float delta)
	{
		Gdx.gl.glClearColor(.2f, .2f, .2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();

		batch.begin();
		stage.draw();
		batch.end();
	}

	public void show()
	{

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		table = new Table();
		table.setFillParent(true);

		font = myGame.assetLoader.font;
		skin = new Skin();
		buttonAtlas = myGame.assetLoader.mainMenuButtonAtlas;
		skin.addRegions(buttonAtlas);
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.up = skin.getDrawable("grey");
		textButtonStyle.down = skin.getDrawable("grey-pressed");
		levelSelectButton = new TextButton("Level Select", textButtonStyle);
		levelEditorButton = new TextButton("Level Editor", textButtonStyle);
		optionsButton = new TextButton("Options", textButtonStyle);
		ultraGravityFont = new LabelStyle();
		ultraGravityFont.font = myGame.assetLoader.titleFont;
		ultraLabel = new Label("ULTRA", ultraGravityFont);
		gravityLabel = new Label("GRAVITY", ultraGravityFont);

		table.add(ultraLabel);
		table.row();
		table.add(gravityLabel);
		table.row();

		table.add(levelSelectButton).width(screenWidth - screenWidth / 8);
		table.row();
		table.add(levelEditorButton).fillX();
		table.row();
		table.add(optionsButton).fillX();

		stage.addActor(table);

		// Button actions
		levelSelectButton.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				myGame.changeToLevelScreen();
			}
		});

		levelEditorButton.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				myGame.changeToLevelEditorScreen();
			}
		});

		optionsButton.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				myGame.changeToOptionsScreen();
			}
		});

		Gdx.input.setCatchBackKey(true);
		InputProcessor backProcessor = new InputAdapter()
		{
			@Override
			public boolean keyDown(int keycode)
			{

				if ((keycode == Keys.ESCAPE) || (keycode == Keys.BACK))

					// Maybe perform other operations before exiting
					Gdx.app.exit();
				return false;
			}
		};

		InputMultiplexer multiplexer = new InputMultiplexer(stage,
				backProcessor);
		Gdx.input.setInputProcessor(multiplexer);
	}

	public void hide()
	{
		System.out.println("Disposing Main Menu Screen");
		this.dispose();
	}

	public void dispose()
	{
		/*
		 * It is very important that everything created is disposed of properly
		 * when it is no longer needed. I find it best to explicitly set
		 * everything equal to null so the garbage collector knows it can remove
		 * the stuff from memory.
		 * 
		 * Calling super.dispose() will get rid of the built in variables, but
		 * it is important that anything that is uniquely created in this class
		 * be disposed.
		 */

		super.dispose();
		stage.dispose();
	}

	public void pause()
	{
	}

	public void resume()
	{
	}

	public void resize(int width, int height)
	{
	}

}
