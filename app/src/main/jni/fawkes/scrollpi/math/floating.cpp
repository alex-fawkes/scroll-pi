#include "floating.hpp"

#include <cmath>

namespace fawkes {
    namespace scrollpi {
        namespace math {
            namespace floating {
                double trunc(const double d, const int digits) {
                    const double tens(std::pow(10.0, digits - 1));
                    return static_cast<double>(static_cast<int>(d * tens)) / tens;
                }

                // TODO: +1, -1 in (pre)trunc supposed to be?
                // TODO: dox both these
                double trunc_front(const double d, const int digits) {
                    return std::fmod(d, std::pow(10.0, 1 - digits));
                }
            }
        }
    }
}
