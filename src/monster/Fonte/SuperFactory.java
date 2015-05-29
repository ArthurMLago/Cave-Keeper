package monster.Fonte;

import monster.Interfaces.AbstractMonsterFactory;

public class SuperFactory implements AbstractMonsterFactory {

	@Override
	public AbstractMonster createSuper(String monsterType) {
		if (monsterType.equalsIgnoreCase("Guardian"))
			return new SuperGuardian();
		else if (monsterType.equalsIgnoreCase("Ghost"))
			return new SuperGhost();
		else
			return null;
	}

	@Override
	public AbstractMonster createNormal(String monsterType) {
		return null;
	}

}
