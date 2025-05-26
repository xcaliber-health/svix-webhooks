# frozen_string_literal: true
# This file is @generated
require "json"

module Svix
  class IngestEndpointUpdate
    attr_accessor :description
    attr_accessor :disabled
    attr_accessor :metadata
    attr_accessor :rate_limit
    # Optional unique identifier for the endpoint.
    attr_accessor :uid
    attr_accessor :url

    ALL_FIELD ||= ["description", "disabled", "metadata", "rate_limit", "uid", "url"].freeze
    private_constant :ALL_FIELD

    def initialize(attributes = {})
      unless attributes.is_a?(Hash)
        fail(ArgumentError, "The input argument (attributes) must be a hash in `Svix::IngestEndpointUpdate` new method")
      end

      attributes.each do |k, v|
        unless ALL_FIELD.include?(k.to_s)
          fail(ArgumentError, "The field #{k} is not part of Svix::IngestEndpointUpdate")
        end

        instance_variable_set("@#{k}", v)
        instance_variable_set("@__#{k}_is_defined", true)
      end
    end

    def self.deserialize(attributes = {})
      attributes = attributes.transform_keys(&:to_s)
      attrs = Hash.new
      attrs["description"] = attributes["description"]
      attrs["disabled"] = attributes["disabled"]
      attrs["metadata"] = attributes["metadata"]
      attrs["rate_limit"] = attributes["rateLimit"]
      attrs["uid"] = attributes["uid"]
      attrs["url"] = attributes["url"]
      new(attrs)
    end

    def serialize
      out = Hash.new
      out["description"] = Svix::serialize_primitive(@description) if @description
      out["disabled"] = Svix::serialize_primitive(@disabled) if @disabled
      out["metadata"] = Svix::serialize_primitive(@metadata) if @metadata
      out["rateLimit"] = Svix::serialize_primitive(@rate_limit) if @rate_limit
      out["uid"] = Svix::serialize_primitive(@uid) if @uid
      out["url"] = Svix::serialize_primitive(@url) if @url
      out
    end

    # Serializes the object to a json string
    # @return String
    def to_json
      JSON.dump(serialize)
    end
  end
end
