package monster.Fonte;

import monster.Interfaces.AbstractMonsterFactory;

/** Gerador de fabricas do tipo AbstractFactory. */
public class FactoryGenerator {

	/** Gera uma fabrica de monstros do tipo guardian ou do tipo ghost.
	 * @param factoryName Nome do tipo de fabrica a ser criada.
	 * @return Retorna referencia para uma fabrica. */
	public static AbstractMonsterFactory getFactory(String factoryName) {
		
		AbstractMonsterFactory aux = null;
		if(factoryName.equalsIgnoreCase("guardian"))
			aux = new GuardianFactory();
		else if (factoryName.equalsIgnoreCase("ghost")){
			aux = new GhostFactory();
		}
		
		return aux;
	}
	
}
