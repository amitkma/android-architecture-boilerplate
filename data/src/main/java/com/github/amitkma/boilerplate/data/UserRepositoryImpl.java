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

package com.github.amitkma.boilerplate.data;

import com.github.amitkma.boilerplate.data.datasource.UserDataStoreFactory;
import com.github.amitkma.boilerplate.data.mapper.UserEntityMapper;
import com.github.amitkma.boilerplate.data.model.UserEntity;
import com.github.amitkma.boilerplate.domain.model.User;
import com.github.amitkma.boilerplate.domain.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * Implementation of {@link UserRepository} interface for communicating to and from data sources
 */
@Singleton
public class UserRepositoryImpl implements UserRepository {

    private final UserDataStoreFactory mUserDataStoreFactory;
    private final UserEntityMapper mUserEntityMapper;

    @Inject
    public UserRepositoryImpl(
            UserDataStoreFactory userDataStoreFactory,
            UserEntityMapper userEntityMapper) {
        mUserDataStoreFactory = userDataStoreFactory;
        mUserEntityMapper = userEntityMapper;
    }

    @Override
    public Completable clearUsers() {
        return mUserDataStoreFactory.createLocalDataStore().clearUsers();
    }

    @Override
    public Completable saveUsers(List<User> users) {
        return mUserDataStoreFactory.createLocalDataStore().saveUsers(
                this.mUserEntityMapper.mapToEntity(users));
    }

    @Override
    public Flowable<List<User>> getUsers() {
        return mUserDataStoreFactory.createLocalDataStore().isStored()
                .flatMapPublisher(aBoolean ->
                        mUserDataStoreFactory.create(aBoolean).getUsers()
                                .flatMap(userEntities -> {
                                    List<User> list = new ArrayList<>();
                                    for (UserEntity userEntity : userEntities) {
                                        list.add(mUserEntityMapper.mapFromEntity(userEntity));
                                    }
                                    return Flowable.just(list)
                                            .flatMap(users -> saveUsers(users).toSingle(
                                                    () -> users).toFlowable());
                                }));
    }
}
