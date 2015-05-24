public class FactoryGenerator {

	public static AbstractMonsterFactory getFactory(String factoryName) {
		
		AbstractMonsterFactory aux;
		if(factoryName.equalsIgnoreCase("super"))
			aux = new SuperFactory();
		else {
			aux = new NormalFactory();
		}
		
		return aux;
	}
	
}
