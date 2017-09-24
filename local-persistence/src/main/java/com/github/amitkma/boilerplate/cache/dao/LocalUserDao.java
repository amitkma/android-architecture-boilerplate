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

package com.github.amitkma.boilerplate.cache.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.github.amitkma.boilerplate.cache.Constants;
import com.github.amitkma.boilerplate.cache.model.User;

import java.util.List;

@Dao
public interface LocalUserDao {

    @Query(Constants.DELETE_ALL_USERS)
    void deleteAllUsers();

    @Query(Constants.QUERY_USERS)
    List<User> getUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);
}
