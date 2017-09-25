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

package com.github.amitkma.boilerplate.presentation.data;

public class Resource<T> {
    public Resource(ResourceState status, T data, String message){
    }

    Resource<T> error(String message, T data){
        return new Resource<>(ResourceState.ERROR, null, message);
    }
    Resource<T> success(T data){
        return new Resource<>(ResourceState.SUCCESS, data, null);
    }
    Resource<T> loading(){
        return new Resource<>(ResourceState.LOADING, null, null);
    }
}
