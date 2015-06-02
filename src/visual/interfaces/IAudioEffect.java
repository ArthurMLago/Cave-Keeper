package visual.interfaces;

import anima.component.ISupports;

/**
 * Interface utilizado para tocar algum efeito sonoro
 * 
 * @author eitiyamamoto
 *
 */
public interface IAudioEffect extends ISupports{
	/**
	 * Toca algum efeito
	 * @param gain - Controle do volume do efeito
	 * @param type - Qual o efeito que será tocado
	 */
	public void playEffect(float gain, String type);
}
