#ifndef FAWKES_SCROLLPI_MATH_FLOATING_HPP
#define FAWKES_SCROLLPI_MATH_FLOATING_HPP

namespace fawkes {
    namespace scrollpi {
        namespace math {
            namespace floating {
                /// Drop digits to the right of the decimal place.
                /// @param digits number of digits to truncate to the right of the
                /// decimal point; zero truncates to integer, negative truncates into
                /// the left of the decimal.
                double trunc(const double d, const int digits);

                /// Drop digits to the left of the decimal place.
                /// @param digits number of digits to truncate to the left of the
                /// decimal point; zero truncates to tenths place, negative truncates
                /// into the right of the decimal.
                double trunc_front(const double d, const int digits);

                /// Equivalent to trunc(trunc_front(d, front_digits), back_digits).
                double trunc_both(const double d, const int front_digits, const int back_digits);

                /// Shift left in base ten.
                double decimal_shift_left(const double d, const int digits);

                /// Shift right in base ten.
                double decimal_shift_right(const double d, const int digits);
            }
        }
    }
}

#endif
