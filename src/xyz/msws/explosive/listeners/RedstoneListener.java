package xyz.msws.explosive.listeners;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.plugin.Plugin;

import xyz.msws.explosive.AbstractListener;
import xyz.msws.explosive.EBlock;
import xyz.msws.explosive.Explosives;
import xyz.msws.explosive.Trigger;

public class RedstoneListener extends AbstractListener {

	public RedstoneListener(Plugin plugin, Explosives explosives) {
		super(plugin, explosives);
	}

	@Override
	public Trigger getType() {
		return Trigger.REDSTONE;
	}

	@EventHandler
	public void onRedstoneChange(BlockRedstoneEvent event) {
		Block block = event.getBlock();
		checkNearbyBlock(block);
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Block block = event.getBlock();
		checkNearbyBlock(block);
	}

	private void checkNearbyBlock(Block block) {
		for (int x = -2; x <= 2; x++) {
			for (int y = -2; y <= 2; y++) {
				for (int z = -2; z <= 2; z++) {
					Block b = block.getLocation().add(x, y, z).getBlock();
					if (b.getBlockPower() == 0)
						continue;
					EBlock eb = getExplosives().getEBlock(b);
					if (eb == null || !eb.getTriggers().contains(getType()))
						continue;
					eb.trigger(b, this);
				}
			}
		}
	}

	@Override
	public void unregister() {
		BlockRedstoneEvent.getHandlerList().unregister(this);
	}

}
