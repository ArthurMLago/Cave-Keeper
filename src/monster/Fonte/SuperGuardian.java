package monster.Fonte;

/** Classe herdeira de AbstractMonster que representa um super guardian. */
public class SuperGuardian extends AbstractMonster {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Inicializa os atributos caracteristicos desse monstro. */
	public SuperGuardian() {
		hp = 2;
		spaces = 1;
	}
	
	@Override
	public String getImage() {
		return "imgGuardianSuper";
	}
}
