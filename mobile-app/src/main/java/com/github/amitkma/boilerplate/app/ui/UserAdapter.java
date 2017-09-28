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

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.amitkma.boilerplate.app.R;
import com.github.amitkma.boilerplate.app.model.UserModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<UserModel> mUserModelList = new ArrayList<>();

    @Inject
    UserAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent,
                false);
        return new ViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserModel userModel = mUserModelList.get(position);
        holder.mNameTextView.setText(userModel.name);
        holder.mFollowerTextView.setText(String.format("Followers: %d", userModel.followers));
    }

    @Override
    public int getItemCount() {
        return mUserModelList.size();
    }

    public void setData(List<UserModel> data) {
        mUserModelList = data;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mNameTextView;
        TextView mFollowerTextView;

        ViewHolder(View itemView) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.text_name);
            mFollowerTextView = itemView.findViewById(R.id.text_title);
        }
    }
}
