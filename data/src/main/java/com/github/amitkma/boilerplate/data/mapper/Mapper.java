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

package com.github.amitkma.boilerplate.data.mapper;

import java.util.Collection;
import java.util.List;

/**
 * Author: Amit Kumar
 * Created on: 23/9/17.
 *
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 */

public interface Mapper<E, D> {

    D mapFromEntity(E e);

    E mapToEntity(D d);

    List<D> mapFromEntity(Collection<E> e);

    List<E> mapToEntity(Collection<D> d);
}
