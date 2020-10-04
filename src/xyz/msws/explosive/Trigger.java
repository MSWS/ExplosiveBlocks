package xyz.msws.explosive;

public enum Trigger {
	EXPLOSION("Activates when the block explodes"), FIRE("Activates when the block comes into contact with fire"),
	ARROW("Activates when an arrow hits the block"), FIREARROW("Activates when a fire arrow hits the block"),
	REDSTONE("Activates when powered by redstone"), CLICK("Activates when a player left clicks on the block"),
	RIGHTCLICK("Activates when a player right clicks on the block"), BREAK("Activates when the blocks is broken"),
	MOVE("Activates when the block is moved"), CUSTOM("Does not activate, use for plugin stuff");

	private String desc;

	Trigger(String desc) {
		this.desc = desc;
	}

	public String getDescription() {
		return desc;
	}
}
