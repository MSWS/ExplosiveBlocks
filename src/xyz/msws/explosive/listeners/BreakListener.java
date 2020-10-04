package xyz.msws.explosive.listeners;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;

import xyz.msws.explosive.AbstractListener;
import xyz.msws.explosive.EBlock;
import xyz.msws.explosive.Explosives;
import xyz.msws.explosive.Trigger;

public class BreakListener extends AbstractListener {

	public BreakListener(Plugin plugin, Explosives explosives) {
		super(plugin, explosives);
	}

	@Override
	public Trigger getType() {
		return Trigger.BREAK;
	}

	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		Block block = event.getBlock();
		EBlock eb = explosives.getEBlock(block);
		if (eb == null || !eb.getTriggers().contains(getType()))
			return;
		eb.trigger(block, this);
	}

	@Override
	public void unregister() {
		BlockBreakEvent.getHandlerList().unregister(this);
	}

}
