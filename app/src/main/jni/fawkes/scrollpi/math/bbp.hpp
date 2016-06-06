/// @file
/// Bailey–Borwein–Plouffe spigot algorithm:
///
///     pi = sigma(n = 0, inf, addend(n))
///
///     addend(n) = left(n) + right(n)
///
///     left(n) = 1 / 16^n
///
///     right(n) = right0(n) + right1(n) + right2(n) + right3(n)
///
///     right0(n) =  4 / (8n + 1)
///     right1(n) = -2 / (8n + 4)
///     right2(n) = -1 / (8n + 5)
///     right3(n) = -1 / (8n + 6)
///
/// This currently does not support spigot functionality.

#ifndef FAWKES_SCROLLPI_MATH_BBP_HPP
#define FAWKES_SCROLLPI_MATH_BBP_HPP

#include <string>

namespace fawkes {
    namespace scrollpi {
        namespace math {
            namespace bbp {
                std::string calculate_to(const int digits);

                std::string calculate_from(const int n, const int digits);

                std::string calculate_digits_from(const int n, const int digits);
            }
        }
    }
}

#endif
