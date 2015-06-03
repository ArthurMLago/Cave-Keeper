package monster.Fonte;

/** Classe herdeira de AbstractMonster que representa um normal guardian. */
public class NormalGuardian extends AbstractMonster {

	/** Inicializa os atributos caracteristicos desse monstro. */
	public NormalGuardian() {
		hp = 1;
		spaces = 1;
	}
	
	@Override
	public int getImage() {
		return 0/*Por enquanto. */;
	}
	
	@Override
	public void walk() {
		// TODO Auto-generated method stub

	}

}
