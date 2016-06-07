#ifndef FAWKES_SCROLLPI_MATH_BBP_DOUBLE_TYPE_HPP
#define FAWKES_SCROLLPI_MATH_BBP_DOUBLE_TYPE_HPP

namespace fawkes {
    namespace scrollpi {
        namespace math {
            namespace bbp {
                namespace double_type {
                    double calculate(const int n);

                    double calculate_from(const int n, const int digits);

                    double calculate_digits_from(const int n, const int digits);

                    double trunc_pi_digits(const double pi, const int n, const int digits);

                    double shift_pi_digits(const double pi, const int n, const int digits);
                }
            }
        }
    }
}

#endif
