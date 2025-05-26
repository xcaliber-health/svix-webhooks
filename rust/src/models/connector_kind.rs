// this file is @generated
use std::fmt;

use serde::{Deserialize, Serialize};

#[derive(
    Clone, Copy, Debug, Default, Eq, PartialEq, Ord, PartialOrd, Hash, Serialize, Deserialize,
)]
pub enum ConnectorKind {
    #[default]
    #[serde(rename = "Custom")]
    Custom,
    #[serde(rename = "CustomerIO")]
    CustomerIo,
    #[serde(rename = "Discord")]
    Discord,
    #[serde(rename = "Hubspot")]
    Hubspot,
    #[serde(rename = "Inngest")]
    Inngest,
    #[serde(rename = "Salesforce")]
    Salesforce,
    #[serde(rename = "Segment")]
    Segment,
    #[serde(rename = "Slack")]
    Slack,
    #[serde(rename = "Teams")]
    Teams,
    #[serde(rename = "TriggerDev")]
    TriggerDev,
    #[serde(rename = "Windmill")]
    Windmill,
    #[serde(rename = "Zapier")]
    Zapier,
}

impl fmt::Display for ConnectorKind {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        let value = match self {
            Self::Custom => "Custom",
            Self::CustomerIo => "CustomerIO",
            Self::Discord => "Discord",
            Self::Hubspot => "Hubspot",
            Self::Inngest => "Inngest",
            Self::Salesforce => "Salesforce",
            Self::Segment => "Segment",
            Self::Slack => "Slack",
            Self::Teams => "Teams",
            Self::TriggerDev => "TriggerDev",
            Self::Windmill => "Windmill",
            Self::Zapier => "Zapier",
        };
        f.write_str(value)
    }
}
