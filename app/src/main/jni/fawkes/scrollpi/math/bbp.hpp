/// @file
/// Bailey–Borwein–Plouffe implementation without spigot property.

#ifndef FAWKES_SCROLLPI_MATH_BBP_HPP
#define FAWKES_SCROLLPI_MATH_BBP_HPP

#include <string>

namespace fawkes {
    namespace scrollpi {
        namespace math {
            namespace bbp {
                std::string calculate(const int digits);

                std::string calculate_from(const int n, const int digits);

                std::string calculate_digits_from(const int n, const int digits);
            }
        }
    }
}

#endif
