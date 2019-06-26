/* (c) Disney. All rights reserved. */
package com.globant.vod.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author rohitkumar.patel
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class User {

    private String id;
    private String name;
    private String email;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
    private LastVisitedInfo[] lastVisitedVideos;
    private boolean isActive;
    private String refreshToken;

}
