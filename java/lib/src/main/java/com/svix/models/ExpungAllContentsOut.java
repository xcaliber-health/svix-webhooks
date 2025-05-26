// This file is @generated
package com.svix.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.svix.Utils;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class ExpungAllContentsOut {
    @JsonProperty private String id;
    @JsonProperty private BackgroundTaskStatus status;
    @JsonProperty private BackgroundTaskType task;

    public ExpungAllContentsOut() {}

    public ExpungAllContentsOut id(String id) {
        this.id = id;
        return this;
    }

    /**
     * The QueueBackgroundTask's ID.
     *
     * @return id
     */
    @javax.annotation.Nonnull
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ExpungAllContentsOut status(BackgroundTaskStatus status) {
        this.status = status;
        return this;
    }

    /**
     * Get status
     *
     * @return status
     */
    @javax.annotation.Nonnull
    public BackgroundTaskStatus getStatus() {
        return status;
    }

    public void setStatus(BackgroundTaskStatus status) {
        this.status = status;
    }

    public ExpungAllContentsOut task(BackgroundTaskType task) {
        this.task = task;
        return this;
    }

    /**
     * Get task
     *
     * @return task
     */
    @javax.annotation.Nonnull
    public BackgroundTaskType getTask() {
        return task;
    }

    public void setTask(BackgroundTaskType task) {
        this.task = task;
    }

    /**
     * Create an instance of ExpungAllContentsOut given an JSON string
     *
     * @param jsonString JSON string
     * @return An instance of ExpungAllContentsOut
     * @throws JsonProcessingException if the JSON string is invalid with respect to
     *     ExpungAllContentsOut
     */
    public static ExpungAllContentsOut fromJson(String jsonString) throws JsonProcessingException {
        return Utils.getObjectMapper().readValue(jsonString, ExpungAllContentsOut.class);
    }

    /**
     * Convert an instance of ExpungAllContentsOut to an JSON string
     *
     * @return JSON string
     */
    public String toJson() throws JsonProcessingException {
        return Utils.getObjectMapper().writeValueAsString(this);
    }
}
