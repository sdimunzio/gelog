package org.hamradio.lw4hbr.ui.controllers;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
import org.hamradio.lw4hbr.ui.ApplicationInstanceListener;
import org.hamradio.lw4hbr.ui.ApplicationInstanceManager;
import org.hamradio.lw4hbr.ui.ErrorDialog;
import org.hamradio.lw4hbr.ui.GeoLogMain;
import org.hamradio.lw4hbr.ui.Messages;

public class MainController extends AbstractController<GeoLogMain> {

	
	private static Logger log = Logger.getLogger(MainController.class.getName());
	
	public MainController(GeoLogMain view) {
		super(view);

		   
		
		
		if (!ApplicationInstanceManager.registerInstance()) {
			// instance already running.
			JOptionPane.showMessageDialog(null, Messages.getString("MainController.alredyRunning")); //$NON-NLS-1$
			System.exit(0);
		}

		ApplicationInstanceManager.setApplicationInstanceListener(new ApplicationInstanceListener() {

			public void newInstanceCreated() {
				log.info(Messages.getString("MainController.newInstanceDetected")); //$NON-NLS-1$
				// this is where your handler code goes...
			}
		});

		String userHome = System.getProperty("user.home");

		System.setProperty("gelog.home", userHome + System.getProperty("file.separator") + "GE-LOG"); //$NON-NLS-1$

		try {


			if (!(new File(userHome + "/GE-LOG").exists())) {
				new File(userHome + "/GE-LOG/").mkdir();
			}

			if (!(new File(userHome + "/GE-LOG/KML").exists())) {
				new File(userHome + "/GE-LOG/KML").mkdir();
			}

            boolean hasCty= new File(System.getProperty("gelog.home") + System.getProperty("file.separator") + "cty.csv").exists() || this.getClass().getClassLoader().getResource("cty.csv")!=null;

			if (!hasCty) {
				ErrorDialog dialog = new ErrorDialog();
				dialog.setModal(true);
				dialog.setVisible(true);

			}
			
			 Logger rootLogger = Logger.getRootLogger();
			    rootLogger.setLevel(Level.INFO);
			    PatternLayout layout = new PatternLayout(PatternLayout.TTCC_CONVERSION_PATTERN);
			    rootLogger.addAppender(new ConsoleAppender(layout));
			    try {
			        RollingFileAppender rfa = new RollingFileAppender(layout, System.getProperty("gelog.home")+"/GeLog.log");
			        rfa.setMaximumFileSize(1000000);
			        rootLogger.addAppender(rfa);

			    } catch (IOException e) {
			           //  e.printStackTrace();
			    }    log.info("Testing...");
			  

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("Error",e);
		}

		// initilize folders

		registerController(new TabPanelController(view));
		registerController(new FilePathController(view));
		registerController(new ConfigurationController(view));
		registerController(new ProcessController(view));
		registerController(new MenuController(view));
		registerController(new StationDataController(view));

	}

}
