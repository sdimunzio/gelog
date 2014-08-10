package org.hamradio.lw4hbr.derby;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.hamradio.lw4hbr.util.CallInfo;

public class DerbyManager {

	Logger log = Logger.getLogger(DerbyManager.class.getName());

	private static String APP_VERSION_101 = "1.0.1b";
	private static String APP_VERSION_102 = "1.0.2b";
	private static String APP_VERSION_LAST=APP_VERSION_102;

	private static DerbyManager instance = null;

	public static DerbyManager getInstance() {
		if (instance == null) {
			instance = new DerbyManager();
			instance.initializeDb();
		}
		return instance;
	}

	private DerbyManager() {
		// TODO Auto-generated constructor stub
	}

	private static String createString1 = "CREATE TABLE CALL_LOCATOR  (CALL_ID VARCHAR(255) NOT NULL, LOCATOR VARCHAR(10), LATITUDE DOUBLE  ,LONGITUDE DOUBLE ) ";
	private static String createString2 = "CREATE TABLE SATION_DATA  (CALL_ID VARCHAR(255) NOT NULL, LOCATOR VARCHAR(10), LATITUDE DOUBLE  ,LONGITUDE DOUBLE ,QRZ_USER VARCHAR(255),QRZ_PASS VARCHAR(255))";

	private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private static String dbName = System.getProperty("gelog.home") + "/locatorDB";

	public void relaseConnection(Connection conn) throws SQLException {
		conn.close();
		conn = null;
	}

	public Connection getDbConnection() throws SQLException {
		Connection conn = null;
		try {

			String connectionURL = "jdbc:derby:" + dbName + ";create=true";

			Class.forName(driver);
			conn = DriverManager.getConnection(connectionURL);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			log.error("Error", e);
		}

		return conn;
	}

	public boolean updateDb102b() {
		String UPDATE_1 = "ALTER TABLE SATION_DATA ADD COLUMN HAM_USER VARCHAR(255)";
		String UPDATE_2 = "ALTER TABLE SATION_DATA ADD COLUMN HAM_PASS VARCHAR(255)";

        String UPDATE_3 = "UPDATE CURRENT_VERSION SET VERSION = '" + APP_VERSION_102 + "'";
		boolean withErrors = false;
		try {

			getDbConnection().createStatement().execute(UPDATE_1);
		} catch (Exception e) {
			log.error("Error", e);
			withErrors = true;

		}
		try {

			getDbConnection().createStatement().execute(UPDATE_2);
		} catch (Exception e) {
			log.error("Error", e);
			withErrors = true;
		}

		try {

			getDbConnection().createStatement().execute(UPDATE_3);
		} catch (Exception e) {
			log.error("Error", e);
			withErrors = true;
		}

		if (withErrors) {
			log.error("Databse updated with errors please start with an empty database!");
		}

		return true;
	}

	public boolean updateDb101b() {
		String UPDATE_1 = "DROP TABLE CALL_LOCATOR";
		String UPDATE_2 = "CREATE TABLE CALL_LOCATOR  (CALL_ID VARCHAR(255) NOT NULL, LOCATOR VARCHAR(10), LATITUDE DECIMAL(15,10)  ,LONGITUDE DECIMAL(15,10)) ";

		String UPDATE_3 = "ALTER TABLE SATION_DATA DROP COLUMN LATITUDE";
		String UPDATE_4 = "ALTER TABLE SATION_DATA DROP COLUMN LONGITUDE";

		String UPDATE_5 = "ALTER TABLE SATION_DATA ADD COLUMN LATITUDE DECIMAL(15,10)";
		String UPDATE_6 = "ALTER TABLE SATION_DATA ADD COLUMN LONGITUDE DECIMAL(15,10)";

		String UPDATE_11 = "CREATE TABLE CURRENT_VERSION (VERSION VARCHAR(255) NOT NULL)";
		String UPDATE_12 = "INSERT INTO CURRENT_VERSION (VERSION) VALUES ('" + APP_VERSION_101 + "')";

		boolean withErrors = false;
		try {

			getDbConnection().createStatement().execute(UPDATE_1);
		} catch (Exception e) {
			log.error("Error", e);
			withErrors = true;

		}
		try {

			getDbConnection().createStatement().execute(UPDATE_2);
		} catch (Exception e) {
			log.error("Error", e);
			withErrors = true;
		}
		try {

			getDbConnection().createStatement().execute(UPDATE_3);
		} catch (Exception e) {
			log.error("Error", e);
			withErrors = true;
		}
		try {

			getDbConnection().createStatement().execute(UPDATE_4);
		} catch (Exception e) {
			log.error("Error", e);
			withErrors = true;
		}
		try {

			getDbConnection().createStatement().execute(UPDATE_5);
		} catch (Exception e) {
			log.error("Error", e);
			withErrors = true;
		}
		try {

			getDbConnection().createStatement().execute(UPDATE_6);
		} catch (Exception e) {
			log.error("Error", e);
			withErrors = true;
		}

		try {

			getDbConnection().createStatement().execute(UPDATE_11);
		} catch (Exception e) {
			log.error("Error", e);
			withErrors = true;
		}

		try {

			getDbConnection().createStatement().execute(UPDATE_12);
		} catch (Exception e) {
			log.error("Error", e);
			withErrors = true;
		}

		if (withErrors) {
			log.error("Databse updated with errors please start with an empty database!");
		}

		return true;
	}

	public boolean updatedTo(String xVersion) {
		Boolean retVal = false;
		try {

			String query = "SELECT VERSION FROM CURRENT_VERSION";
			Connection con = getDbConnection();
			java.sql.PreparedStatement stm = con.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				String version = rs.getString("VERSION");
				if (xVersion.equalsIgnoreCase(version)) {
					retVal = true;
				}
			}


		} catch (Exception e) {
			log.error("Error", e);
		}
		return retVal;
	}

	public boolean initializeDb() {
		try {
			if (!isdabaseInitialized()) {
				getDbConnection().createStatement().execute(createString1);
				getDbConnection().createStatement().execute(createString2);
				updateDb101b();
				updateDb102b();
				initStationData();
				return true;
			}

            if (!updatedTo(APP_VERSION_101) && !updatedTo(APP_VERSION_102)) {
                if (!updateDb101b()) {
                    log.error("Unexpceted error, can't update to current version");
                    System.exit(0);
                }
            }

			if (!updatedTo(APP_VERSION_102)) {
				if (!updateDb102b()) {
					log.error("Unexpceted error, can't update to current version");
					System.exit(0);
				}
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("Error", e);

		}
		return false;
	}

	public boolean isdabaseInitialized() throws SQLException {
		ResultSet result;
		boolean tableExists = false;
		DatabaseMetaData metadata = null;
		Connection conn = getDbConnection();
		metadata = conn.getMetaData();
		String[] names = { "TABLE" };
		ResultSet tableNames = metadata.getTables(null, "APP", null, names);
		while ((tableNames.next()) && (tableExists == false)) {

			if (tableNames.getString("TABLE_NAME").equalsIgnoreCase("CALL_LOCATOR")) {
				tableExists = true;
			}
		}

		relaseConnection(conn);
		return tableExists;
	}

	public CallInfo getCallInfo(String call) throws SQLException {
		String query = "SELECT * FROM CALL_LOCATOR WHERE CALL_ID=?";
		Connection con = getDbConnection();
		java.sql.PreparedStatement stm = con.prepareStatement(query);
		stm.setString(1, call);
		ResultSet rs = stm.executeQuery();
		// Coordinate c = null;
		return CallInfo.buildFromResultSet(rs);
	}

	public CallInfo getCallFromCache(String call) throws SQLException {
		String query = "SELECT * FROM CALL_LOCATOR WHERE CALL_ID=?";
		Connection con = getDbConnection();
		java.sql.PreparedStatement stm = con.prepareStatement(query);
		stm.setString(1, call);
		ResultSet rs = stm.executeQuery();
		CallInfo c = null;
		if (rs.next()) {
			c = new CallInfo();
			c.setCall(call);
			c.setLon(rs.getDouble("LONGITUDE"));
			c.setLat(rs.getDouble("LATITUDE"));
		}
		return c;
	}

	public String getCallLocator(String call) throws SQLException {
		String query = "SELECT * FROM CALL_LOCATOR WHERE CALL_ID=?";
		Connection con = getDbConnection();
		java.sql.PreparedStatement stm = con.prepareStatement(query);
		stm.setString(1, call);
		ResultSet rs = stm.executeQuery();
		String locator = null;
		if (rs.next()) {
			locator = rs.getString("LOCATOR");

		}
		return locator;
	}

	public void cacheCallInfo(CallInfo cinfo) throws SQLException {

		if ((cinfo.getLat() != null) && (cinfo.getLon() != null)) {
			String query = "INSERT INTO CALL_LOCATOR (CALL_ID,LOCATOR,LONGITUDE,LATITUDE) VALUES (?,?,?,?)";
			Connection con = getDbConnection();
			java.sql.PreparedStatement stm = con.prepareStatement(query);
			int index = 1;
			stm.setString(index++, cinfo.getCall());
			stm.setString(index++, cinfo.getGrid());
			stm.setDouble(index++, cinfo.getLon());
			stm.setDouble(index++, cinfo.getLat());
			stm.execute();
		}

	}

	public void cleanDatabase() throws SQLException {

		String query = "truncate table CALL_LOCATOR";
		Connection con = getDbConnection();
		java.sql.PreparedStatement stm = con.prepareStatement(query);
		stm.execute();
	}

	public void initStationData() throws SQLException {
		String query = "INSERT INTO SATION_DATA (CALL_ID) VALUES (?)";
		Connection con = getDbConnection();
		java.sql.PreparedStatement stm = con.prepareStatement(query);
		int index = 1;
		stm.setString(index++, "MYCALL");
		stm.execute();
		
		con.prepareStatement("INSERT INTO CURRENT_VERSION (VERSION) VALUES ('" + APP_VERSION_LAST + "')").execute();
	}

	public void setStationData(String call, String locator, String qrzUser, String qrzPass, Double lat, Double lon,String hamUser, String hamPass) throws SQLException {
		Connection con = getDbConnection();
		String query0 = "DELETE FROM SATION_DATA";
		con.prepareCall(query0).execute();

		String query = "INSERT INTO SATION_DATA (CALL_ID,LOCATOR,LONGITUDE,LATITUDE,QRZ_USER,QRZ_PASS,HAM_USER,HAM_PASS) VALUES (?,?,?,?,?,?,?,?)";

		java.sql.PreparedStatement stm = con.prepareStatement(query);
		int index = 1;
		stm.setString(index++, call);
		stm.setString(index++, locator);
		stm.setDouble(index++, (lon != null) ? lon : 0d);
		stm.setDouble(index++, (lat != null) ? lat : 0d);
		stm.setString(index++, qrzUser);
		stm.setString(index++, qrzPass);
		stm.setString(index++, hamUser);
		stm.setString(index++, hamPass);
		stm.execute();
	}

	public HashMap<String, Object> getStationData() throws SQLException {
		String query = "Select * from  SATION_DATA";
		Connection con = getDbConnection();
		java.sql.PreparedStatement stm = con.prepareStatement(query);
		ResultSet rs = stm.executeQuery();
		HashMap<String, Object> result = new HashMap<String, Object>();
		if (rs.next()) {
			result.put("CALL_ID", rs.getString("CALL_ID"));
			result.put("LOCATOR", rs.getString("LOCATOR"));
			result.put("LONGITUDE", rs.getDouble("LONGITUDE"));
			result.put("LATITUDE", rs.getDouble("LATITUDE"));
			result.put("QRZ_PASS", rs.getString("QRZ_PASS"));
			result.put("QRZ_USER", rs.getString("QRZ_USER"));
			result.put("HAM_USER", rs.getString("HAM_USER"));
			result.put("HAM_PASS", rs.getString("HAM_PASS"));
		}
		return result;
	}

}
