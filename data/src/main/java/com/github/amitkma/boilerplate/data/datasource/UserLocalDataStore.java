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

package com.github.amitkma.boilerplate.data.datasource;

import com.github.amitkma.boilerplate.data.model.UserEntity;
import com.github.amitkma.boilerplate.data.repository.UserLocalRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class UserLocalDataStore implements UserDataStore {

    private final UserLocalRepository mUserLocalRepository;

    @Inject
    UserLocalDataStore(UserLocalRepository userLocalRepository) {
        this.mUserLocalRepository = userLocalRepository;
    }

    @Override
    public Flowable<List<UserEntity>> getUsers() {
        return mUserLocalRepository.getUsers();
    }

    @Override
    public Completable saveUsers(List<UserEntity> userEntityList) {
        return mUserLocalRepository.saveUsers(userEntityList).doOnComplete(
                () -> mUserLocalRepository.setLastStoredTime(System.currentTimeMillis()));
    }

    @Override
    public Completable clearUsers() {
        return mUserLocalRepository.clearUsers();
    }

    @Override
    public Single<Boolean> isStored() {
        return mUserLocalRepository.isStored();
    }
}
