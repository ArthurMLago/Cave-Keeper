package monster;

import monster.Interfaces.*;
import monster.Fonte.*;

import java.util.Random;
import java.util.ArrayList;

public class Monster {
	
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
			factoryToBeGenerated = FactoryGenerator.getFactory("normal");
			monsterToBeGenerated = factoryToBeGenerated.createNormal("guardian");
			list.add(monsterToBeGenerated);
		}
		else if(fase == 2) {
			factoryToBeGenerated = FactoryGenerator.getFactory("normal");
			monsterToBeGenerated = factoryToBeGenerated.createNormal("ghost");
			list.add(monsterToBeGenerated);
		}
		else if(fase == 3) {
			factoryToBeGenerated = FactoryGenerator.getFactory("super");
			monsterToBeGenerated = factoryToBeGenerated.createNormal("ghost");
			list.add(monsterToBeGenerated);
		}
		else if(fase == 4) {
			factoryToBeGenerated = FactoryGenerator.getFactory("super");
			monsterToBeGenerated = factoryToBeGenerated.createNormal("guardian");
			list.add(monsterToBeGenerated);
		}
		else {
			for(int i = 0; i < 2; i++) {
				factoryToBeGenerated = FactoryGenerator.getFactory("normal");
				monsterToBeGenerated = factoryToBeGenerated.createNormal("ghost");
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
