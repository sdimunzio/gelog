package org.hamradio.lw4hbr.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.hamradio.lw4hbr.derby.DerbyManager;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class HamQTHInterface {

	private static Logger log = Logger.getLogger(QRZInterface.class.getName());
	private String user;
	private String pass;
	private String sessionKey = null;

	public HamQTHInterface(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public boolean isLoggedIn() {
		return sessionKey != null;
	}

	/**
	 * <?xml version="1.0" ?> <QRZDatabase version="1.18"
	 * xmlns="http://www.qrz.com"> <Session>
	 * <Key>2331uf894c4bd29f3923f3bacf02c532d7bd9</Key> <Count>123</Count>
	 * <SubExp>Wed Jan 1 12:34:03 2010</SubExp> <GMTime>Sun Aug 16 03:51:47
	 * 2009</GMTime> </Session> </QRZDatabase>
	 * 
	 * @return
	 * @throws Exception 
	 * @throws DOMException 
	 */
	public boolean login() throws DOMException, Exception {
		String url = "http://www.hamqth.com/xml.php?u=" + this.user + "&p=" + this.pass + "&agent=GeLog-1.0.2b";
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(new URL(url).openStream());
			
			if (doc.getElementsByTagName("Error").getLength()>0){
				throw new Exception("HAM QTH ERROR: "+doc.getElementsByTagName("error").item(0).getTextContent());
			}
			

			if (doc.getElementsByTagName("session_id").getLength()>0) {
				this.sessionKey = doc.getElementsByTagName("session_id").item(0).getTextContent();
				return true;
			}

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			log.error("Error", e);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			log.error("Error", e);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			log.error("Error", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("Error", e);
		}

		return false;
	}

	public CallInfo getCallInfo(String call, boolean useCache) throws Exception {
		CallInfo c = null;
		try {
			if (this.sessionKey == null) {
				throw new Exception("You must loging first");
			}

			

			if (useCache) {
				c = DerbyManager.getInstance().getCallFromCache(call);
				if(c!=null){
					log.info(call+ " found in cache ");
				}
			}

			if (c == null) {
				//String url = "http://www.qrz.com/xml?s=" + this.sessionKey + ";callsign=" + call;
				String url = "http://www.hamqth.com/xml.php?id=" + this.sessionKey + "&callsign="+call+"&prg=GeLog";
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db;

				db = dbf.newDocumentBuilder();
				Document doc = db.parse(new URL(url).openStream());

				c = build(doc);
				
				if (useCache){
					DerbyManager.getInstance().cacheCallInfo(c);
				}
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			log.error("Error", e);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			log.error("Error", e);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			log.error("Error", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("Error", e);
		}

		return c;
	}
	
	
	
	
	
	/*
	 * <callsign>ok2cqr</callsign> <nick>Petr</nick> <qth>Neratovice</qth>
	 * <country>Czech Republic</country> <itu>28</itu> <cq>15</cq>
	 * <grid>JO70GG</grid> <adr_name>Petr Hlozek</adr_name> <adr_street1>17.
	 * listopadu 1065</adr_street1> <adr_city>Neratovice</adr_city>
	 * <adr_zip>27711</adr_zip> <adr_country>Czech Republic</adr_country>
	 * <district>BME</district> <lotw>Y</lotw> <qsl>Y</qsl> <eqsl>Y</eqsl>
	 * <email>petr@ok2cqr.com</email> <jabber>petr@ok2cqr.com</jabber>
	 * <skype>PetrHH</skype> <birth_year>1982</birth_year>
	 * <lic_year>1998</lic_year> <web>http://www.ok2cqr.com</web>
	 * <picture>http:/
	 * /www.hamqth.com/userfiles/o/ok/ok2cqr/_profile/ok2cqr_nove.jpg</picture>
	 * <lookups>8274</lookups>
	 */

	public  CallInfo build(Document doc) {
		CallInfo c = new CallInfo();
		c.call = (doc.getElementsByTagName("callsign").item(0) != null) ? doc.getElementsByTagName("callsign").item(0).getTextContent() : null;

		// c.dxcc = (doc.getElementsByTagName("dxcc").item(0) != null) ?
		// doc.getElementsByTagName("dxcc").item(0).getTextContent() : null;

		c.fname = (doc.getElementsByTagName("adr_name").item(0) != null) ? doc.getElementsByTagName("adr_name").item(0).getTextContent() : null;

		c.name = (doc.getElementsByTagName("nick").item(0) != null) ? doc.getElementsByTagName("nick").item(0).getTextContent() : null;

		c.addr1 = (doc.getElementsByTagName("adr_street1").item(0) != null) ? doc.getElementsByTagName("adr_street1").item(0).getTextContent() : null;
		c.addr2 = (doc.getElementsByTagName("adr_city").item(0) != null) ? doc.getElementsByTagName("adr_city").item(0).getTextContent() : null;
		c.state = (doc.getElementsByTagName("district").item(0) != null) ? doc.getElementsByTagName("district").item(0).getTextContent() : null;
		c.zip = (doc.getElementsByTagName("adr_zip").item(0) != null) ? doc.getElementsByTagName("adr_zip").item(0).getTextContent() : null;
		c.country = (doc.getElementsByTagName("adr_country").item(0) != null) ? doc.getElementsByTagName("adr_country").item(0).getTextContent() : null;

		// c.ccode = (doc.getElementsByTagName("ccode").item(0) != null) ?
		// doc.getElementsByTagName("ccode").item(0).getTextContent() : null;
		c.grid = (doc.getElementsByTagName("grid").item(0) != null) ? doc.getElementsByTagName("grid").item(0).getTextContent() : null;
		try{
		if (c.grid != null) {

			c.lat = LocatorUtil.loc2degminsec(c.grid).getLatitude();
			c.lon = LocatorUtil.loc2degminsec(c.grid).getLongitude();

		}
		}catch (Exception e) {
			// TODO: handle exception
		}

		// c.county = (doc.getElementsByTagName("county").item(0) != null) ?
		// doc.getElementsByTagName("county").item(0).getTextContent() : null;
		// c.qslmgr = (doc.getElementsByTagName("qslmgr").item(0) != null) ?
		// doc.getElementsByTagName("qslmgr").item(0).getTextContent() : null;

		c.email = (doc.getElementsByTagName("email").item(0) != null) ? doc.getElementsByTagName("email").item(0).getTextContent() : null;

		c.url = (doc.getElementsByTagName("web").item(0) != null) ? doc.getElementsByTagName("web").item(0).getTextContent() : null;

		c.image = (doc.getElementsByTagName("picture").item(0) != null) ? doc.getElementsByTagName("picture").item(0).getTextContent() : null;

		c.cqzone = (doc.getElementsByTagName("cq").item(0) != null) ? doc.getElementsByTagName("cq").item(0).getTextContent() : null;

		c.ituzone = (doc.getElementsByTagName("itu").item(0) != null) ? doc.getElementsByTagName("itu").item(0).getTextContent() : null;

		return c;
	}
}
