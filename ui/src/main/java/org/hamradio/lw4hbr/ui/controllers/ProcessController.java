package org.hamradio.lw4hbr.ui.controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import org.apache.log4j.Logger;
import org.hamradio.lw4hbr.ConTestMapGenerator;
import org.hamradio.lw4hbr.model.Configuration;
import org.hamradio.lw4hbr.model.StatusChangeListener;
import org.hamradio.lw4hbr.model.StatusEvent;
import org.hamradio.lw4hbr.ui.GeoLogMain;
import org.hamradio.lw4hbr.ui.Messages;
import org.hamradio.lw4hbr.ui.model.ConfigOptionsModel;
import org.hamradio.lw4hbr.ui.model.FilePathModel;
import org.hamradio.lw4hbr.ui.model.StationDataModel;

public class ProcessController extends AbstractController<GeoLogMain> implements StatusChangeListener {
	private static Logger log = Logger.getLogger(ProcessController.class.getName());
	private boolean running = false;

	public static Boolean doStop = false;

	public ProcessController(GeoLogMain view) {
		super(view);
		initComponets();
	}

	private void initComponets() {
		view.getBtnStart().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if(running){
					Configuration.doStop=true;
				}else{
					Configuration.doStop=false;
				}
				view.getBtnStart().setText(Messages.getString("GeoLogMain.btnStart.text.cancel"));

				new Thread(new Runnable() {
					@Override
					public void run() {
						running=true;
						Configuration cfg = new Configuration();
						// cfg.setLocationResolutionType(locationResolutionType);
						FilePathModel modelFile = (FilePathModel) getRegisteredModel(FilePathModel.MODEL_KEY);
						ConfigOptionsModel modelConfig = (ConfigOptionsModel) getRegisteredModel(ConfigOptionsModel.MODEL_KEY);

						cfg.setLocationResolutionType(modelConfig.getLocationResolutionType());
						cfg.setPathType(modelConfig.getPathType());
						cfg.setQsoOrganizationType(modelConfig.getQsoOrganizationType());
						cfg.setTimeType(modelConfig.getTimeType());
						cfg.setInputFilePath(modelFile.getFilePath());

						cfg.setOuputFilePath("KML" + System.getProperty("file.separator") + modelFile.getFileKmlName());
						cfg.addStatusChangeListener(ProcessController.this);

						StationDataModel stModel = (StationDataModel) getRegisteredModel(StationDataModel.MODEL_KEY);

						cfg.setMayCall(stModel.getCall());
						cfg.setMayLocator(stModel.getLocator());

						cfg.setQrzUser(stModel.getQrzUser());
						cfg.setQrzPass(stModel.getQrzPassword());
						
						cfg.setHamQTHUser(stModel.getHamQTHUser());
						cfg.setHamQTHPass(stModel.getHamQTHPassword());

						cfg.setUseQRZCache(modelConfig.getUseCache());

						ConTestMapGenerator gen = new ConTestMapGenerator();

						try {
							gen.createKml(cfg);

							Runtime load = Runtime.getRuntime();
							load.exec("explorer " + System.getProperty("gelog.home") + System.getProperty("file.separator") + cfg.getOuputFilePath());

						} catch (Exception e) {
							log.error("Error",e);
							view.getStatusPane().setText(e.getMessage());
							view.getStatusPane().setForeground(Color.RED);
							view.getBtnStart().setEnabled(true);
						}

						view.getBtnStart().setEnabled(true);
						view.getBtnStart().setText(Messages.getString("GeoLogMain.btnStart.text"));
						running=false;
					}
				}).start();
			}

		});
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		super.propertyChange(evt);
	}

	@Override
	public void fireEvent(StatusEvent event) {
		view.getProccessstatus().setValue(event.getPercetage());
		view.getStatusPane().setText("<font face='arial' size='2'>" + event.getStatusText() + "</font>");
	}

}
