package org.hamradio.lw4hbr.util;

import org.apache.log4j.Logger;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;

public class LocatorUtil {
	private static Logger log = Logger.getLogger(LocatorUtil.class.getName());

	static String letters = "ABCDEFGHIJKLMNOPQRSTUVWX";

	public static Coordinate loc2degminsec(String loc){
		Coordinate c=null;
		try {
		
			if (loc != null) {
				loc = loc.toUpperCase();
				boolean long_rad;
				boolean lat_rad;
				boolean ff;

				if (loc.length() == 4) {
					loc += "MM";

					ff = false;
				} else
					ff = true;

				double x_l = letters.indexOf(loc.charAt(0)); // Laenge berechnen
				double x_m = Integer.parseInt(String.valueOf(loc.charAt(2)));
				double x_r = letters.indexOf(loc.charAt(4));
				double y_l = letters.indexOf(loc.charAt(1)); // Breite berechnen
				double y_m = Integer.parseInt(String.valueOf(loc.charAt(3)));
				double y_r = letters.indexOf(loc.charAt(5));

				double x = x_l * 10 + x_m + x_r / 24;

				if (ff)
					x = x + 1 / 48;
				x *= 2;
				x -= 180;

				if (x < 0) // western hemisphere?
					long_rad = true;
				else
					long_rad = false;

				double y = y_l * 10 + y_m + y_r / 24;
				if (ff)
					y = y + 1 / 48;
				y -= 90;

				if (y < 0) // down under?
					lat_rad = true;
				else
					lat_rad = false;
				c = new Coordinate(x, y);
				log.info(loc + " : " + c.toString());
			}
			
		} catch (Exception e) {
			log.error("Error" + loc, e);
		}
		return c;
	}
}
