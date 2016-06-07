/// @file
/// Bailey–Borwein–Plouffe implementation without spigot property.

#ifndef FAWKES_SCROLLPI_MATH_BBP_ALGORITHM_HPP
#define FAWKES_SCROLLPI_MATH_BBP_ALGORITHM_HPP

namespace fawkes {
    namespace scrollpi {
        namespace math {
            namespace bbp {
                // TODO: copyright statements
                // TODO: make this actually the spigot form of the algorithm
                namespace algorithm {
                    double pi(const int n);

                    double addend(const double n);

                    double left(const double n);

                    double right(const double n);

                    double right0(const double n);

                    double right1(const double n);

                    double right2(const double n);

                    double right3(const double n);
                }
            }
        }
    }
}

#endif
