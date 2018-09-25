package com.bteteam.bteLite.init;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.fusesource.hawtjni.runtime.FieldFlag;

import com.bteteam.bteLite.init.blocks.Blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistryEntry;
import scala.Array;

public class Registry {
	
	public static <T extends IEntry, E extends IForgeRegistryEntry<E>> void register(IEntryHolder<E> holder, RegistryEvent.Register<E> event) {
		Field[] fields = holder.getClass().getFields();
		for(Field field : fields) {
			if(field.getType() == holder.getEntryType()) {
				String name = field.getName();
				try {
					T value = (T) field.get(holder);
					value.prepare(name.getBytes());
					event.getRegistry().register((E) value);
					value.onRegistry(event);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
