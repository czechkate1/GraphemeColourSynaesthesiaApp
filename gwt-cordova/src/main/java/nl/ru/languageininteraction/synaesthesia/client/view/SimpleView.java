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
package nl.ru.languageininteraction.synaesthesia.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.ScrollPanel;
import nl.ru.languageininteraction.synaesthesia.client.listener.PresenterEventListner;

/**
 * @since Oct 7, 2014 2:06:28 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class SimpleView extends AbstractView {

    public enum ButtonType {

        menu, back, next
    }
    private final HorizontalPanel footerPanel;
    private final HorizontalPanel headerPanel;
    private final HorizontalPanel borderedContentPanel;
    private final ScrollPanel scrollPanel;

    public SimpleView() {
        headerPanel = new HorizontalPanel();
        headerPanel.setStylePrimaryName("headerPanel");
        footerPanel = new HorizontalPanel();
        borderedContentPanel = new HorizontalPanel();
        borderedContentPanel.setStylePrimaryName("contentPanel");
        footerPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        addNorth(headerPanel, 50);
        addSouth(footerPanel, 50);
        scrollPanel = new ScrollPanel();
        borderedContentPanel.add(scrollPanel);
        add(borderedContentPanel);
    }

    public void setContent(Panel panel) {
        scrollPanel.setWidget(panel);
    }

    public void setDisplayText(String text) {
        final HTML html = new HTML(new SafeHtmlBuilder().appendEscapedLines(text).toSafeHtml());
        scrollPanel.setWidget(html);
    }

    public void addTitle(String label, final PresenterEventListner presenterListerner) {
        final Label headerIcon = new Label();
        headerIcon.addStyleName("headerIcon");

        final Label headerLabel = new Label(label);
        headerLabel.setStylePrimaryName("headerLabel");
        if (presenterListerner != null) {
            final Label headerArrow = new Label();
            headerArrow.addStyleName("headerArrow");
            headerPanel.add(headerArrow);

            final Label headerButton = new Label(presenterListerner.getLabel());
            headerButton.addStyleName("headerButton");
            headerPanel.add(headerButton);
            final ClickHandler backHandler = new ClickHandler() {

                @Override
                public void onClick(ClickEvent event) {
                    presenterListerner.eventFired(null);
                }
            };
            headerArrow.addClickHandler(backHandler);
            headerButton.addClickHandler(backHandler);
            headerIcon.addClickHandler(backHandler);
        }
        headerPanel.add(headerIcon);
        headerPanel.add(headerLabel);
    }

    public Button setButton(ButtonType buttonType, final PresenterEventListner presenterListerner) {
        final Button nextButton = new Button(presenterListerner.getLabel());
        nextButton.addStyleName(buttonType.name() + "Button");
        nextButton.setEnabled(true);
        footerPanel.add(nextButton);
        nextButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                presenterListerner.eventFired(nextButton);
            }
        });
        return nextButton;
    }

    public void removeButton(Button button) {
        footerPanel.remove(button);
    }

    @Override
    protected void parentResized(int height, int width, String units) {
        footerPanel.setWidth(width + "px");
        scrollPanel.setWidth(width + "px");
        scrollPanel.setHeight(height - 50 - 50 + "px");
    }
}
