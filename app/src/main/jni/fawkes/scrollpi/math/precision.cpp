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

#include "precision.hpp"

namespace fawkes {
    namespace scrollpi {
        namespace math {
            namespace precision {
                rational pow(const rational& base, const int exponent) {
                    return pow(base, rational(exponent));
                }

                rational pow(const rational& base, const integer& exponent) {
                    rational power(1);
                    for (integer i; i < exponent; ++i) {
                        power *= base;
                    }
                    return power;
                }

                rational pow(const rational& base, const rational& exponent) {
                    using boost::multiprecision::numerator;
                    using boost::multiprecision::denominator;
                    return pow(base, integer(numerator(exponent) / denominator(exponent)));
                }







                integer mod(const rational& left, const rational& right) {
                    // duplicated elsewhere
                    using boost::multiprecision::numerator;
                    using boost::multiprecision::denominator;

                    const integer left_integer(numerator(left) / denominator(left));
                    const integer right_integer(numerator(right) / denominator(right));
                    return left_integer - ((left_integer / right_integer) * right_integer);
                }

                rational trunc(const rational& value, const int digits) {
                    const int truncated(static_cast<int>(decimal_shift_left(value, digits)));
                    return decimal_shift_right(truncated, digits);
                }

                rational trunc_front(const rational& value, const int digits) {
                    return mod(value, pow(10, digits));
                }

                rational trunc_both(const rational& value,
                                  const int front_digits, const int back_digits) {
                    return trunc(trunc_front(value, front_digits), back_digits);
                }

                rational decimal_shift_left(const rational& value, const int digits) {
                    return value * pow(10, digits);
                }

                rational decimal_shift_right(const rational& value, const int digits) {
                    return decimal_shift_left(value, -digits);
                }
            }
        }
    }
}
