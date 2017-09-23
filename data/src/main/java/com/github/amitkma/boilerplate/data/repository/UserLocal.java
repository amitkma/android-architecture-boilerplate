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

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Interface defining methods for the local persistence of Users. This is to be implemented by the
 * local-persistence layer, using this interface as a way of communication.
 */
public interface UserLocal {

    /**
     * Clear all users from the local storage.
     */
    Completable clearUsers();

    /**
     * Save a given list of {@link UserEntity} to the local storage.
     *
     * @param userEntities list to be saved in local storage
     */
    Completable saveUsers(List<UserEntity> userEntities);

    /**
     * Retrieve a list of {@link UserEntity} from the local storage.
     */
    Observable<List<UserEntity>> getUsers();

    /**
     * Check if a local storage exist in local storage or not.
     * @return true if element is stored, otherwise false.
     */
    boolean isStored();

    /**
     * Set the last stored time of local storage.
     * @param lastStoredTime time in long to store.
     */
    void setLastStoredTime(Long lastStoredTime);

    /**
     * Check if local storage is expired.
     * @return true if the local storage is expired, otherwise false.
     */
    boolean isExpired();
}
