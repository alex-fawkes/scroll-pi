#ifndef FAWKES_SCROLLPI_MATH_BBP_DOUBLE_TYPE_HPP
#define FAWKES_SCROLLPI_MATH_BBP_DOUBLE_TYPE_HPP

namespace fawkes {
    namespace scrollpi {
        namespace math {
            namespace bbp {
                namespace double_type {
                    double calculate(const int digits);

                    double calculate_from(const int index, const int digits);

                    double calculate_digits_from(const int index, const int digits);

                    double trunc_pi_digits(const double pi, const int index, const int digits);

                    double shift_pi_digits(const double pi, const int index, const int digits);
                }
            }
        }
    }
}

#endif
