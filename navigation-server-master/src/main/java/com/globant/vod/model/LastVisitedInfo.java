/* (c) Disney. All rights reserved. */
package com.globant.vod.model;

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
public class LastVisitedInfo {

    private String lastVisitedVideoId;
    private String lastVisitedVideoTime;
}
