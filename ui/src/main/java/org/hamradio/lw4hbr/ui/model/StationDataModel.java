package org.hamradio.lw4hbr.ui.model;

public class StationDataModel extends AbstractModel {
	public static String MODEL_KEY = "STATION_DATA";
	public static String MODEL_CALL = "CALL";
	public static String MODEL_LATITUDE = "LATITUDE";
	public static String MODEL_LONGITUDE = "LONGITUDE";
	public static String MODEL_LOCATOR = "LOCATOR";
	
	public static String MODEL_QRZ_USER = "MODEL_QRZ_USER";
	public static String MODEL_QRZ_PASS = "MODEL_QRZ_PASS";

	public static String MODEL_HAM_USER = "MODEL_HAM_USER";
	public static String MODEL_HAM_PASS = "MODEL_HAM_PASS";

	private String call;
	private String locator;
	
	private String qrzUser;
	private String qrzPassword;

	private String hamQTHUser;
	private String hamQTHPassword;

	
	
	public String getHamQTHPassword() {
		return hamQTHPassword;
	}

	public void setHamQTHPassword(String hamQTHPassword) {
		this.hamQTHPassword = hamQTHPassword;
	}

	public String getHamQTHUser() {
		return hamQTHUser;
	}

	public void setHamQTHUser(String hamQTHUser) {
		this.hamQTHUser = hamQTHUser;
	}

	private Double latitude;
	private Double longitude;

	

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		firePropertyChange(MODEL_LATITUDE, qrzUser,this.latitude = latitude);
		
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		firePropertyChange(MODEL_LONGITUDE, qrzUser,this.longitude = longitude);
		
	}

	public String getQrzUser() {
		return qrzUser;
	}

	public void setQrzUser(String qrzUser) {
		firePropertyChange(MODEL_QRZ_USER, qrzUser, this.qrzUser = qrzUser);
	}

	public String getQrzPassword() {
		return qrzPassword;
	}

	public void setQrzPassword(String qrzPassword) {
		firePropertyChange(MODEL_QRZ_PASS, qrzPassword, this.qrzPassword = qrzPassword);

	}

	public String getLocator() {
		return locator;
	}

	public void setLocator(String locator) {
		firePropertyChange(MODEL_LOCATOR, locator, this.locator = locator);

	}

	
	public String getCall() {
		return call;
	}

	public void setCall(String call) {
		firePropertyChange(MODEL_CALL, call, this.call = call);
	}

	


}
