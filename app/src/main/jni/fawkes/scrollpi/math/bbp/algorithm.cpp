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

#include "algorithm.hpp"

namespace fawkes {
    namespace scrollpi {
        namespace math {
            namespace bbp {
                namespace algorithm {
                    using precision::rational;

                    rational pi(const int digits) {
                        const int accuracy_padded_digits(digits + digits * 4 / 3);

                        rational pi(0);
                        for (int i(0); i < accuracy_padded_digits; ++i) {
                            pi += addend(rational(i));
                        }
                        return pi;
                    }

                    rational addend(const rational& index) {
                        return left(index) * right(index);
                    }

                    rational left(const rational& index) {
                        using precision::pow;
                        return rational(1) / pow(rational(16), index);
                    }

                    rational right(const rational& index) {
                        return right0(index) + right1(index) + right2(index) + right3(index);
                    }

                    rational right0(const rational& index) {
                        return rational(4) / (rational(8) * index + rational(1));
                    }

                    rational right1(const rational& index) {
                        return rational(-2) / (rational(8) * index + rational(4));
                    }

                    rational right2(const rational& index) {
                        return rational(-1) / (rational(8) * index + rational(5));
                    }

                    rational right3(const rational& index) {
                        return rational(-1) / (rational(8) * index + rational(6));
                    }










                    rational calculate(const int digits) {
                        return pi(digits);
                    }

                    rational trunc_pi_digits(const rational& pi, const int index, const int digits) {
                        return precision::trunc_both(pi, 1 - index, index + digits + 1);
                    }

                    rational calculate_from(const int index, const int digits) {
                        return trunc_pi_digits(bbp::algorithm::pi(digits), index, digits);
                    }

                    rational shift_pi_digits(const rational& pi, const int index, const int digits) {
                        return precision::decimal_shift_left(pi, index + digits - 1);
                    }

                    rational calculate_digits_from(const int index, const int digits) {
                        return shift_pi_digits(calculate_from(index, digits), index, digits);
                    }
                }
            }
        }
    }
}
