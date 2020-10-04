package xyz.msws.explosive.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.plugin.Plugin;

import xyz.msws.explosive.AbstractListener;
import xyz.msws.explosive.Trigger;

public class RedstoneListener extends AbstractListener {

	public RedstoneListener(Plugin plugin) {
		super(plugin);
	}

	@Override
	public Trigger getType() {
		return Trigger.REDSTONE;
	}

	@EventHandler
	public void onRedstoneChange(BlockRedstoneEvent event) {

	}

	@Override
	public void unregister() {

	}

}
