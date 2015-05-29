package monster.Fonte;

import monster.Interfaces.AbstractMonsterFactory;

public class GuardianFactory implements AbstractMonsterFactory {

	@Override
	public AbstractMonster createGhost(String monsterType) {
		return null;
	}

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
