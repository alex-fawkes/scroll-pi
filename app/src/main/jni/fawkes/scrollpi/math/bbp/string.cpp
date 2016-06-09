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

#include <boost/multiprecision/cpp_dec_float.hpp>

namespace fawkes {
    namespace scrollpi {
        namespace math {
            namespace bbp {
                namespace string {
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



                    static std::string stringify(const algorithm::rational& value);

                    std::string calculate(const int digits) {
                        std::string pi(stringify(algorithm::pi(digits)));
                        erase_decimal_point(pi);
                        truncate(pi, digits);
                        insert_decimal_point(pi);
                        return pi;
                    }

                    std::string calculate_from(const int index, const int digits) {
                        std::string pi(stringify(algorithm::pi(digits)));
                        erase_decimal_point(pi);
                        truncate(pi, index, digits);
                        for (int i(0); i < index; ++i) {
                            pi[i] = '0';
                        }
                        insert_decimal_point(pi);
                        return pi;
//                        return stringify(double_type::calculate_from(index, digits), digits);
                    }

                    std::string calculate_digits_from(const int index, const int digits) {
                        std::string pi(stringify(algorithm::pi(digits)));
//                        erase_decimal_point(pi);
//                        truncate(pi, index, digits);
//                        truncate_front(pi, index);
                        return pi;
//                        return stringify(double_type::calculate_digits_from(index, digits), digits);
                    }

                    std::string stringify(const double value, const int digits) {
                        std::stringstream stream;
                        stream << std::setprecision(digits) << value;
                        return stream.str();
                    }

                    std::string stringify(const algorithm::rational& value) {
                        const int bit_width(4096);
                        typedef boost::multiprecision::cpp_dec_float<4096> dec_float;
                        typedef boost::multiprecision::number<dec_float> number;

                        const number numerator(boost::multiprecision::numerator(value));
                        const number denominator(boost::multiprecision::denominator(value));
                        const number evaluated(numerator / denominator);

                        std::stringstream stream;
                        stream << std::setprecision(bit_width) << evaluated;
                        return stream.str();
                    }
                }
            }
        }
    }
}
