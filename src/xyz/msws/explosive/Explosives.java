package xyz.msws.explosive;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class Explosives implements Collection<EBlock> {

	private Set<EBlock> blocks;

	public Explosives(Set<EBlock> blocks) {
		this.blocks = blocks;
	}

	@Override
	public Iterator<EBlock> iterator() {
		return blocks.iterator();
	}

	public EBlock getEBlock(Block block) {
		return getEBlock(block.getType());
	}

	public EBlock getEBlock(Material mat) {
		for (EBlock block : this) {
			if (block.getType() == mat)
				return block;
		}
		return null;
	}

	public boolean isEBlock(Block block) {
		return getEBlock(block) != null;
	}

	@Override
	public int size() {
		return blocks.size();
	}

	@Override
	public boolean isEmpty() {
		return blocks.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return blocks.contains(o);
	}

	@Override
	public Object[] toArray() {
		return blocks.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return blocks.toArray(a);
	}

	@Override
	public boolean add(EBlock e) {
		return blocks.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return blocks.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return blocks.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends EBlock> c) {
		return blocks.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return blocks.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return blocks.retainAll(c);
	}

	@Override
	public void clear() {
		blocks.clear();
	}

}
