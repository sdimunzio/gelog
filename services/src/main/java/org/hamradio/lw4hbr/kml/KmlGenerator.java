package org.hamradio.lw4hbr.kml;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hamradio.lw4hbr.model.Configuration;
import org.hamradio.lw4hbr.model.Qso;
import org.hamradio.lw4hbr.resolvers.ResolveManager;
import org.hamradio.lw4hbr.util.CallInfo;
import org.hamradio.lw4hbr.util.LocatorUtil;

import de.micromata.opengis.kml.v_2_2_0.AltitudeMode;
import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Icon;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.LineString;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Snippet;
import de.micromata.opengis.kml.v_2_2_0.Style;
import de.micromata.opengis.kml.v_2_2_0.Units;
import de.micromata.opengis.kml.v_2_2_0.Vec2;

public class KmlGenerator implements IKmlGenerator {

	private static Logger log = Logger.getLogger(KmlGenerator.class.getName());

	private static SimpleDateFormat CRHOME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	private static SimpleDateFormat HOUR_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:00");

	private String hostCall;
	private String normalCallIcon;
	private Coordinate myCoordiantes = null;
	String myCallStation = null;

	public Kml makeKml(List<Qso> list, Configuration cfg) throws Exception {
		myCoordiantes = LocatorUtil.loc2degminsec(cfg.getMayLocator());
		myCallStation = cfg.getMayCall();
		int current = 0;
		int total = list.size();
		log.info("Total QSO " + list.size());

		final Kml kml = new Kml();

		kml.createAndSetScreenOverlay().withName("Absolute Positioning: Top left").withOverlayXY(new Vec2().withX(0).withY(1).withYunits(Units.FRACTION).withXunits(Units.FRACTION))
				.withScreenXY(new Vec2().withX(0).withY(1).withYunits(Units.FRACTION).withXunits(Units.FRACTION))
				.withSize(new Vec2().withX(0).withY(0).withYunits(Units.FRACTION).withXunits(Units.FRACTION))
				.withIcon(new Icon().withHref("http://code.google.com/apis/kml/documentation/top_left.jpg"));

		/*
		 * <ScreenOverlay> <name>Absolute Positioning: Top left</name> <Icon>
		 * <href
		 * >http://code.google.com/apis/kml/documentation/top_left.jpg</href>
		 * </Icon> <overlayXY x="0" y="1" xunits="fraction" yunits="fraction"/>
		 * <screenXY x="0" y="1" xunits="fraction" yunits="fraction"/>
		 * <rotationXY x="0" y="0" xunits="fraction" yunits="fraction"/> <size
		 * x="0" y="0" xunits="fraction" yunits="fraction"/> </ScreenOverlay>
		 */

		Folder root = kml.createAndSetFolder().withName("QSOs");
		HashMap<String, Folder> operatorFolder;
		HashMap<String, Folder> pathFolder;
		HashMap<String, Folder> bandFolder;

		// PATH
		// 0 show
		// 1 hidde

		// Time
		// 0 persistent
		// 1 volatile
		bandFolder = new HashMap<String, Folder>();
		operatorFolder = new HashMap<String, Folder>();

		pathFolder = new HashMap<String, Folder>();

		// kml.createAndSetNetworkLink().withAddress("http://www.hamatlas.eu/Gg/DXCC.kmz").withOpen(true);

		Icon icon = new Icon();
		icon.setHref("http://maps.google.com/mapfiles/kml/paddle/wht-blank.png");

		Placemark myLocation = root.createAndAddPlacemark().withName(myCallStation);
		myLocation.createAndAddStyle().createAndSetIconStyle().withIcon(icon).withScale(1);
		myLocation.createAndSetPoint().withCoordinates(new ArrayList<Coordinate>()).getCoordinates().add(myCoordiantes);
		myLocation.withSnippet(new Snippet().withMaxLines(1).withValue(myCallStation));
		int i = 1;

		for (Qso qso : list) {
			if (Configuration.doStop) {
				break;
			} else {
				String band = qso.getBand();

				String href = "http://thydzik.com/thydzikGoogleMap/markerlink.php?text=?&color=FFFFFF";
				String lineColor = "FFFFFF";

				if (band != null) {

					String bandNumber = band.toUpperCase().replaceAll("M", "");
					bandNumber = bandNumber.replaceAll("CM", "");

					if (band.equalsIgnoreCase("160M")) {
						href = "http://google-maps-icons.googlecode.com/files/teal16.png";
						lineColor = "008080";
					}
					if (band.equalsIgnoreCase("80M")) {
						href = "http://google-maps-icons.googlecode.com/files/black" + bandNumber + ".png";
						lineColor = "000000";
					}

					if (band.equalsIgnoreCase("40M")) {
						href = "http://google-maps-icons.googlecode.com/files/red" + bandNumber + ".png";
						lineColor = "cc9999";
					}

					if (band.equalsIgnoreCase("30M")) {
						href = "http://google-maps-icons.googlecode.com/files/red" + bandNumber + ".png";
						lineColor = "FF4500";
					}

					if (band.equalsIgnoreCase("20M")) {
						href = "http://google-maps-icons.googlecode.com/files/darkblue" + bandNumber + ".png";
						lineColor = "00008B";
					}

					if (band.equalsIgnoreCase("17M")) {
						href = "http://google-maps-icons.googlecode.com/files/red" + bandNumber + ".png";
						lineColor = "FF4500";
					}

					if (band.equalsIgnoreCase("15M")) {
						href = "http://google-maps-icons.googlecode.com/files/orange" + bandNumber + ".png";
						lineColor = "FFA500";
					}

					if (band.equalsIgnoreCase("12M")) {
						href = "http://google-maps-icons.googlecode.com/files/red" + bandNumber + ".png";
						lineColor = "FF4500";
					}

					if (band.equalsIgnoreCase("10M")) {
						href = "http://google-maps-icons.googlecode.com/files/green" + bandNumber + ".png";
						lineColor = "98FB98";
					}

					if (band.equalsIgnoreCase("6M")) {
						href = "http://thydzik.com/thydzikGoogleMap/markerlink.php?text=" + bandNumber + "&color=FF9900";
						lineColor = "FF9900";
					}

					if (band.equalsIgnoreCase("2M")) {
						href = "http://thydzik.com/thydzikGoogleMap/markerlink.php?text=" + bandNumber + "&color=99FF00";
						lineColor = "99FF00";
					}

					// centimetros
					if (band.equalsIgnoreCase("70CM")) {
						href = "http://thydzik.com/thydzikGoogleMap/markerlink.php?text=" + bandNumber + "&color=6699CC";
						lineColor = "6699CC";
					}

					// centimetros
					if (band.equalsIgnoreCase("33CM")) {
						href = "http://thydzik.com/thydzikGoogleMap/markerlink.php?text=" + bandNumber + "&color=33CC66";
						lineColor = "33CC66";
					}

					// centimetros
					if (band.equalsIgnoreCase("23CM")) {
						href = "http://thydzik.com/thydzikGoogleMap/markerlink.php?text=" + bandNumber + "&color=FFCC66";
						lineColor = "FFCC66";
					}

					// centimetros
					if (band.equalsIgnoreCase("13CM")) {
						href = "http://thydzik.com/thydzikGoogleMap/markerlink.php?text=" + bandNumber + "&color=CC66CC";
						lineColor = "CC66CC";
					}

					// centimetros
					if (band.equalsIgnoreCase("9CM")) {
						href = "http://thydzik.com/thydzikGoogleMap/markerlink.php?text=" + bandNumber + "&color=006633";
						lineColor = "006633";
					}

					// centimetros
					if (band.equalsIgnoreCase("6CM")) {
						href = "http://thydzik.com/thydzikGoogleMap/markerlink.php?text=" + bandNumber + "&color=FFCC33";
						lineColor = "FFCC33";
					}

				}

				Icon bandIcon = new Icon().withHref(href);

				Coordinate qsoCoordinates = null;

				CallInfo c = ResolveManager.resolveCall(qso.getCall(), qso.getQTHLocator(), cfg.getLocationResolutionType(), cfg);
				if (c != null) {
					qsoCoordinates = c.getCoordinates();
				}

				if (qsoCoordinates != null || qso.getCountry() != null) {

					// if resolced coordinates are null use country coordinates
					if (qsoCoordinates == null) {
						qsoCoordinates = qso.getCountry().getCoordinates();
					}

					Folder qsoFolder = null;
					Folder fPaths = null;
					Folder fBand = null;
					Folder fOperator = null;
					// ORG
					// 0 none
					// 1 by band
					// 2 by band and operator
					// 3 by operator
					// 4 by operator and band

					switch (cfg.getQsoOrganizationType()) {
					case 1:// 1 by band
						fBand = bandFolder.get(qso.getBand());
						fPaths = pathFolder.get(qso.getBand());

						if (fBand == null) {
							fBand = root.createAndAddFolder().withName(qso.getBand());
							log.info("Creating folder:" + qso.getBand());
							bandFolder.put(qso.getBand(), fBand);
						}

						if (fPaths == null) {
							fPaths = fBand.createAndAddFolder().withName("Path");
							log.info("Creating paths:" + qso.getBand() + qso.getOperator());
							pathFolder.put(qso.getBand(), fPaths);
						}
						qsoFolder = fBand;
						break;

					case 2:
						fBand = bandFolder.get(qso.getBand());
						fOperator = operatorFolder.get(qso.getBand() + qso.getOperator());

						fPaths = pathFolder.get(qso.getBand() + qso.getOperator());

						if (fBand == null) {
							fBand = root.createAndAddFolder().withName(qso.getBand());
							log.info("Creating folder:" + qso.getBand());
							bandFolder.put(qso.getBand(), fBand);
						}
						if (fOperator == null) {
							fOperator = fBand.createAndAddFolder().withName(qso.getOperator());
							log.info("Creating operator: " + qso.getBand() + qso.getOperator());
							operatorFolder.put(qso.getBand() + qso.getOperator(), fOperator);
						}

						if (fPaths == null) {
							fPaths = fOperator.createAndAddFolder().withName("Path");
							log.info("Creating paths: " + qso.getBand() + qso.getOperator());
							pathFolder.put(qso.getBand() + qso.getOperator(), fPaths);
						}
						qsoFolder = fOperator;
						break;
					case 3:
						fOperator = operatorFolder.get(qso.getOperator());
						fPaths = pathFolder.get(qso.getOperator());

						if (fOperator == null) {
							fOperator = root.createAndAddFolder().withName(qso.getOperator());
							log.info("Creating operator folder for: " + qso.getOperator());
							operatorFolder.put(qso.getOperator(), fOperator);
						}

						if (fPaths == null) {
							fPaths = fOperator.createAndAddFolder().withName("Path");
							log.info("Creating path folder for:" + qso.getOperator());
							pathFolder.put(qso.getOperator(), fPaths);
						}
						qsoFolder = fOperator;
						break;
					case 4:

						fOperator = operatorFolder.get(qso.getOperator());
						fBand = bandFolder.get(qso.getOperator() + qso.getBand());
						fPaths = pathFolder.get(qso.getOperator() + qso.getBand());

						if (fOperator == null) {
							fOperator = root.createAndAddFolder().withName(qso.getOperator());
							log.info("Creating operator: " + qso.getBand() + qso.getOperator());
							operatorFolder.put(qso.getOperator(), fOperator);
						}

						if (fBand == null) {
							fBand = fOperator.createAndAddFolder().withName(qso.getBand());
							log.info("Creating folder for band: " + qso.getBand());
							bandFolder.put(qso.getOperator() + qso.getBand(), fBand);
						}

						if (fPaths == null) {
							fPaths = fBand.createAndAddFolder().withName("Path");
							log.info("Creating path :  " + qso.getBand() + qso.getOperator());
							pathFolder.put(qso.getOperator() + qso.getBand(), fPaths);
						}
						qsoFolder = fBand;
						break;

					case 5:
					case 6:
					case 7:

					default:
						qsoFolder = root;
						fPaths = pathFolder.get("root");
						if (fPaths == null) {
							fPaths = qsoFolder.createAndAddFolder().withName("Paths");
							pathFolder.put("root", fPaths);
						}
						break;

					}

					Placemark qsoLocation = qsoFolder.createAndAddPlacemark();
					qsoLocation.withName(qso.getCall()).createAndSetPoint().setCoordinates(Arrays.asList((new Coordinate[] { qsoCoordinates })));

					if (cfg.getTimeType() == 0) {
						qsoLocation.createAndSetTimeSpan().withBegin(CRHOME_FORMAT.format(qso.getDate()) + "Z");
					} else {
						qsoLocation.createAndSetTimeSpan().withBegin(CRHOME_FORMAT.format(qso.getDate()) + "Z").withEnd(CRHOME_FORMAT.format(qso.getDate()) + "Z");
					}

					Style style = qsoLocation.createAndAddStyle();
					style.createAndSetIconStyle().withIcon(bandIcon).withScale(1);

					style.createAndSetBalloonStyle().withText(qso.getQsoAsHTML());

					if (cfg.getPathType() == 0) {
						Placemark linePlace = fPaths.createAndAddPlacemark().withName(qso.getCall() + "_path");
						LineString line = linePlace.createAndSetLineString();
						line.withCoordinates(new ArrayList<Coordinate>()).getCoordinates().add(myCoordiantes);
						line.getCoordinates().add(qsoCoordinates);
						line.withAltitudeMode(AltitudeMode.CLAMP_TO_GROUND);
						line.withTessellate(true);
						linePlace.createAndAddStyle().createAndSetLineStyle().withColor("#ff" + (new StringBuffer(lineColor).reverse())).withWidth(1);
						// linePlace.createAndSetTimeStamp().setWhen(CRHOME_FORMAT.format(qso.getDate()));
						if (cfg.getTimeType() == 0) {
							linePlace.createAndSetTimeSpan().withBegin(CRHOME_FORMAT.format(qso.getDate()) + "Z");
						} else {
							linePlace.createAndSetTimeSpan().withBegin(CRHOME_FORMAT.format(qso.getDate()) + "Z").withEnd(CRHOME_FORMAT.format(qso.getDate()) + "Z");
						}
					} else {
						fPaths = null;
					}
					String message = "Call " + qso.getCall() + " has coordinates  (" + qsoCoordinates.getLatitude() + "," + qsoCoordinates.getLongitude() + ")";
					cfg.setStatusText(message);
					log.info("Current record #" + (i++));

					current++;

					cfg.setProgress(new Double((100d / total) * current).intValue());
				}
			}
		}
		return kml;
	}
}
