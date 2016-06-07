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

                    std::string calculate_from(const int index, const int digits) {
                        return stringify(double_type::calculate_from(index, digits), digits);
                    }

                    std::string calculate_digits_from(const int index, const int digits) {
                        return stringify(double_type::calculate_digits_from(index, digits), digits);
                    }

                    std::string stringify(const double value, const int digits) {
                        std::stringstream stream;
                        stream << std::setprecision(digits) << value;
                        return stream.str();
                    }
                }
            }
        }
    }
}
