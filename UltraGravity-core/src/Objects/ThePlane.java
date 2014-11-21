package Objects;

import java.util.ArrayList;

import Physics.Constants;
import Physics.WorldUtils;

import com.APAAAEAIA.UltraGravity.MyGame;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class ThePlane
{
	// made this class a bit more usefull without those pesky arrays
	int planeX = 12;
	int planeY = 6;

	int boundX = planeX * 100;
	int boundY = planeY * 100;

	int gravity;

	ThePlane thePlane;

	ArrayList<Item> worldList = new ArrayList<Item>();

	MyGame myGame;

	// WorldUtils worldUtils;

	public ThePlane(MyGame myGame, int x, int y)
	{
		this.myGame = myGame;
		this.planeX = x;
		this.planeY = y;
		// TODO Auto-generated constructor stub
	}

	public void setGravity(int grav)
	{
		this.gravity = grav;
	}

	public void addItem(int item, int x, int y)
	{
		if (item == 1)
		{
			Item ground = new GroundBlock(myGame, x, y);
			worldList.add(ground);
		}
		if (item == 2)
		{
			Item box = new Box(myGame, x, y);
			worldList.add(box);
		}
		if (item == 3)
		{
			Item safeZone = new SafeZone(myGame, x, y);
			worldList.add(safeZone);
		}
		if (item == 4)
		{
			Item character = new MainCharacter(myGame, x, y);
			worldList.add(character);
		}

	}

	public int getXSize()
	{
		return planeX;
	}

	public int getYSize()
	{
		return planeY;
	}

	public void setSize(int x, int y)
	{
		System.out.println("new world created at size " + x + "," + y);
		planeX = x;
		planeY = y;
	}

	public void setBounds(int x, int y)
	{
		boundX = x;
		boundY = y;
	}

	public void fillWorld(World world)
	{

		WorldUtils worldUtils = new WorldUtils(myGame);
		System.out.println("Filling the world" + worldList.size());
		for (int i = 0; i < worldList.size(); i++)
		{
			Item current = worldList.get(i);
			
			worldUtils.createItemInWorld(current, world);
			System.out.println("Added an item to the world");
		}

	}
	// public Item[][] getArray() {
	// return World;
	// }

}
