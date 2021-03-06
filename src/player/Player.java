package player;

import java.io.Serializable;

import gameController.Entidade;
import items.excecoes.OutofItemsException;
import items.interfaces.IItemManagement;
import items.itemManagement.ItemManagement;
import items.itemManagement.ItemsList;
import map.enumerations.EventType;
import map.enumerations.TileType;
import map.events.Event;
import map.exceptions.OutOfMapBoundsException;
import map.interfaces.IGameMap;
import monster.Interfaces.IMonster;
import anima.annotation.Component;
import anima.component.base.ComponentBase;

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

@Component(id = "<http://cave.com/player.Player>", provides = { "<http://cave.com/player.IPlayerMax>" })
public class Player extends ComponentBase implements IPlayerPosition, IPlayerMax, Entidade, Serializable {

	private static final long serialVersionUID = 1L;

	private int posX, posY;

	private char facing;

	private boolean lighter;

	private IMonster monster;
	private IItemManagement bag;
	private IGameMap map;

	/**
	 * Construtor �nico estabelece as condi��es de in�cio de jogo
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
	 * <<<<<<< HEAD <<<<<<< HEAD
	 * 
	 * @return dire��o para a qual o jogador est� olhando ======= =======
	 *         >>>>>>> branch 'master' of
	 *         https://github.com/ArthurMLago/Cave-Keeper.git
	 * @return direção para a qual o jogador está olhando
	 */
	public char getFacing() {
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

	public void checkLighter() {
		if (getLighter()) {
			try {
				bag.useItem(ItemsList.Fuel);
			} catch (OutofItemsException e) {
				setLighter(false);
			}
		}
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
		System.out.println(direction);

		try {
			if (direction == Facing.NORTH) {
				if ((TileType.Walkable == map.getTileAt(posX, posY - 1).getType()))
					posY--;
				else
					return false;
			}

			else if (direction == Facing.SOUTH) {
				if ((TileType.Walkable.equals(map.getTileAt(posX, posY + 1)
						.getType())))
					posY++;
				else
					return false;
			}

			else if (direction == Facing.EAST) {
				if ((TileType.Walkable == map.getTileAt(posX + 1, posY).getType()))
					posX++;
				else
					return false;
			}

			else if (direction == Facing.WEST) {
				if ((TileType.Walkable == map.getTileAt(posX - 1, posY).getType()))
					posX--;
				else
					return false;
			} else
				return false;
		} catch (OutOfMapBoundsException erro) {
		}

		Event event;
		try {
			event = map.getTileAt(posX, posY).checkForEvents();
			if (event != null){
				if (event.getType() == EventType.ITEM) {
					map.getTileAt(posX, posY).triggerEvent();
					map.getTileAt(posX, posY).discardEvent();
				}else if(event.getType() == EventType.TRAP){
					map.getTileAt(posX, posY).triggerEvent();
					map.getTileAt(posX, posY).discardEvent();
				}
			}
		} catch (OutOfMapBoundsException e) {
		}
		/*
		 * try { event = map.getTileAt(posX,
		 * posY).checkForEvents(EventType.TRAP); if (event != null && event
		 * instanceof EventTrap) { map.getTileAt(posX,
		 * posY).triggerEventOfType(EventType.TRAP); map.getTileAt(posX,
		 * posY).discardEventOfType(EventType.TRAP); } } catch
		 * (OutOfMapBoundsException erro) { }
		 */
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

		try {
			bag.useItem(ItemsList.SaltAmmo);
		} catch (OutofItemsException e) {
			return false;
		}

		monster.setFollow();

		facing = direction;

		int x = posX, y = posY;

		int flag = 0;
		try {
			if (direction == Facing.EAST) {
				for (int i = x + 1; flag == 0; i++) {
					System.out.println("X:" + x + "Y:" + y);
					if ((monster.getX(0) == i) && (monster.getY(0) == posY))
						flag = 2;
					else if ((TileType.Walkable != map.getTileAt(i, y)
							.getType()))
						flag = 1;

				}
			}

			if (direction == Facing.WEST) {
				for (int i = x - 1; flag == 0; i--) {
					System.out.println("X:" + x + "Y:" + y);
					if ((monster.getX(0) == i) && (monster.getY(0) == posY))
						flag = 2;
					else if ((TileType.Walkable != map.getTileAt(i, y)
							.getType()))
						flag = 1;
				}
			}

			if (direction == Facing.NORTH) {
				System.out.println("X:" + x + "Y:" + y);
				for (int i = y - 1; flag == 0; i--) {
					if ((monster.getX(0) == posX) && (monster.getY(0) == i))
						flag = 2;
					else if ((TileType.Walkable != map.getTileAt(x, i)
							.getType()))
						flag = 1;
				}
			}

			if (direction == Facing.SOUTH) {
				System.out.println("X:" + x + "Y:" + y);
				for (int i = y + 1; flag == 0; i++) {
					if ((monster.getX(0) == posX) && (monster.getY(0) == i))
						flag = 2;
					else if ((TileType.Walkable != map.getTileAt(x, i)
							.getType()))
						flag = 1;
				}
			}

		}

		catch (OutOfMapBoundsException erro) {
		}

		System.out.println("Flag: " + flag);

		if (flag == 2) {
			monster.getHit(0);
		}
		return true;
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
			
			Event event;
			System.out.println("item usado");
			
			try {
				event = map.getTileAt(posX + 1, posY).checkForEvents();
				if (event != null){
					if(event.getType() == EventType.TRAP) {
						map.getTileAt(posX + 1, posY).discardEvent();
					}
				}
			} catch (OutOfMapBoundsException e) {
			}
			
			try {
				event = map.getTileAt(posX - 1, posY).checkForEvents();
				if (event != null){
					if(event.getType() == EventType.TRAP) {
						map.getTileAt(posX - 1, posY).discardEvent();
					}
				}
			} catch (OutOfMapBoundsException e) {
			}
			
			try {
				event = map.getTileAt(posX, posY + 1).checkForEvents();
				if (event != null){
					if(event.getType() == EventType.TRAP) {
						map.getTileAt(posX, posY + 1).discardEvent();
					}
				}
			} catch (OutOfMapBoundsException e) {
			}
			
			try {
				event = map.getTileAt(posX, posY - 1).checkForEvents();
				if (event != null){
					if(event.getType() == EventType.TRAP) {
						map.getTileAt(posX, posY - 1).discardEvent();
					}
				}
			} catch (OutOfMapBoundsException e) {
			}
			
			
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

	@Override
	public void connect(IMonster arg0, IItemManagement arg1, IGameMap arg2) {
		this.monster = arg0;
		this.bag = arg1;
		this.map = arg2;
	}
}
