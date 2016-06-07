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

#include "double_type.hpp"

#include "algorithm.hpp"
#include "../floating.hpp"

namespace fawkes {
    namespace scrollpi {
        namespace math {
            namespace bbp {
                namespace double_type {
                    double calculate(const int digits) {
                        return algorithm::pi(digits);
                    }

                    double calculate_from(const int index, const int digits) {
                        return trunc_pi_digits(algorithm::pi(digits), index, digits);
                    }

                    double calculate_digits_from(const int index, const int digits) {
                        return shift_pi_digits(calculate_from(index, digits), index, digits);
                    }

                    double trunc_pi_digits(const double pi, const int index, const int digits) {
                        return floating::trunc_both(pi, 1 - index, index + digits + 1);
                    }

                    double shift_pi_digits(const double pi, const int index, const int digits) {
                        return floating::decimal_shift_left(pi, index + digits - 1);
                    }
                }
            }
        }
    }
}
