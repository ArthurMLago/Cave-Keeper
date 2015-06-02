/* O que deve ser feito:
 *   - saber as coordenadas do player para implementar o metodo walk.
 *   - setar a posicao inicial dos monstros de acordo com o gerador do mapa.
 *   - completar o metodo walk nos outros monstros e implementar a caminhada aleatoria.
 *   - adicionar funcoes extras de manipulacao de monstros.
 *   - deixar o algoritmos de seguir o player um pouco mais aleatorio. */

package monster;

import monster.Interfaces.*;
import anima.component.base.ComponentBase;
import monster.Fonte.*;
import java.util.ArrayList;

public class Monster extends ComponentBase {
	
	int fase;
	ArrayList<AbstractMonster> list;
	
	public Monster() {
		fase = 1;
		list = new ArrayList<AbstractMonster>();
	}
	
	/* Função iniciadora do componente. */
	public ArrayList<AbstractMonster> generateMonsters() {

		AbstractMonster monsterToBeGenerated;
		AbstractMonsterFactory factoryToBeGenerated;

		if(fase == 1) {
			factoryToBeGenerated = FactoryGenerator.getFactory("guardian");
			monsterToBeGenerated = factoryToBeGenerated.createGuardian("normal");
			list.add(monsterToBeGenerated);
		}
		else if(fase == 2) {
			factoryToBeGenerated = FactoryGenerator.getFactory("ghost");
			monsterToBeGenerated = factoryToBeGenerated.createGhost("normal");
			list.add(monsterToBeGenerated);
		}
		else if(fase == 3) {
			factoryToBeGenerated = FactoryGenerator.getFactory("ghost");
			monsterToBeGenerated = factoryToBeGenerated.createGhost("super");
			list.add(monsterToBeGenerated);
		}
		else if(fase == 4) {
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
		
		return list;
	}
	
	/* Retorna um boolean se os monstros estao vivos. */
	public boolean isMonstersAlive() {
		
		boolean vivos = true;
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).isAlive() == false)
				vivos = false;
		}
		return vivos;
			
	}
	
	public AbstractMonster getMonster() {
		if(fase >= 1 && fase < 5)
			return list.get(0);
		else
			return list.get(0); /*Ajustar*/
	}
	
	
	
}
