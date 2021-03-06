package org.hamradio.lw4hbr.resolvers;

import org.apache.log4j.Logger;
import org.hamradio.lw4hbr.model.Configuration;
import org.hamradio.lw4hbr.util.CallInfo;
import org.hamradio.lw4hbr.util.QRZInterface;

public class QrzResolver implements Resolver {
	private static Logger log = Logger.getLogger(QrzResolver.class.getName());
	private static QRZInterface instance;

	public CallInfo getCallInfo(String call, Configuration cfg) throws Exception {

		// TODO Auto-generated method stub
		if ((instance == null) || (!cfg.getQrzPass().equalsIgnoreCase(instance.getPass()))) {
			instance = new QRZInterface(cfg.getQrzUser(), cfg.getQrzPass());

		}

		if (!instance.isLoggedIn()) {
			instance.login();
		}

		CallInfo c = null;

		try {
			c = instance.getCallInfo(call, cfg.isUseQRZCache());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("Error", e);
		}
		return c;
	}

	@Override
	public CallInfo getCallInfo(String call) throws Exception {
		throw new Exception("Not implemented");
	}

	@Override
	public CallInfo getCallInfo(String call, String qth, Configuration cfg) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not implemented");
	}

}
