# this file is @generated
import typing as t

from .common import BaseModel


class EndpointIn(BaseModel):
    channels: t.Optional[t.List[str]] = None
    """List of message channels this endpoint listens to (omit for all)."""

    description: t.Optional[str] = None

    disabled: t.Optional[bool] = None

    filter_types: t.Optional[t.List[str]] = None

    headers: t.Optional[t.Dict[str, str]] = None

    metadata: t.Optional[t.Dict[str, str]] = None

    rate_limit: t.Optional[int] = None

    secret: t.Optional[str] = None
    """The endpoint's verification secret.

    Format: `base64` encoded random bytes optionally prefixed with `whsec_`.
    It is recommended to not set this and let the server generate the secret."""

    uid: t.Optional[str] = None
    """Optional unique identifier for the endpoint."""

    url: str

    version: t.Optional[int] = None
