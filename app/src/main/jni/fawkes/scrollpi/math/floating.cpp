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

#include "floating.hpp"

#include "precision.hpp"

#include <cmath>

namespace fawkes {
    namespace scrollpi {
        namespace math {
            namespace floating {
                double trunc(const double value, const int digits) {
                    const int truncated(static_cast<int>(decimal_shift_left(value, digits)));
                    return decimal_shift_right(truncated, digits);
                }

                double trunc_front(const double value, const int digits) {
                    return std::fmod(value, std::pow(10.0, digits));
                }

                double trunc_both(const double value,
                                  const int front_digits, const int back_digits) {
                    return trunc(trunc_front(value, front_digits), back_digits);
                }

                double decimal_shift_left(const double value, const int digits) {
                    return value * std::pow(10.0, digits);
                }

                double decimal_shift_right(const double value, const int digits) {
                    return decimal_shift_left(value, -digits);
                }
            }
        }
    }
}
