/*
 * Copyright (C) 2014 Language In Interaction
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package nl.ru.languageininteraction.synaesthesia.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @since Oct 7, 2014 2:17:51 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ScreenPresenter implements Presenter {

    private final Messages messages = GWT.create(Messages.class);
    private final RootPanel widgetTag;
    final SimpleView simpleView = new SimpleView();

    public ScreenPresenter(RootPanel widgetTag) {
        this.widgetTag = widgetTag;
    }

    public void setState(AppController.ApplicationState state, final AppEventListner appEventListner) {
        widgetTag.clear();
        switch (state) {
            case intro:
                simpleView.clearAll();
                simpleView.setButton("bla", new AppEventListner() {

                    public void eventFired() {
                        appEventListner.eventFired();
                    }

                });
                simpleView.setDisplayText(messages.nl_ru_languageininteraction_synaesthesia_introductionscreentext());
                widgetTag.add(simpleView);
                break;
            case end:
                simpleView.clearAll();
                simpleView.addLink("StyleTestPage", "StyleTestPage.html");
                widgetTag.add(simpleView);
                break;
            case stimulus:
//                widgetTag.add(new ColourPickerTableView());              
                break;
            default:
                simpleView.clearAll();
                simpleView.setButton("foo", new AppEventListner() {

                    public void eventFired() {
                        appEventListner.eventFired();
                    }

                });
                simpleView.setDisplayText(state.name());
                widgetTag.add(simpleView);
                break;
        }
    }
}