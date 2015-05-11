package Interfaces;

public interface AbstractMonsterFactory {
	
	public AbstractMonster createSuper(String monsterType);
	public AbstractMonster createNormal(String monsterType);
	
}
