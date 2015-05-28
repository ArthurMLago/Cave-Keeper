package visual;

import java.util.ArrayList;
import java.util.HashMap;

import gameController.IGameController;
import map.IMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import player.IPlayer;
import anima.component.IRequires;
import anima.component.ISupports;
import anima.component.InterfaceType;

public class CompositeMap extends BasicGame implements
		IRequires<IGameController> {
	private Animation spritePlayer, playerUp, playerDown, playerLeft,
			playerRight;
	private Animation shadowPlayer, shadowUp, shadowDown, shadowLeft,
			shadowRight, shadowNext;
	private ArrayList<Integer[]> tileRender;
	private int x = 32, y = 32;
	private int posX, posY, xFacing, yFacing;
	private IGameController gameController;
	private IPlayer player;
	private IMap map;
	private String character = "cat";
	private int duration = 10;
	private boolean flare = false;
	private int flareTime;
	private HashMap<Integer, Image> imageMap;

	public CompositeMap(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		faceSprite(player.getFacing());
		drawTile(player.getX(), player.getY());
		drawTile(player.getX() + xFacing, player.getY() + yFacing);

		if (flare) {
			for (int xR = 0; xR < map.getLimiteX(); xR++) {
				for (int yR = 0; yR < map.getLimiteY(); yR++) {
					drawTile(xR, yR);

				}
			}
			

			if (flareTime > 2000)
				flare = false;
		} else {
			shadowNext.draw((player.getX() + xFacing) * this.x,
					(player.getY() + yFacing) * this.y);

			shadowPlayer.draw(player.getX() * x, player.getY() * y);
		}

		spritePlayer.draw(player.getX() * x, player.getY() * y);
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		Image[] movementUp = { new Image("character/" + character + "_up.png") };
		Image[] movementDown = { new Image("character/" + character
				+ "_down.png") };
		Image[] movementLeft = { new Image("character/" + character
				+ "_left.png") };
		Image[] movementRight = { new Image("character/" + character
				+ "_right.png") };

		// Iniciando as animações
		playerUp = new Animation(movementUp, duration, false);
		playerDown = new Animation(movementDown, duration, false);
		playerLeft = new Animation(movementLeft, duration, false);
		playerRight = new Animation(movementRight, duration, false);

		String pathShadow = "tile/shadow/";
		Image[] shaPlayer = { new Image(pathShadow + "on_character.png") };
		Image[] shaUp = { new Image(pathShadow + "up.png") };
		Image[] shaDown = { new Image(pathShadow + "down.png") };
		Image[] shaLeft = { new Image(pathShadow + "left.png") };
		Image[] shaRight = { new Image(pathShadow + "right.png") };
		shadowPlayer = new Animation(shaPlayer, duration, false);
		shadowUp = new Animation(shaUp, duration, false);
		shadowDown = new Animation(shaDown, duration, false);
		shadowRight = new Animation(shaRight, duration, false);
		shadowLeft = new Animation(shaLeft, duration, false);

		faceSprite(gameController.getPlayer().getFacing());
		tileRender = new ArrayList<Integer[]>();
		imageMap = new HashMap<Integer, Image>();
	}

	private void faceSprite(String facing) {
		if ("up".compareTo(facing) == 0) {
			spritePlayer = playerUp;
			shadowNext = shadowUp;
			yFacing = -1;
			xFacing = 0;
		} else if ("down".compareTo(facing) == 0) {
			spritePlayer = playerDown;
			shadowNext = shadowDown;
			yFacing = 1;
			xFacing = 0;
		} else if ("left".compareTo(facing) == 0) {
			spritePlayer = playerLeft;
			shadowNext = shadowLeft;
			xFacing = -1;
			yFacing = 0;
		} else if ("right".compareTo(facing) == 0) {
			spritePlayer = playerRight;
			shadowNext = shadowRight;
			xFacing = 1;
			yFacing = 0;
		}
	}

	private void drawTile(int x, int y) {
		try {
			Image tile;
			if (imageMap.containsKey(map.getTileAt(x, y).getImage())) {
				tile =imageMap.get(map.getTileAt(x, y).getImage());
			} else {
				tile = new Image("tile/"
						+ map.getTileAt(x, y).getImage() + ".png");
				imageMap.put(map.getTileAt(x, y).getImage(), tile);
			}
			Image[] tiles = { tile };
			Animation tileAnimation = new Animation(tiles, 1000);
			tileAnimation.draw(x * this.x, y * this.y);
		} catch (SlickException e) {
			System.out.println(e);
		}

	}

	public void flare() {
		flare = true;
		flareTime = 0;
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		gameController.setCommand(gc.getInput());

		gameController.update();

		flareTime += delta;
	}

	@Override
	public void connect(IGameController gameController) {
		this.gameController = gameController;
		map = gameController.getMap();
		player = gameController.getPlayer();
	}

	@Override
	public int addRef() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getInstanceId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends ISupports> T queryInterface(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends ISupports> T queryInterface(String arg0,
			InterfaceType arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends ISupports> IRequires<T> queryReceptacle(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int release() {
		// TODO Auto-generated method stub
		return 0;
	}

}
