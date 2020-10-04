package xyz.msws.explosive;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public abstract class AbstractListener implements Listener {
	private Plugin plugin;

	public AbstractListener(Plugin plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);

		this.plugin = plugin;
	}

	public Plugin getPlugin() {
		return plugin;
	}

	public abstract Trigger getType();
	
	public abstract void unregister();
}
