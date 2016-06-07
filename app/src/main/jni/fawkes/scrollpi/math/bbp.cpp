#include "bbp.hpp"
#include "floating.hpp"

#include <iomanip>
#include <sstream>

namespace fawkes {
    namespace scrollpi {
        namespace math {
            namespace bbp {
                // TODO: copyright statements
                // TODO: make this actually the spigot form of the algorithm

                static double pi(const int n);

                static double addend(const double n);

                static double left(const double n);

                static double right(const double n);

                static double right0(const double n);

                static double right1(const double n);

                static double right2(const double n);

                static double right3(const double n);

                static std::string string(const double d, const int digits);

                // TODO: header etc.
                static double calculate_double(const int n, const int digits) {
                    return floating::trunc_front(floating::trunc(pi(digits), n + digits), n);
                }

                std::string calculate(const int digits) {
                    return string(pi(digits), digits);
                }

                std::string calculate_from(const int n, const int digits) {
                    return string(calculate_double(n, digits), digits);
                }

                std::string calculate_digits_from(const int n, const int digits) {
                    // TODO: functional pow?
                    return string(calculate_double(n, digits) * std::pow(10.0, n + digits - 1),
                                  digits);
                }

                double pi(const int n) {
                    double pi(0.0);
                    for (int i(0); i < n; ++i) {
                        pi += addend(static_cast<double>(i));
                    }
                    return pi;
                }

                double addend(const double n) {
                    return left(n) * right(n);
                }

                double left(const double n) {
                    return 1.0 / std::pow(16.0, n);
                }

                double right(const double n) {
                    return right0(n) + right1(n) + right2(n) + right3(n);
                }

                double right0(const double n) {
                    return 4.0 / (8.0 * n + 1.0);
                }

                double right1(const double n) {
                    return -2.0 / (8.0 * n + 4.0);
                }

                double right2(const double n) {
                    return -1.0 / (8.0 * n + 5.0);
                }

                double right3(const double n) {
                    return -1.0 / (8.0 * n + 6.0);
                }

                std::string string(const double d, const int digits) {
                    std::stringstream ss;
                    ss << std::setprecision(digits) << d;
                    return ss.str();
                }
            }
        }
    }
}
