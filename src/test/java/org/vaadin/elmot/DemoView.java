package org.vaadin.elmot;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;
import org.vaadin.elmot.qr.QRReader;
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
@StyleSheet("context://css/main.css")
@Route("")
public class DemoView extends Div implements PageConfigurator {
    public DemoView() {
        H2 title = new H2("V10 QR Reader Demo");
        title.addClassName("main-layout__title");

        add(title);

        addClassName("main-layout");
        QRReader qrReader = new QRReader(Notification::show);

        add(qrReader);
    }

    @Override
    public void configurePage(InitialPageSettings settings) {
        settings.addMetaTag("apple-mobile-web-app-capable", "yes");
        settings.addMetaTag("apple-mobile-web-app-status-bar-style", "black");
    }
}
