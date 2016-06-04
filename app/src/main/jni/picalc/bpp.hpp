#ifndef SCRATCHAPP_PICALC_BPP_HPP
#define SCRATCHAPP_PICALC_BPP_HPP

#include <string>

namespace scratchapp {
    namespace picalc {
        namespace bpp {
            std::string calculate_to(int digits);

            std::string calculate_from(int n, int digits);

            std::string calculate_digits_from(int n, int digits);
        }
    }
}

#endif
