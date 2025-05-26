// This file is @generated
package com.svix.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.svix.Utils;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Objects;

@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class BackgroundTaskFinishedEvent {
    @JsonProperty private BackgroundTaskFinishedEvent2 data;
    @JsonProperty private TypeEnum type;

    public BackgroundTaskFinishedEvent() {}

    public BackgroundTaskFinishedEvent data(BackgroundTaskFinishedEvent2 data) {
        this.data = data;
        return this;
    }

    /**
     * Get data
     *
     * @return data
     */
    @javax.annotation.Nonnull
    public BackgroundTaskFinishedEvent2 getData() {
        return data;
    }

    public void setData(BackgroundTaskFinishedEvent2 data) {
        this.data = data;
    }

    public BackgroundTaskFinishedEvent type(TypeEnum type) {
        this.type = type;
        return this;
    }

    /**
     * Get type
     *
     * @return type
     */
    @javax.annotation.Nonnull
    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public enum TypeEnum {
        BACKGROUND_TASK_FINISHED("background_task.finished");
        private final String value;

        TypeEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static TypeEnum fromValue(String value) {
            if (!Objects.equals(value, "background_task.finished")) {
                throw new IllegalArgumentException("Unexpected value '" + value + "'");
            }
            return BACKGROUND_TASK_FINISHED;
        }
    }

    /**
     * Create an instance of BackgroundTaskFinishedEvent given an JSON string
     *
     * @param jsonString JSON string
     * @return An instance of BackgroundTaskFinishedEvent
     * @throws JsonProcessingException if the JSON string is invalid with respect to
     *     BackgroundTaskFinishedEvent
     */
    public static BackgroundTaskFinishedEvent fromJson(String jsonString)
            throws JsonProcessingException {
        return Utils.getObjectMapper().readValue(jsonString, BackgroundTaskFinishedEvent.class);
    }

    /**
     * Convert an instance of BackgroundTaskFinishedEvent to an JSON string
     *
     * @return JSON string
     */
    public String toJson() throws JsonProcessingException {
        return Utils.getObjectMapper().writeValueAsString(this);
    }
}
