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

#include "math.hpp"

namespace scrollpi {
    namespace math {
        const std::vector<long double> bin_powers_32(calculate_bin_powers(32L));

        long double pow(const long base, const long exponent) {
            const long double floating_base(base);
            const long double floating_exponent(exponent);
            return std::pow(floating_base, floating_exponent);
        }

        long double mod(const long double numerator, const long double modulus) {
            return numerator - static_cast<long>(numerator / modulus) * modulus;
        }

        long floor(const long value, const long multiple) {
            return value - value % multiple;
        }

        long double fractional(const long double value) {
            return value - static_cast<long>(value);
        }

        long next_bin_exponent_32(const long double value) {
            long exponent(0L);
            while (bin_powers_32[exponent] < value) {
                ++exponent;
            }
            return exponent + 1L;
        }

        std::vector<long double> calculate_bin_powers(const long count) {
            std::vector<long double> powers;
            for (long i(0); i < count; ++i) {
                powers.push_back(math::pow(2, i));
            }
            return powers;
        }

        long double pow_hex_mod(const long double exponent, const long double modulus) {
            if (near_equal(modulus, 1.0L)) {
                return 0.0L;
            }
            const long bin_exponent(next_bin_exponent_32(exponent));
            return pow_hex_mod(1.0L, exponent, modulus, bin_exponent);
        }

        long double pow_hex_mod(long double accumulated,
                                long double remaining,
                                const long double modulus,
                                long double bin_exponent) {
            long double bin_power(std::pow(2.0L, bin_exponent));
            while (bin_exponent >= 0.0L) {
                if (remaining >= bin_power) {
                    accumulated = mod(16.0L * accumulated, modulus);
                    remaining -= bin_power;
                }
                bin_power *= 0.5L;
                if (bin_power >= 1.0L) {
                    accumulated = mod(mod(accumulated * accumulated, modulus), modulus);
                }
                bin_exponent -= 1.0L;
            }
            return accumulated;
        }

        /// Check if two long double values are effectively equal.
        /// @see https://randomascii.wordpress.com/2012/02/25/comparing-floating-point-numbers-2012-edition/
        bool near_equal(const long double left, const long double right) {
            const long double delta(std::abs(left - right));

            if (delta <= max_absolute_delta<long double>()) {
                return true;
            }
            const long double greatest(std::max(std::abs(left), std::abs(right)));
            return delta <= greatest * max_relative_delta<long double>();
        }

        char hex_digit(const long value) {
            static const char* table("0123456789abcdef");
            return table[value % 16L];
        }

        char hex_digit(const long double value) {
            return hex_digit(static_cast<long>(value));
        }

        /// Return the first specified number of hex digits of a given value.
        std::string hex_string(const std::string& preceding,
                               const long double value,
                               const long digits) {
            if (digits == 0L) {
                return preceding;
            }
            const long double magnitude(std::abs(value));
            const long double front(16.0L * (magnitude - std::floor(magnitude)));
            const std::string accumulated(preceding + hex_digit(front));
            return hex_string(accumulated, front, digits - 1L);
        }

        std::string hex_string(const long double value, const long digits) {
            return hex_string(std::string(), value, digits);
        }
    }
}
