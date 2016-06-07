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
