package xyz.msws.explosive.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.plugin.Plugin;

import xyz.msws.explosive.AbstractListener;
import xyz.msws.explosive.EBlock;
import xyz.msws.explosive.Explosives;
import xyz.msws.explosive.Trigger;

public class FireListener extends AbstractListener {

	public FireListener(Plugin plugin, Explosives explosives) {
		super(plugin, explosives);
	}

	@Override
	public Trigger getType() {
		return Trigger.FIRE;
	}

	@EventHandler
	public void onBlockIgnite(BlockIgniteEvent event) {
		EBlock eb = getExplosives().getEBlock(event.getBlock());
		if (eb == null || !eb.getTriggers().contains(getType()))
			return;
		eb.trigger(event.getBlock(), this);
	}

	@Override
	public void unregister() {
		BlockIgniteEvent.getHandlerList().unregister(this);
	}

}
