/*
 * Copyright 2017 Amit Kumar.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.amitkma.boilerplate.cache;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PreferencesHelper {

    private static final String PREF_PACKAGE_NAME = "com.github.amitkma.boilerplate.preferences";

    private static final String PREF_KEY_LAST_STORE = "last_store";

    private SharedPreferences mSharedPreferences;

    @Inject
    public PreferencesHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREF_PACKAGE_NAME, Context.MODE_PRIVATE);
    }

    public long getLastStoredTime() {
        return mSharedPreferences.getLong(PREF_KEY_LAST_STORE, 0);
    }

    public void setLastStoredTime(long lastStored) {
        mSharedPreferences.edit().putLong(PREF_KEY_LAST_STORE, lastStored).apply();
    }


}
