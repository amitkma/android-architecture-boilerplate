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

package com.github.amitkma.boilerplate.app.mapper;

import com.github.amitkma.boilerplate.app.model.UserModel;
import com.github.amitkma.boilerplate.presentation.vo.UserView;

import javax.inject.Inject;

public class UserMapper implements Mapper<UserModel, UserView> {

    @Inject
    UserMapper() {
    }

    @Override
    public UserModel mapToView(UserView userView) {
        UserModel userModel = null;
        if (userView != null) {
            userModel = new UserModel();
            userModel.name = userView.name;
            userModel.followers = userView.followers;
        }
        return userModel;
    }
}
