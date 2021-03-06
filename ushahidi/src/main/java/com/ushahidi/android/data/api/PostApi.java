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

package com.ushahidi.android.data.api;

import com.ushahidi.android.data.api.model.Posts;
import com.ushahidi.android.data.api.service.PostService;
import com.ushahidi.android.data.entity.PostEntity;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author Ushahidi Team <team@ushahidi.com>
 */
public class PostApi {

    private final PostService mPostService;

    @Inject
    public PostApi(@NonNull PostService postService) {
        mPostService = postService;
    }

    public Observable<List<PostEntity>> getPostList() {
        return Observable.create((subscriber) -> mPostService.posts().map((tags) -> setPost(tags)));
    }

    /**
     * Sets the {@link PostEntity} entity properties from the {@link Posts}
     *
     * @param posts The jsonElement to retrieve the raw JSON string from.
     */
    private Observable<List<PostEntity>> setPost(Posts posts) {
        return Observable.create(subscriber -> {
            subscriber.onNext(posts.getPosts());
            subscriber.onCompleted();
        });
    }
}
