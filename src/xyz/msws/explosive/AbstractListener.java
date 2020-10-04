package xyz.msws.explosive;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

/**
 * Represents a listener to listen to events to trigger blocks
 * 
 * @author msws
 *
 */
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

	/**
	 * Returns the registered explosives
	 * 
	 * @return
	 */
	public Explosives getExplosives() {
		return explosives;
	}

	/**
	 * Should return the Trigger type, CUSTOM should be used if no event will
	 * trigger the explosion
	 * 
	 * @return
	 */
	public abstract Trigger getType();

	/**
	 * Responsible for unregistering event handlers
	 */
	public abstract void unregister();
}
