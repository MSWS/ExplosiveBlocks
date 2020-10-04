package xyz.msws.explosive.event;

import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import xyz.msws.explosive.AbstractListener;
import xyz.msws.explosive.EBlock;

public class BlockCustomExplodeEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	private Block block;
	private EBlock eb;
	private AbstractListener cause;
	private boolean cancel;

	public BlockCustomExplodeEvent(Block block, EBlock eb, AbstractListener cause) {
		this.block = block;
		this.eb = eb;
		this.cause = cause;
	}

	@Override
	public boolean isCancelled() {
		return cancel;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancel = cancel;
	}

	public Block getBlock() {
		return block;
	}

	public EBlock getEBlock() {
		return eb;
	}

	public AbstractListener getCause() {
		return cause;
	}

}
