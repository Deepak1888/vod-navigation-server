package com.globant.vod.controller;

import java.util.List;

import org.elasticsearch.action.index.IndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globant.vod.dao.CategoryDao;
import com.globant.vod.dao.VideoDao;
import com.globant.vod.model.Category;
import com.globant.vod.model.Video;

/**
 * @author rohitkumar.patel
 */
@RestController
@RequestMapping(path = "/video")
public class VideoController {

    @Autowired
    private VideoDao videoDao;

    @Autowired
    private CategoryDao categoryDao;

    @PostMapping(path = "/add")
    public ResponseEntity<List<IndexResponse>> insertVideo(@RequestBody List<Video> videos) throws Exception {
        return new ResponseEntity<List<IndexResponse>>(videoDao.insertVideo(videos), HttpStatus.OK);
    }

    /*
     * @GetMapping(path = "/all") public ResponseEntity<List<Video>> fetchAllVideos() throws
     * Exception { List<Video> videos = videoDao.getAllVideos(); return new
     * ResponseEntity<List<Video>>(videos, HttpStatus.OK); }
     */

    @GetMapping(path = "/{category}")
    public ResponseEntity<List<Video>> fetchVideosOfCategory(@PathVariable String category) throws Exception {
        List<Video> videos = videoDao.fetchVideosOfCategory(category);
        return new ResponseEntity<List<Video>>(videos, HttpStatus.OK);
    }

    @PostMapping(path = "/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) throws Exception {
        return new ResponseEntity<Category>(categoryDao.insertCategory(category), HttpStatus.OK);
    }

    @GetMapping(path = "/categories")
    public ResponseEntity<List<Category>> fetchAllCategories() throws Exception {
        List<Category> categories = categoryDao.getAllCategories();
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }
}
