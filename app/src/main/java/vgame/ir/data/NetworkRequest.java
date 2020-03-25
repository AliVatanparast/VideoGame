/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package vgame.ir.data;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import vgame.ir.app.AppLoader;
import vgame.ir.data.prefrence.UserInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class NetworkRequest<ResultType> {
    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    @MainThread
    public NetworkRequest() {
        result.setValue(Resource.loading(null));
        createCall().enqueue(new Callback<ResultType>() {
            @Override
            public void onResponse(Call<ResultType> call, Response<ResultType> response) {
                if (response.isSuccessful()) {
                    result.setValue(Resource.success(response.body()));
                } else {
                    if (response.code() == 401) {
                        UserInfo.INSTANCE.setToken("");
                       // AppLoader.Companion.getCurrentActivity().startActivity(AuthActivity.newIntent(AppLoader.Companion.getInstance()));
                        AppLoader.Companion.getCurrentActivity().finish();

                    }
                    result.setValue(Resource.error(response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<ResultType> call, Throwable t) {
                onFetchFailed();
                result.setValue(Resource.error(t.getMessage(), null));
            }
        });
    }

    @NonNull
    @MainThread
    protected abstract Call<ResultType> createCall();

    @MainThread
    protected void onFetchFailed() {
    }

    public final LiveData<Resource<ResultType>> getAsLiveData() {
        return result;
    }
}
