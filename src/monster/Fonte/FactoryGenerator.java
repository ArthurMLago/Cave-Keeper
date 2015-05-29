package monster.Fonte;

import monster.Interfaces.AbstractMonsterFactory;

public class FactoryGenerator {

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
