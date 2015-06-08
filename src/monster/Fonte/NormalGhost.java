package monster.Fonte;

/** Classe herdeira de AbstractMonster que representa um normal ghost. */
public class NormalGhost extends AbstractMonster {

	private static final long serialVersionUID = 1L;

	/** Inicializa os atributos caracteristicos desse monstro. */
	public NormalGhost() {
		hp = 1;
		spaces = 1;
	}
	
	@Override
	public String getImage() {
		return "imgGhostNormal";
	}

}
