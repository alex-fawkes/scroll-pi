// Copyright (c) 2016, Alex Fawkes
//
// Permission to use, copy, modify, and/or distribute this software for any
// purpose with or without fee is hereby granted, provided that the above
// copyright notice and this permission notice appear in all copies.
//
// THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
// WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
// SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
// WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
// ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR
// IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.

#include "math.hpp"

namespace scrollpi {
    namespace math {
        /// binary power lookup table up to 95th power
        static const long double bin_powers[] = {
                1.0L,
                2.0L,
                4.0L,
                8.0L,
                16.0L,
                32.0L,
                64.0L,
                128.0L,
                256.0L,
                512.0L,
                1024.0L,
                2048.0L,
                4096.0L,
                8192.0L,
                16384.0L,
                32768.0L,
                65536.0L,
                131072.0L,
                262144.0L,
                524288.0L,
                1048576.0L,
                2097152.0L,
                4194304.0L,
                8388608.0L,
                16777216.0L,
                33554432.0L,
                67108864.0L,
                134217728.0L,
                268435456.0L,
                536870912.0L,
                1073741824.0L,
                2147483648.0L,
                4294967296.0L,
                8589934592.0L,
                17179869184.0L,
                34359738368.0L,
                68719476736.0L,
                137438953472.0L,
                274877906944.0L,
                549755813888.0L,
                1099511627776.0L,
                2199023255552.0L,
                4398046511104.0L,
                8796093022208.0L,
                17592186044416.0L,
                35184372088832.0L,
                70368744177664.0L,
                140737488355328.0L,
                281474976710656.0L,
                562949953421312.0L,
                1125899906842624.0L,
                2251799813685248.0L,
                4503599627370496.0L,
                9007199254740992.0L,
                18014398509481984.0L,
                36028797018963968.0L,
                72057594037927936.0L,
                144115188075855872.0L,
                288230376151711744.0L,
                576460752303423488.0L,
                1152921504606846976.0L,
                2305843009213693952.0L,
                4611686018427387904.0L,
                9223372036854775808.0L,
                18446744073709551616.0L,
                36893488147419103232.0L,
                73786976294838206464.0L,
                147573952589676412928.0L,
                295147905179352825856.0L,
                590295810358705651712.0L,
                1180591620717411303424.0L,
                2361183241434822606848.0L,
                4722366482869645213696.0L,
                9444732965739290427392.0L,
                18889465931478580854784.0L,
                37778931862957161709568.0L,
                75557863725914323419136.0L,
                151115727451828646838272.0L,
                302231454903657293676544.0L,
                604462909807314587353088.0L,
                1208925819614629174706176.0L,
                2417851639229258349412352.0L,
                4835703278458516698824704.0L,
                9671406556917033397649408.0L,
                19342813113834066795298816.0L,
                38685626227668133590597632.0L,
                77371252455336267181195264.0L,
                154742504910672534362390528.0L,
                309485009821345068724781056.0L,
                618970019642690137449562112.0L,
                1237940039285380274899124224.0L,
                2475880078570760549798248448.0L,
                4951760157141521099596496896.0L,
                9903520314283042199192993792.0L,
                19807040628566084398385987584.0L,
                39614081257132168796771975168.0L
        };

        long floor(const long value, const long multiple) {
            return value - value % multiple;
        }

        long double fractional(const long double value) {
            return value - static_cast<long>(value);
        }

        long next_bin_exponent(const long double value) {
            long exponent(0L);
            while (bin_powers[exponent] < value) {
                ++exponent;
            }
            return exponent + 1L;
        }

        long double pow_hex_mod(long double exponent, const long double modulus) {
            if (near_equal(modulus, 1.0L)) {
                return 0.0L;
            }

            long double accumulated(1.0L);
            long bin_exponent(next_bin_exponent(exponent));
            long double bin_power(bin_powers[bin_exponent]);
            while (bin_exponent >= 0L) {
                if (exponent >= bin_power) {
                    accumulated = SCROLLPI_MATH_FAST_FMOD_LONG(16.0L * accumulated, modulus);

                    // this subtraction is the secondary bottleneck of the bbp algorithm
                    exponent -= bin_power;
                }
                bin_power = bin_powers[--bin_exponent];
                if (bin_power >= 1.0L) {
                    // this fmod is the primary bottleneck of the bbp algorithm
                    accumulated = SCROLLPI_MATH_FAST_FMOD_LONG(accumulated * accumulated, modulus);
                }
            }
            return accumulated;
        }

        /// Check if two long double values are effectively equal.
        /// @see https://randomascii.wordpress.com/2012/02/25/comparing-floating-point-numbers-2012-edition/
        bool near_equal(const long double left, const long double right) {
            const long double delta(std::abs(left - right));

            if (delta <= max_absolute_delta<long double>()) {
                return true;
            }
            const long double greatest(std::max(std::abs(left), std::abs(right)));
            return delta <= greatest * max_relative_delta<long double>();
        }

        char hex_digit(const long value) {
            static const char* table("0123456789abcdef");
            return table[value % 16L];
        }

        char hex_digit(const long double value) {
            return hex_digit(static_cast<long>(value));
        }

        /// Return the first specified number of hex digits of a given value.
        std::string hex_string(const std::string& preceding,
                               const long double value,
                               const long digits) {
            if (digits == 0L) {
                return preceding;
            }
            const long double magnitude(std::abs(value));
            const long double front(16.0L * (magnitude - std::floor(magnitude)));
            const std::string accumulated(preceding + hex_digit(front));
            return hex_string(accumulated, front, digits - 1L);
        }

        std::string hex_string(const long double value, const long digits) {
            return hex_string(std::string(), value, digits);
        }
    }
}
