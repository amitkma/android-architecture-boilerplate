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

package com.github.amitkma.boilerplate.remote.mapper;

import com.github.amitkma.boilerplate.data.model.UserEntity;
import com.github.amitkma.boilerplate.remote.model.UserModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class UserModelEntityMapper implements Mapper<UserModel, UserEntity> {

    @Inject
    UserModelEntityMapper() {
    }

    @Override
    public UserEntity mapFromRemote(UserModel userModel) {
        UserEntity userEntity = null;
        if (userModel != null) {
            userEntity = new UserEntity();
            userEntity.setUserId(userModel.getUserId());
            userEntity.setCoverUrl(userModel.getCoverUrl());
            userEntity.setFullName(userModel.getFullName());
            userEntity.setDescription(userModel.getDescription());
            userEntity.setFollowers(userModel.getFollowers());
            userEntity.setEmail(userModel.getEmail());
        }
        return userEntity;
    }

    @Override
    public List<UserEntity> mapFromRemote(Collection<UserModel> m) {
        List<UserEntity> userEntities = new ArrayList<>();
        for (UserModel userModel : m) {
            if (userModel != null) {
                userEntities.add(mapFromRemote(userModel));
            }
        }
        return userEntities;
    }
}
