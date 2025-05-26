// this file is @generated
package com.svix.api;

import com.svix.SvixHttpClient;
import com.svix.exceptions.ApiException;
import com.svix.models.AggregateEventTypesOut;
import com.svix.models.AppUsageStatsIn;
import com.svix.models.AppUsageStatsOut;

import okhttp3.Headers;
import okhttp3.HttpUrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Statistics {
    private final SvixHttpClient client;

    public Statistics(SvixHttpClient client) {
        this.client = client;
    }

    /**
     * Creates a background task to calculate the message destinations for all applications in the
     * environment.
     *
     * <p>Note that this endpoint is asynchronous. You will need to poll the `Get Background Task`
     * endpoint to retrieve the results of the operation.
     */
    public AppUsageStatsOut aggregateAppStats(final AppUsageStatsIn appUsageStatsIn)
            throws IOException, ApiException {
        return this.aggregateAppStats(appUsageStatsIn, new StatisticsAggregateAppStatsOptions());
    }

    /**
     * Creates a background task to calculate the message destinations for all applications in the
     * environment.
     *
     * <p>Note that this endpoint is asynchronous. You will need to poll the `Get Background Task`
     * endpoint to retrieve the results of the operation.
     */
    public AppUsageStatsOut aggregateAppStats(
            final AppUsageStatsIn appUsageStatsIn, final StatisticsAggregateAppStatsOptions options)
            throws IOException, ApiException {
        HttpUrl.Builder url = this.client.newUrlBuilder().encodedPath("/api/v1/stats/usage/app");
        Map<String, String> headers = new HashMap<>();
        if (options.idempotencyKey != null) {
            headers.put("idempotency-key", options.idempotencyKey);
        }
        return this.client.executeRequest(
                "POST", url.build(), Headers.of(headers), appUsageStatsIn, AppUsageStatsOut.class);
    }

    /**
     * Creates a background task to calculate the listed event types for all apps in the
     * organization.
     *
     * <p>Note that this endpoint is asynchronous. You will need to poll the `Get Background Task`
     * endpoint to retrieve the results of the operation.
     */
    public AggregateEventTypesOut aggregateEventTypes() throws IOException, ApiException {

        HttpUrl.Builder url =
                this.client.newUrlBuilder().encodedPath("/api/v1/stats/usage/event-types");
        return this.client.executeRequest(
                "PUT", url.build(), null, null, AggregateEventTypesOut.class);
    }
}
