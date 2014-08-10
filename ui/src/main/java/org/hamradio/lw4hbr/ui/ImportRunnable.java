package org.hamradio.lw4hbr.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.sql.SQLException;
import java.util.StringTokenizer;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import org.apache.log4j.Logger;
import org.hamradio.lw4hbr.derby.DerbyManager;
import org.hamradio.lw4hbr.model.CallLocator;

public class ImportRunnable implements Runnable {
	private static Logger log = Logger.getLogger(ImportRunnable.class.getName());
	private boolean stopRequested = false;
	private JLabel lblImportigQthLocator;
	private File file;
	private JProgressBar progresBar;

	public boolean isStopRequested() {
		return stopRequested;
	}

	public void setStopRequested(boolean stopRequested) {
		this.stopRequested = stopRequested;
	}

	public JLabel getLblImportigQthLocator() {
		return lblImportigQthLocator;
	}

	public void setLblImportigQthLocator(JLabel lblImportigQthLocator) {
		this.lblImportigQthLocator = lblImportigQthLocator;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public JProgressBar getProgresBar() {
		return progresBar;
	}

	public void setProgresBar(JProgressBar progresBar) {
		this.progresBar = progresBar;
	}

	
	public void runAsThread(){
		new Thread(this).start();
	}
	
	
	public void run() {
		Integer current = 0;
		String line = null;
		int row = 0;
		int col = 0;
		FileReader r;
		try {
			DerbyManager.getInstance().cleanDatabase();
			r = new FileReader(getFile());
			LineNumberReader numberRaeader = new LineNumberReader(r);
			numberRaeader.skip(Long.MAX_VALUE);
			Integer total = numberRaeader.getLineNumber();
			r = new FileReader(getFile());
			BufferedReader bufRdr = new BufferedReader(r);
			while ((line = bufRdr.readLine()) != null) {
				if (stopRequested) {
					break;
				}
				StringTokenizer st = new StringTokenizer(line, ";");
				String call = st.nextToken();
				String locator = st.nextToken();
				new CallLocator(call, locator).save();
				lblImportigQthLocator.setText("Importing locator for " + call);
				Double value = (100d / total) * (current++);
				getProgresBar().setValue(value.intValue());
			}
			
			Thread.currentThread().interrupt();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			log.error("Error",e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("Error",e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("Error",e);
		}
	}
}
