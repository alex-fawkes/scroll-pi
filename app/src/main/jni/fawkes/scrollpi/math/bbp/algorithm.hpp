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
/// Bailey–Borwein–Plouffe implementation without spigot property.
// TODO: make this actually the spigot form of the algorithm

#ifndef FAWKES_SCROLLPI_MATH_BBP_ALGORITHM_HPP
#define FAWKES_SCROLLPI_MATH_BBP_ALGORITHM_HPP

#include <boost/multiprecision/cpp_int.hpp>

namespace fawkes {
    namespace scrollpi {
        namespace math {
            namespace bbp {
                namespace algorithm {
                    typedef boost::multiprecision::cpp_rational rational;

                    rational pi(const int digits);

                    rational addend(const rational& index);

                    rational left(const rational& index);

                    rational right(const rational& index);

                    rational right0(const rational& index);

                    rational right1(const rational& index);

                    rational right2(const rational& index);

                    rational right3(const rational& index);
                }
            }
        }
    }
}

#endif
