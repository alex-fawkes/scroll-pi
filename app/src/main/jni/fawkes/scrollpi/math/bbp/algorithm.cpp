#include "algorithm.hpp"

#include <cmath>

namespace fawkes {
    namespace scrollpi {
        namespace math {
            namespace bbp {
                namespace algorithm {
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
                }
            }
        }
    }
}
