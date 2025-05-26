# this file is @generated
import typing as t

from .common import BaseModel


class EndpointEnabledEventData(BaseModel):
    """Sent when an endpoint has been enabled."""

    app_id: str
    """The Application's ID."""

    app_uid: t.Optional[str] = None
    """The Application's UID."""

    endpoint_id: str
    """The Endpoint's ID."""

    endpoint_uid: t.Optional[str] = None
    """The Endpoint's UID."""
