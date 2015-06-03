package monster.Fonte;

import monster.Interfaces.AbstractMonsterFactory;

/** Fabrica de monstros do tipo guardian. */
public class GuardianFactory implements AbstractMonsterFactory {

	/** Metodo inutil, retorna nulo. */
	@Override
	public AbstractMonster createGhost(String monsterType) {
		return null;
	}

	/** Cria um guardian do tipo normal ou do tipo super.
	 * @param monsterType Especifica o tipo de guardian.
	 * @return Retorna uma referencia para o monstro pedido. */
	@Override
	public AbstractMonster createGuardian(String monsterType) {
		if (monsterType.equalsIgnoreCase("normal"))
			return new NormalGuardian();
		else if (monsterType.equalsIgnoreCase("super"))
			return new SuperGuardian();
		else
			return null;

	}

}
