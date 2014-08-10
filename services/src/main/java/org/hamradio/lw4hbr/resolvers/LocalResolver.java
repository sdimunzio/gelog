package org.hamradio.lw4hbr.resolvers;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.hamradio.lw4hbr.derby.DerbyManager;
import org.hamradio.lw4hbr.model.Configuration;
import org.hamradio.lw4hbr.util.CallInfo;

public class LocalResolver implements Resolver {
	private static Logger log = Logger.getLogger(LocalResolver.class.getName());
	@Override
	public CallInfo getCallInfo(String call) {
		try {

			return DerbyManager.getInstance().getCallInfo(call);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("Error", e);
		}
		return null;

	}

	@Override
	public CallInfo getCallInfo(String call, Configuration cfg) {
		return getCallInfo(call);
	}

	@Override
	public CallInfo getCallInfo(String call, String qth, Configuration cfg) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
