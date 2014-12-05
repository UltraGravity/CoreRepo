package Dialog;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class PauseDialog extends Dialog
{
	MyGame myGame;
	int music;
	int sfx;
	TextButton mainMenu;
	TextButton restart;
	TextButton resume;
	TextButton musicButton;
	TextButton sfxButton;
	
	public PauseDialog(MyGame myGame, String title, Skin skin)
	{
		super(title, skin);
		
		this.myGame = myGame;
		TextButtonStyle textButtonStyle = myGame.assetLoader.textButtonStyle;
		
		Table table = new Table();
		mainMenu = new TextButton("Main Menu", textButtonStyle);
		resume = new TextButton("Resume", textButtonStyle);
		restart = new TextButton("Restart", textButtonStyle);
		
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
			sfxButton = new TextButton("SFX On", textButtonStyle);
			sfx = 1;
		}
		else
		{
			sfxButton = new TextButton("SFX Off", textButtonStyle);
			sfx = 0;
		}	
		
		table.add(resume).width((float)myGame.screenWidth/3).row();
		table.add(restart).width((float)myGame.screenWidth/3).row();
		table.add(mainMenu).width((float)myGame.screenWidth/3).row();
		table.add(musicButton).width((float)myGame.screenWidth/3).row();
		table.add(sfxButton).width((float)myGame.screenWidth/3);
		
		this.add(table);
		setupButtons();
	}
	
	
	public void setupButtons()
	{
		resume.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				myGame.gameScreen.resumeGame();
				hide();
			}
		});
		
		mainMenu.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				myGame.changeToMainMenuScreen();
			}
		});
		
		restart.addListener(new ChangeListener()
		{
			public void changed(ChangeEvent event, Actor actor)
			{
				myGame.playClick();
				myGame.changeToGameScreen(myGame.gameScreen.levelName, myGame.gameScreen.folder);
			}
		});
		
		musicButton.addListener(new ChangeListener() 
		{
	        public void changed (ChangeEvent event, Actor actor) 
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
	            file.writeString(String.valueOf(music) + String.valueOf(sfx), false);
	        }});
		
		sfxButton.addListener(new ChangeListener() 
		{
	        public void changed (ChangeEvent event, Actor actor) 
	        {
	        	myGame.playClick();
	            
	            if (myGame.sfx)
	            {
	            	myGame.sfx = false;
	            	sfxButton.setText("SFX Off");
	            	sfx = 0;
	            }
	            else
	            {
	            	myGame.sfx = true;
	            	sfxButton.setText("SFX On");
	            	sfx = 1;
	            }
	            FileHandle file = Gdx.files.local("Settings.txt");
	            file.writeString(String.valueOf(music) + String.valueOf(sfx), false);
	        }});
		
		
	}
}