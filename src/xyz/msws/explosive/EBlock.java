package xyz.msws.explosive;

import java.util.EnumSet;

import org.bukkit.Material;

public interface EBlock {
	double getPower();
	void setPower(double power);
	Material getType();
	
	EnumSet<Trigger> getTriggers();
	void trigger();
}
