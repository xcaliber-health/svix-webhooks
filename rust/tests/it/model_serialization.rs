use std::fmt::Debug;

use serde::de::DeserializeOwned;
use serde_json::json;
use svix::api::{
    CronConfig, IngestSourceIn, IngestSourceInConfig, IngestSourceOut, IngestSourceOutConfig,
    ListResponseApplicationOut, SegmentConfig, SegmentConfigOut, SvixConfig, SvixConfigOut,
};

#[test]
fn test_list_response_xxx_out() {
    // first test with iterator and prevIterator
    let json_str =
        r#"{"data":[],"done":true,"iterator":"iterator-str","prevIterator":"prevIterator-str"}"#;
    let loaded_json: ListResponseApplicationOut = serde_json::from_str(json_str).unwrap();

    let expected_model = ListResponseApplicationOut {
        data: vec![],
        done: true,
        iterator: Some("iterator-str".to_string()),
        prev_iterator: Some("prevIterator-str".to_string()),
    };

    assert_eq!(expected_model, loaded_json);

    // without iterator and prevIterator
    let json_str = r#"{"data":[],"done":true,"iterator":null,"prevIterator":null}"#;
    let loaded_json: ListResponseApplicationOut = serde_json::from_str(json_str).unwrap();

    let expected_model = ListResponseApplicationOut {
        data: vec![],
        done: true,
        ..Default::default()
    };

    assert_eq!(expected_model, loaded_json);
}

#[test]
fn test_ingest_source_in() {
    assert_eq!(
        json!(IngestSourceIn {
            name: "foo".to_owned(),
            uid: None,
            config: IngestSourceInConfig::GenericWebhook,
        }),
        json!({
            "name": "foo",
            "type": "generic-webhook",
        }),
    );

    assert_eq!(
        json!(IngestSourceIn {
            name: "foo".to_owned(),
            uid: None,
            config: IngestSourceInConfig::Svix(SvixConfig {
                secret: "xxx".to_owned()
            }),
        }),
        json!({
            "name": "foo",
            "type": "svix",
            "config": { "secret": "xxx" },
        }),
    );

    assert_eq!(
        json!(IngestSourceIn {
            name: "foo".to_owned(),
            uid: None,
            config: IngestSourceInConfig::Segment(SegmentConfig { secret: None })
        }),
        json!({
            "name": "foo",
            "type": "segment",
            "config": {},
        }),
    );

    assert_eq!(
        json!(IngestSourceIn {
            name: "foo".to_owned(),
            uid: None,
            config: IngestSourceInConfig::Cron(CronConfig {
                content_type: None,
                payload: "💣".to_owned(),
                schedule: "* * * * *".to_owned(),
            })
        }),
        json!({
            "name": "foo",
            "type": "cron",
            "config": {
                "payload": "💣",
                "schedule": "* * * * *",
            },
        }),
    );
}

#[test]
fn test_ingest_source_out() {
    assert_deserializes_to(
        json!({
            "id": "Rjb52OFZK6aYPfF4EpqYqD8Ptcyr",
            "createdAt": "2006-01-02T15:04:05Z",
            "updatedAt": "2006-01-02T15:04:05Z",
            "name": "foo",
            "ingestUrl": "https://in.example.invalid/xyz",
            "type": "generic-webhook",
        }),
        IngestSourceOut {
            created_at: "2006-01-02T15:04:05Z".to_owned(),
            id: "Rjb52OFZK6aYPfF4EpqYqD8Ptcyr".to_owned(),
            ingest_url: Some("https://in.example.invalid/xyz".to_owned()),
            name: "foo".to_owned(),
            uid: None,
            updated_at: "2006-01-02T15:04:05Z".to_owned(),
            config: IngestSourceOutConfig::GenericWebhook,
        },
    );

    assert_deserializes_to(
        json!({
            "id": "Rjb52OFZK6aYPfF4EpqYqD8Ptcyr",
            "createdAt": "2006-01-02T15:04:05Z",
            "updatedAt": "2006-01-02T15:04:05Z",
            "name": "foo",
            "ingestUrl": "https://in.example.invalid/xyz",
            "type": "svix",
            "config": { "secret": "xxx" },
        }),
        IngestSourceOut {
            created_at: "2006-01-02T15:04:05Z".to_owned(),
            id: "Rjb52OFZK6aYPfF4EpqYqD8Ptcyr".to_owned(),
            ingest_url: Some("https://in.example.invalid/xyz".to_owned()),
            name: "foo".to_owned(),
            uid: None,
            updated_at: "2006-01-02T15:04:05Z".to_owned(),
            config: IngestSourceOutConfig::Svix(SvixConfigOut {}),
        },
    );

    assert_deserializes_to(
        json!({
            "id": "Rjb52OFZK6aYPfF4EpqYqD8Ptcyr",
            "createdAt": "2006-01-02T15:04:05Z",
            "updatedAt": "2006-01-02T15:04:05Z",
            "name": "foo",
            "ingestUrl": "https://in.example.invalid/xyz",
            "type": "segment",
            "config": {},
        }),
        IngestSourceOut {
            created_at: "2006-01-02T15:04:05Z".to_owned(),
            id: "Rjb52OFZK6aYPfF4EpqYqD8Ptcyr".to_owned(),
            ingest_url: Some("https://in.example.invalid/xyz".to_owned()),
            name: "foo".to_owned(),
            uid: None,
            updated_at: "2006-01-02T15:04:05Z".to_owned(),
            config: IngestSourceOutConfig::Segment(SegmentConfigOut::default()),
        },
    );

    assert_deserializes_to(
        json!({
            "id": "Rjb52OFZK6aYPfF4EpqYqD8Ptcyr",
            "createdAt": "2006-01-02T15:04:05Z",
            "updatedAt": "2006-01-02T15:04:05Z",
            "name": "foo",
            "type": "cron",
            "config": {
                "payload": "💣",
                "schedule": "* * * * *",
            },
        }),
        IngestSourceOut {
            created_at: "2006-01-02T15:04:05Z".to_owned(),
            id: "Rjb52OFZK6aYPfF4EpqYqD8Ptcyr".to_owned(),
            ingest_url: None,
            name: "foo".to_owned(),
            uid: None,
            updated_at: "2006-01-02T15:04:05Z".to_owned(),
            config: IngestSourceOutConfig::Cron(CronConfig {
                content_type: None,
                payload: "💣".to_owned(),
                schedule: "* * * * *".to_owned(),
            }),
        },
    );
}

fn assert_deserializes_to<T: Debug + PartialEq + DeserializeOwned>(
    value: serde_json::Value,
    expected: T,
) {
    let actual = T::deserialize(value).unwrap();
    assert_eq!(actual, expected);
}
