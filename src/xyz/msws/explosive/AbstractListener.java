package xyz.msws.explosive;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public abstract class AbstractListener implements Listener {
	private Plugin plugin;
	protected Explosives explosives;

	public AbstractListener(Plugin plugin, Explosives explosives) {
		Bukkit.getPluginManager().registerEvents(this, plugin);

		this.explosives = explosives;
		this.plugin = plugin;
	}

	public Plugin getPlugin() {
		return plugin;
	}

	public Explosives getExplosives() {
		return explosives;
	}

	public abstract Trigger getType();

	public abstract void unregister();
}
