#include "floating.hpp"

#include <cmath>

namespace fawkes {
    namespace scrollpi {
        namespace math {
            namespace floating {
                double trunc(const double d, const int digits) {
                    const int truncated(static_cast<int>(decimal_shift_left(d, digits)));
                    return decimal_shift_right(truncated, digits);
                }

                double trunc_front(const double d, const int digits) {
                    return std::fmod(d, std::pow(10.0, digits));
                }

                double trunc_both(const double d, const int front_digits, const int back_digits) {
                    return trunc(trunc_front(d, front_digits), back_digits);
                }

                double decimal_shift_left(const double d, const int digits) {
                    return d * std::pow(10.0, digits);
                }

                double decimal_shift_right(const double d, const int digits) {
                    return decimal_shift_left(d, -digits);
                }
            }
        }
    }
}
