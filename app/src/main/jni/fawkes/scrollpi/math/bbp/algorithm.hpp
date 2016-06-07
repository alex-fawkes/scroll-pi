/// @file
/// Bailey–Borwein–Plouffe implementation without spigot property.

#ifndef FAWKES_SCROLLPI_MATH_BBP_ALGORITHM_HPP
#define FAWKES_SCROLLPI_MATH_BBP_ALGORITHM_HPP

namespace fawkes {
    namespace scrollpi {
        namespace math {
            namespace bbp {
                // TODO: copyright and licensing statements
                // TODO: make this actually the spigot form of the algorithm
                namespace algorithm {
                    double pi(const int digits);

                    double addend(const double index);

                    double left(const double index);

                    double right(const double index);

                    double right0(const double index);

                    double right1(const double index);

                    double right2(const double index);

                    double right3(const double index);
                }
            }
        }
    }
}

#endif
