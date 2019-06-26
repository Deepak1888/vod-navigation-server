/* (c) Disney. All rights reserved. */
package com.globant.vod.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author rohitkumar.patel
 */
@JsonInclude(Include.NON_NULL)
public class Video {

    private String id;
    private String title;
    private String description;
    private boolean isActive;
    private String videoUrl;
    private String thumbnailUrl;
    private List<String> tags;
    private List<String> categoryList;
    private Long createdDate;
    private Long lastUpdatedDate;
    @JsonIgnore
    private List<String> moderationLabels;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String color) {
        this.description = color;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<String> categoryList) {
        this.categoryList = categoryList;
    }
    
    public List<String> getModerationLabels() {
        return moderationLabels;
    }

    public void setMSoderationLabels(List<String> labels) {
        this.moderationLabels = labels;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Video [id=" + id + ", title=" + title + ", description=" + description + ", isActive=" + isActive
                + ", videoUrl=" + videoUrl + ", thumbnailUrl=" + thumbnailUrl + ", tags=" + tags
                + ", categoryList=" + categoryList + ", createdDate=" + createdDate + ", lastUpdatedDate="
                + lastUpdatedDate + "]";
    }

}
