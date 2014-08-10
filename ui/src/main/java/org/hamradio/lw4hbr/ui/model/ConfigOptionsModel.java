package org.hamradio.lw4hbr.ui.model;


public class ConfigOptionsModel extends AbstractModel {
	public static String MODEL_KEY="CONFIGURATION";
	public static String TIME_TYPE_PROPERTY = "TIME_PROPERTY";
	public static String CACHE_PROPERTY = "CACHE_PROPERTY";
	public static String PATH_TIME_PROPERTY = "PATH_TIME_PROPERTY";

	public static String QSO_ORGANIZATION_PROPERTY = "QSO_ORGANIZATION_PROPERTY";
	public static String LOCATION_RESOLUTION_PROPERTY = "LOCATION_RESOLUTION_PROPERTY";

	private Integer timeType=0;
	private Integer pathType=0;
	private Integer qsoOrganizationType=0;
	private Integer locationResolutionType=0;
	
	private Boolean useCache=false;
	
	public Integer getTimeType() {
		return timeType;
	}

	public Boolean getUseCache() {
		firePropertyChange(CACHE_PROPERTY, this.useCache,
				this.useCache = useCache);
		return useCache;
	}

	public void setUseCache(Boolean useCache) {
		this.useCache = useCache;
	}

	public void setTimeType(Integer timeType) {
		firePropertyChange(TIME_TYPE_PROPERTY, this.timeType,
				this.timeType = timeType);
	}

	public Integer getPathType() {
		return pathType;
	}

	public void setPathType(Integer pathType) {
		firePropertyChange(PATH_TIME_PROPERTY, this.pathType,
				this.pathType = pathType);
	}

	public Integer getQsoOrganizationType() {
		return qsoOrganizationType;
	}

	public void setQsoOrganizationType(Integer qsoOrganizationType) {
		firePropertyChange(QSO_ORGANIZATION_PROPERTY, this.qsoOrganizationType,
				this.qsoOrganizationType = qsoOrganizationType);

	}

	public Integer getLocationResolutionType() {
		return locationResolutionType;
	}

	public void setLocationResolutionType(Integer locationResolutionType) {
		firePropertyChange(LOCATION_RESOLUTION_PROPERTY,
				this.locationResolutionType,
				this.locationResolutionType = locationResolutionType);
	}

}

