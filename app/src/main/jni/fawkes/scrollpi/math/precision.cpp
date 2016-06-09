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
                using namespace boost::multiprecision;

                namespace detail {
                    typedef boost::multiprecision::cpp_int_backend<> cpp_int_backend;
                    typedef boost::multiprecision::cpp_dec_float<8192> cpp_dec_float;
                }

                typedef boost::multiprecision::number<detail::cpp_int_backend> integer;
                typedef boost::multiprecision::number<detail::cpp_dec_float> floating;
                typedef boost::multiprecision::cpp_rational rational;

//                template <typename integer_type, typename rational_type>
//                integer_type numerator(const rational_type& value) {
//                    return boost::multiprecision::numerator(value);
//                }
//
//                template <typename integer_type, typename rational_type>
//                integer_type denominator(const rational_type& value) {
//                    return boost::multiprecision::denominator(value);
//                }

                rational pow(const rational& base, const rational& exponent);
            }
        }
    }
}

#endif
