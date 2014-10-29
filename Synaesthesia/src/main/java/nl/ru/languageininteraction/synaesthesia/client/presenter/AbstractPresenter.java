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
package nl.ru.languageininteraction.synaesthesia.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import nl.ru.languageininteraction.synaesthesia.client.listener.AppEventListner;
import nl.ru.languageininteraction.synaesthesia.client.Messages;
import nl.ru.languageininteraction.synaesthesia.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.synaesthesia.client.view.SimpleView;

/**
 * @since Oct 28, 2014 3:32:10 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public abstract class AbstractPresenter implements Presenter {

    protected final Messages messages = GWT.create(Messages.class);
    protected final RootPanel widgetTag;
    final SimpleView simpleView = new SimpleView();

    public AbstractPresenter(RootPanel widgetTag) {
        this.widgetTag = widgetTag;
    }

    @Override
    public void setState(final AppEventListner appEventListner, final AppEventListner.ApplicationState prevState, final AppEventListner.ApplicationState nextState) {
        widgetTag.clear();
        if (prevState != null) {
            simpleView.setButton(messages.prevbutton(), new PresenterEventListner() {

                @Override
                public void eventFired() {
                    appEventListner.requestApplicationState(prevState);
                }

            });
        }
        if (nextState != null) {
            simpleView.setButton(messages.nextbutton(), new PresenterEventListner() {

                @Override
                public void eventFired() {
                    appEventListner.requestApplicationState(nextState);
                }

            });
        }
        setTitle();
        setContent();
        simpleView.resizeView();
        widgetTag.add(simpleView);
    }

    abstract void setTitle();

    abstract void setContent();
}