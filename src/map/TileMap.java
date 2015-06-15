package map;

import java.io.Serializable;

import map.Event;
import map.enumerations.TileType;
import map.enumerations.VisibilityMode;
import map.enumerations.EventType;

/**
 * Classe usada para armazenar um Tile do mapa(um quadrado andavel).
 * Contém informações do tile(andavel ou não?), a imagem que ele exibe, a visibilidade dele e o evento em um
 * determinado tile.
 * @author ArthurMLago
 *
 */
public class TileMap implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TileType type;
	private VisibilityMode visibility;
	private String tileImage;
	private Event Event;
	
	/**
	 * Construtor padrão para um tile, recebe todas as informações necessarias para a formação de um Tile com
	 * excessão da visibilidade, que sempre começa como desconhecido.
	 * @param type Tipo do tile, se ele é andavel ou não. (vide {@link map.enumerations.TileType Enumeration TileType}).
	 * @param tileImage String com o caminho para o arquivo da imagem.
	 * @param Objeto da classe Event representando o Evento presente nesse Tile.
	 */
	public TileMap(TileType type, String tileImage, Event Event){
		this.type = type;
		this.tileImage = tileImage;
		this.Event = Event;
		this.visibility = VisibilityMode.Unknown;
	}
	
	/**
	 * Getter simples para o tipo do Tile
	 * @return Um tipo da{@link map.enumerations.TileType Enumeration TileType} com o tipo do tile.
	 */
	public TileType getType(){
		return type;
	}
	
	/**
	 * Getter simples para o caminho da imagem do Tile.
	 * @return Sting com o caminho para a imagem do Tile.
	 */
	public String getImage(){
		return tileImage;
	}
	
	/**
	 * Getter simples para a visibilidade do mapa.
	 * @return Um tipo da {@link map.enumerations.VisibilityMode Enumeration VisibilityMode} com a visiblidade desse Tile.
	 */
	public VisibilityMode getVisibility(){
		return visibility;
	}
	
	/**
	 * Método para descobrir se existe um determinado tipo de evento neste Tile.
	 * @param type Tipo do evento.
	 * @return true, se existe um evento do tipo especificado, false, caso contrario.
	 */
	public Event checkForEvents(){
		return Event;
	}
	
	/**
	 * Método para ativar o evento do Tile:
	 * @param type Tipo do evento a ser ativado
	 */
	public void triggerEvent(){
		if (Event != null){
			Event.applyEffect();
		}
	}
	
	/**
	 * Método para descartar eventos de determinado tipo.
	 * @param type Tipo do evento.
	 */
	public void discardEvent(){
		Event = null;
	}
	
}
