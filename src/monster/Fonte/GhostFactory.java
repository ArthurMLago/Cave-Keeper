package monster.Fonte;

import monster.Interfaces.AbstractMonsterFactory;

public class GhostFactory implements AbstractMonsterFactory {

	@Override
	public AbstractMonster createGhost(String monsterType) {
		if (monsterType.equalsIgnoreCase("normal"))
			return new NormalGhost();
		else if (monsterType.equalsIgnoreCase("super"))
			return new SuperGhost();
		else
			return null;
	}

	@Override
	public AbstractMonster createGuardian(String monsterType) {
		return null;
	}

}
