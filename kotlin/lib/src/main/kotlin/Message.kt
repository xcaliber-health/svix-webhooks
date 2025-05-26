// this file is @generated
package com.svix.kotlin

import com.svix.kotlin.models.ApplicationIn
import com.svix.kotlin.models.ExpungeAllContentsOut
import com.svix.kotlin.models.ListResponseMessageOut
import com.svix.kotlin.models.MessageIn
import com.svix.kotlin.models.MessageOut
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import okhttp3.Headers

data class MessageListOptions(
    /** Limit the number of returned items */
    val limit: ULong? = null,
    /** The iterator returned from a prior invocation */
    val iterator: String? = null,
    /** Filter response based on the channel. */
    val channel: String? = null,
    /** Only include items created before a certain date. */
    val before: Instant? = null,
    /** Only include items created after a certain date. */
    val after: Instant? = null,
    /** When `true` message payloads are included in the response. */
    val withContent: Boolean? = null,
    /** Filter messages matching the provided tag. */
    val tag: String? = null,
    /** Filter response based on the event type */
    val eventTypes: Set<String>? = null,
)

data class MessageCreateOptions(
    /** When `true`, message payloads are included in the response. */
    val withContent: Boolean? = null,
    val idempotencyKey: String? = null,
)

data class MessageExpungeAllContentsOptions(val idempotencyKey: String? = null)

data class MessageGetOptions(
    /** When `true` message payloads are included in the response. */
    val withContent: Boolean? = null
)

class Message(private val client: SvixHttpClient) {
    val poller: MessagePoller = MessagePoller(client)

    /**
     * List all of the application's messages.
     *
     * The `before` and `after` parameters let you filter all items created before or after a
     * certain date. These can be used alongside an iterator to paginate over results within a
     * certain window.
     *
     * Note that by default this endpoint is limited to retrieving 90 days' worth of data relative
     * to now or, if an iterator is provided, 90 days before/after the time indicated by the
     * iterator ID. If you require data beyond those time ranges, you will need to explicitly set
     * the `before` or `after` parameter as appropriate.
     */
    suspend fun list(
        appId: String,
        options: MessageListOptions = MessageListOptions(),
    ): ListResponseMessageOut {
        val url = client.newUrlBuilder().encodedPath("/api/v1/app/$appId/msg")
        options.limit?.let { url.addQueryParameter("limit", serializeQueryParam(it)) }
        options.iterator?.let { url.addQueryParameter("iterator", it) }
        options.channel?.let { url.addQueryParameter("channel", it) }
        options.before?.let { url.addQueryParameter("before", serializeQueryParam(it)) }
        options.after?.let { url.addQueryParameter("after", serializeQueryParam(it)) }
        options.withContent?.let { url.addQueryParameter("with_content", serializeQueryParam(it)) }
        options.tag?.let { url.addQueryParameter("tag", it) }
        options.eventTypes?.let { url.addQueryParameter("event_types", serializeQueryParam(it)) }
        return client.executeRequest<Any, ListResponseMessageOut>("GET", url.build())
    }

    /**
     * Creates a new message and dispatches it to all of the application's endpoints.
     *
     * The `eventId` is an optional custom unique ID. It's verified to be unique only up to a day,
     * after that no verification will be made. If a message with the same `eventId` already exists
     * for the application, a 409 conflict error will be returned.
     *
     * The `eventType` indicates the type and schema of the event. All messages of a certain
     * `eventType` are expected to have the same schema. Endpoints can choose to only listen to
     * specific event types. Messages can also have `channels`, which similar to event types let
     * endpoints filter by them. Unlike event types, messages can have multiple channels, and
     * channels don't imply a specific message content or schema.
     *
     * The `payload` property is the webhook's body (the actual webhook message). Svix supports
     * payload sizes of up to 1MiB, though it's generally a good idea to keep webhook payloads
     * small, probably no larger than 40kb.
     */
    suspend fun create(
        appId: String,
        messageIn: MessageIn,
        options: MessageCreateOptions = MessageCreateOptions(),
    ): MessageOut {
        val url = client.newUrlBuilder().encodedPath("/api/v1/app/$appId/msg")
        options.withContent?.let { url.addQueryParameter("with_content", serializeQueryParam(it)) }
        val headers = Headers.Builder()
        options.idempotencyKey?.let { headers.add("idempotency-key", it) }
        var msgInInternal =
            MessageInInternal(
                messageIn.application,
                messageIn.channels,
                messageIn.eventId,
                messageIn.eventType,
                mapOf(),
                messageIn.payloadRetentionHours,
                messageIn.payloadRetentionPeriod,
                messageIn.tags,
                messageIn.transformationsParams,
            )
        if (msgInInternal.transformationsParams != null) {
            // only set rawPayload if not already set
            if (msgInInternal.transformationsParams!!["rawPayload"] == null) {
                var trParams =
                    (msgInInternal.transformationsParams as Map<String, Any>).toMutableMap()
                trParams["rawPayload"] = messageIn.payload
                msgInInternal.transformationsParams = trParams.toMap()
            }
        } else {
            val trParams = mapOf("rawPayload" to messageIn.payload)
            msgInInternal.transformationsParams = trParams
        }
        return client.executeRequest<MessageInInternal, MessageOut>(
            "POST",
            url.build(),
            headers = headers.build(),
            reqBody = msgInInternal,
        )
    }

    /**
     * Delete all message payloads for the application.
     *
     * This operation is only available in the <a href="https://svix.com/pricing"
     * target="_blank">Enterprise</a> plan.
     */
    suspend fun expungeAllContents(
        appId: String,
        options: MessageExpungeAllContentsOptions = MessageExpungeAllContentsOptions(),
    ): ExpungeAllContentsOut {
        val url = client.newUrlBuilder().encodedPath("/api/v1/app/$appId/msg/expunge-all-contents")
        val headers = Headers.Builder()
        options.idempotencyKey?.let { headers.add("idempotency-key", it) }
        return client.executeRequest<Any, ExpungeAllContentsOut>(
            "POST",
            url.build(),
            headers = headers.build(),
        )
    }

    /** Get a message by its ID or eventID. */
    suspend fun get(
        appId: String,
        msgId: String,
        options: MessageGetOptions = MessageGetOptions(),
    ): MessageOut {
        val url = client.newUrlBuilder().encodedPath("/api/v1/app/$appId/msg/$msgId")
        options.withContent?.let { url.addQueryParameter("with_content", serializeQueryParam(it)) }
        return client.executeRequest<Any, MessageOut>("GET", url.build())
    }

    /**
     * Delete the given message's payload.
     *
     * Useful in cases when a message was accidentally sent with sensitive content. The message
     * can't be replayed or resent once its payload has been deleted or expired.
     */
    suspend fun expungeContent(appId: String, msgId: String) {
        val url = client.newUrlBuilder().encodedPath("/api/v1/app/$appId/msg/$msgId/content")
        client.executeRequest<Any, Boolean>("DELETE", url.build())
    }
}

/**
 * Creates a [MessageIn] with a pre-serialized payload.
 *
 * The payload is not normalized on the server. Normally, payloads are required to be JSON, and Svix
 * will minify the payload before sending the webhooks (for example, by removing extraneous
 * whitespace or unnecessarily escaped characters in strings). With this function, the payload will
 * be sent "as is", without any minification or other processing.
 *
 * @param payload Serialized message payload
 * @param contentType The value to use for the Content-Type header of the webhook sent by Svix,
 *   overwriting the default of `application/json` if specified
 *
 * See the class documentation for details about the other parameters.
 */
fun messageInRaw(
    eventType: String,
    payload: String,
    contentType: String? = null,
    application: ApplicationIn? = null,
    channels: Set<String>? = null,
    eventId: String? = null,
    payloadRetentionHours: Long? = null,
    payloadRetentionPeriod: Long? = 90L,
    tags: Set<String>? = null,
    transformationsParams: Map<String, JsonElement> = mapOf(),
): MessageIn {
    val transformationsParams = transformationsParams.toMutableMap()
    transformationsParams["rawPayload"] = JsonPrimitive(payload)
    if (contentType != null) {
        val headers = mapOf("content-type" to JsonPrimitive(contentType))
        transformationsParams["headers"] = JsonObject(headers)
    }

    return MessageIn(
        eventType = eventType,
        payload = "",
        application = application,
        channels = channels,
        eventId = eventId,
        payloadRetentionHours = payloadRetentionHours,
        payloadRetentionPeriod = payloadRetentionPeriod,
        tags = tags,
        transformationsParams = JsonObject(transformationsParams),
    )
}

@Serializable
private data class MessageInInternal(
    val application: ApplicationIn? = null,
    val channels: Set<String>? = null,
    val eventId: String? = null,
    val eventType: String,
    @Serializable(with = StringAnyMapSerializer::class) var payload: Map<String, Any>,
    val payloadRetentionHours: Long? = null,
    val payloadRetentionPeriod: Long? = null,
    val tags: Set<String>? = null,
    @Serializable(with = StringAnyMapSerializer::class)
    var transformationsParams: Map<String, Any>? = null,
)
