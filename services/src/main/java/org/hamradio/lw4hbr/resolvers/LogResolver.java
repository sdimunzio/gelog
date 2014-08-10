package org.hamradio.lw4hbr.resolvers;

import org.hamradio.lw4hbr.model.Configuration;
import org.hamradio.lw4hbr.util.CallInfo;
import org.hamradio.lw4hbr.util.LocatorUtil;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;

public class LogResolver implements Resolver{

	@Override
	public CallInfo getCallInfo(String call, Configuration cfg) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CallInfo getCallInfo(String call) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CallInfo getCallInfo(String call, String grid, Configuration cfg) throws Exception {
		CallInfo cInfo=new CallInfo();
		 Coordinate c=LocatorUtil.loc2degminsec(grid);
		 if (c!=null){
			 cInfo.setLat(c.getLatitude());
			 cInfo.setLon(c.getLongitude());
			 cInfo.setGrid(grid);
		 }
		 return cInfo;
	}

}
