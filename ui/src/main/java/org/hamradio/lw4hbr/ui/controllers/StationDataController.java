package org.hamradio.lw4hbr.ui.controllers;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.hamradio.lw4hbr.derby.DerbyManager;
import org.hamradio.lw4hbr.ui.GeoLogMain;
import org.hamradio.lw4hbr.ui.model.StationDataModel;
import org.hamradio.lw4hbr.ui.model.TabPaneModel;
import org.hamradio.lw4hbr.util.LocatorUtil;

public class StationDataController extends AbstractController<GeoLogMain> {
	
	private static Logger log = Logger.getLogger(StationDataController.class.getName());
	
	StationDataModel model = null;
	private static DecimalFormat FORMATTER = new DecimalFormat("#.000000000");
	static {
		FORMATTER.getDecimalFormatSymbols().setDecimalSeparator('.');
		FORMATTER.getDecimalFormatSymbols().setGroupingSeparator('.');
	}

	public StationDataController(GeoLogMain view) {
		super(view);
		model = new StationDataModel();
		registerModel(StationDataModel.MODEL_KEY, model);
		initComponets();
	}

	private void initComponets() {
		try {

			final Desktop desk = Desktop.getDesktop();

			HashMap<String, Object> stationData = DerbyManager.getInstance().getStationData();

			model.setCall((String) stationData.get("CALL_ID"));
			model.setLocator((String) stationData.get("LOCATOR"));
			model.setQrzUser((String) stationData.get("QRZ_USER"));
			model.setQrzPassword((String) stationData.get("QRZ_PASS"));

			model.setHamQTHUser((String) stationData.get("HAM_USER"));
			model.setHamQTHPassword((String) stationData.get("HAM_PASS"));
			
			model.setQrzPassword((String) stationData.get("QRZ_PASS"));

			
			view.getTxtCall().setText((String) stationData.get("CALL_ID"));
			view.getTxtQrzUser().setText((String) stationData.get("QRZ_USER"));
			view.getQrzPassword().setText((String) stationData.get("QRZ_PASS"));

			view.getTextHamQthUser().setText((String) stationData.get("HAM_USER"));
			view.getTextHamQthPassword().setText((String) stationData.get("HAM_PASS"));

			
			if (stationData.get(StationDataModel.MODEL_LATITUDE) != null) {
				view.getFieldLatitude().setText(FORMATTER.format((Double) stationData.get(StationDataModel.MODEL_LATITUDE)));
			}

			if (stationData.get(StationDataModel.MODEL_LONGITUDE) != null) {
				view.getFieldLongitude().setText(FORMATTER.format((Double) stationData.get(StationDataModel.MODEL_LONGITUDE)));
			}

			view.getTxtCall().addFocusListener(new FocusListener() {

				@Override
				public void focusLost(FocusEvent arg0) {
					try {


					} catch (Exception e) {
						// TODO Auto-generated catch block
						log.error("Error",e);
					}

				}

				@Override
				public void focusGained(FocusEvent arg0) {
					// TODO Auto-generated method stub

				}
			});

			view.getBtnCancel().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					TabPaneModel tabPaneModel = (TabPaneModel) getRegisteredModel(TabPaneModel.MODEL_KEY);
					tabPaneModel.setPanelIndex(0);

				}
			});

			view.getTxtLocator().setText((String) stationData.get("LOCATOR"));
			// view.getLableStationLatitude().setText(new
			// DecimalFormat("#.0000").format(LocatorUtil.loc2degminsec((String)
			// stationData.get("LOCATOR")).getLatitude()));
			// view.getLabeStationLongitude().setText(new
			// DecimalFormat("#.0000").format(LocatorUtil.loc2degminsec((String)
			// stationData.get("LOCATOR")).getLongitude()));

			// view.getLabeStationLongitude().setText(LocatorUtil.loc2degminsec((String)stationData.get("LOCATOR")).getLongitude());
			view.getTxtLocator().addFocusListener(new FocusListener() {

				@Override
				public void focusLost(FocusEvent e) {
					if (!view.getTxtLocator().getText().isEmpty()) {
						 view.getFieldLatitude().setText(FORMATTER.format(LocatorUtil.loc2degminsec(view.getTxtLocator().getText()).getLatitude()));
						 view.getFieldLongitude().setText(FORMATTER.format(LocatorUtil.loc2degminsec(view.getTxtLocator().getText()).getLongitude()));
						
					}
				}

				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub

				}
			});

			view.getBtnSaveStationData().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						TabPaneModel tabPaneModel = (TabPaneModel) getRegisteredModel(TabPaneModel.MODEL_KEY);

						model.setCall(view.getTxtCall().getText());
						model.setLocator(view.getTxtLocator().getText());
						model.setQrzUser(view.getTxtQrzUser().getText());
						model.setQrzPassword(view.getQrzPassword().getText());
						
						model.setHamQTHUser(view.getTextHamQthUser().getText());
						model.setHamQTHPassword(view.getTextHamQthPassword().getText());
						
						
						
						model.setLatitude(FORMATTER.parse(view.getFieldLatitude().getText()).doubleValue());
						model.setLongitude(FORMATTER.parse(view.getFieldLongitude().getText()).doubleValue());
						
						DerbyManager.getInstance().setStationData(model.getCall(),model.getLocator(),model.getQrzUser(),model.getQrzPassword(),model.getLatitude(),model.getLongitude(),model.getHamQTHUser(),model.getHamQTHPassword());
						
						
						tabPaneModel.setPanelIndex(0);
						
					} catch (Exception e) {	
						// TODO Auto-generated catch block
						log.error("Error", e);
					}

				}
			});

			view.getBtnUseMap().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						desk.browse(new URI("http://f6fvy.free.fr/qthLocator/fullScreen.php"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						log.error("Error", e1);
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						log.error("Error", e1);
					}

				}
			});

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("Error", e);
		}

	}
}
