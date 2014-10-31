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
package nl.ru.languageininteraction.synaesthesia.client.service;

import nl.ru.languageininteraction.synaesthesia.client.model.UserResults;
import com.google.gwt.storage.client.Storage;
import nl.ru.languageininteraction.synaesthesia.client.model.MetadataField;

/**
 * @since Oct 24, 2014 3:01:35 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class LocalStorage {

    private Storage stockstore = null;
    private static final String USER_RESULTS = "UserResults.";
    final MetadataFieldProvider metadataFieldProvider = new MetadataFieldProvider();

    public UserResults getStoredData() {
        UserResults userResults = new UserResults();
        stockstore = Storage.getLocalStorageIfSupported();
        if (stockstore != null) {
            for (MetadataField metadataField : metadataFieldProvider.metadataFieldArray) {
                userResults.setMetadataValue(metadataField.getPostName(), stockstore.getItem(USER_RESULTS + metadataField.getPostName()));
            }
        }
        return userResults;
    }

    public void storeData(UserResults userResults) {
        stockstore = Storage.getLocalStorageIfSupported();
        if (stockstore != null) {
            for (MetadataField metadataField : metadataFieldProvider.metadataFieldArray) {
                stockstore.setItem(USER_RESULTS + metadataField.getPostName(), userResults.getMetadataValue(metadataField.getPostName()));
            }
        }
    }
}