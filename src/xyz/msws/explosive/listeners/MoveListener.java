package xyz.msws.explosive.listeners;

import java.util.Collection;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.plugin.Plugin;

import xyz.msws.explosive.AbstractListener;
import xyz.msws.explosive.EBlock;
import xyz.msws.explosive.Explosives;
import xyz.msws.explosive.Trigger;

public class MoveListener extends AbstractListener {

	public MoveListener(Plugin plugin, Explosives explosives) {
		super(plugin, explosives);
	}

	@Override
	public Trigger getType() {
		return Trigger.MOVE;
	}

	@EventHandler
	public void onExtend(BlockPistonExtendEvent event) {
		checkBlocks(event.getBlocks());
	}

	@EventHandler
	public void onRetract(BlockPistonRetractEvent event) {
		checkBlocks(event.getBlocks());
	}

	private void checkBlocks(Collection<Block> blocks) {
		for (Block b : blocks) {
			EBlock eb = explosives.getEBlock(b);
			if (eb == null || !eb.getTriggers().contains(getType()))
				continue;
			eb.trigger(b, this);
		}
	}

	@Override
	public void unregister() {
		BlockPistonExtendEvent.getHandlerList().unregister(this);
		BlockPistonRetractEvent.getHandlerList().unregister(this);
	}

}
