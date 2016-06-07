#ifndef FAWKES_SCROLLPI_MATH_BBP_STRING_HPP
#define FAWKES_SCROLLPI_MATH_BBP_STRING_HPP

#include <string>

namespace fawkes {
    namespace scrollpi {
        namespace math {
            namespace bbp {
                namespace string {
                    std::string calculate(const int digits);

                    std::string calculate_from(const int n, const int digits);

                    std::string calculate_digits_from(const int n, const int digits);

                    std::string stringify(const double d, const int digits);
                }
            }
        }
    }
}

#endif
