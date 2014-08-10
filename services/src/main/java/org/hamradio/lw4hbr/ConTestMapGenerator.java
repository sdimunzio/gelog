package org.hamradio.lw4hbr;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.hamradio.lw4hbr.adif.ADIFReader;
import org.hamradio.lw4hbr.kml.KmlGenerator;
import org.hamradio.lw4hbr.model.Configuration;
import org.hamradio.lw4hbr.model.Qso;

import de.micromata.opengis.kml.v_2_2_0.Kml;

public class ConTestMapGenerator {

	private static Logger log = Logger.getLogger(ConTestMapGenerator.class.getName());

	public static void createKml(Configuration cfg) throws Exception {

		List<Qso> qsoList = null;
		cfg.setStatusText("Reading ADIF file");

        boolean hasCty= new File(System.getProperty("gelog.home") + System.getProperty("file.separator") + "cty.csv").exists() || ConTestMapGenerator.class.getClassLoader().getResource("cty.csv")!=null;

        if (!hasCty) {
			throw new Exception("cty.csv NOT FOUND");
		}
		try {
			ADIFReader reader = new ADIFReader(cfg.getInputFilePath());
			qsoList = reader.read();
		} catch (Exception e) {
			log.error("Error", e);
			throw new Exception("Error while reading ADIF file pleasse check file format");
		}

		if (qsoList != null) {
			KmlGenerator generator = new KmlGenerator();
			Kml kml = generator.makeKml(qsoList, cfg);
			kml.marshal(new File(System.getProperty("gelog.home") + "/" + cfg.getOuputFilePath()));
			cfg.setProgress(100);
			cfg.setStatusText("KML files has been created: " + new File(cfg.getOuputFilePath()).getAbsolutePath());
		}

	}

	public static void main(String[] args) {
		try {
			createKml(new Configuration());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("Error", e);
		}
	}

}
