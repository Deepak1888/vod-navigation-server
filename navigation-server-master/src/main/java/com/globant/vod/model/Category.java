/* (c) Disney. All rights reserved. */
package com.globant.vod.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author rohitkumar.patel
 */
@JsonInclude(Include.NON_NULL)
public class Category {

    private String id;
    private String name;
    @JsonIgnore
    private String description;
	private Long createdDate;
    private Long lastUpdatedDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + ", createdDate=" + createdDate + ", lastUpdatedDate="
                + lastUpdatedDate + "]";
    }

}
