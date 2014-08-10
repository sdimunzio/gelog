package org.hamradio.lw4hbr.adif;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hamradio.lw4hbr.model.Qso;

public class ADIFReader {
	
	Logger log = Logger.getLogger(ADIFReader.class.getName());
	
	
	private ArrayList<Qso> list = new ArrayList<Qso>();
	private File file;

	public ADIFReader(File file) {
		this.file = file;
	}

	public ADIFReader(String filePath) {
		this.file = new File(filePath);
	}

	public List<Qso> read() throws Exception {

		ADIFParser parser = new ADIFParser();
		BufferedReader r = new BufferedReader(new FileReader(file));
		String line;
		int i = 1;

		boolean read = false;
		boolean gotRecord = false;
		StringBuffer record = new StringBuffer();

		while ((line = r.readLine()) != null) {

			line = line.trim();

			if (line.toUpperCase().indexOf("<EOH>") > -1) {
				read = true;
			} else {
				if (read) {
					record.append(line);
					if (line.indexOf("<EOR>") > -1) {
						gotRecord = true;

					}
				}

			}

			if (gotRecord) {
				try {

					Qso qso = parser.parseLine(record.toString().replaceAll("\n", "\\n"));

					
					if ((qso != null)&&(qso.getCall()!=null)) {
						// qsoPerBand.put(key, value)
						list.add(qso);
						setOtherBandsIfExist(qso);
					}else{
						log.error ("Qso With null call found, possible paser error");
					}
				} catch (Exception e) {
					log.error("Error",e);
					
				} finally {
					record = new StringBuffer();
					gotRecord = false;
				}
			}

		}

		return list;
	}

	private void setOtherBandsIfExist(Qso q) {

		for (Qso qso : list) {
			if (qso.getBand() != null) {
				if ((qso.getCall()!=null) &&(qso.getCall().equalsIgnoreCase(q.getCall())) && (!qso.getBand().equalsIgnoreCase(q.getBand()))) {
					qso.addWorkedAlso(q);
					q.addWorkedAlso(qso);
				}
			}
		}

	}
}
