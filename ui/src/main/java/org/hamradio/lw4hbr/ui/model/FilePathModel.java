package org.hamradio.lw4hbr.ui.model;

import java.io.File;

import org.hamradio.lw4hbr.ui.controllers.MainController;

public class FilePathModel extends AbstractModel{
	public static String MODEL_KEY="FILE_PATH";
	private String filePath;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		firePropertyChange(MainController.FILE_PATH_PROPERTY, this.filePath,
				this.filePath = filePath);
	}
	
	
	public String getFileKmlName() {
		File f=new File(filePath);
		return f.getName().substring(0,f.getName().indexOf("."))+".kml";
	}
}
