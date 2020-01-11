package contract.input;

import static java.awt.event.KeyEvent.*;

public class KeyChar {
    public static final char ERROR = 0;

    private static boolean alpha = true;
    private static boolean numeric = true;
    private static boolean symbolic = false;

    public static void setAlpha(boolean alpha) {
        KeyChar.alpha = alpha;
    }

    public static void setNumeric(boolean numeric) {
        KeyChar.numeric = numeric;
    }

    public static void setSymbolic(boolean symbolic) {
        KeyChar.symbolic = symbolic;
    }
    public static boolean andEquals(int value, int mask) {
        return (value & mask) == mask;
    }
    public static char toChar(int keyCode, int keyMod) {
        switch (keyCode) {
            case VK_A:
                return alpha ? andEquals(keyMod, SHIFT_DOWN_MASK) ? 'A' : 'a' : ERROR;
            case VK_B:
                return alpha ? andEquals(keyMod, SHIFT_DOWN_MASK) ? 'B' : 'b' : ERROR;
            case VK_C:
                return alpha ? andEquals(keyMod, SHIFT_DOWN_MASK) ? 'C' : 'c' : ERROR;
            case VK_D:
                return alpha ? andEquals(keyMod, SHIFT_DOWN_MASK) ? 'D' : 'd' : ERROR;
            case VK_E:
                return alpha ? andEquals(keyMod, SHIFT_DOWN_MASK) ? 'E' : 'e' : ERROR;
            case VK_F:
                return alpha ? andEquals(keyMod, SHIFT_DOWN_MASK) ? 'F' : 'f' : ERROR;
            case VK_G:
                return alpha ? andEquals(keyMod, SHIFT_DOWN_MASK) ? 'G' : 'g' : ERROR;
            case VK_H:
                return alpha ? andEquals(keyMod, SHIFT_DOWN_MASK) ? 'H' : 'h' : ERROR;
            case VK_I:
                return alpha ? andEquals(keyMod, SHIFT_DOWN_MASK) ? 'I' : 'i' : ERROR;
            case VK_J:
                return alpha ? andEquals(keyMod, SHIFT_DOWN_MASK) ? 'J' : 'j' : ERROR;
            case VK_K:
                return alpha ? andEquals(keyMod, SHIFT_DOWN_MASK) ? 'K' : 'k' : ERROR;
            case VK_L:
                return alpha ? andEquals(keyMod, SHIFT_DOWN_MASK) ? 'L' : 'l' : ERROR;
            case VK_M:
                return alpha ? andEquals(keyMod, SHIFT_DOWN_MASK) ? 'M' : 'm' : ERROR;
            case VK_N:
                return alpha ? andEquals(keyMod, SHIFT_DOWN_MASK) ? 'N' : 'n' : ERROR;
            case VK_O:
                return alpha ? andEquals(keyMod, SHIFT_DOWN_MASK) ? 'O' : 'o' : ERROR;
            case VK_P:
                return alpha ? andEquals(keyMod, SHIFT_DOWN_MASK) ? 'P' : 'p' : ERROR;
            case VK_Q:
                return alpha ? andEquals(keyMod, SHIFT_DOWN_MASK) ? 'Q' : 'q' : ERROR;
            case VK_R:
                return alpha ? andEquals(keyMod, SHIFT_DOWN_MASK) ? 'R' : 'r' : ERROR;
            case VK_S:
                return alpha ? andEquals(keyMod, SHIFT_DOWN_MASK) ? 'S' : 's' : ERROR;
            case VK_T:
                return alpha ? andEquals(keyMod, SHIFT_DOWN_MASK) ? 'T' : 't' : ERROR;
            case VK_U:
                return alpha ? andEquals(keyMod, SHIFT_DOWN_MASK) ? 'U' : 'u' : ERROR;
            case VK_V:
                return alpha ? andEquals(keyMod, SHIFT_DOWN_MASK) ? 'V' : 'v' : ERROR;
            case VK_W:
                return alpha ? andEquals(keyMod, SHIFT_DOWN_MASK) ? 'W' : 'w' : ERROR;
            case VK_X:
                return alpha ? andEquals(keyMod, SHIFT_DOWN_MASK) ? 'X' : 'x' : ERROR;
            case VK_Y:
                return alpha ? andEquals(keyMod, SHIFT_DOWN_MASK) ? 'Y' : 'y' : ERROR;
            case VK_Z:
                return alpha ? andEquals(keyMod, SHIFT_DOWN_MASK) ? 'Z' : 'z' : ERROR;
            case VK_1:
                return andEquals(keyMod, SHIFT_DOWN_MASK) ? symbolic ? '!' : ERROR : numeric ? '1' : ERROR;
            case VK_2:
                return andEquals(keyMod, SHIFT_DOWN_MASK) ? symbolic ? '@' : ERROR : numeric ? '2' : ERROR;
            case VK_3:
                return andEquals(keyMod, SHIFT_DOWN_MASK) ? symbolic ? '#' : ERROR : numeric ? '3' : ERROR;
            case VK_4:
                return andEquals(keyMod, SHIFT_DOWN_MASK) ? symbolic ? '$' : ERROR : numeric ? '4' : ERROR;
            case VK_5:
                return andEquals(keyMod, SHIFT_DOWN_MASK) ? symbolic ? '%' : ERROR : numeric ? '5' : ERROR;
            case VK_6:
                return andEquals(keyMod, SHIFT_DOWN_MASK) ? symbolic ? '^' : ERROR : numeric ? '6' : ERROR;
            case VK_7:
                return andEquals(keyMod, SHIFT_DOWN_MASK) ? symbolic ? '&' : ERROR : numeric ? '7' : ERROR;
            case VK_8:
                return andEquals(keyMod, SHIFT_DOWN_MASK) ? symbolic ? '*' : ERROR : numeric ? '8' : ERROR;
            case VK_9:
                return andEquals(keyMod, SHIFT_DOWN_MASK) ? symbolic ? '(' : ERROR : numeric ? '9' : ERROR;
            case VK_0:
                return andEquals(keyMod, SHIFT_DOWN_MASK) ? symbolic ? ')' : ERROR : numeric ? '0' : ERROR;
            default:
                return ERROR;
        }
    }
}
