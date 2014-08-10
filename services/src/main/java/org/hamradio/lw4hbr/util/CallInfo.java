package org.hamradio.lw4hbr.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;

public class CallInfo {
	
	private static Logger log = Logger.getLogger(CallInfo.class.getName());
	
	String call;
	String dxcc;
	String fname;
	String name;
	String addr1;
	String addr2;
	String state;
	String zip;
	String country;
	String ccode;
	Double lat;
	Double lon;
	String grid;
	String county;
	String qslmgr;
	String email;
	String url;
	String image;
	String cqzone;
	String ituzone;

	/*
	 * <Callsign> <call>AA7BQ</call> <dxcc>291</dxcc> <fname>FRED L</fname>
	 * <name>LLOYD</name> <addr1>8711 E PINNACLE PEAK RD 159</addr1>
	 * <addr2>SCOTTSDALE</addr2> <state>AZ</state> <zip>85014</zip>
	 * <country>United States</country> <ccode>291</ccode> <lat>34.23456</lat>
	 * <lon>-112.34356</lon> <grid>DM32af</grid> <county>Maricopa</county>
	 * <fips>04013</fips> <land>USA</land> <efdate>2000-01-20</efdate>
	 * <expdate>2010-01-20</expdate> <p_call>KJ6RK</p_call> <class>E</class>
	 * <codes>HAI</codes> <qslmgr>NONE</qslmgr> <email>flloyd@qrz.com</email>
	 * <url>http://www.qrz.com/callsign/aa7bq</url> <u_views>115336</views>
	 * <bio>3937/2003-11-04</bio>
	 * <image>http://files.qrz.com/q/aa7bq/aa7bq.jpg</image>
	 * <serial>3626</serial> <moddate>2003-11-04 19:37:02</moddate>
	 * <MSA>6200</MSA> <AreaCode>602</AreaCode> <TimeZone>Mountain</TimeZone>
	 * <GMTOffset>-7</GMTOffset> <DST>N</DST> <eqsl>Y</eqsl> <mqsl>Y</mqsl>
	 * <cqzone>3</cqzone> <ituzone>2</ituzone> <locref>1</locref>
	 * <born>1953</born> </Callsign>
	 */
	public static CallInfo build(Document doc) {
		CallInfo c = new CallInfo();
		c.call = (doc.getElementsByTagName("call").item(0) != null) ? doc.getElementsByTagName("call").item(0).getTextContent() : null;

		c.dxcc = (doc.getElementsByTagName("dxcc").item(0) != null) ? doc.getElementsByTagName("dxcc").item(0).getTextContent() : null;
		c.fname = (doc.getElementsByTagName("fname").item(0) != null) ? doc.getElementsByTagName("fname").item(0).getTextContent() : null;
		c.name = (doc.getElementsByTagName("name").item(0) != null) ? doc.getElementsByTagName("name").item(0).getTextContent() : null;
		c.addr1 = (doc.getElementsByTagName("addr1").item(0) != null) ? doc.getElementsByTagName("addr1").item(0).getTextContent() : null;
		c.addr2 = (doc.getElementsByTagName("addr2").item(0) != null) ? doc.getElementsByTagName("addr2").item(0).getTextContent() : null;
		c.state = (doc.getElementsByTagName("state").item(0) != null) ? doc.getElementsByTagName("state").item(0).getTextContent() : null;
		c.zip = (doc.getElementsByTagName("zip").item(0) != null) ? doc.getElementsByTagName("zip").item(0).getTextContent() : null;
		c.country = (doc.getElementsByTagName("country").item(0) != null) ? doc.getElementsByTagName("country").item(0).getTextContent() : null;
		c.ccode = (doc.getElementsByTagName("ccode").item(0) != null) ? doc.getElementsByTagName("ccode").item(0).getTextContent() : null;
		c.lat = (doc.getElementsByTagName("lat").item(0) != null) ? Double.parseDouble(doc.getElementsByTagName("lat").item(0).getTextContent()) : null;
		c.lon = (doc.getElementsByTagName("lon").item(0) != null) ? Double.parseDouble(doc.getElementsByTagName("lon").item(0).getTextContent()) : null;
		c.grid = (doc.getElementsByTagName("grid").item(0) != null) ? doc.getElementsByTagName("grid").item(0).getTextContent() : null;
		c.county = (doc.getElementsByTagName("county").item(0) != null) ? doc.getElementsByTagName("county").item(0).getTextContent() : null;
		c.qslmgr = (doc.getElementsByTagName("qslmgr").item(0) != null) ? doc.getElementsByTagName("qslmgr").item(0).getTextContent() : null;
		c.email = (doc.getElementsByTagName("email").item(0) != null) ? doc.getElementsByTagName("email").item(0).getTextContent() : null;
		c.url = (doc.getElementsByTagName("url").item(0) != null) ? doc.getElementsByTagName("url").item(0).getTextContent() : null;
		c.image = (doc.getElementsByTagName("image").item(0) != null) ? doc.getElementsByTagName("image").item(0).getTextContent() : null;
		c.cqzone = (doc.getElementsByTagName("cqzone").item(0) != null) ? doc.getElementsByTagName("cqzone").item(0).getTextContent() : null;
		c.ituzone = (doc.getElementsByTagName("ituzone").item(0) != null) ? doc.getElementsByTagName("ituzone").item(0).getTextContent() : null;

		return c;
	}

	public static CallInfo buildFromResultSet(ResultSet rs) {
		CallInfo c = null;
		try {
			if (rs.next()) {
				c = new CallInfo();
				// CALL_ID VARCHAR(255) NOT NULL, LOCATOR VARCHAR(10), LATITUDE
				// DECIMAL ,LONGITUDE DECIMAL
				c.call = rs.getString("CALL_ID");
				c.lat = rs.getDouble("LATITUDE");
				c.lon = rs.getDouble("LONGITUDE");
				c.grid = rs.getString("LOCATOR");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("Error", e);
		}
		return c;
	}

	public String toString() {
		return call + "," + dxcc + "," + fname + "," + name + "," + addr1 + "," + addr2 + "," + state + "," + zip + "," + country + "," + ccode + "," + lat + "," + lon + "," + grid + "," + county
				+ "," + qslmgr + "," + email + "," + url + "," + image + "," + cqzone + "," + ituzone;
	}


	public Coordinate getCoordinates() {
		if (lat != null && lon != null) {
			return new Coordinate(lon, lat);
		} else if (grid != null) {
			return LocatorUtil.loc2degminsec(grid);
		} else {
			return null;
		}

	}

	public String getCall() {
		return call;
	}

	public void setCall(String call) {
		this.call = call;
	}

	public String getDxcc() {
		return dxcc;
	}

	public void setDxcc(String dxcc) {
		this.dxcc = dxcc;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCcode() {
		return ccode;
	}

	public void setCcode(String ccode) {
		this.ccode = ccode;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public String getGrid() {
		return grid;
	}

	public void setGrid(String grid) {
		this.grid = grid;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getQslmgr() {
		return qslmgr;
	}

	public void setQslmgr(String qslmgr) {
		this.qslmgr = qslmgr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCqzone() {
		return cqzone;
	}

	public void setCqzone(String cqzone) {
		this.cqzone = cqzone;
	}

	public String getItuzone() {
		return ituzone;
	}

	public void setItuzone(String ituzone) {
		this.ituzone = ituzone;
	}



}
