package org.hamradio.lw4hbr.resolvers;

import org.hamradio.lw4hbr.model.Configuration;
import org.hamradio.lw4hbr.util.CallInfo;

public interface Resolver {
	
	public CallInfo getCallInfo(String call,String qth,Configuration cfg) throws Exception;
	public CallInfo getCallInfo(String call,Configuration cfg) throws Exception;
	public CallInfo getCallInfo(String call) throws Exception;

}
