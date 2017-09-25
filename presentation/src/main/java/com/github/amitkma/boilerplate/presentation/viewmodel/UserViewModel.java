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

package com.github.amitkma.boilerplate.presentation.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.github.amitkma.boilerplate.domain.interactor.GetUserList;
import com.github.amitkma.boilerplate.domain.model.User;
import com.github.amitkma.boilerplate.presentation.data.Resource;
import com.github.amitkma.boilerplate.presentation.data.ResourceState;
import com.github.amitkma.boilerplate.presentation.mapper.UserMapper;
import com.github.amitkma.boilerplate.presentation.vo.UserView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.subscribers.DisposableSubscriber;

public class UserViewModel extends ViewModel {

    private final GetUserList mGetUserList;
    private final UserMapper mUserMapper;

    @Inject
    public UserViewModel(GetUserList getUserList,
            UserMapper userMapper) {
        mGetUserList = getUserList;
        mUserMapper = userMapper;
        fetchUsers();
    }

    void fetchUsers(){
        mUserMutableLiveData.postValue(new Resource(ResourceState.LOADING, null, null));
        mGetUserList.execute(new UserSubscriber(), null);
    }

    private LiveData<Resource<List<UserView>>> getUsers(){
        return mUserMutableLiveData;
    }

    @Override
    protected void onCleared() {
        mGetUserList.dispose();
        super.onCleared();
    }

    private final MutableLiveData<Resource<List<UserView>>> mUserMutableLiveData =
            new MutableLiveData<>();

    class UserSubscriber extends DisposableSubscriber<List<User>> {

        @Override
        public void onNext(List<User> users) {
            List<UserView> list = new ArrayList<>();
            for (User user : users) {
                list.add(mUserMapper.mapToView(user));
            }
            mUserMutableLiveData.postValue(new Resource(ResourceState.SUCCESS, list, null));
        }

        @Override
        public void onError(Throwable t) {
            mUserMutableLiveData.postValue(new Resource(ResourceState.ERROR, null, t.getMessage()));
        }

        @Override
        public void onComplete() {

        }
    }
}
