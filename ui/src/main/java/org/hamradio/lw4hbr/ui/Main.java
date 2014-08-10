package org.hamradio.lw4hbr.ui;

import org.hamradio.lw4hbr.ui.controllers.MainController;

/**
 * Created by sebas on 8/10/14.
 */
public class Main {


    public static void main(String[] args) {
        GeoLogMain view = new GeoLogMain();
        new MainController(view);
        view.setVisible(true);
    }
}
