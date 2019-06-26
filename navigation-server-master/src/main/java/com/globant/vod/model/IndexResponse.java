/* (c) Disney. All rights reserved. */
package com.globant.vod.model;

import org.elasticsearch.indices.cluster.IndicesClusterStateService.Shard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IndexResponse<T> {
    @JsonProperty("_index")
    private String index;
    @JsonProperty("_shards")
    private Shard shards;
    @JsonProperty("_id")
    private String id;
    @JsonProperty("_type")
    private String type;
    @JsonProperty("_version")
    private long version;
    @JsonProperty("_source")
    private T source;
    private boolean forcedRefresh;
    private boolean created;
    protected Result result;

    public enum Result {
        CREATED("created"), UPDATED("updated"), DELETED("deleted"), NOT_FOUND("not_found"), NOOP("noop");

        private String value;

        Result(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return this.value;
        }
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public Shard getShards() {
        return shards;
    }

    public void setShards(Shard shards) {
        this.shards = shards;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public T getSource() {
        return source;
    }

    public void setSource(T source) {
        this.source = source;
    }

    public boolean isForcedRefresh() {
        return forcedRefresh;
    }

    public void setForcedRefresh(boolean forcedRefresh) {
        this.forcedRefresh = forcedRefresh;
    }

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "IndexResponse [index=" + index + ", shards=" + shards + ", id=" + id + ", type=" + type
                + ", version=" + version + ", source=" + source + ", forcedRefresh=" + forcedRefresh + ", created="
                + created + ", result=" + result + "]";
    }

}
