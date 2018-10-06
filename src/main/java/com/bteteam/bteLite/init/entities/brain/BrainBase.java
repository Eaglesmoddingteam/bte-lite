package com.bteteam.bteLite.init.entities.brain;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class BrainBase<E extends Entity> {
	E entity;

	public BrainBase(E entityAttached) {
		this.entity = entityAttached;
	}
	
	public abstract void onAttackedBy(Entity attacker, World world);
	
	public abstract void onAttacked();
	
	public abstract boolean agressiveAgainst(Entity e);
	
	public abstract Vec3d getrandomMovementPosition();
	
	public abstract boolean isIntelligent();
}
