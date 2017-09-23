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

package com.github.amitkma.boilerplate.data.mapper;

import com.github.amitkma.boilerplate.data.model.UserEntity;
import com.github.amitkma.boilerplate.domain.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link UserEntity} (in the data layer) to {@link User} (in the
 * domain layer).
 */
@Singleton
public class UserEntityMapper implements Mapper<UserEntity, User> {

    @Inject
    public UserEntityMapper() {
    }

    /**
     * Map a {@link UserEntity} into an {@link User}.
     *
     * @param userEntity Object to be mapped.
     * @return {@link User} if valid {@link UserEntity} otherwise null.
     */
    @Override
    public User mapFromEntity(UserEntity userEntity) {
        User user = null;
        if (userEntity != null) {
            user = new User(userEntity.getUserId());
            user.setCoverUrl(userEntity.getCoverUrl());
            user.setFullName(userEntity.getFullName());
            user.setDescription(userEntity.getDescription());
            user.setFollowers(userEntity.getFollowers());
            user.setEmail(userEntity.getEmail());
        }
        return user;
    }

    /**
     * Map a {@link User} into an {@link UserEntity}.
     *
     * @param user Object to be mapped.
     * @return {@link UserEntity} if valid {@link User} otherwise null.
     */
    @Override
    public UserEntity mapToEntity(User user) {
        UserEntity userEntity = null;
        if (user != null) {
            userEntity = new UserEntity();
            userEntity.setUserId(user.getUserId());
            userEntity.setCoverUrl(user.getCoverUrl());
            userEntity.setFullName(user.getFullName());
            userEntity.setDescription(user.getDescription());
            userEntity.setFollowers(user.getFollowers());
            userEntity.setEmail(user.getEmail());
        }
        return userEntity;
    }

    /**
     * Map a list of {@link UserEntity} into another list of {@link User}.
     *
     * @param userEntities Object to be mapped.
     * @return List of {@link User}.
     */
    public List<User> mapFromEntity(Collection<UserEntity> userEntities) {
        final List<User> userList = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            final User user = mapFromEntity(userEntity);
            if (user != null) {
                userList.add(user);
            }
        }
        return userList;
    }

    /**
     * Map a list of {@link User} into another list of {@link UserEntity}.
     *
     * @param users Object to be mapped.
     * @return List of {@link UserEntity}.
     */
    public List<UserEntity> mapToEntity(Collection<User> users) {
        final List<UserEntity> userEntityList = new ArrayList<>();
        for (User user : users) {
            final UserEntity userEntity = mapToEntity(user);
            if (userEntity != null) {
                userEntityList.add(userEntity);
            }
        }
        return userEntityList;
    }
}
