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

package com.github.amitkma.boilerplate.remote;

import com.github.amitkma.boilerplate.data.model.UserEntity;
import com.github.amitkma.boilerplate.data.repository.UserRemoteRepository;
import com.github.amitkma.boilerplate.remote.mapper.UserModelEntityMapper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class UserRemoteRepositoryImpl implements UserRemoteRepository {

    private final ApiService mApiService;
    private final UserModelEntityMapper mUserModelEntityMapper;

    @Inject
    public UserRemoteRepositoryImpl(ApiService apiService,
            UserModelEntityMapper userModelEntityMapper) {
        mApiService = apiService;
        mUserModelEntityMapper = userModelEntityMapper;
    }


    @Override
    public Flowable<List<UserEntity>> getUsers() {
        return mApiService.getUsers().map(this.mUserModelEntityMapper::mapFromRemote);
    }
}
