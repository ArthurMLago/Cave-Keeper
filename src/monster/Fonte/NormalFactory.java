package monster.Fonte;

import monster.Interfaces.AbstractMonsterFactory;

public class NormalFactory implements AbstractMonsterFactory {

	@Override
	public AbstractMonster createSuper(String monsterType) {
		return null;
	}

	@Override
	public AbstractMonster createNormal(String monsterType) {
		if (monsterType.equalsIgnoreCase("Guardian"))
			return new NormalGuardian();
		else if (monsterType.equalsIgnoreCase("Normal"))
			return new NormalGhost();
		else
			return null;

	}

}
