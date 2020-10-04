package xyz.msws.explosive.listeners;

import java.util.Collection;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.Plugin;

import xyz.msws.explosive.AbstractListener;
import xyz.msws.explosive.EBlock;
import xyz.msws.explosive.Explosives;
import xyz.msws.explosive.Trigger;

public class ExplosionListener extends AbstractListener {

	public ExplosionListener(Plugin plugin, Explosives explosives) {
		super(plugin, explosives);
	}

	@Override
	public Trigger getType() {
		return Trigger.EXPLOSION;
	}

	@EventHandler
	public void blockExplode(BlockExplodeEvent event) {
		runCheck(event.blockList());
	}

	@EventHandler
	public void blockExplode(EntityExplodeEvent event) {
		runCheck(event.blockList());
	}

	private void runCheck(Collection<Block> blocks) {
		for (Block b : blocks) {
			EBlock eb = getExplosives().getEBlock(b);
			if (eb == null || !eb.getTriggers().contains(getType()))
				continue;
			eb.trigger(b, this);
		}
	}

	@Override
	public void unregister() {
		BlockExplodeEvent.getHandlerList().unregister(this);
		EntityExplodeEvent.getHandlerList().unregister(this);
	}

}
