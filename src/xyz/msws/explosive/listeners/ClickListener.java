package xyz.msws.explosive.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

import xyz.msws.explosive.AbstractListener;
import xyz.msws.explosive.EBlock;
import xyz.msws.explosive.Explosives;
import xyz.msws.explosive.Trigger;

public class ClickListener extends AbstractListener {

	public ClickListener(Plugin plugin, Explosives explosives) {
		super(plugin, explosives);
	}

	@Override
	public Trigger getType() {
		return Trigger.CLICK;
	}

	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		if (event.getAction() != Action.LEFT_CLICK_BLOCK)
			return;
		Block block = event.getClickedBlock();
		if (block == null || block.getType() == Material.AIR)
			return;
		EBlock eb = explosives.getEBlock(block);
		if (eb == null || !eb.getTriggers().contains(getType()))
			return;
		eb.trigger(block, this);
	}

	@Override
	public void unregister() {
		PlayerInteractEvent.getHandlerList().unregister(this);
	}

}
