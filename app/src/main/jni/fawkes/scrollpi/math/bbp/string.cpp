#include "string.hpp"

#include "double_type.hpp"

#include <iomanip>
#include <sstream>

namespace fawkes {
    namespace scrollpi {
        namespace math {
            namespace bbp {
                namespace string {
                    std::string calculate(const int digits) {
                        return stringify(double_type::calculate(digits), digits);
                    }

                    std::string calculate_from(const int n, const int digits) {
                        return stringify(double_type::calculate_from(n, digits), digits);
                    }

                    std::string calculate_digits_from(const int n, const int digits) {
                        return stringify(double_type::calculate_digits_from(n, digits), digits);
                    }

                    std::string stringify(const double d, const int digits) {
                        std::stringstream ss;
                        ss << std::setprecision(digits) << d;
                        return ss.str();
                    }
                }
            }
        }
    }
}
