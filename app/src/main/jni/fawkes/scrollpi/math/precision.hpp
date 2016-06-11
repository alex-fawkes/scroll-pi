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

#ifndef FAWKES_SCROLLPI_MATH_PRECISION_HPP
#define FAWKES_SCROLLPI_MATH_PRECISION_HPP

#include <boost/multiprecision/cpp_dec_float.hpp>
#include <boost/multiprecision/cpp_int.hpp>
#include <boost/multiprecision/number.hpp>

namespace fawkes {
    namespace scrollpi {
        namespace math {
            namespace precision {
                typedef boost::multiprecision::cpp_int integer;
                typedef boost::multiprecision::cpp_rational rational;

                rational pow(const rational& base, const int exponent);
                rational pow(const rational& base, const integer& exponent);
                rational pow(const rational& base, const rational& exponent);




                /// Drop digits to the right of the decimal place.
                /// @param digits number of digits to truncate to the right of the
                /// decimal point; zero truncates to integer, negative truncates into
                /// the left of the decimal.
                rational trunc(const rational& value, const int digits);

                /// Drop digits to the left of the decimal place.
                /// @param digits number of digits to truncate to the left of the
                /// decimal point; zero truncates to tenths place, negative truncates
                /// into the right of the decimal.
                rational trunc_front(const rational& value, const int digits);

                /// Equivalent to trunc(trunc_front(d, front_digits), back_digits).
                rational trunc_both(const rational& value, const int front_digits, const int back_digits);

                /// Shift left in base ten.
                rational decimal_shift_left(const rational& value, const int digits);

                /// Shift right in base ten.
                rational decimal_shift_right(const rational& value, const int digits);
            }
        }
    }
}

#endif
