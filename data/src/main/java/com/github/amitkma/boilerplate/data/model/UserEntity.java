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

package com.github.amitkma.boilerplate.data.model;

/**
 * Author: Amit Kumar
 * Created on: 23/9/17.
 *
 * Representation for a {@link UserEntity} fetched from external layer data source
 */

public class UserEntity {

    private int mUserId;
    private String mCoverUrl;
    private String mFullName;
    private String mEmail;
    private String mDescription;
    private int mFollowers;

    public UserEntity() {
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public int getUserId() {
        return mUserId;
    }

    public String getCoverUrl() {
        return mCoverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        mCoverUrl = coverUrl;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        this.mFullName = fullName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public int getFollowers() {
        return mFollowers;
    }

    public void setFollowers(int followers) {
        mFollowers = followers;
    }
}
