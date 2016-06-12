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

/// @file
/// Implementation of BBP spigot algorithm derived from work by David H. Bailey:
/// @see http://www.experimentalmath.info/bbp-codes/piqpr8.c
///
/// This logic has not been optimized in the slightest and runs very slowly.
/// Benchmarked at 1 million digits in 15 seconds on an Intel i5-3450S.

#include "bbp.hpp"

#include "math.hpp"

#include <sstream>
#include <vector>

namespace scrollpi {
    namespace bbp {
        static void trim_left(std::string& string, const long count);

        static void trim_right(std::string& string, const long count);

        std::string calculate_hex_digits_from(const long index, const long digits) {
            if (index == 0L) {
                std::string hex(calculate_fractional_hex_digits_from(index, digits));
                hex.pop_back();
                return "3." + hex;
            }
            return calculate_fractional_hex_digits_from(index - 1L, digits);
        }

        std::string calculate_fractional_hex_digits_from(const long index, const long digits) {
            std::string hex;
            for (long i(math::floor(index, 8L)); i < index + digits; i += 8L) {
                hex += calculate_fractional_hex_digits_8_from(i);
            }
            trim_left(hex, index % 8L);
            trim_right(hex, hex.size() % digits);
            return hex;
        }

        std::string calculate_fractional_hex_digits_8_from(const long index) {
            const long double s1(series(index, 1L));
            const long double s2(series(index, 4L));
            const long double s3(series(index, 5L));
            const long double s4(series(index, 6L));
            const long double pi(math::fractional(4.0L * s1 - 2.0L * s2 - s3 - s4) + 1.0L);
            return math::hex_string(pi, 8);

        }

        long double series(const long index, const long m) {
            long double accumulated(series_requested(index, m));
            return series_additional(accumulated, index, m);
        }

        long double series_requested(const long index, const long m) {
            long double accumulated(0.0L);
            for (long k(0L); k < index; ++k) {
                accumulated = series_requested(accumulated, index, m, k);
            }
            return accumulated;
        }

        long double series_requested(const long double accumulated,
                                     const long index,
                                     const long m,
                                     const long k) {
            const long double denominator(8L * k + m);
            const long double numerator(math::pow_hex_mod(index - k, denominator));
            return math::fractional(accumulated + numerator / denominator);
        }

        long double series_additional(const long double accumulated,
                                      const long index,
                                      const long m) {
            return series_additional(accumulated, index, m, index);
        }

        long double series_additional(const long double accumulated,
                                      const long index,
                                      const long m,
                                      const long k) {
            static const long double additional(100L);
            if (k > index + additional) {
                return accumulated;
            }
            const long double exponent(index - k);
            const long double term(std::pow(16.0L, exponent) / (8L * k + m));
            if (term < std::numeric_limits<long double>::epsilon()) {
                return accumulated;
            }
            return series_additional(math::fractional(accumulated + term), index, m, k + 1);
        }

        static void trim_left(std::string& string, const long count) {
            if (!string.empty()) {
                if (count > 0) {
                    typedef std::string::iterator iterator_type;
                    const iterator_type first(std::min(string.begin() + count, string.end()));
                    string.erase(string.begin(), first);
                }
            }
        }

        static void trim_right(std::string& string, const long count) {
            if (!string.empty()) {
                if (count > 0) {
                    typedef std::string::iterator iterator_type;
                    const iterator_type last(std::max(string.end() - count, string.begin()));
                    string.erase(last, string.end());
                }
            }
        }
    }
}
