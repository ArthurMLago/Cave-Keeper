package player;

import anima.component.IRequires;
import anima.component.ISupports;
import anima.component.InterfaceType;
import visual.interfaces.IActionPlayer;
import visual.interfaces.IActionPlayerMapVisual;
import visual.interfaces.IMapVisual;
import player.IPlayerAction;

public class PlayerShootUpAction implements IActionPlayerMapVisual {
	private int key;
	private IPlayerAction player;
	private IMapVisual map;

	@Override
	public int getKey() {
		return key;
	}

	@Override
	public void setKey(int key) {
		this.key = key;
	}

	@Override
	public void execute() {
		player.shoot(Facing.NORTH);
		map.shootDirection(Facing.NORTH);
	}

	@Override
	public void connect(IPlayerAction player) {
		this.player = player;
	}

	@Override
	public void connect(IMapVisual map) {
		this.map = map;
	}

	@Override
	public int addRef() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getInstanceId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends ISupports> T queryInterface(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends ISupports> T queryInterface(String arg0,
			InterfaceType arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends ISupports> IRequires<T> queryReceptacle(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int release() {
		// TODO Auto-generated method stub
		return 0;
	}

}
