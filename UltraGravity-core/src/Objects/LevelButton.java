package Objects;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class LevelButton extends TextButton
{
	String fileName;

	public LevelButton(String text, TextButtonStyle style, String fileName)
	{
		super(text, style);
		this.fileName = fileName;
	}

	public String getLevelName()
	{
		return fileName;
	}

}
