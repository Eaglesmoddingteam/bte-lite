package com.bteteam.bteLite.util.list;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class Lists {

	private Lists() {
	}

	public static <T> boolean equalsUnordered(List<T> list1, List<T> list2) {
		List<T> cloneList1 = clone(list1);
		List<T> cloneList2 = clone(list2);
		int currentIndex = 0;
		boolean flag = true;
		for (T t : cloneList1) {
			currentIndex++;
			if (flag) {
				boolean flag2 = contains(cloneList2, t);
				if (flag2) {
					remove(cloneList2, t);
				} else {
					flag = false;
				}
			}
		}
		return flag && cloneList2.isEmpty();
	}
	
	public static <T> void remove(List<T> list, T value) {
		int currentIndex = 0;
		for(T t : list) {
			
			if (t == value || t.equals(value)) {
				break;
			} else if (t instanceof ItemStack) {
				if (ItemStack.areItemStacksEqual((ItemStack) t, (ItemStack) value)) {
					break;
				}
			}
			currentIndex++;
		}
		list.remove(currentIndex);
	}

	public static <T> boolean contains(List<T> list, T value) {
		for (T t : list) {
			if (t == value || t.equals(value)) {
				return true;
			} else if (t instanceof ItemStack) {
				if (ItemStack.areItemStacksEqual((ItemStack) t, (ItemStack) value)) {
					return true;
				}
			}
		}
		return false;
	}

	public static <T> List<T> clone(List<T> list) {
		return new ArrayList<>(list);
	}

	public static <T> List<T> asList(T... array) {
		List<T> l = new ArrayList();
		for (T t : array) {
			l.add(t);
		}
		return l;
	}

}
