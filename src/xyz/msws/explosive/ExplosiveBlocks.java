package xyz.msws.explosive;

import java.util.Set;

import org.bukkit.plugin.java.JavaPlugin;

public class ExplosiveBlocks extends JavaPlugin {
	private Set<AbstractListener> listeners;

	@Override
	public void onEnable() {
		
	}

	public Set<AbstractListener> getListeners() {
		return listeners;
	}

	public void registerListener(AbstractListener listener) {
		listeners.add(listener);
	}

	public void unregisterListener(AbstractListener listener) {
		if (!listeners.contains(listener))
			return;
		listeners.remove(listener);
		listener.unregister();
	}
}
