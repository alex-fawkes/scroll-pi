#ifndef SCRATCHAPP_PICALC_BBP_HPP
#define SCRATCHAPP_PICALC_BBP_HPP

#include <string>

namespace scratchapp {
    namespace picalc {
        namespace bbp {
            std::string calculate_to(int digits);

            std::string calculate_from(int n, int digits);

            std::string calculate_digits_from(int n, int digits);
        }
    }
}

#endif
