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

package com.github.amitkma.boilerplate.app.di.module;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;

import com.github.amitkma.boilerplate.app.UiThread;
import com.github.amitkma.boilerplate.cache.BuildConfig;
import com.github.amitkma.boilerplate.cache.PreferencesHelper;
import com.github.amitkma.boilerplate.cache.UserLocalRepositoryImpl;
import com.github.amitkma.boilerplate.cache.db.UserDatabase;
import com.github.amitkma.boilerplate.data.UserRepositoryImpl;
import com.github.amitkma.boilerplate.data.datasource.UserDataStoreFactory;
import com.github.amitkma.boilerplate.data.executor.JobExecutor;
import com.github.amitkma.boilerplate.data.mapper.UserEntityMapper;
import com.github.amitkma.boilerplate.data.repository.UserLocalRepository;
import com.github.amitkma.boilerplate.data.repository.UserRemoteRepository;
import com.github.amitkma.boilerplate.domain.executor.PostExecutionThread;
import com.github.amitkma.boilerplate.domain.executor.ThreadExecutor;
import com.github.amitkma.boilerplate.domain.repository.UserRepository;
import com.github.amitkma.boilerplate.remote.ApiService;
import com.github.amitkma.boilerplate.remote.ApiServiceFactory;
import com.github.amitkma.boilerplate.remote.UserRemoteRepositoryImpl;
import com.github.amitkma.boilerplate.remote.mapper.UserModelEntityMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ViewModelModule.class})
public class ApplicationModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(Context context) {
        return new PreferencesHelper(context);
    }

    @Provides
    @Singleton
    UserRepository provideUserRepository(UserDataStoreFactory userDataStoreFactory,
            UserEntityMapper userEntityMapper) {
        return new UserRepositoryImpl(userDataStoreFactory, userEntityMapper);
    }

    @Provides
    @Singleton
    UserLocalRepository provideUserLocalRepository(UserDatabase userDatabase,
            PreferencesHelper helper,
            com.github.amitkma.boilerplate.cache.mapper.UserEntityMapper userEntityMapper) {
        return new UserLocalRepositoryImpl(userDatabase, helper, userEntityMapper);
    }

    @Provides
    @Singleton
    UserRemoteRepository provideUserRemoteRepository(ApiService apiService,
            UserModelEntityMapper userModelEntityMapper) {
        return new UserRemoteRepositoryImpl(apiService, userModelEntityMapper);
    }

    @Provides
    @Singleton
    ThreadExecutor provideJobExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UiThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    ApiService provideApiService() {
        return new ApiServiceFactory().createApiService(BuildConfig.DEBUG);
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelFactory() {
        return new ViewModelProvider.NewInstanceFactory();
    }

    @Provides
    @Singleton
    UserDatabase providesUserDatabase(Application application) {
        return UserDatabase.getInstance(application);
    }

}
