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

#include "string.hpp"

#include "algorithm.hpp"

namespace fawkes {
    namespace scrollpi {
        namespace math {
            namespace bbp {
                namespace string {
                    using precision::integer;
                    using precision::rational;

                    template<typename container_type>
                    void erase_decimal_point(container_type& container) {
                        typedef typename container_type::iterator iterator;
                        const iterator decimal_point(container.begin() + 1);
                        if (decimal_point < container.end()) {
                            container.erase(decimal_point);
                        }
                    }

                    template<typename container_type>
                    void insert_decimal_point(container_type& container) {
                        typedef typename container_type::iterator iterator;
                        const iterator decimal_point(container.begin() + 1);
                        if (decimal_point < container.end()) {
                            container.insert(decimal_point, '.');
                        }
                    }

                    template<typename container_type>
                    typename container_type::size_type safe_size(
                            const typename container_type::iterator& begin,
                            const typename container_type::iterator& end,
                            const int index,
                            const int digits) {
                        typedef typename container_type::size_type size_type;
                        const size_type target(index + digits);
                        return std::min(target, size_type(end - begin));
                    }

                    template<typename container_type>
                    typename container_type::iterator safe_end(
                            const typename container_type::iterator& begin,
                            const typename container_type::iterator& end,
                            const int index,
                            const int digits) {
                        return begin + safe_size<container_type>(begin, end, index, digits);
                    }

                    template<typename container_type>
                    typename container_type::iterator safe_begin(
                            const typename container_type::iterator& begin,
                            const typename container_type::iterator& end,
                            const int index) {
                        typedef typename container_type::iterator iterator;
                        const iterator safe(safe_end<container_type>(begin, end, index, 0));
                        return std::max(begin, safe);
                    }

                    template<typename container_type>
                    void truncate(container_type& container, const int index, const int digits) {
                        typedef typename container_type::iterator iterator;

                        const iterator begin(container.begin());
                        const iterator end(container.end());
                        const iterator last(safe_end<container_type>(begin, end, index, digits));
                        if (last < end) {
                            container.erase(last, end);
                        }
                    }

                    template<typename container_type>
                    void truncate(container_type& container, const int digits) {
                        truncate(container, 0, digits);
                    }

                    template<typename container_type>
                    void truncate_front(container_type& container, const int index) {
                        typedef typename container_type::iterator iterator;
                        typedef typename container_type::size_type size_type;

                        const iterator begin(container.begin());
                        const iterator end(container.end());
                        const iterator first(safe_begin<container_type>(begin, end, index));
                        if (first < container.end()) {
                            container.erase(container.begin(), first);
                        }
                    }



                    static std::string stringify(const rational& value);

                    integer mod(const rational& left, const rational& right) {
                        // duplicated elsewhere
                        using boost::multiprecision::numerator;
                        using boost::multiprecision::denominator;

                        const integer left_integer(numerator(left) / denominator(left));
                        const integer right_integer(numerator(right) / denominator(right));
                        return left_integer - ((left_integer / right_integer) * right_integer);
                    }

                    std::string calculate(const int digits) {
//                        std::string pi(calculate_from(0, digits));
//                        return pi;
                        return stringify(algorithm::calculate(digits));
                    }

                    std::string calculate_from(const int index, const int digits) {
//                        if (index == 0) {
//                            std::string pi(calculate_digits_from(index, digits));
//                            insert_decimal_point(pi);
//                            return pi;
//                        }
//
//                        std::stringstream stream;
//                        for (int i(0); i < index; ++i) {
//                            stream << '0';
//                        }
//                        stream << calculate_digits_from(index, digits);
//
//                        std::string pi(stream.str());
//                        insert_decimal_point(pi);
//                        return pi;
                        return stringify(algorithm::calculate_from(index, digits));
                    }

                    std::string calculate_digits_from(const int index, const int digits) {
//                        using precision::pow;
//
//                        const rational shift(pow(rational(10), index + digits - 1));
//                        const rational shifted(shift * algorithm::pi(digits));
//                        const rational mask(pow(rational(10), index));
//                        const integer modded(mod(shifted, mask));
//
//                        std::stringstream stream;
//                        stream << std::setprecision(128) << modded;
//                        return stream.str();


//                        std::string pi(stringify(algorithm::pi(digits)));
//                        erase_decimal_point(pi);
//                        truncate(pi, index, digits);
//                        truncate_front(pi, index);
//                        return pi;
//                        return stringify(double_type::calculate_digits_from(index, digits), digits);
                        return stringify(algorithm::calculate_digits_from(index, digits));
                    }

                    std::string stringify(const double value, const int digits) {
                        std::stringstream stream;
                        stream << std::setprecision(digits) << value;
                        return stream.str();
                    }

                    std::string stringify(const rational& value) {
//                        const int bit_width(4096);
//                        typedef boost::multiprecision::cpp_dec_float<4096> dec_float;
//                        typedef boost::multiprecision::number<dec_float> number;
//
//                        const number numerator(boost::multiprecision::numerator(value));
//                        const number denominator(boost::multiprecision::denominator(value));
//                        const number evaluated(numerator / denominator);

                        std::stringstream stream;
//                        stream << std::setprecision(bit_width) << evaluated;
                        stream << value;
                        return stream.str();
                    }
                }
            }
        }
    }
}
