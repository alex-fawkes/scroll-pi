// Copyright (c) 2016, Alex Fawkes
//
// Permission to use, copy, modify, and/or distribute this software for any
// purpose with or without fee is hereby granted, provided that the above
// copyright notice and this permission notice appear in all copies.
//
// THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
// WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
// SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
// WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
// ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR
// IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.

#ifndef SCROLLPI_MATH_HPP
#define SCROLLPI_MATH_HPP

#include <algorithm>
#include <cmath>
#include <limits>
#include <string>
#include <vector>

/// Perform fmod 150% faster than std::fmod and 70% faster than inline or template function.
#define SCROLLPI_MATH_FAST_FMOD_LONG(numerator, divisor) \
    (numerator) - static_cast<long>((numerator) / (divisor)) * (divisor)

namespace scrollpi {
    namespace math {
        /// Round down to the nearest multiple of the specified value.
        long floor(const long value, const long multiple);

        long double fractional(const long double value);

        /// Find the exponent for the first binary power greater than the specified value.
        /// Supports values up to 2^95.
        long next_bin_exponent(const long double value);

        /// Calculate 16^n % m using binary exponentiation.
        long double pow_hex_mod(long double exponent, const long double modulus);

        /// Check if two long double values are effectively equal.
        /// @see https://randomascii.wordpress.com/2012/02/25/comparing-floating-point-numbers-2012-edition/
        bool near_equal(const long double left, const long double right);

        char hex_digit(const long value);

        char hex_digit(const long double value);

        /// Return the first specified number of hex digits of a given value.
        std::string hex_string(const long double value, const long digits);

        std::string hex_string(const std::string& preceding,
                               const long double value,
                               const long digits);

        template<typename primitive_type>
        primitive_type max_relative_delta() {
            return std::numeric_limits<primitive_type>::epsilon();
        }

        template<typename floating_point_type>
        floating_point_type max_absolute_delta() {
            typedef std::numeric_limits<floating_point_type> limits;
            return std::pow(limits::epsilon(), static_cast<floating_point_type>(1000));
        }
    }
}

#endif
