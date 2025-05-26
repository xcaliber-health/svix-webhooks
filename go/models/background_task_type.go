// Package svix this file is @generated DO NOT EDIT
package models

import (
	"encoding/json"
	"fmt"
	"slices"
)

type BackgroundTaskType string

const (
	BACKGROUNDTASKTYPE_ENDPOINT_REPLAY           BackgroundTaskType = "endpoint.replay"
	BACKGROUNDTASKTYPE_ENDPOINT_RECOVER          BackgroundTaskType = "endpoint.recover"
	BACKGROUNDTASKTYPE_APPLICATION_STATS         BackgroundTaskType = "application.stats"
	BACKGROUNDTASKTYPE_MESSAGE_BROADCAST         BackgroundTaskType = "message.broadcast"
	BACKGROUNDTASKTYPE_SDK_GENERATE              BackgroundTaskType = "sdk.generate"
	BACKGROUNDTASKTYPE_EVENT_TYPE_AGGREGATE      BackgroundTaskType = "event-type.aggregate"
	BACKGROUNDTASKTYPE_APPLICATION_PURGE_CONTENT BackgroundTaskType = "application.purge_content"
)

var allowedBackgroundTaskType = []BackgroundTaskType{
	"endpoint.replay",
	"endpoint.recover",
	"application.stats",
	"message.broadcast",
	"sdk.generate",
	"event-type.aggregate",
	"application.purge_content",
}

func (v *BackgroundTaskType) UnmarshalJSON(src []byte) error {
	var value string
	err := json.Unmarshal(src, &value)
	if err != nil {
		return err
	}
	enumVal := BackgroundTaskType(value)
	if slices.Contains(allowedBackgroundTaskType, enumVal) {
		*v = enumVal
		return nil
	}
	return fmt.Errorf("`%+v` is not a valid BackgroundTaskType", value)

}

var BackgroundTaskTypeFromString = map[string]BackgroundTaskType{
	"endpoint.replay":           BACKGROUNDTASKTYPE_ENDPOINT_REPLAY,
	"endpoint.recover":          BACKGROUNDTASKTYPE_ENDPOINT_RECOVER,
	"application.stats":         BACKGROUNDTASKTYPE_APPLICATION_STATS,
	"message.broadcast":         BACKGROUNDTASKTYPE_MESSAGE_BROADCAST,
	"sdk.generate":              BACKGROUNDTASKTYPE_SDK_GENERATE,
	"event-type.aggregate":      BACKGROUNDTASKTYPE_EVENT_TYPE_AGGREGATE,
	"application.purge_content": BACKGROUNDTASKTYPE_APPLICATION_PURGE_CONTENT,
}
