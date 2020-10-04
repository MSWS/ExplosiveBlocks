package xyz.msws.explosive;

import java.util.EnumSet;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

public interface EBlock extends ConfigurationSerializable {
	float getPower();

	void setPower(float power);

	Material getType();

	EnumSet<Trigger> getTriggers();

	void trigger(Block block, AbstractListener cause);
}
