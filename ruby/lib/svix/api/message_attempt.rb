# frozen_string_literal: true
# This file is @generated

require "net/http"

module Svix
  class MessageAttempt
    def initialize(client)
      @client = client
    end

    def list_by_endpoint(app_id, endpoint_id, options = {})
      options = options.transform_keys(&:to_s)
      res = @client.execute_request(
        "GET",
        "/api/v1/app/#{app_id}/attempt/endpoint/#{endpoint_id}",
        query_params: {
          "limit" => options["limit"],
          "iterator" => options["iterator"],
          "status" => options["status"],
          "status_code_class" => options["status_code_class"],
          "channel" => options["channel"],
          "tag" => options["tag"],
          "before" => options["before"],
          "after" => options["after"],
          "with_content" => options["with_content"],
          "with_msg" => options["with_msg"],
          "event_types" => options["event_types"]
        }
      )
      ListResponseMessageAttemptOut.deserialize(res)
    end

    def list_by_msg(app_id, msg_id, options = {})
      options = options.transform_keys(&:to_s)
      res = @client.execute_request(
        "GET",
        "/api/v1/app/#{app_id}/attempt/msg/#{msg_id}",
        query_params: {
          "limit" => options["limit"],
          "iterator" => options["iterator"],
          "status" => options["status"],
          "status_code_class" => options["status_code_class"],
          "channel" => options["channel"],
          "tag" => options["tag"],
          "endpoint_id" => options["endpoint_id"],
          "before" => options["before"],
          "after" => options["after"],
          "with_content" => options["with_content"],
          "event_types" => options["event_types"]
        }
      )
      ListResponseMessageAttemptOut.deserialize(res)
    end

    def list_attempted_messages(app_id, endpoint_id, options = {})
      options = options.transform_keys(&:to_s)
      res = @client.execute_request(
        "GET",
        "/api/v1/app/#{app_id}/endpoint/#{endpoint_id}/msg",
        query_params: {
          "limit" => options["limit"],
          "iterator" => options["iterator"],
          "channel" => options["channel"],
          "tag" => options["tag"],
          "status" => options["status"],
          "before" => options["before"],
          "after" => options["after"],
          "with_content" => options["with_content"],
          "event_types" => options["event_types"]
        }
      )
      ListResponseEndpointMessageOut.deserialize(res)
    end

    def get(app_id, msg_id, attempt_id)
      res = @client.execute_request(
        "GET",
        "/api/v1/app/#{app_id}/msg/#{msg_id}/attempt/#{attempt_id}"
      )
      MessageAttemptOut.deserialize(res)
    end

    def expunge_content(app_id, msg_id, attempt_id)
      @client.execute_request(
        "DELETE",
        "/api/v1/app/#{app_id}/msg/#{msg_id}/attempt/#{attempt_id}/content"
      )
    end

    def list_attempted_destinations(app_id, msg_id, options = {})
      options = options.transform_keys(&:to_s)
      res = @client.execute_request(
        "GET",
        "/api/v1/app/#{app_id}/msg/#{msg_id}/endpoint",
        query_params: {
          "limit" => options["limit"],
          "iterator" => options["iterator"]
        }
      )
      ListResponseMessageEndpointOut.deserialize(res)
    end

    def resend(app_id, msg_id, endpoint_id, options = {})
      options = options.transform_keys(&:to_s)
      @client.execute_request(
        "POST",
        "/api/v1/app/#{app_id}/msg/#{msg_id}/endpoint/#{endpoint_id}/resend",
        headers: {
          "idempotency-key" => options["idempotency-key"]
        }
      )
    end

  end
end
