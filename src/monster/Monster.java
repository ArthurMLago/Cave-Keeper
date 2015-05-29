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
	
	/* Função iniciadora do componente. Gera os monstros e os increve no observer. */
	public ArrayList<AbstractMonster> generateMonsters() {

		AbstractMonster monsterToBeGenerated;
		AbstractMonsterFactory factoryToBeGenerated;
		/* OBS: falta setar a posicao dos monstros e outras coisas. Isso deve ser feita no
		 * metodo construtor da classe abstrata. */
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
	
	
	
}
