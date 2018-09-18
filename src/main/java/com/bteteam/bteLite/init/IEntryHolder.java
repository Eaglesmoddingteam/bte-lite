package com.bteteam.bteLite.init;

public interface IEntryHolder<T> {

	public Class<? extends T> getEntryType();
	
	public IEntryHolder<T> getInstance();

}
