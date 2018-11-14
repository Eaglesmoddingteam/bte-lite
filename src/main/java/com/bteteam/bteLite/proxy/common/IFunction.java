package com.bteteam.bteLite.proxy.common;

public interface IFunction {

	void exec(Object... args);

	public interface NoArgs{
		
		void exec();
	}
}
