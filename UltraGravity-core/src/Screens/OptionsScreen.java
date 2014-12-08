package Screens;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class OptionsScreen extends GenericScreen
{

	Stage stage;
	BitmapFont font;
	Table table;
	TextureAtlas buttonAtlas;
	TextButtonStyle textButtonStyle;
	TextButton soundEffectsButton;
	TextButton musicButton;
	TextButton backButton;
	Label ultraGravity;
	LabelStyle ultraGravityFont;
	int music;
	int sfx;

	public OptionsScreen(MyGame myGame)
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
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		table = new Table();
		table.setFillParent(true);

		font = myGame.assetLoader.font;
		textButtonStyle = myGame.assetLoader.textButtonStyle;

		if (myGame.music)
		{
			musicButton = new TextButton("Music On", textButtonStyle);
			music = 1;
		}
		else
		{
			musicButton = new TextButton("Music Off", textButtonStyle);
			music = 0;
		}

		if (myGame.sfx)
		{
			soundEffectsButton = new TextButton("SFX On", textButtonStyle);
			sfx = 1;
		}
		else
		{
			soundEffectsButton = new TextButton("SFX Off", textButtonStyle);
			sfx = 0;
		}

		backButton = new TextButton("Back", textButtonStyle);

		table.add(musicButton).width(screenWidth - screenWidth / 8);
		table.row();
		table.add(soundEffectsButton).fillX();
		table.row();
		table.add(backButton).fillX();

		stage.addActor(table);

		// Button actions
		musicButton.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();

				if (myGame.music)
				{
					myGame.music = false;
					musicButton.setText("Music Off");
					music = 0;
					myGame.assetLoader.music.stop();
				}
				else
				{
					myGame.music = true;
					musicButton.setText("Music On");
					music = 1;
					myGame.playMusic();
				}
				FileHandle file = Gdx.files.local("Settings.txt");
				file.writeString(String.valueOf(music) + String.valueOf(sfx),
						false);
			}
		});

		soundEffectsButton.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();

				if (myGame.sfx)
				{
					myGame.sfx = false;
					soundEffectsButton.setText("SFX Off");
					sfx = 0;
				}
				else
				{
					myGame.sfx = true;
					soundEffectsButton.setText("SFX On");
					sfx = 1;
				}
				FileHandle file = Gdx.files.local("Settings.txt");
				file.writeString(String.valueOf(music) + String.valueOf(sfx),
						false);
			}
		});

		backButton.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				myGame.changeToMainMenuScreen();
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
					myGame.changeToMainMenuScreen();

				return false;
			}
		};

		InputMultiplexer multiplexer = new InputMultiplexer(stage,
				backProcessor);
		Gdx.input.setInputProcessor(multiplexer);

	}

	public void hide()
	{
		System.out.println("Disposing Options Screen");
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
		font = null;
	}

	public void resize(int width, int height)
	{
	}

	public void pause()
	{
	}

	public void resume()
	{
	}

}
