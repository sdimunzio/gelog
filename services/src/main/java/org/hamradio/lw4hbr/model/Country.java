package org.hamradio.lw4hbr.model;

import java.util.Arrays;
import java.util.Collection;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;

/**
 * @author Sebastian Dimunzio May 22, 2011
 */
public class Country {

	private String name;
	private Collection<String> prefixes;
	private String CQZone;
	Coordinate coordinates = null;

	public Coordinate getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinate coordinates) {
		this.coordinates = coordinates;
	}

	private String ITUZone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<String> getPrefixes() {
		return prefixes;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}

	public void setPrefixes(String str) {
		str = str.substring(0, str.length() - 1);// remove the ;
		String[] arrayPrefixes = str.split(" ");
		prefixes = Arrays.asList(arrayPrefixes);
	}

	public void setPrefixes(Collection<String> prefixes) {
		this.prefixes = prefixes;
	}

	public String getCQZone() {
		return CQZone;
	}

	public void setCQZone(String zone) {
		CQZone = zone;
	}

	public String getITUZone() {
		return ITUZone;
	}

	public void setITUZone(String zone) {
		ITUZone = zone;
	}

}
