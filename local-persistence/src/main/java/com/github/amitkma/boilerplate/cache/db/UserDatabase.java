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

package com.github.amitkma.boilerplate.cache.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.github.amitkma.boilerplate.cache.dao.LocalUserDao;
import com.github.amitkma.boilerplate.cache.model.User;

import javax.inject.Inject;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    @Inject
    UserDatabase() {
    }

    private static volatile UserDatabase sInstance;

    public abstract LocalUserDao localUserDao();

    public static UserDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (UserDatabase.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            UserDatabase.class, "Sample.db")
                            .build();
                }
            }
        }
        return sInstance;
    }
}
