package map;

import java.io.Serializable;

/**
 * Classe simples apenas para passar uma posição no mapa.
 * @author ArthurMLago
 *
 */
public class Position implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public int x,y;
	
	/**
	 * Construtor padrão da classe, tomando a posição x e y.
	 * @param x Posição Horizontal
	 * @param y Posição Vertical
	 */
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	
	/**
	 * Getter basico para recuperar a coordenada da posição em X.
	 * @return Posição em x
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Getter basico para recuperar a coordenada da posição em Y.
	 * @return Posição em y
	 */
	public int getY(){
		return y;
	}
	
	
	/**
	 * Função basica para setar a as coordenadas da posição
	 * @param x Coordenada em X
	 * @param y Coordenada em Y
	 */
	public void set(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	
	/**
	 * Função que calcula a distância desta posição a uma segunda posição dada.
	 * @param Segunda posição, para calcular a distância dessa posição
	 * @return A distância desta posição 
	 */
	public double distanceTo(Position toPos){
		return Math.sqrt( (toPos.getX() - x)*(toPos.getX() - x) + (toPos.getY() - y) * (toPos.getY() - y) );
	}

}
