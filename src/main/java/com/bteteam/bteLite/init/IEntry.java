package com.bteteam.bteLite.init;

public interface IEntry {

	public void prepare();
	
	public default void prepare(byte... info) {
		prepare();
	}
}
