package monster.Interfaces;

import monster.Fonte.AbstractMonster;

/** Interface que define o que uma fabrica deve ter. Como criaremos duas fabricas,
 * existem duas funcoes que criam tipos diferentes de monstros. */
public interface AbstractMonsterFactory {
	
	public AbstractMonster createGuardian(String monsterType);
	public AbstractMonster createGhost(String monsterType);
	
}
