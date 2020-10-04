package xyz.msws.explosive.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.Plugin;

import xyz.msws.explosive.AbstractListener;
import xyz.msws.explosive.EBlock;
import xyz.msws.explosive.Explosives;
import xyz.msws.explosive.Trigger;

public class ArrowListener extends AbstractListener {

	public ArrowListener(Plugin plugin, Explosives explosives) {
		super(plugin, explosives);
	}

	@Override
	public Trigger getType() {
		return Trigger.ARROW;
	}

	@EventHandler
	public void onArrow(ProjectileHitEvent event) {
		Entity ent = event.getEntity();
		if (ent.getType() != EntityType.ARROW && ent.getType() != EntityType.SPECTRAL_ARROW
				&& ent.getType() != EntityType.TIPPED_ARROW)
			return;

		Block block = event.getHitBlock();
		if (block == null || block.getType() == Material.AIR)
			return;
		EBlock eb = explosives.getEBlock(block);
		if (eb == null || !eb.getTriggers().contains(getType()))
			return;
		eb.trigger(block, this);
	}

	@Override
	public void unregister() {
		ProjectileHitEvent.getHandlerList().unregister(this);
	}

}
