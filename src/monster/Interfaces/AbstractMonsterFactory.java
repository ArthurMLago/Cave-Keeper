package monster.Interfaces;

import monster.Fonte.AbstractMonster;

public interface AbstractMonsterFactory {
	
	public AbstractMonster createSuper(String monsterType);
	public AbstractMonster createNormal(String monsterType);
	
}
