package player;

import anima.annotation.Component;
import anima.component.base.ComponentBase;
import map.enumerations.TileType;
import map.exceptions.OutOfMapBoundsException;
import gameController.*;
import monster.Interfaces.*;
import items.itemManagement.ItemManagement;
import player.IPlayerMax;

/**
 * Classe que armazena as informa��es e implemneta todas as a��es do jogador.
 * <p>
 * Um objeto da classe Player mant�m a sua posi��o e os seus itens. Seus m�todos
 * implementam as a��es possiveis do jogador registrando todas as mudan�as de
 * estado e comunicando todos os outros componentes do jogo.
 * 
 * @author Guilherme I. M. de Ara�jo
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

	/**
	 * Construtor �nico que estabelece as condi��es de in�cio de jogo
	 */
	public Player() {
		facing = Facing.SOUTH;
		lighter = false;
		bag = new ItemManagement();
		this.posX = 0;
		this.posY = 0;
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
	 * M�todo que estabelece a posi��o inicial do jogador no mapa
	 * 
	 * @param x
	 *            posi��o horizontal desejada
	 * @param y
	 *            posi��o vertical desejada
	 */
	public void setSpawnPointPlayer(int x, int y) {
		this.posX = x;
		this.posY = y;
	}

	/**
	 * @return posi��o horizontal do jogador
	 */
	public int getX() {
		return posX;
	}

	/**
	 * @return posi��o vertical do jogador
	 */
	public int getY() {
		return posY;
	}

	/**
	 * @return dire��o para a qual o jogador est� olhando
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
	 * M�todo que muda o estado da lamparina, se est� ligado, ele desliga e vice
	 * versa
	 */
	public void setLighter() {
		if (lighter)
			lighter = false;
		else
			lighter = true;
	}

	/**
	 * M�todo que muda o estado da lamparina para um desejado
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
	 *            caractere mai�sculo que indica a dire��o cardeal para a qual
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
	 * M�todo que dirpara a arma em uma certa dire��o
	 * 
	 * @param direction
	 *            caractere mai�sculo que indica a dire��o cardeal para a qual
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
	 * M�todo que utiliza o item flare
	 */
	public void useFlare() {
		bag.useItem(0/* FLARE */);
	}

	/**
	 * M�todo que utiliza o item stick
	 */
	public void useStick() {
		bag.useItem(5);
	}
}
