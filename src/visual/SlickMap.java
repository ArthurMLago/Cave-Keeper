package visual;

import gameController.Entidade;
import gameController.GameController;
import gameController.IGameController;
import items.itemManagement.ItemsList;

import java.awt.Font;
import java.awt.TextField;
import java.io.IOException;
import java.util.HashMap;

import map.Event;
import map.enumerations.EventType;
import map.enumerations.TileType;
import map.events.EventItem;
import map.exceptions.OutOfMapBoundsException;
import map.interfaces.IGameMap;
import monster.Interfaces.IMonster;

import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

import player.Facing;
import player.IPlayerMax;
import player.IPlayerPosition;
import anima.component.IRequires;
import anima.component.ISupports;
import anima.component.InterfaceType;

/**
 * Mapa que realiza a intera��o com a biblioteca utilizado para imprimir as
 * informa��es na janela
 * 
 * @author eitiyamamoto
 *
 */
public class SlickMap extends BasicGame {
	
	private Animation spritePlayer, playerUp, playerDown, playerLeft,
			playerRight;
	private Animation shadowPlayer, shadowUp, shadowDown, shadowLeft,
			shadowRight, shadowNext, shadowAround;
	private int xFacing, yFacing;
	private IGameController gameController;
	private IPlayerMax player;
	private IGameMap map;
	private String character = "char";
	private int duration = 10;
	private boolean flare = false, message = false;
	private int flareTime;
	private HashMap<String, Image> imageMap;
	private Audio footstepAudio;
	private IMonster monsters;
	private boolean explosionShoot = false;
	private int explosionX, explosionY, explosionTime, messageTime;
	private String messageTxt;

	public SlickMap(String title) {
		super(title);
		gameController = GameController.getSharedInstance();
		map = gameController.getMap();
		player = gameController.getPlayer();
		monsters = gameController.getEntidades();
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		faceSprite(player.getFacing());
		drawTile(player.getX(), player.getY());
		drawTile(player.getX() + xFacing, player.getY() + yFacing);

		if (flare) {
			drawFlare();
		} else if (player.getLighter()) {
			drawAround();
		} else {
			drawShadowWithLighter();
		}
		if (explosionShoot)
			drawExplosion();

		spritePlayer.draw(player.getX() * MapVisual.SIZEIMAGE, player.getY()
				* MapVisual.SIZEIMAGE);
		
		if (message) {
			Font font = new Font("Verdana", Font.BOLD, 30);
			TrueTypeFont trueTypeFont = new TrueTypeFont(font, true);
			trueTypeFont.drawString(50, 50, messageTxt);
		}
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		Image[] movementUp = { new Image("resources/character/" + character
				+ "_up.png") };
		Image[] movementDown = { new Image("resources/character/" + character
				+ "_down.png") };
		Image[] movementLeft = { new Image("resources/character/" + character
				+ "_left.png") };
		Image[] movementRight = { new Image("resources/character/" + character
				+ "_right.png") };

		// Iniciando as anima��es
		playerUp = new Animation(movementUp, duration, false);
		playerDown = new Animation(movementDown, duration, false);
		playerLeft = new Animation(movementLeft, duration, false);
		playerRight = new Animation(movementRight, duration, false);

		String pathShadow = "resources/tile/shadow/";
		Image[] shaPlayer = { new Image(pathShadow + "on_character.png") };
		Image[] shaUp = { new Image(pathShadow + "up.png") };
		Image[] shaDown = { new Image(pathShadow + "down.png") };
		Image[] shaLeft = { new Image(pathShadow + "left.png") };
		Image[] shaRight = { new Image(pathShadow + "right.png") };
		Image[] shaAround = { new Image(pathShadow + "around.png") };
		shadowPlayer = new Animation(shaPlayer, duration, false);
		shadowUp = new Animation(shaUp, duration, false);
		shadowDown = new Animation(shaDown, duration, false);
		shadowRight = new Animation(shaRight, duration, false);
		shadowLeft = new Animation(shaLeft, duration, false);
		shadowAround = new Animation(shaAround, duration, false);

		String pathAudio = "resources/audio/";
		try {
			footstepAudio = AudioLoader.getAudio(
					"OGG",
					ResourceLoader.getResourceAsStream(pathAudio
							+ "footstep.ogg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		faceSprite(gameController.getPlayer().getFacing());
		imageMap = new HashMap<String, Image>();
	}

	/**
	 * Desenha o tile desejado S� funciona quando chamado no render
	 * 
	 * @param x
	 *            - Posi��o x do tile a ser desenhado
	 * @param y
	 *            - POsi��o Y do tile a ser desenhado
	 */
	private void drawTile(int x, int y) {
		try {
			Image tile = getImage("resources/tile/"
					+ map.getTileAt(x, y).getImage() + ".png", imageMap);

			Image[] tiles = { tile };
			Animation tileAnimation = new Animation(tiles, 1000);
			tileAnimation
					.draw(x * MapVisual.SIZEIMAGE, y * MapVisual.SIZEIMAGE);

			Event event = map.getTileAt(x, y).checkForEvents();
			String nome = null;
			if (event != null) {
				if (event.getType() == EventType.ITEM){
					nome = nameItem(((EventItem) event).getItemType());
	
					Image item = getImage("resources/item/" + nome + ".png",imageMap);
					
					item.draw(x * MapVisual.SIZEIMAGE, y * MapVisual.SIZEIMAGE);
				}else if(event.getType() == EventType.TRAP){
					Image trap = getImage("resources/item/trap.png",imageMap);
					trap.draw(x * MapVisual.SIZEIMAGE, y * MapVisual.SIZEIMAGE);
				}
			}
			if(monsters.isMonstersAlive() && monsters.getX(0) == x && monsters.getY(0) == y){
				drawMonster(monsters);
			}
		} catch (SlickException e) {
			System.out.println(e);
		} catch (OutOfMapBoundsException e) {
		}
	}

	/**
	 * Desenha a entidade desejada S� funciona quando chamado no render
	 * 
	 * @param e
	 *            - Entidade a ser desenhada
	 */
	private void drawMonster(IMonster monster) {
			try {
				Image tile = getImage(
						"resources/monster/" + monster.getImage(0) + ".png",
						imageMap);

				Image[] tiles = { tile };
				Animation tileAnimation = new Animation(tiles, 1000);
				tileAnimation.draw(monster.getX(0) * MapVisual.SIZEIMAGE,
						monster.getY(0) * MapVisual.SIZEIMAGE);
			} catch (SlickException ex) {
				System.out.println(ex);
			}
	}

	private void drawFlare() {
		for (int xR = 0; xR < map.getLimitX(); xR++) {
			for (int yR = 0; yR < map.getLimitY(); yR++) {
				drawTile(xR, yR);
			}
		}

		drawMonster(monsters);

		if (flareTime > 2000)
			flare = false;
	}

	private void drawExplosion() {
		Image explosionImage;
		try {
			explosionImage = getImage("resources/shoot/explosion.png", imageMap);
			Image[] explosions = { explosionImage };
			Animation explosionAnimation = new Animation(explosions, 1000);
			explosionAnimation.draw(explosionX * MapVisual.SIZEIMAGE,
					explosionY * MapVisual.SIZEIMAGE);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		if (explosionTime > 800)
			explosionShoot = false;
	}

	private void drawAround() {
		for (int xA = player.getX() - 1; xA <= player.getX() + 1; xA++) {
			for (int yA = player.getY() - 1; yA <= player.getY() + 1; yA++) {
				drawTile(xA, yA);
			}
		}

		shadowAround.draw((player.getX() - 1) * MapVisual.SIZEIMAGE,
				(player.getY() - 1) * MapVisual.SIZEIMAGE);
	}

	private void drawShadowWithLighter() {
		shadowNext.draw((player.getX() + xFacing) * MapVisual.SIZEIMAGE,
				(player.getY() + yFacing) * MapVisual.SIZEIMAGE);

		shadowPlayer.draw(player.getX() * MapVisual.SIZEIMAGE, player.getY()
				* MapVisual.SIZEIMAGE);

		if (monsters.getX(0) == player.getX() + xFacing
				&& monsters.getY(0) == player.getY() + yFacing)
			drawMonster(monsters);
	}
	

	/**
	 * A fun��o mostra que no momento do render deve considerar a a��o por um
	 * tempo determinado
	 */
	public void flare() {
		flare = true;
		flareTime = 0;
	}

	public void shootDirection(char direction) {
		explosionShoot = true;
		explosionTime = 0;
		int xExDir = 1, yExDir = 1;
		int xR = player.getX();
		int yR = player.getY();
		boolean wallFind = false;

		switch (direction) {
		case Facing.NORTH:
			yExDir = -1;
			xExDir = 0;
			break;
		case Facing.SOUTH:
			yExDir = 1;
			xExDir = 0;
			break;
		case Facing.WEST:
			xExDir = -1;
			yExDir = 0;
			break;
		case Facing.EAST:
			xExDir = 1;
			yExDir = 0;
		default:
			break;
		}

		while (!wallFind) {
			xR += xExDir;
			yR += yExDir;
			if (monsters.getX(0) == xR && monsters.getY(0) == yR) {
				explosionX = xR;
				explosionY = yR;
				wallFind = true;
			}
			try {
				if (!wallFind
						&& map.getTileAt(xR, yR).getType() != TileType.Walkable) {
					explosionX = xR;
					explosionY = yR;
					wallFind = true;
				}
			} catch (OutOfMapBoundsException e1) {
				e1.printStackTrace();
				wallFind = true;
			}
		}
	}

	private String nameItem(ItemsList item) {
		String name = null;
		switch (item) {
		case Flare:
			name = "flare";
			break;
		case Flash:
			name = "flash";
			break;
		case Fuel:
			name = "fuel";
			break;
		case SaltAmmo:
			name = "salt";
			break;
		case Stick:
			name = "stick";
			break;
		default:
			break;
		}

		return name;
	}
	
	public void setMessage(String messageTxt){
		this.messageTxt = messageTxt;
		message = true;
	}

	/**
	 * Reproduz o som de passos do monster
	 * 
	 * @param gain
	 */
	public void playFootstep(float gain) {
		footstepAudio.playAsSoundEffect(1.0f, gain, false);
	}

	private Image getImage(String path, HashMap<String, Image> map)
			throws SlickException {
		Image tile;
		if (imageMap.containsKey(path)) {
			tile = imageMap.get(path);
		} else {
			tile = new Image(path);
			imageMap.put(path, tile);
		}

		return tile;
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		gameController.setCommand(gc.getInput());

		gameController.update();

		flareTime += delta;

		explosionTime += delta;
	}

	/**
	 * Verifica qual o lado que o personagem est� olhando e define qual tile
	 * deve ser mostrado na tela
	 * 
	 * @param c
	 */
	public void faceSprite(char facing) {
		switch (facing) {
		case Facing.NORTH:
			spritePlayer = playerUp;
			shadowNext = shadowUp;
			yFacing = -1;
			xFacing = 0;
			break;
		case Facing.SOUTH:
			spritePlayer = playerDown;
			shadowNext = shadowDown;
			yFacing = 1;
			xFacing = 0;
			break;
		case Facing.WEST:
			spritePlayer = playerLeft;
			shadowNext = shadowLeft;
			xFacing = -1;
			yFacing = 0;
			break;
		case Facing.EAST:
			spritePlayer = playerRight;
			shadowNext = shadowRight;
			xFacing = 1;
			yFacing = 0;
			break;
		default:
			break;
		}
	}
}