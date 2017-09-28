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

package com.github.amitkma.boilerplate.app.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.github.amitkma.boilerplate.app.R;
import com.github.amitkma.boilerplate.app.mapper.UserMapper;
import com.github.amitkma.boilerplate.app.model.UserModel;
import com.github.amitkma.boilerplate.app.ui.widget.EmptyView;
import com.github.amitkma.boilerplate.app.ui.widget.ErrorView;
import com.github.amitkma.boilerplate.presentation.data.ResourceState;
import com.github.amitkma.boilerplate.presentation.factory.ViewModelFactory;
import com.github.amitkma.boilerplate.presentation.viewmodel.UserViewModel;
import com.github.amitkma.boilerplate.presentation.vo.UserView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class BrowseActivity extends AppCompatActivity implements ErrorView.ErrorListener,
        EmptyView.EmptyListener {

    @Inject
    UserAdapter mUserAdapter;
    @Inject
    UserMapper mUserMapper;
    @Inject
    ViewModelFactory mViewModelFactory;
    private UserViewModel mUserViewModel;

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private ErrorView mErrorView;
    private EmptyView mEmptyView;

    private static final String TAG = "BrowseActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_browse);
        mUserViewModel = ViewModelProviders.of(this, mViewModelFactory)
                .get(UserViewModel.class);

        mRecyclerView = findViewById(R.id.recycler_browse);
        mProgressBar = findViewById(R.id.progress);
        mEmptyView = findViewById(R.id.view_empty);
        mErrorView = findViewById(R.id.view_error);

        setupBrowseRecycler();
    }

    private void setupBrowseRecycler() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mUserAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mUserViewModel.getUsers().observe(this,
                listResource -> {
                    if (listResource != null) {
                        this.handleDataState(listResource.status, listResource.data,
                                listResource.message);
                    }
                });
    }

    private void handleDataState(ResourceState resourceState, List<UserView> data,
            String message) {
        Log.d(TAG,
                "handleDataState() called with: resourceState = [" + resourceState + "], data = ["
                        + data + "], message = [" + message + "]");
        if (resourceState == ResourceState.LOADING) {
            setupScreenForLoadingState();
        } else if (resourceState == ResourceState.SUCCESS) {
            setupScreenForSuccess(data);
        } else {
            setupScreenForError(message);
        }
    }

    private void setupScreenForLoadingState() {
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);
    }

    public void setupScreenForSuccess(List<UserView> data) {
        mErrorView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
        if (data != null && data.size() > 0) {
            List<UserModel> userModels = new ArrayList<>();
            for (UserView userView : data) {
                userModels.add(mUserMapper.mapToView(userView));
            }
            mUserAdapter.setData(userModels);
            mRecyclerView.setVisibility(View.VISIBLE);
        } else {
            mEmptyView.setVisibility(View.VISIBLE);
        }
    }

    public void setupScreenForError(String message) {
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onTryAgainClick() {
        mUserViewModel.fetchUsers();
    }

    @Override
    public void onCheckAgainClick() {
        mUserViewModel.fetchUsers();
    }
}
