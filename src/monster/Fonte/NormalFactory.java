package Fonte;

import Interfaces.AbstractMonsterFactory;

public class NormalFactory implements AbstractMonsterFactory {

	@Override
	public AbstractMonster createSuper(String monsterType) {
		return null;
	}

	@Override
	public AbstractMonster createNormal(String monsterType) {
		if(monsterType.equalsIgnoreCase("Guardian"))
			return new NormalGuardian();
		else
			return new NormalGhost();

	}

}
