package monster.Interfaces;

import monster.Fonte.AbstractMonster;

public interface AbstractMonsterFactory {
	
	public AbstractMonster createGuardian(String monsterType);
	public AbstractMonster createGhost(String monsterType);
	
}
