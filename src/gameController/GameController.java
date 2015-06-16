package gameController;

import ioComponent.IoComponent;
import ioComponent.interfaces.IIoComponent;
import items.interfaces.IItemManagement;
import items.itemManagement.ItemsList;

import java.util.ArrayList;

import map.ItemSpawn;
import map.MapGenerator;
import map.Position;
import map.interfaces.IGameMap;
import monster.Interfaces.IMonster;
import player.IPlayerMax;
import saveGame.IsaveManagement;
import visual.MapVisual;
import visual.interfaces.IAudioEffect;
import visual.interfaces.IMapVisual;

/**
 * Componente que faz a conexao dos outros componentes
 * 
 * @author MiguelHenrique
 *
 */

public class GameController implements IGameController {
	private static final GameController sharedInstance = new GameController();
	private IGameMap compMap;
	private IMonster compMonster;
	private IPlayerMax compPlayer;
	private IItemManagement compItemManagement;
	private IMapVisual compMapVisual;
	private IIoComponent compIo;
	private IsaveManagement compSave;
	private int fase;

	// criacao do vetor de Itemspawns

	/**
	 * O Metodo tem o objetivo de colocar os itens no mapa, usando os metodos de
	 * itens e map, construindo novos itens, colocando-os no vetor ItemSpawn e
	 * mandando para o metodo setItemSpawnPoint, que coloca os itens no mapa
	 */
	public void colocaItens() {
		ItemSpawn[] vetor = new ItemSpawn[25];
		for (int i = 0; i < 50; i++) {
			if (i >= 0 && i < 5) {
				vetor[i] = new ItemSpawn(ItemsList.Flare, 5);
			}
			if (i >= 5 && i < 10) {
				vetor[i] = new ItemSpawn(ItemsList.Flash, 5);
			}
			if (i >= 10 && i < 15) {
				vetor[i] = new ItemSpawn(ItemsList.Fuel, 5);
			}
			if (i >= 15 && i < 20) {
				vetor[i] = new ItemSpawn(ItemsList.SaltAmmo, 5);
			}
			if (i >= 20 && i < 25) {
				vetor[i] = new ItemSpawn(ItemsList.Stick, 5);
			}
		}

		MapGenerator.sharedInstance().setItemSpawnList(vetor);
		MapGenerator.sharedInstance().setNTraps(5);
	}

	/**
	 * Construtor vazio, respeitando o padrçao de design Singleton
	 */

	private GameController() {
	}

	/**
	 * Metodo chamado na funcao main que conecta os componentes inicializados na
	 * main usando o fluid web ao GameController, posteriormente chamando o
	 * metodo bootGameController, que inicializara o jogo
	 * 
	 * @param compMonster
	 *            : componente do Monster instanciado na main
	 * @param compPlayer
	 *            : componente do Player instanciado na main
	 * @param compItemManagement
	 *            : componente ItemManagement (administrador de itens)
	 *            instanciado na main
	 * @param svg
	 *            : instancia do componente que serializa o jogo
	 * @param f
	 *            : fase
	 */

	public void conectar(IMonster compMonster, IPlayerMax compPlayer,
			IItemManagement compItemManagement, IsaveManagement svg, int f/*
																		 * ,
																		 * IMapVisual
																		 * compMapVisual
																		 */) {
		this.compMonster = compMonster;
		this.compPlayer = compPlayer;
		this.compItemManagement = compItemManagement;
		this.compSave = svg;
		/* this.compMapVisual = compMapVisual; */
		this.fase = f;
		bootGameController();
	}

	/**
	 * Recebe a instancia gerada na main e carrega os objetos serializados, no
	 * caso de carregar um save
	 * 
	 * @param svg
	 */
	public void loadFromDeserialization(IsaveManagement svg) {
		this.compSave = svg;
		ArrayList<Object> list = compSave.deserializeEverything();

		// Corrigir essa falha de polimorfismo, nao sei como resolver. ACHO Q
		// CORRIGIDO
		this.compItemManagement = (IItemManagement) list.get(0);
		this.compPlayer = (IPlayerMax) list.get(1);
		this.compMonster = (IMonster) list.get(2);
		this.compMap = (IGameMap) list.get(3);

		compIo = new IoComponent();

		// Inicializa o componente visual ISSO AINDA NAO SEI MUDAR
		compMapVisual = new MapVisual(compMap, compPlayer, compMonster, compIo,
				compItemManagement);

		compIo.connect(compMapVisual, compPlayer, compSave);
		compIo.setActions();

		compMapVisual.start();

	}

	/**
	 * Metodo que inicializa os componentes, conecta-os e inicializa o jogo.
	 * Aqui acontecem a geracao do mapa, a geracao dos monstros, a geracao do
	 * player, a instanciacao do componente io (entrada e saida), a instanciacao
	 * do mapVisual e a conexao de cada componente, terminando o metodo com a
	 * inicializacao do jogo
	 */
	private void bootGameController() {
		Position playerSpawn;

		// Inicializa o componente mapa.
		MapGenerator.sharedInstance().setMapHeight(20);
		MapGenerator.sharedInstance().setMapWidth(20);
		MapGenerator.sharedInstance().setWalkablePath(175);
		MapGenerator.sharedInstance().connect(compItemManagement);
		colocaItens();
		compMap = MapGenerator.sharedInstance().generateMap();

		// Inicializa o componente player
		playerSpawn = compMap.getSpawnPoint(compPlayer);
		compPlayer.setSpawnPointPlayer(playerSpawn.getX(), playerSpawn.getY());
		compPlayer.connect(compMonster, compItemManagement, compMap);

		// Inicializa o componente monster
		compMonster.connect(compPlayer, compMap);
		compMonster.generateMonsters(this.fase);
		compMonster.setMonsterPosition(0);

		compIo = new IoComponent();

		// Inicializa o componente visual
		compMapVisual = new MapVisual(compMap, compPlayer, compMonster, compIo,
				compItemManagement);

		compIo.connect(compMapVisual, compPlayer, compSave);
		compIo.setActions();

		compMapVisual.start();
	}

	/**
	 * Retorna a instancia do gameController
	 * 
	 * @return sharedInstance
	 */
	public static GameController getSharedInstance() {
		return sharedInstance;
	}

	/* Eh chamado todo frame, ou seja, esta, praticamente, em loop infinito. */

	/**
	 * Classe update, roda muitas vezes por segundo, testando 3 coisas : Se o
	 * jogador perdeu, se o jogador ganhou o jogo e se houve algum input por
	 * parte do cliente, nesse caso enviado para ioComponent a jey para realizar
	 * a acao desejada pelo jogador
	 */
	@Override
	public void update() {
		if (playerGameOver()) {
			System.out.println("Entrou no game over.");
			compMapVisual.end();
			System.out.println("Voce morreu.");
		} else if (playerTestWin()) {
			System.out.println("entrou no test win");
			compMapVisual.end();
			System.out.println("Voce ganhou.");
		} else {
			// System.out.println("entrou no handler");
			compIo.executeAction();
		}
	}

	/**
	 * Chamado toda vez que o player se move. Realiza o movimento dos monstrose
	 * cria o efeito sonoro com volume definido de acordo com a distancia do
	 * player ao monstro
	 */
	public void move() {
		compMonster.runMonstersActions(0);
		double volume = 1 - (compMonster.getDistance(0) / 10);

		if (volume < 0) {
			volume = 0;
		}

		if (compMapVisual instanceof IAudioEffect) {
			((IAudioEffect) compMapVisual).playEffect((float) volume,
					"footstep");
		}
	}

	/**
	 * Metodo para testar se o jogador ganhou o jogo
	 */
	public boolean playerTestWin() {
		if (compMonster.isMonstersAlive() == false)
			return true;
		else
			return false;
	}

	/**
	 * Metodo para testar se o jogador perdeu
	 */
	public boolean playerGameOver() {
		if (compPlayer.getX() == compMonster.getX(0)
				&& compPlayer.getY() == compMonster.getY(0))
			return true;
		else
			return false;
	}

	/**
	 * Metodo que coloca em uma lista ligada generalizada todos os objetos a
	 * serem serializados
	 * 
	 * @return list
	 */
	public ArrayList<Object> getComponentsToSave() {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(compItemManagement);
		list.add(compPlayer);
		list.add(compMonster);
		list.add(compMap);
		return list;
	}

}
