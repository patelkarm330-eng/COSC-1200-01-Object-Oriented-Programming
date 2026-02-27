 /*
  * Name:Karm Patel
  * Program name: TestNumberTools
  * Date:26 February 2026
  * Description: This class contain 4 function to test.
  */

 public class NumberTools {
        // Returns true if the number is even (divisible by 2)
        public static boolean isEven(int value) {
            return value % 2 == 0;
        }

        // Returns true if the number is positive (greater than 0)
        public static boolean isPositive(int value) {
            return value > 0;
        }

        // Returns true if the number is a power of two (2, 4, 8, 16)
        public static boolean isPowerOfTwo(int value) {
            if (value <= 0) {
                return false;
            }
            // Bitwise trick: only powers of two have exactly one bit set
            return (value & (value - 1)) == 0;
        }

        // Returns true if the number is a perfect square (4, 9, 16)
        public static boolean isSquare(int value) {
            if (value < 0) {
                return false;
            }
            int root = (int) Math.sqrt(value);
            return root * root == value;
        }
    }


