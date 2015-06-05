package visual;

import gameController.Entidade;
import gameController.IGameController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import map.GameMap;
import map.enumerations.TileType;
import map.exceptions.OutOfMapBoundsException;

import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

import player.Facing;
import player.IPlayerPosition;
import anima.component.IRequires;
import anima.component.ISupports;
import anima.component.InterfaceType;

/**
 * Mapa que realiza a interação com a biblioteca utilizado para imprimir as
 * informações na janela
 * 
 * @author eitiyamamoto
 *
 */
public class SlickMap extends BasicGame implements IRequires<IGameController> {
	private Animation spritePlayer, playerUp, playerDown, playerLeft,
			playerRight;
	private Animation shadowPlayer, shadowUp, shadowDown, shadowLeft,
			shadowRight, shadowNext;
	private int x = 32, y = 32;
	private int xFacing, yFacing;
	private IGameController gameController;
	private IPlayerPosition player;
	private GameMap map;
	private String character = "cat";
	private int duration = 10;
	private boolean flare = false;
	private int flareTime;
	private HashMap<String, Image> imageMap;
	private Audio footstepAudio;
	private ArrayList<Entidade> entidades;
	private boolean explosionShoot = false;
	private int explosionX, explosionY, explosionTime;

	public SlickMap(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		faceSprite(player.getFacing());
		drawTile(player.getX(), player.getY());
		drawTile(player.getX() + xFacing, player.getY() + yFacing);

		if (flare) {
			drawFlare();
		} else {
			shadowNext.draw((player.getX() + xFacing) * this.x,
					(player.getY() + yFacing) * this.y);

			shadowPlayer.draw(player.getX() * x, player.getY() * y);

			for (Entidade e : entidades) {
				if (e.getX() == player.getX() + xFacing
						&& e.getY() == player.getY() + yFacing)
					drawEntidade(e);
			}
		}
		if (explosionShoot)
			drawExplosion();

		spritePlayer.draw(player.getX() * x, player.getY() * y);
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		Image[] movementUp = { new Image("resource/character/" + character
				+ "_up.png") };
		Image[] movementDown = { new Image("resource/character/" + character
				+ "_down.png") };
		Image[] movementLeft = { new Image("resource/character/" + character
				+ "_left.png") };
		Image[] movementRight = { new Image("resource/character/" + character
				+ "_right.png") };

		// Iniciando as animações
		playerUp = new Animation(movementUp, duration, false);
		playerDown = new Animation(movementDown, duration, false);
		playerLeft = new Animation(movementLeft, duration, false);
		playerRight = new Animation(movementRight, duration, false);

		String pathShadow = "resource/tile/shadow/";
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

		String pathAudio = "resource/audio/";
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
	 * Verifica qual o lado que o personagem está olhando e define qual tile
	 * deve ser mostrado na tela
	 * 
	 * @param c
	 */
	private void faceSprite(int facing) {
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
		case Facing.WEST:
			spritePlayer = playerLeft;
			shadowNext = shadowLeft;
			xFacing = -1;
			yFacing = 0;
		case Facing.EAST:
			spritePlayer = playerRight;
			shadowNext = shadowRight;
			xFacing = 1;
			yFacing = 0;
		default:
			break;
		}
	}

	/**
	 * Desenha o tile desejado Só funciona quando chamado no render
	 * 
	 * @param x
	 *            - Posição x do tile a ser desenhado
	 * @param y
	 *            - POsição Y do tile a ser desenhado
	 */
	private void drawTile(int x, int y) {
		try {
			if (map.getTileAt(x, y).getType() != TileType.Void) {
				Image tile = getImage("resource/tile/"
						+ map.getTileAt(x, y).getImage() + ".png", imageMap);

				Image[] tiles = { tile };
				Animation tileAnimation = new Animation(tiles, 1000);
				tileAnimation.draw(x * this.x, y * this.y);
			}
		} catch (SlickException e) {
			System.out.println(e);
		} catch (OutOfMapBoundsException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Desenha a entidade desejada Só funciona quando chamado no render
	 * 
	 * @param e
	 *            - Entidade a ser desenhada
	 */
	private void drawEntidade(Entidade e) {
		try {
			Image tile = getImage("resource/monster/" + e.getImage() + ".png",
					imageMap);

			Image[] tiles = { tile };
			Animation tileAnimation = new Animation(tiles, 1000);
			tileAnimation.draw(e.getX() * this.x, e.getY() * this.y);
		} catch (SlickException ex) {
			System.out.println(ex);
		}
	}

	private void drawFlare() {
		for (int xR = 0; xR < map.getLimiteX(); xR++) {
			for (int yR = 0; yR < map.getLimiteY(); yR++) {
				drawTile(xR, yR);
			}
		}

		for (Entidade e : entidades) {
			drawEntidade(e);
		}

		if (flareTime > 2000)
			flare = false;
	}

	private void drawExplosion() {
		Image explosionImage;
		try {
			explosionImage = getImage("resource/shoot/explosion.png", imageMap);
			Image[] explosions = { explosionImage };
			Animation explosionAnimation = new Animation(explosions, 1000);
			explosionAnimation.draw(explosionX * this.x, explosionY * this.y);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		if (explosionTime > 800)
			explosionShoot = false;

	}

	/**
	 * A função mostra que no momento do render deve considerar a ação por um
	 * tempo determinado
	 */
	public void flare() {
		flare = true;
		flareTime = 0;
	}

	public void shoot() {
		explosionShoot = true;
		explosionTime = 0;
		int xR = player.getX();
		int yR = player.getY();
		boolean wallFind = false;
		while (!wallFind) {
			xR += xFacing;
			yR += yFacing;
			for (Entidade e : entidades) {
				if (e.getX() == xR && e.getY() == yR) {
					explosionX = xR;
					explosionY = yR;
					wallFind = true;
				}
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

	/**
	 * Reproduz o som de passos do monstro
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

	@Override
	public void connect(IGameController gameController) {
		this.gameController = gameController;
		map = gameController.getMap();
		player = (IPlayerPosition) gameController.getPlayer();
		entidades = gameController.getEntidades();
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
