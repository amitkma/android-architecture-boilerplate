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

package com.github.amitkma.boilerplate.domain.repository;

import com.github.amitkma.boilerplate.domain.model.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * Author: Amit Kumar
 * Created on: 20/9/17.
 *
 * Interface that represents a Repository for getting {@link User} related data.
 */

public interface UserRepository {

    /**
     * Clear all users from the local storage.
     */
    Completable clearUsers();

    /**
     * Save a given list of {@link User} to the local storage.
     * @param users list to be saved in local storage
     */
    Completable saveUsers(List<User> users);

    /**
     * Retrieve a list of {@link User} from the local storage.
     */
    Flowable<List<User>> getUsers();
}
