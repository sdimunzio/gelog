package org.hamradio.lw4hbr.model;

import java.util.ArrayList;

public class Configuration {
	
	public static boolean doStop;
	
	private String qrzUser;
	private String qrzPass;
	
	private String hamQTHUser;
	private String hamQTHPass;
	private Boolean useQRZCache=false;
	private Integer contestNumber;

	public Integer getContestNumber() {
		return contestNumber;
	}

	public void setContestNumber(Integer contestNumber) {
		this.contestNumber = contestNumber;
	}

	public void setUseQRZCache(Boolean useQRZCache) {
		this.useQRZCache = useQRZCache;
	}

	private String statusText = "";

	private String mayCall;

	public String getHamQTHUser() {
		return hamQTHUser;
	}

	public void setHamQTHUser(String hamQTHUser) {
		this.hamQTHUser = hamQTHUser;
	}

	public String getHamQTHPass() {
		return hamQTHPass;
	}

	public void setHamQTHPass(String hamQTHPass) {
		this.hamQTHPass = hamQTHPass;
	}

	public Boolean isUseQRZCache() {
		return useQRZCache;
	}

	public void setUseQRZCache(boolean useQRZCache) {
		this.useQRZCache = useQRZCache;
	}

	public boolean isQRZenabled() {
		return (this.qrzPass != null && this.qrzUser != null);
	}
	
	public boolean isHamQTHenabled() {
		return (this.hamQTHUser != null && this.hamQTHPass != null);
	}

	public String getQrzUser() {
		return qrzUser;
	}

	public void setQrzUser(String qrzUser) {
		this.qrzUser = qrzUser;
	}

	public String getQrzPass() {
		return qrzPass;
	}

	public void setQrzPass(String qrzPass) {
		this.qrzPass = qrzPass;
	}

	public String getMayCall() {
		return mayCall;
	}

	public void setMayCall(String mayCall) {
		this.mayCall = mayCall;
	}

	private String mayLocator;

	public String getMayLocator() {
		return mayLocator;
	}

	public void setMayLocator(String mayLocator) {
		this.mayLocator = mayLocator;
	}

	private Integer progress = 0;

	private String inputFilePath;

	private String ouputFilePath;

	public String getInputFilePath() {
		return inputFilePath;
	}

	public void setInputFilePath(String inputFilePath) {
		this.inputFilePath = inputFilePath;
	}

	public String getOuputFilePath() {
		return ouputFilePath;
	}

	public void setOuputFilePath(String ouputFilePath) {
		this.ouputFilePath = ouputFilePath;
	}

	private Integer timeType = 0;
	private Integer pathType = 0;
	private Integer qsoOrganizationType = 0;
	private Integer locationResolutionType = 0;

	public Integer getTimeType() {
		return timeType;
	}

	public void setTimeType(Integer timeType) {
		this.timeType = timeType;
	}

	public Integer getPathType() {
		return pathType;
	}

	public void setPathType(Integer pathType) {
		this.pathType = pathType;
	}

	public Integer getQsoOrganizationType() {
		return qsoOrganizationType;
	}

	public void setQsoOrganizationType(Integer qsoOrganizationType) {
		this.qsoOrganizationType = qsoOrganizationType;
	}

	public Integer getLocationResolutionType() {
		return locationResolutionType;
	}

	public void setLocationResolutionType(Integer locationResolutionType) {
		this.locationResolutionType = locationResolutionType;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
		fireEvent();
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
		fireEvent();
	}

	private java.util.List<StatusChangeListener> listeners = new ArrayList<StatusChangeListener>();

	public void addStatusChangeListener(StatusChangeListener listener) {
		listeners.add(listener);
	}

	private void fireEvent() {
		StatusEvent event = new StatusEvent();
		event.setPercetage(progress);
		event.setStatusText(this.statusText);
		for (StatusChangeListener listener : listeners) {
			listener.fireEvent(event);
		}
	}
}
