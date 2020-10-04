package xyz.msws.explosive;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.serialization.SerializableAs;

import xyz.msws.explosive.event.BlockCustomExplodeEvent;

@SerializableAs("Explosive")
public class NativeBlock implements EBlock {

	private float power = 1;
	private Material material;
	private EnumSet<Trigger> triggers;
	private boolean bDamage = true, fire = false;
	private static List<Long> actions = new ArrayList<>();

	public NativeBlock(Material type, float power, EnumSet<Trigger> triggers) {
		this.material = type;
		this.power = power;
		this.triggers = triggers;
	}

	public NativeBlock(Material type, float power, Trigger... triggers) {
		this.material = type;
		this.power = power;
		this.triggers = triggers.length == 0 ? EnumSet.noneOf(Trigger.class) : EnumSet.of(triggers[0], triggers);
	}

	@SuppressWarnings("unchecked")
	public NativeBlock(Map<String, Object> data) {
		if (!data.containsKey("Power") || !data.containsKey("Material") || !data.containsKey("Triggers"))
			return;
		try {
			power = ((Number) data.get("Power")).floatValue();
		} catch (ClassCastException e) {
			Bukkit.getLogger().log(Level.WARNING, String.format("%s is not a float", data.get("power")));
		}
		try {
			material = Material.valueOf((String) data.get("Material"));
		} catch (IllegalArgumentException e) {
			Bukkit.getLogger().log(Level.WARNING, String.format("%s is an invalid material", material));
		}
		triggers = EnumSet.noneOf(Trigger.class);
		for (String s : ((List<String>) data.get("Triggers"))) {
			try {
				triggers.add(Trigger.valueOf(s.toUpperCase()));
			} catch (IllegalArgumentException e) {
				Bukkit.getLogger().log(Level.WARNING, String.format("%s is an invalid trigger for %s", s, material));
			}
		}
		fire = (boolean) data.getOrDefault("fire", false);
	}

	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> data = new HashMap<>();
		data.put("Power", power);
		data.put("Material", material.toString());
		Set<String> names = triggers.parallelStream().map(s -> s.toString()).collect(Collectors.toSet());
		data.put("Triggers", names.toArray());
		data.put("Fire", fire);
		data.put("BreakBlocks", bDamage);
		return data;
	}

	@Override
	public float getPower() {
		return power;
	}

	@Override
	public void setPower(float power) {
		if (power < 0)
			throw new IllegalArgumentException("power cannot be less than 0");
		this.power = power;
	}

	@Override
	public Material getType() {
		return material;
	}

	@Override
	public EnumSet<Trigger> getTriggers() {
		return triggers;
	}

	@Override
	public void trigger(Block block, AbstractListener cause) {
		BlockCustomExplodeEvent event = new BlockCustomExplodeEvent(block, this, cause);
		int max = ExplosiveBlocks.getInstance().getConfig().getInt("MaxExplosives");
		int count = 0;
		for (int i = actions.size() - 1; i >= 0; i--) {
			if (System.currentTimeMillis() - actions.get(i) >= 1000)
				break;
			count++;
		}

		if (count >= max)
			event.setCancelled(true);

		Bukkit.getPluginManager().callEvent(event);
		if (event.isCancelled())
			return;
		if (block == null)
			return;
		actions.add(System.currentTimeMillis());

		block.getWorld().createExplosion(block.getX(), block.getY(), block.getZ(), power, fire, bDamage);
	}

}
