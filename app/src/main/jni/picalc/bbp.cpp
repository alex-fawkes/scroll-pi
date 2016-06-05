#include "bbp.hpp"

#include <iomanip>
#include <sstream>

namespace scratchapp {
    namespace picalc {
        //  Bailey–Borwein–Plouffe spigot algorithm:
        //
        //      pi = sigma(n = 0, inf, addend(n))
        //
        //      addend(n) = left(n) + right(n)
        //
        //      left(n) = 1 / 16^n
        //
        //      right(n) = right0(n) + right1(n) + right2(n) + right3(n)
        //
        //      right0(n) =  4 / (8n + 1)
        //      right1(n) = -2 / (8n + 4)
        //      right2(n) = -1 / (8n + 5)
        //      right3(n) = -1 / (8n + 6)
        //
        // TODO: move dox to header, make class
        namespace bbp {
            static double pi(int n);
            static double addend(double n);
            static double left(double n);
            static double right(double n);
            static double right0(double n);
            static double right1(double n);
            static double right2(double n);
            static double right3(double n);
            static std::string string(double d, int digits);

            double trunc(double d, int n) {
                const double tens(std::pow(10.0, n - 1));
                return static_cast<double>(static_cast<int>(d * tens)) / tens;
            }
            // TODO: +1, -1 in (pre)trunc supposed to be?
            double pretrunc(double d, int n) {
                return std::fmod(d, std::pow(10.0, 1 - n));
            }

            // TODO: header etc.
            static double calculate_to_double(int n, int digits) {
                return pretrunc(trunc(pi(digits), n + digits), n);
            }

            std::string calculate_to(int digits) {
                return string(pi(digits), digits);
            }

            std::string calculate_from(int n, int digits) {
                return string(calculate_to_double(n, digits), digits);
            }

            std::string calculate_digits_from(int n, int digits) {
                // TODO: functional pow?
                return string(calculate_to_double(n, digits) * std::pow(10.0, n + digits - 1), digits);
            }

            double pi(int n) {
                double pi(0.0);
                for (int i(0); i < n; ++i) {
                    pi += addend(static_cast<double>(i));
                }
                return pi;
            }

            double addend(double n) {
                return left(n) * right(n);
            }

            double left(double n) {
                return 1.0 / std::pow(16.0, n);
            }

            double right(double n) {
                return right0(n) + right1(n) + right2(n) + right3(n);
            }

            double right0(double n) {
                return 4.0 / (8.0 * n + 1.0);
            }

            double right1(double n) {
                return -2.0 / (8.0 * n + 4.0);
            }

            double right2(double n) {
                return -1.0 / (8.0 * n + 5.0);
            }

            double right3(double n) {
                return -1.0 / (8.0 * n + 6.0);
            }

            std::string string(double d, int digits) {
                std::stringstream ss;
                ss << std::setprecision(digits) << d;
                return ss.str();
            }
        }
    }
}
