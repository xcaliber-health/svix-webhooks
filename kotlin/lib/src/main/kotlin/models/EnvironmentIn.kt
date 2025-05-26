// This file is @generated
package com.svix.kotlin.models

import com.svix.kotlin.StringAnyMapSerializer
import kotlinx.serialization.Serializable

@Serializable
data class EnvironmentIn(
    val connectors: List<ConnectorIn>? = null,
    val eventTypes: List<EventTypeIn>? = null,
    @Serializable(with = StringAnyMapSerializer::class) val settings: Map<String, Any>? = null,
)
