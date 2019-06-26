package com.globant.vod.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.globant.vod.dao.SearchDao;
import com.globant.vod.model.SearchRequest;
import com.globant.vod.model.Video;

/**
 * @author rohitkumar.patel
 */
@RestController
public class SearchController {

    @Autowired
    private SearchDao searchDao;

    @PostMapping(path = "/search/query")
    public ResponseEntity<List<Video>> search(@RequestBody SearchRequest searchRequest) throws Exception {
        List<Video> videos = searchDao.search(searchRequest);
        return new ResponseEntity<List<Video>>(videos, HttpStatus.OK);
    }
}
