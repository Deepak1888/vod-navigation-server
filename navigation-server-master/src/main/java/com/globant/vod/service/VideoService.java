/* (c) Disney. All rights reserved. */
package com.globant.vod.service;

import java.util.Optional;

import com.globant.vod.model.Video;

/**
 * @author rohitkumar.patel
 */
public interface VideoService {
    Video save(Video video);

    void delete(Video video);

    Optional<Video> findOne(String id);

    Iterable<Video> findAll();

    Optional<Video> findByCategoryLeafIds(String[] categoryLeafIds);
}
