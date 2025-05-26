# frozen_string_literal: true
require "date"

# Constant time string comparison, for fixed length strings.
# Code borrowed from ActiveSupport
# https://github.com/rails/rails/blob/75ac626c4e21129d8296d4206a1960563cc3d4aa/activesupport/lib/active_support/security_utils.rb#L33
#
# The values compared should be of fixed length, such as strings
# that have already been processed by HMAC. Raises in case of length mismatch.
module Svix
  if defined?(OpenSSL.fixed_length_secure_compare)
    def fixed_length_secure_compare(a, b)
      OpenSSL.fixed_length_secure_compare(a, b)
    end
  else
    def fixed_length_secure_compare(a, b)
      raise ArgumentError, "string length mismatch." unless a.bytesize == b.bytesize

      l = a.unpack("C#{a.bytesize}")

      res = 0
      b.each_byte { |byte| res |= byte ^ l.shift }
      res == 0
    end
  end

  module_function :fixed_length_secure_compare

  # Secure string comparison for strings of variable length.
  #
  # While a timing attack would not be able to discern the content of
  # a secret compared via secure_compare, it is possible to determine
  # the secret length. This should be considered when using secure_compare
  # to compare weak, short secrets to user input.
  def secure_compare(a, b)
    a.length == b.length && fixed_length_secure_compare(a, b)
  end

  module_function :secure_compare

  def serialize_primitive(v)
    if v.kind_of?(Time)
      v.utc.to_datetime.rfc3339
    else
      v
    end
  end

  module_function :serialize_primitive

  def deserialize_date(v)
    DateTime.rfc3339(v)
  end

  module_function :deserialize_date

  def serialize_schema_ref(v)
    # Enums are a schema_ref but since we pass them around using the underlying value
    # we need to check if they have the serialize method before calling it
    if v.class.method_defined? :serialize
      v.serialize
    else
      v
    end
  end

  module_function :serialize_schema_ref

end
