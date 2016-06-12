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

#ifndef SCROLLPI_BBP_HPP
#define SCROLLPI_BBP_HPP

#include <string>

namespace scrollpi {
    namespace bbp {
        /// Zero index starts at ones place.
        std::string calculate_hex_digits_from(const long index, const long digits);

        /// Zero index starts at tenths place.
        std::string calculate_fractional_hex_digits_from(const long index, const long digits);

        // Eight digits form a "batch" as the limit of accuracy during a single round.
        std::string calculate_fractional_hex_digits_8_from(const long index);

        /// Evaluate the series sum(k = 0, infinity, 16^(index - k) / (8 * k + m)
        /// using the modular exponentiation technique.
        long double series(const long index, const long m);

        /// Calculate steps of the series necessary for the requested number of digits.
        long double series_requested(const long index, const long m);

        long double series_requested(const long double accumulated,
                                     const long index,
                                     const long m,
                                     const long k);

        /// Calculate additional steps of the series to ensure accuracy.
        long double series_additional(const long double accumulated,
                                      const long index,
                                      const long m);

        long double series_additional(const long double accumulated,
                                      const long index,
                                      const long m,
                                      const long k);
    }
}

#endif
