package monster.Fonte;

import monster.Interfaces.AbstractMonsterFactory;

/** Fabrica de monstros do tipo ghost. */
public class GhostFactory implements AbstractMonsterFactory {

	/** Cria um ghost do tipo normal ou do tipo super.
	 * @param monsterType Especifica o tipo de ghost.
	 * @return Retorna uma referencia para o monstro pedido. */
	@Override
	public AbstractMonster createGhost(String monsterType) {
		if (monsterType.equalsIgnoreCase("normal"))
			return new NormalGhost();
		else if (monsterType.equalsIgnoreCase("super"))
			return new SuperGhost();
		else
			return null;
	}

	/** Metodo inutil, retorna nulo. */
	@Override
	public AbstractMonster createGuardian(String monsterType) {
		return null;
	}

}
