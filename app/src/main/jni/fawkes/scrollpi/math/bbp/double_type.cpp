#include "double_type.hpp"

#include "algorithm.hpp"
#include "../floating.hpp"

namespace fawkes {
    namespace scrollpi {
        namespace math {
            namespace bbp {
                namespace double_type {
                    double calculate(const int n) {
                        return algorithm::pi(n);
                    }

                    double calculate_from(const int n, const int digits) {
                        return trunc_pi_digits(algorithm::pi(digits), n, digits);
                    }

                    double calculate_digits_from(const int n, const int digits) {
                        return shift_pi_digits(calculate_from(n, digits), n, digits);
                    }

                    double trunc_pi_digits(const double pi, const int n, const int digits) {
                        return floating::trunc_both(pi, 1 - n, n + digits + 1);
                    }

                    double shift_pi_digits(const double pi, const int n, const int digits) {
                        return floating::decimal_shift_left(pi, n + digits - 1);
                    }
                }
            }
        }
    }
}
