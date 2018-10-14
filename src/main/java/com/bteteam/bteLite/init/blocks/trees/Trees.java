package com.bteteam.bteLite.init.blocks.trees;

import com.bteteam.bteLite.init.IEntry;
import com.bteteam.bteLite.init.IEntryHolder;

public class Trees implements IEntryHolder<ITree>{

	public static Trees INSTANCE = new Trees();
	
	@Override
	public Class<? extends ITree> getEntryType() {
		return ITree.class;
	}

	@Override
	public IEntryHolder<ITree> getInstance() {
		return INSTANCE;
	}

	
}
