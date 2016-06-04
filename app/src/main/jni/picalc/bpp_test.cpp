#include <gtest/gtest.h>

#include "bpp.hpp"

TEST(bpp_test, zero_zero) {
    EXPECT_EQ(0, 0 + 0);
}

TEST(bpp_test, one_one) {
    EXPECT_EQ(2, 1 + 1);
}
