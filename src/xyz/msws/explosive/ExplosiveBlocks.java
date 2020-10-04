package xyz.msws.explosive;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import xyz.msws.explosive.listeners.ArrowListener;
import xyz.msws.explosive.listeners.BreakListener;
import xyz.msws.explosive.listeners.ClickListener;
import xyz.msws.explosive.listeners.ExplosionListener;
import xyz.msws.explosive.listeners.FireArrowListener;
import xyz.msws.explosive.listeners.FireListener;
import xyz.msws.explosive.listeners.MoveListener;
import xyz.msws.explosive.listeners.RedstoneListener;
import xyz.msws.explosive.listeners.RightClickListener;

public class ExplosiveBlocks extends JavaPlugin {
	private Set<AbstractListener> listeners;
	private Explosives explosives;

	private File cyml;
	private YamlConfiguration config;
	private static ExplosiveBlocks plugin;

	@Override
	public void onEnable() {
		plugin = this;
		listeners = new HashSet<>();
		explosives = new Explosives(new HashSet<>());

		loadConfig();
		loadExplosives();
		registerListeners();
	}

	public static ExplosiveBlocks getInstance() {
		return plugin;
	}

	private void registerListeners() {
		registerListener(new ExplosionListener(this, explosives));
		registerListener(new FireListener(this, explosives));
		registerListener(new ArrowListener(this, explosives));
		registerListener(new FireArrowListener(this, explosives));
		registerListener(new RedstoneListener(this, explosives));
		registerListener(new ClickListener(this, explosives));
		registerListener(new RightClickListener(this, explosives));
		registerListener(new BreakListener(this, explosives));
		registerListener(new MoveListener(this, explosives));
	}

	private void loadConfig() {
		cyml = new File(getDataFolder(), "config.yml");
		if (!cyml.exists()) {
			saveResource("config.yml", true);
		}
		ConfigurationSerialization.registerClass(NativeBlock.class);

		config = YamlConfiguration.loadConfiguration(cyml);
	}

	@SuppressWarnings("unchecked")
	private void loadExplosives() {
		List<EBlock> exp = (List<EBlock>) config.getList("Explosives");
		if (exp == null) {
			getLogger().log(Level.WARNING, "The Explosives configuration section does not exist.");
			return;
		}
		explosives.addAll(exp);
		getLogger().log(Level.INFO, String.format("Successfully registered %d block%s.", explosives.size(),
				explosives.size() == 1 ? "" : "s"));
	}

	public Explosives getExplosives() {
		return explosives;
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
