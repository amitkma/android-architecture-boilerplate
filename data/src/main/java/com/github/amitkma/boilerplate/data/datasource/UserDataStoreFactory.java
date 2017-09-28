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

import com.github.amitkma.boilerplate.data.repository.UserLocalRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that create an instance of {@link UserDataStore}
 */
@Singleton
public class UserDataStoreFactory {

    private final UserLocalDataStore mUserLocalDataStore;
    private final UserRemoteDataStore mUserRemoteDataStore;
    private final UserLocalRepository mUserLocalRepository;

    @Inject
    UserDataStoreFactory(
            UserLocalRepository userLocalRepository,
            UserLocalDataStore userLocalDataStore,
            UserRemoteDataStore userRemoteDataStore) {
        this.mUserLocalRepository = userLocalRepository;
        this.mUserLocalDataStore = userLocalDataStore;
        this.mUserRemoteDataStore = userRemoteDataStore;
    }


    public UserDataStore create(boolean isStored) {
        if (isStored && !mUserLocalRepository.isExpired()) {
            return createLocalDataStore();
        }
        return createRemoteDataStore();

    }

    /**
     * Returns an instance of Local Data Store.
     */
    public UserDataStore createLocalDataStore() {
        return mUserLocalDataStore;
    }

    /**
     * Return an instance of Remote Data Store.
     */
    private UserDataStore createRemoteDataStore() {
        return mUserRemoteDataStore;
    }
}
