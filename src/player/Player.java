package player;

import anima.annotation.Component;
import anima.component.base.ComponentBase;
import map.enumerations.TileType;
import map.exceptions.OutOfMapBoundsException;
import map.interfaces.IGameMap;
import gameController.*;
import monster.Interfaces.*;
import items.excecoes.OutofItemsException;
import items.itemManagement.ItemManagement;
import items.itemManagement.ItemsList;
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
@Component(id="<http://santanvarzea.com/player.Player>", provides={"<http://santanvarzea.com/player.IPlayer>"})
public class Player extends ComponentBase  implements IPlayerPosition, IPlayerAction, IPlayerMax,
		Entidade {

	private int posX, posY;

	private char facing;

	private boolean lighter;

	private ItemManagement bag;

	private IMonster monster;
	
	private IGameMap map;
	
	/**
	 * Construtor �nico estabelece as condi��es de in�cio de jogo
	 */
	public Player(IMonster monster) {
		facing = Facing.SOUTH;
		lighter = false;
		bag = new ItemManagement();
		this.posX = 0;
		this.posY = 0;
		this.monster = monster;
	}

	/**
	 * M�todo para obter o nome da classe Player de forma simples
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
<<<<<<< HEAD
	 * @return dire��o para a qual o jogador est� olhando
=======
	 * @return direção para a qual o jogador está olhando
>>>>>>> 23fa0c6b39de5d31269b93786e63d4ff7668fc3e
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

		int event = GameController.getSharedInstance().getMap()
				.getTileAt(posX, posY).checkEventsAt();
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
		if (bag.displayNumber(ItemsList.SaltAmmo) == 0)
			return false;

		facing = direction;

		int x = posX, y = posY;

		int flag = 0;
		try {
			if (direction == 'E') {
				for (int i = x + 1; flag == 0; i++) {
					if ((TileType.Walkable.equals(GameController
							.getSharedInstance().getMap().getTileAt(i, y)
							.getType())))
						flag = 1;
					else if ((monster.getX(0) == i)
							&& (monster.getY(0) == posY))
						flag = 2;
				}
			}

			if (direction == 'W') {
				for (int i = x - 1; flag == 0; i--) {
					if ((TileType.Walkable.equals(GameController
							.getSharedInstance().getMap().getTileAt(i, y)
							.getType())))
						flag = 1;
					else if ((monster.getX(0) == i)
							&& (monster.getY(0) == posY))
						flag = 2;
				}
			}

			if (direction == 'N') {
				for (int i = y + 1; flag == 0; i++) {
					if ((TileType.Walkable.equals(GameController
							.getSharedInstance().getMap().getTileAt(x, i)
							.getType())))
						flag = 1;
					else if ((monster.getX(0) == posX)
							&& (monster.getY(0) == i))
						flag = 2;
				}
			}

			if (direction == 'S') {
				for (int i = y - 1; flag == 0; i--) {
					if ((TileType.Walkable.equals(GameController
							.getSharedInstance().getMap().getTileAt(x, i)
							.getType())))
						flag = 1;
					else if ((monster.getX(0) == posX)
							&& (monster.getY(0) == i))
						flag = 2;
				}
			}

		}

		catch (OutOfMapBoundsException erro) {
		}

		if (flag == 1)
			return false;
		if (flag == 2) {
			monster.getHit(0);
		}
			return true;

		return false;

	}

	/**
	 * Método que utiliza o item flare
	 */
	public boolean useFlare() {
		boolean usado = true;
		try {
			bag.useItem(ItemsList.Flare);
		} catch (OutofItemsException e) {
			usado = false;
		}
		return usado;
	}

	/**
	 * M�todo que utiliza o item stick
	 */
	public boolean useStick() {
		boolean usado = true;
		try {
			bag.useItem(ItemsList.Stick);
		} catch (OutofItemsException e) {
			usado = false;
		}
		
		return usado;
	}

	/**
	 * M�todo que utiliza o item flash
	 */
	public boolean useFlash() {
		boolean usado = true;
		try {
			bag.useItem(ItemsList.Flash);
		} catch (OutofItemsException e) {
			usado = false;
		}
		return usado;
	}
}
