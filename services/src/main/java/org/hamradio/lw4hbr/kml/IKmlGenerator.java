package org.hamradio.lw4hbr.kml;

import java.util.List;

import org.hamradio.lw4hbr.model.Configuration;
import org.hamradio.lw4hbr.model.Qso;

import de.micromata.opengis.kml.v_2_2_0.Kml;

public interface IKmlGenerator {
	
	public Kml makeKml(List<Qso> list, Configuration cfg) throws Exception; 
	
}
