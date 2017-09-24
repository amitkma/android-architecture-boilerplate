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

import com.github.amitkma.boilerplate.cache.db.UserDatabase;
import com.github.amitkma.boilerplate.cache.mapper.UserEntityMapper;
import com.github.amitkma.boilerplate.cache.model.User;
import com.github.amitkma.boilerplate.data.model.UserEntity;
import com.github.amitkma.boilerplate.data.repository.UserLocalRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class UserLocalRepositoryImpl implements UserLocalRepository {

    private static final long EXPIRATION_TIME = (60 * 10 * 1000);

    private final UserDatabase mUserDatabase;
    private final PreferencesHelper mPreferencesHelper;
    private final UserEntityMapper mUserEntityMapper;

    @Inject
    public UserLocalRepositoryImpl(
            UserDatabase userDatabase,
            PreferencesHelper preferencesHelper,
            UserEntityMapper userEntityMapper) {
        mUserDatabase = userDatabase;
        mPreferencesHelper = preferencesHelper;
        mUserEntityMapper = userEntityMapper;
    }


    @Override
    public Completable clearUsers() {
        return Completable.defer(() -> {
            mUserDatabase.localUserDao().deleteAllUsers();
            return Completable.complete();
        });
    }

    @Override
    public Completable saveUsers(List<UserEntity> userEntities) {
        return Completable.defer(() -> {
            for (UserEntity userEntity : userEntities) {
                mUserDatabase.localUserDao().insertUser(mUserEntityMapper.mapToCached(userEntity));
            }
            return Completable.complete();
        });
    }

    @Override
    public Flowable<List<UserEntity>> getUsers() {
        return Flowable.defer(() -> Flowable.just(mUserDatabase.localUserDao().getUsers())
                .map(users -> {
                    List<UserEntity> list = new ArrayList<>();
                    for (User user : users) {
                        list.add(this.mUserEntityMapper.mapFromLocalStorage(user));
                    }
                    return list;
                }));
    }

    @Override
    public boolean isStored() {
        return !mUserDatabase.localUserDao().getUsers().isEmpty();
    }

    @Override
    public void setLastStoredTime(Long lastStoredTime) {
        mPreferencesHelper.setLastStoredTime(lastStoredTime);
    }

    @Override
    public boolean isExpired() {
        long currentTime = System.currentTimeMillis();
        long lastUpdateTime = this.mPreferencesHelper.getLastStoredTime();
        return currentTime - lastUpdateTime > EXPIRATION_TIME;
    }
}
