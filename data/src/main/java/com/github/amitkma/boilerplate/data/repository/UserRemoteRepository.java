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

package com.github.amitkma.boilerplate.data.repository;

import com.github.amitkma.boilerplate.data.model.UserEntity;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Interface defining methods for fetching {@link UserEntity} from remote. This is to be implemented
 * by the remote layer, using this interface as a way of communicating.
 */
public interface UserRemoteRepository {

    /**
     * Retrieve a list of {@link UserEntity} from the remote.
     */
    Flowable<List<UserEntity>> getUsers();
}
