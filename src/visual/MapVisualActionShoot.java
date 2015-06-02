package visual;

import visual.interfaces.IActionMapVisual;
import visual.interfaces.IMapVisual;
import anima.component.IRequires;
import anima.component.base.ComponentBase;

public class MapVisualActionShoot extends ComponentBase implements IActionMapVisual, IRequires<IMapVisual>{
	private int key;
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
		map.shoot();
	}

	@Override
	public void connect(IMapVisual map) {
		this.map = map;
	}

}
