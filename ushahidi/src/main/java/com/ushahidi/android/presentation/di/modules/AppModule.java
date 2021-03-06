/*
 * Copyright (c) 2015 Ushahidi.
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program in the file LICENSE-AGPL. If not, see
 * https://www.gnu.org/licenses/agpl-3.0.html
 */

package com.ushahidi.android.presentation.di.modules;

import com.addhen.android.raiburari.data.pref.RxSharedPreferences;
import com.addhen.android.raiburari.presentation.di.qualifier.ActivityScope;
import com.squareup.otto.Bus;
import com.ushahidi.android.data.repository.DeploymentDataRepository;
import com.ushahidi.android.domain.repository.DeploymentRepository;
import com.ushahidi.android.presentation.state.AppState;
import com.ushahidi.android.presentation.state.UserState;

import android.content.Context;
import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;

import static android.content.Context.MODE_PRIVATE;

/**
 * Reusable Dagger modules for the entire app
 *
 * @author Ushahidi Team <team@ushahidi.com>
 */
@Module
public class AppModule {

    @Provides
    @ActivityScope
    SharedPreferences provideSharedPreferences(Context context) {
        return context.getApplicationContext().getSharedPreferences("ushahidi-android-shared-prefs",
                MODE_PRIVATE);
    }

    @Provides
    @ActivityScope
    RxSharedPreferences provideRxSharedPreferences(SharedPreferences sharedPreferences) {
        return new RxSharedPreferences(sharedPreferences);
    }

    @Provides
    @ActivityScope
    DeploymentRepository provideDeploymentRepository(
            DeploymentDataRepository deploymentDataRepository) {
        return deploymentDataRepository;
    }

    @Provides
    @ActivityScope
    public AppState provideApplicationState(Bus bus) {
        return new AppState(bus);
    }

    @Provides
    @ActivityScope
    UserState provideUserState(AppState appState) {
        return appState;
    }
}
