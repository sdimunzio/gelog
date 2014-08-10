package org.hamradio.lw4hbr.ui;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;

public class LocatorTableModel extends AbstractTableModel {
	private static Logger log = Logger.getLogger(LocatorTableModel.class.getName());
	
	public static final String UNABLE_TO_FETCH_DATA = "CONNECTION ERROR";

	private ResultSet dataSource = null;
	private String[] columms;

	public LocatorTableModel(ResultSet rs, String[] columms) {
		this.dataSource = rs;
		this.columms = columms;
		
	}

	public int getRowCount() {
		int rowCount = 0;
		try {
			if (dataSource.last()) {
				rowCount = dataSource.getRow();
			}
		} catch (SQLException e) {
			log.error("Error",e);
		}
		return rowCount;
	}

	public int getColumnCount() {
		int columnCount = 0;
		try {
			ResultSetMetaData columnMetaData = dataSource.getMetaData();
			columnCount = columnMetaData.getColumnCount();
		} catch (SQLException e) {
			// If erros are found, just ignore them and returns 0
		}
		return columnCount;
	}

	@Override
	public String getColumnName(int column) {
		return columms[column];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Object cellData = null;
		try {
			dataSource.absolute(rowIndex + 1);
			cellData = dataSource.getObject(columnIndex + 1);
		} catch (SQLException e) {
			// If erros are found, just ignore them and returns the default
			// value from UNABLE_TO_FETCH_DATA constant
			cellData = UNABLE_TO_FETCH_DATA;
		}

		return cellData;
	}
	
	
}
