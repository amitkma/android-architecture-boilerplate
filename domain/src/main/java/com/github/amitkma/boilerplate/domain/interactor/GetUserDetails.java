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

package com.github.amitkma.boilerplate.domain.interactor;

import com.github.amitkma.boilerplate.domain.executor.PostExecutionThread;
import com.github.amitkma.boilerplate.domain.executor.ThreadExecutor;
import com.github.amitkma.boilerplate.domain.model.User;
import com.github.amitkma.boilerplate.domain.repository.UserRepository;
import com.google.common.base.Preconditions;

import io.reactivex.Observable;

/**
 * Author: Amit Kumar
 * Created on: 20/9/17.
 *
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link User}.
 */

public class GetUserDetails extends UseCase<User, GetUserDetails.Params> {

    private final UserRepository mUserRepository;

    public GetUserDetails(UserRepository userRepository, ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        mUserRepository = userRepository;
    }


    @Override
    Observable<User> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params);
        return this.mUserRepository.user(params.mUserId);
    }

    public static final class Params {

        private final int mUserId;

        private Params(int userId) {
            this.mUserId = userId;
        }

        public static Params forUser(int userId) {
            return new Params(userId);
        }
    }
}
