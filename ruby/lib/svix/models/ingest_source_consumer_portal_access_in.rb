# frozen_string_literal: true
# This file is @generated
require "json"

module Svix
  class IngestSourceConsumerPortalAccessIn
    # How long the token will be valid for, in seconds.
    #
    # Valid values are between 1 hour and 7 days. The default is 7 days.
    attr_accessor :expiry
    # Whether the app portal should be in read-only mode.
    attr_accessor :read_only

    ALL_FIELD ||= ["expiry", "read_only"].freeze
    private_constant :ALL_FIELD

    def initialize(attributes = {})
      unless attributes.is_a?(Hash)
        fail(
          ArgumentError,
          "The input argument (attributes) must be a hash in `Svix::IngestSourceConsumerPortalAccessIn` new method"
        )
      end

      attributes.each do |k, v|
        unless ALL_FIELD.include?(k.to_s)
          fail(ArgumentError, "The field #{k} is not part of Svix::IngestSourceConsumerPortalAccessIn")
        end

        instance_variable_set("@#{k}", v)
        instance_variable_set("@__#{k}_is_defined", true)
      end
    end

    def self.deserialize(attributes = {})
      attributes = attributes.transform_keys(&:to_s)
      attrs = Hash.new
      attrs["expiry"] = attributes["expiry"]
      attrs["read_only"] = attributes["readOnly"]
      new(attrs)
    end

    def serialize
      out = Hash.new
      out["expiry"] = Svix::serialize_primitive(@expiry) if @expiry
      out["readOnly"] = Svix::serialize_primitive(@read_only) if @read_only
      out
    end

    # Serializes the object to a json string
    # @return String
    def to_json
      JSON.dump(serialize)
    end
  end
end
