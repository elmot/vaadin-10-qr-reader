/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.vaadin.elmot.qr;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.function.SerializableConsumer;

/**
 * The main layout contains the header with the navigation buttons, and the
 * child views below that.
 */
@JavaScript("/js/jsQR.js")
@JavaScript("/js/camSupport.js")
@StyleSheet("/css/jsQRCam.css")
public class QRReader extends Div {

    private SerializableConsumer<String> codeConsumer;

    public QRReader() {
        add(new Html("<div id='jsQRCamLoadingMessage'></div>"));
        add(new Html("<canvas id='jsQRCamCanvas' hidden style='width:100%'></canvas>"));
    }

    public QRReader(SerializableConsumer<String> codeConsumer) {
        this();
        this.codeConsumer = codeConsumer;
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        attachEvent.getUI().getPage().executeJavaScript("jsQRCam.init()");
    }

    public void reset() {
        getUI().ifPresent(ui -> ui.getPage().executeJavaScript("jsQRCam.reset()"));
    }

    @ClientCallable
    public void onClientCodeRead(String code) {
        if (codeConsumer != null) {
            codeConsumer.accept(code);
        }
    }

    public void onCode(SerializableConsumer<String> codeConsumer) {
        this.codeConsumer = codeConsumer;
    }
}
