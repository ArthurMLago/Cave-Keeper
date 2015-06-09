/* O que deve ser feito:
 *   - saber as coordenadas do player para implementar o metodo walk.
 *   - setar a posicao inicial dos monstros de acordo com o gerador do mapa.
 *   - completar o metodo walk nos outros monstros e implementar a caminhada aleatoria.
 *   - adicionar funcoes extras de manipulacao de monstros.
 * OK  - deixar o algoritmos de seguir o player um pouco mais aleatorio.
 *   - escolher imagem dos monstros
 *   - verificar qual funcao utilizar para reproduzir sons
 *  OK - criar documentacao javadocs */

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

@Component(id="<http://santanvarzea.com/monster.Monster>", provides={"<http://santanvarzea.com/monster.Interfaces.IMonster>"})
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
	 * @param f Fase atual do jogo.
	 * @return ArrayList dos monstros. */
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
	
	public void setMonsterPosition(int monsterID) {
		Position pos;
		pos = map.getSpawnPoint(list.get(monsterID));
		list.get(monsterID).setPosition(pos.getX(), pos.getY());
	}
	
	public void runMonstersActions(int monsterID) {
		int playerX = this.player.getX();
		int playerY = this.player.getY();
		
		
		AbstractMonster monster = list.get(monsterID);
		
		monster.emitSound(playerX, playerY);
		
		if (monster.getFollowing() == true) {
			monster.followWalk(playerX, playerY, map);
		}
		else if (monster.getFollowing() == false) {
			monster.randomWalk(map);
		}
	}
	
	public void setFollow() {
		for(int i = 0; i < list.size();i++) {
			list.get(i).setFollowing(true);
		}
	}
	
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
	public double getDistance (int monsterID) {
		double distance;
		distance = list.get(monsterID).getDistance(player.getX(), player.getY());
		return distance;
	}
	public int getX(int monsterID) {
		return list.get(monsterID).getX();
	}
	public int getY(int monsterID) {
		return list.get(monsterID).getY();
	}
	public String getImage(int monsterID) {
		return list.get(monsterID).getImage();
	}
}
