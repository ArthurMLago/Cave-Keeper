package monster;

import map.Position;
import map.interfaces.IGameMap;
import player.IPlayerMax;
import monster.Interfaces.*;
import anima.annotation.Component;
import anima.component.base.ComponentBase;
import monster.Fonte.*;

import java.util.ArrayList;

/** Classe de gerenciamento dos monstros. Realiza a interacao com o game controller.
 * @author Mateus Coelho
 * @author Pedro Ono */

@Component(id="<http://cave.com/monster.Monster>", provides={"<http://cave.com/monster.Interfaces.IMonster>"})
public class Monster extends ComponentBase implements IMonster {	

	IPlayerMax player;
	IGameMap map;

	private ArrayList<AbstractMonster> list;
	
	public Monster() {
		list = new ArrayList<AbstractMonster>();
	}
	
	public void connect (IPlayerMax player, IGameMap map)  {
		this.player = player;
		this.map = map;
	}
	
	/** Gera os monstros de acordo com o nivel do jogo.
	 * @param f Fase atual do jogo. */
	public void generateMonsters(int f) {

		AbstractMonster monsterToBeGenerated;
		AbstractMonsterFactory factoryToBeGenerated;

		if(f == 1) {
			factoryToBeGenerated = FactoryGenerator.getFactory("guardian");
			monsterToBeGenerated = factoryToBeGenerated.createGuardian("normal");
			list.add(monsterToBeGenerated);
		}
		else if(f == 2) {
			factoryToBeGenerated = FactoryGenerator.getFactory("ghost");
			monsterToBeGenerated = factoryToBeGenerated.createGhost("normal");
			list.add(monsterToBeGenerated);
		}
		else if(f == 3) {
			factoryToBeGenerated = FactoryGenerator.getFactory("ghost");
			monsterToBeGenerated = factoryToBeGenerated.createGhost("super");
			list.add(monsterToBeGenerated);
		}
		else if(f == 4) {
			factoryToBeGenerated = FactoryGenerator.getFactory("guardian");
			monsterToBeGenerated = factoryToBeGenerated.createGuardian("super");
			list.add(monsterToBeGenerated);
		}
		else {
			for(int i = 0; i < 2; i++) {
				factoryToBeGenerated = FactoryGenerator.getFactory("ghost");
				monsterToBeGenerated = factoryToBeGenerated.createGhost("normal");
				list.add(monsterToBeGenerated);
			}
		}	
		
	}
	
	/** Seta a posicao inicial dos monstros de acordo com as coordenadas geradas pelo mapa.
	 * @param monsterID Identificacao do monstro cuja posicao incial sera setada. */
	public void setMonsterPosition(int monsterID) {
		Position pos;
		pos = map.getSpawnPoint(list.get(monsterID));
		list.get(monsterID).setPosition(pos.getX(), pos.getY());
	}
	
	/** Chama o metodo de movimentacao do monstro para seguir o player ou andar randomicamente,
	 * @param monsterID Identificacao do monstro que sera movido. */
	public void runMonstersActions(int monsterID) {
		
		int playerX = this.player.getX();
		int playerY = this.player.getY();
		
		AbstractMonster monster = list.get(monsterID);
		
		if (monster.getFollowing() == false) {
			monster.randomWalk(map);
		}
		
		else if (monster.getFollowing() == true) {
			for (int i = 0; i < monster.getSpaces(); i++) {
				monster.followWalk(playerX, playerY, map);
			}
			
		}
	}
	
	/** Seta os monstros para seguir o player. */
	public void setFollow() {
		for(int i = 0; i < list.size();i++) {
			list.get(i).setFollowing(true);
		}
	}
	
	/** Chamado quando o monstro recebe um tiro. Atualiza a vida do monstro.
	 * @param monsterID Identificacao do monstro cuja coordenada X sera retornada. */
	public void getHit(int monsterID) {
		list.get(monsterID).takeShot();
	}
	
	/** Remove a lista de monstros */
	public void deleteMonsters() {
		list.clear();
	}

	/** Verifica se todos os monstros estao vivos. */
	public boolean isMonstersAlive() {
		
		boolean vivos = true;
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).isAlive() == false)
				vivos = false;
		}
		return vivos;
	}
	
	/** Calcula a distancia entre o player e o monstro.
	 * @param monsterID Identificacao do monstro cuja distancia deve ser calculada. */
	public double getDistance (int monsterID) {
		double distance;
		distance = list.get(monsterID).getDistance(player.getX(), player.getY());
		return distance;
	}
	
	/** Pega a coordenada X do monstro.
	 * @param monsterID Identificacao do monstro cuja coordenada X sera retornada. */
	public int getX(int monsterID) {
		return list.get(monsterID).getX();
	}
	

	/** Pega a coordenada Y do monstro.
	 * @param monsterID Identificacao do monstro cuja coordenada Y sera retornada. */
	public int getY(int monsterID) {
		return list.get(monsterID).getY();
	}
	
	/** Retorna o nome da imagem do monstro.
	 * @param monsterID Identificacao do montro cujo nome da imagem sera retornado. */
	public String getImage(int monsterID) {
		return list.get(monsterID).getImage();
	}
}
