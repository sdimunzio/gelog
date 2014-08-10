package org.hamradio.lw4hbr.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.hamradio.lw4hbr.derby.DerbyManager;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class QRZInterface {
	private static Logger log = Logger.getLogger(QRZInterface.class.getName());
	private String user;
	private String pass;
	private String sessionKey = null;

	public QRZInterface(String user, String pass) {
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
		String url = "http://www.qrz.com/xml?username=" + this.user + ";password=" + this.pass + ";agent=Earth Log 1.0";
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(new URL(url).openStream());
			
			if (doc.getElementsByTagName("Error").getLength()>0){
				throw new Exception("QRZ ERROR: "+doc.getElementsByTagName("Error").item(0).getTextContent());
			}
			

			if (doc.getElementsByTagName("Key").getLength()>0) {
				this.sessionKey = doc.getElementsByTagName("Key").item(0).getTextContent();
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
				String url = "http://www.qrz.com/xml?s=" + this.sessionKey + ";callsign=" + call;
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db;

				db = dbf.newDocumentBuilder();
				Document doc = db.parse(new URL(url).openStream());

				c = CallInfo.build(doc);
				
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
}