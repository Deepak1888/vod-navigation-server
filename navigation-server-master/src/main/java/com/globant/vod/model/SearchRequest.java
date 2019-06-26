/* (c) Disney. All rights reserved. */
package com.globant.vod.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public final class SearchRequest {
    private String query;
    private String language;
    private Paging paging;

    public final static class Paging {
        private Integer offset;
        private Integer size;

        public Integer getOffset() {
            return offset;
        }

        public void setOffset(Integer offset) {
            this.offset = offset;
        }

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        @Override
        public String toString() {
            return "Paging [offset=" + offset + ", size=" + size + "]";
        }

    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    @Override
    public String toString() {
        return "SearchRequest [query=" + query + ", language=" + language + ", paging=" + paging + "]";
    }

}
