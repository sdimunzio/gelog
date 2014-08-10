package org.hamradio.lw4hbr.cty;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hamradio.lw4hbr.model.Country;

import au.com.bytecode.opencsv.CSVReader;
import de.micromata.opengis.kml.v_2_2_0.Coordinate;

public class CountryFinder {
    private static Logger log = Logger.getLogger(CountryFinder.class.getName());
    private static List<Country> countryList = new ArrayList<Country>();

    static {
        try {

            FileReader ctyReader = null;
            ctyReader = new File(System.getProperty("gelog.home") + "/cty.csv").exists() ? new FileReader(System.getProperty("gelog.home") + "/cty.csv")
                    : new FileReader(CSVReader.class.getClassLoader().getResource("cty.csv").getFile());


            CSVReader reader = new CSVReader(ctyReader);
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                Country country = new Country();
                country.setName(nextLine[1]);
                country.setPrefixes(nextLine[9]);
                country.setCQZone(nextLine[4]);
                Coordinate c = new Coordinate(Double.parseDouble(nextLine[7]) * -1, Double.parseDouble(nextLine[6]));
                country.setCoordinates(c);
                countryList.add(country);

            }
        } catch (Exception e) {
            log.error("Error", e);
        }
    }

    public static Country getCountryFromCall(String call) {
        if (call != null) {
            HashMap<String, Country> results = new HashMap<String, Country>();
            for (Country c : countryList) {
                for (String prefix : c.getPrefixes()) {
                    if ((call.toLowerCase().startsWith(prefix.toLowerCase())) || (("=" + call).equalsIgnoreCase(prefix))) {
                        results.put(prefix, c);
                    }
                }
            }
            if (results.size() > 1) {
                String prevPrefix = "";
                for (String p : results.keySet()) {
                    if (p.length() > prevPrefix.length()) {
                        prevPrefix = p;
                    }
                }

                return results.get(prevPrefix);
            }
            if (results.size() > 0) {
                return results.values().iterator().next();
            }

        }

        return null;
    }
}
