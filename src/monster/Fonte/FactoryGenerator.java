package monster.Fonte;

import monster.Interfaces.AbstractMonsterFactory;

public class FactoryGenerator {

	public static AbstractMonsterFactory getFactory(String factoryName) {
		
		AbstractMonsterFactory aux = null;
		if(factoryName.equalsIgnoreCase("super"))
			aux = new SuperFactory();
		else if (factoryName.equalsIgnoreCase("normal")){
			aux = new NormalFactory();
		}
		
		return aux;
	}
	
}
