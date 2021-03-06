/*
 * Copyright (c) 2015 Ushahidi Inc
 *
 * This program is free software: you can redistribute it and/or modify it under
 *  the terms of the GNU Affero General Public License as published by the Free
 *  Software Foundation, either version 3 of the License, or (at your option)
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful, but WITHOUT
 *  ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 *  FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program in the file LICENSE-AGPL. If not, see
 *  https://www.gnu.org/licenses/agpl-3.0.html
 */

package com.ushahidi.android.data.repository.datasource;

import com.ushahidi.android.data.entity.mapper.UserAccountEntityDataMapper;
import com.ushahidi.android.data.repository.datasource.useraccount.UserAccountDataSource;
import com.ushahidi.android.data.repository.datasource.useraccount.UserAccountDataSourceFactory;
import com.ushahidi.android.domain.entity.Tag;
import com.ushahidi.android.domain.entity.UserAccount;
import com.ushahidi.android.domain.repository.UserAccountRepository;

import android.support.annotation.NonNull;

import rx.Observable;

/**
 * @author Ushahidi Team <team@ushahidi.com>
 */
public class UserAccountDataRepository implements UserAccountRepository {

    private final UserAccountDataSourceFactory mUserAccountDataSourceFactory;

    private final UserAccountEntityDataMapper mUserAccountEntityDataMapper;

    public UserAccountDataRepository(
            @NonNull UserAccountDataSourceFactory userAccountDataSourceFactory, @NonNull
    UserAccountEntityDataMapper userAccountEntityDataMapper) {
        mUserAccountDataSourceFactory = userAccountDataSourceFactory;
        mUserAccountEntityDataMapper = userAccountEntityDataMapper;
    }

    @Override
    public Observable login(UserAccount userAccount) {
        final UserAccountDataSource userAccountDataSource = mUserAccountDataSourceFactory
                .createApiDataSource();
        return userAccountDataSource
                .loginUserAccountEntity(mUserAccountEntityDataMapper.map(userAccount));
    }

    @Override
    public Observable<Long> putUserAccount(Tag entity) {
        // Do nothing
        return null;
    }

    @Override
    public Observable<Long> deleteUserAccount(Long id) {
        // Do nothing
        return null;
    }
}
