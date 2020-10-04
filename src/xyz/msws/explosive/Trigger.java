package xyz.msws.explosive;

public enum Trigger {
	EXPLOSION("Activates when the block explodes"), FIRE("Activates when the block comes into contact with fire"),
	ARROW("Activates when an arrow hits the block"), FIREARROW("Activates when a fire arrow hits the block"),
	REDSTONE("Activates when powered by redstone"), CUSTOM("Does not activate, use for plugin stuff");

	private String desc;

	Trigger(String desc) {
		this.desc = desc;
	}

	public String getDescription() {
		return desc;
	}
}
