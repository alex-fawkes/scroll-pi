#include "floating.hpp"

#include <cmath>

namespace fawkes {
    namespace scrollpi {
        namespace math {
            namespace floating {
                double trunc(const double value, const int digits) {
                    const int truncated(static_cast<int>(decimal_shift_left(value, digits)));
                    return decimal_shift_right(truncated, digits);
                }

                double trunc_front(const double value, const int digits) {
                    return std::fmod(value, std::pow(10.0, digits));
                }

                double trunc_both(const double value,
                                  const int front_digits, const int back_digits) {
                    return trunc(trunc_front(value, front_digits), back_digits);
                }

                double decimal_shift_left(const double value, const int digits) {
                    return value * std::pow(10.0, digits);
                }

                double decimal_shift_right(const double value, const int digits) {
                    return decimal_shift_left(value, -digits);
                }
            }
        }
    }
}
