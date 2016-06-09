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

#include <boost/multiprecision/cpp_int.hpp>

namespace fawkes {
    namespace scrollpi {
        namespace math {
            namespace bbp {
                namespace algorithm {
                    double pi(const int digits) {
                        double pi(0.0);
                        for (int i(0); i < digits; ++i) {
                            pi += addend(static_cast<double>(i));
                        }
                        return pi;
                    }

                    double addend(const double index) {
                        return left(index) * right(index);
                    }

                    double left(const double index) {
                        return 1.0 / std::pow(16.0, index);
                    }

                    double right(const double index) {
                        return right0(index) + right1(index) + right2(index) + right3(index);
                    }

                    double right0(const double index) {
                        return 4.0 / (8.0 * index + 1.0);
                    }

                    double right1(const double index) {
                        return -2.0 / (8.0 * index + 4.0);
                    }

                    double right2(const double index) {
                        return -1.0 / (8.0 * index + 5.0);
                    }

                    double right3(const double index) {
                        return -1.0 / (8.0 * index + 6.0);
                    }
                }
            }
        }
    }
}
