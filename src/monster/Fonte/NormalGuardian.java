package monster.Fonte;

/** Classe herdeira de AbstractMonster que representa um normal guardian. */
public class NormalGuardian extends AbstractMonster {

	/** Inicializa os atributos caracteristicos desse monstro. */
	public NormalGuardian() {
		hp = 1;
		spaces = 1;
	}
	
	@Override
	public String getImage() {
		return "imgGuardianNormal";
	}
	
	@Override
	public void walk() {
		// TODO Auto-generated method stub

	}

}
