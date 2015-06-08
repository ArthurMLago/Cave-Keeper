package monster.Fonte;
import map.*;

/** Classe herdeira de AbstractMonster que representa um super ghost. */
public class SuperGhost extends AbstractMonster {

	/** Inicializa os atributos caracteristicos desse monstro. */
	public SuperGhost() {
		hp = 2;
		spaces = 1;
	}
	
	@Override
	public String getImage() {
		return "imgGhostSuper";
	}
	
	@Override
	public void walk() {
	}

}
