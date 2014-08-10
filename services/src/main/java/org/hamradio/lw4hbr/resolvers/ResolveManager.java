package org.hamradio.lw4hbr.resolvers;

import org.hamradio.lw4hbr.model.Configuration;
import org.hamradio.lw4hbr.util.CallInfo;

public class ResolveManager {

	public static CallInfo resolveCall(String call, String QTH, int type, Configuration cfg) throws Exception {
		CallInfo c = null;
		Resolver r;
		switch (type) {

		case 0:
			if (cfg.isQRZenabled()) {
				r = new QrzResolver();
				if (c == null) {
					c = r.getCallInfo(call, cfg);
				}
			} else {
				throw new Exception("You need to provide a valid QRZ account");
			}

			break;

		case 1:
			if (cfg.isHamQTHenabled()) {
				r = new HamQTHResolver();
				if (c == null) {
					c = r.getCallInfo(call, cfg);
				}
			} else {
				throw new Exception("You need to provide a valid HAM QTH account");
			}

			break;

		case 2:
			r = new LogResolver();
			c = r.getCallInfo(call, QTH, cfg);
			break;

		case 3:
			r = new LocalResolver();
			c = r.getCallInfo(call);
			break;
		default:
			break;
		}

		return c;
	}
}
