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
import com.github.amitkma.boilerplate.data.repository.UserRemote;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class UserRemoteDataStore implements UserDataStore {

    private final UserRemote mUserRemote;

    @Inject
    UserRemoteDataStore(UserRemote userRemote) {
        mUserRemote = userRemote;
    }

    @Override
    public Observable<List<UserEntity>> getUsers() {
        return mUserRemote.getUsers();
    }

    @Override
    public Completable saveUsers(List<UserEntity> userEntityList) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Completable clearUsers() {
        throw new UnsupportedOperationException();
    }
}
