package monster.Fonte;

import monster.Interfaces.AbstractMonsterFactory;

public class SuperFactory implements AbstractMonsterFactory {

	@Override
	public AbstractMonster createSuper(String monsterType) {
		if(monsterType.equalsIgnoreCase("Guardian"))
			return new SuperGuardian();
		else
			return new SuperGhost();
	}

	@Override
	public AbstractMonster createNormal(String monsterType) {
		return null;
	}

}
