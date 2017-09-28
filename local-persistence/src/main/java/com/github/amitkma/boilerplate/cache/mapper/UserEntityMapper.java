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

package com.github.amitkma.boilerplate.cache.mapper;

import com.github.amitkma.boilerplate.cache.model.User;
import com.github.amitkma.boilerplate.data.model.UserEntity;

import javax.inject.Inject;

public class UserEntityMapper implements EntityMapper<User, UserEntity> {

    @Inject
    public UserEntityMapper(){}
    @Override
    public UserEntity mapFromLocalStorage(User user) {
        UserEntity userEntity = null;
        if (user != null) {
            userEntity = new UserEntity();
            userEntity.setUserId(user.id);
            userEntity.setCoverUrl(user.coverUrl);
            userEntity.setDescription(user.description);
            userEntity.setFullName(user.name);
            userEntity.setEmail(user.email);
            userEntity.setFollowers(user.followers);
        }
        return userEntity;
    }

    @Override
    public User mapToCached(UserEntity userEntity) {
        User user = null;
        if (userEntity != null) {
            user = new User();
            user.id = userEntity.getUserId();
            user.coverUrl = userEntity.getCoverUrl();
            user.description = userEntity.getDescription();
            user.followers = userEntity.getFollowers();
            user.name = userEntity.getFullName();
            user.email = userEntity.getEmail();
        }
        return user;
    }
}
