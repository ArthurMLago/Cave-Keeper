package player;

import map.enumerations.TileType;
import map.exceptions.OutOfMapBoundsException;
import gameController.*;
import monster.Interfaces.*;
import items.itemManagement.ItemManagement;
import player.IPlayerMax;

/**
 * Classe que armazena as informações e implemneta todas as ações do jogador.
 * <p>
 * Um objeto da classe Player mantém a sua posição e os seus itens. Seus métodos
 * implementam as ações possiveis do jogador registrando todas as mudanças de
 * estado e comunicando todos os outros componentes do jogo.
 * 
 * @author Guilherme I. M. de Araújo
 * @author Diego S. Martines
 *
 */
public class Player implements IPlayerPosition, IPlayerAction, IPlayerMax,
		Entidade {

	private int posX, posY;

	private char facing;

	private boolean lighter;

	private ItemManagement bag;

	/**
	 * Construtor único que estabelece as condições de início de jogo
	 */
	public Player() {
		facing = Facing.SOUTH;
		lighter = false;
		bag = new ItemManagement();
		this.posX = 0;
		this.posY = 0;
	}

	/**
	 * Método para obter o nome da classe Player de forma simples
	 */
	public String getTipo() {
		return "player";
	}

	/**
	 * @return caminho da imagem do jogador
	 * @deprecated
	 */
	public String getImage() {		
		return null;
	}

	/**
	 * Método que estabelece a posição inicial do jogador no mapa
	 * 
	 * @param x
	 *            posição horizontal desejada
	 * @param y
	 *            posição vertical desejada
	 */
	public void setSpawnPointPlayer(int x, int y) {
		this.posX = x;
		this.posY = y;
	}

	/**
	 * @return posição horizontal do jogador
	 */
	public int getX() {
		return posX;
	}

	/**
	 * @return posição vertical do jogador
	 */
	public int getY() {
		return posY;
	}

	/**
	 * @return direção para a qual o jogador está olhando
	 */
	public int getFacing() {
		return facing;
	}

	/**
	 * @return boolean que indica o estado ligado/desligado da lamparina
	 */
	public boolean getLighter() {
		return lighter;
	}

	/**
	 * Método que muda o estado da lamparina, se está ligado, ele desliga e vice
	 * versa
	 */
	public void setLighter() {
		if (lighter)
			lighter = false;
		else
			lighter = true;
	}

	/**
	 * Método que muda o estado da lamparina para um desejado
	 * 
	 * @param state
	 *            estado desejado para a lamparina
	 */
	public void setLighter(boolean state) {
		lighter = state;
	}

	/**
	 * Metodo que move o personagem
	 * 
	 * @param direction
	 *            caractere maiúsculo que indica a direção cardeal para a qual
	 *            se deseja andar
	 * @return verdadeiro se o movimento foi efetuado com sucesso
	 */
	public boolean move(char direction) {
		facing = direction;

		try {
			if (direction == Facing.NORTH) {
				if ((TileType.Walkable.equals(GameController
						.getSharedInstance().getMap().getTileAt(posX, posY + 1)
						.getType())))
					posY++;
				else
					return false;
			}

			else if (direction == Facing.SOUTH) {
				if ((TileType.Walkable.equals(GameController
						.getSharedInstance().getMap().getTileAt(posX, posY - 1)
						.getType())))
					posY--;
				else
					return false;
			}

			else if (direction == Facing.EAST) {
				if ((TileType.Walkable.equals(GameController
						.getSharedInstance().getMap().getTileAt(posX + 1, posY)
						.getType())))
					posX++;
				else
					return false;
			}

			else if (direction == Facing.WEST) {
				if ((TileType.Walkable.equals(GameController
						.getSharedInstance().getMap().getTileAt(posX - 1, posY)
						.getType())))
					posX--;
				else
					return false;
			} else
				return false;
		} catch (OutOfMapBoundsException erro) {
		}
		
		int event = GameController.getSharedInstance().getMap().getTileAt(posX, posY).checkEvents();
		bag.obtainItem(event);
		
		return true;
	}

	/**
	 * Método que dirpara a arma em uma certa direção
	 * 
	 * @param direction
	 *            caractere maiúsculo que indica a direção cardeal para a qual
	 *            se deseja atirar
	 * @return verdadeiro se o tiro acertou o monstro
	 */
	public boolean shoot(char direction) {
		if (bag.displayNumber(4/* AMMO */) == 0)
			return false;

		facing = direction;

		int x = posX, y = posY;

		IMonster monsterReference = GameController.getSharedInstance()
				.getEntidades();

		int flag = 0;
		try {
			if (direction == 'E') {
				for (int i = x + 1; flag == 0; i++) {
					if ((TileType.Walkable.equals(GameController
							.getSharedInstance().getMap().getTileAt(i, y)
							.getType())))
						flag = 1;
					else if ((monsterReference.getX() == i)
							&& (monsterReference.getY() == posY))
						flag = 2;
				}
			}

			if (direction == 'W') {
				for (int i = x - 1; flag == 0; i--) {
					if ((TileType.Walkable.equals(GameController
							.getSharedInstance().getMap().getTileAt(i, y)
							.getType())))
						flag = 1;
					else if ((monsterReference.getX() == i)
							&& (monsterReference.getY() == posY))
						flag = 2;
				}
			}

			if (direction == 'N') {
				for (int i = y + 1; flag == 0; i++) {
					if ((TileType.Walkable.equals(GameController
							.getSharedInstance().getMap().getTileAt(x, i)
							.getType())))
						flag = 1;
					else if ((monsterReference.getX() == posX)
							&& (monsterReference.getY() == i))
						flag = 2;
				}
			}

			if (direction == 'S') {
				for (int i = y - 1; flag == 0; i--) {
					if ((TileType.Walkable.equals(GameController
							.getSharedInstance().getMap().getTileAt(x, i)
							.getType())))
						flag = 1;
					else if ((monsterReference.getX() == posX)
							&& (monsterReference.getY() == i))
						flag = 2;
				}
			}

		}

		catch (OutOfMapBoundsException erro) {
		}

		if (flag == 1)
			return false;
		if (flag == 2)
			return true;

		return false;

	}

	/**
	 * Método que utiliza o item flare
	 */
	public void useFlare() {
		bag.useItem(0/* FLARE */);
	}

	/**
	 * Método que utiliza o item stick
	 */
	public void useStick() {
		bag.useItem(5);
	}
}
