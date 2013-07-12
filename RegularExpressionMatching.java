/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.expression.matching;

/**
 *
 * @author zxa
 */
public class RegularExpressionMatching {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println(isMatch("aacaaacbacccbcba", "c*a*.*a*.a.ac*bc"));
    }

    //returns true if the input string s matches the pattern p
    public static boolean isMatch(String s, String p) {

        int[][] DP = new int[s.length() + 1][p.length() + 1];
        isMatch(DP, s, p, 0, 0);
        return DP[0][0] == 2;
    }

    //1: false 2:true
    public static int isMatch(int[][] DP, String s, String p, int sIndex, int pIndex) {

        if (sIndex == s.length() && pIndex == p.length()) {
            DP[sIndex][pIndex] = 2;
            return 2;
        }
        if (DP[sIndex][pIndex] != 0) {
            return DP[sIndex][pIndex];
        }

        if (sIndex < s.length() && pIndex < p.length() && (s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '.')) {
            if (pIndex + 1 < p.length() && p.charAt(pIndex + 1) == '*') {
                for (int i = sIndex; i < s.length(); i++) {
                    if (s.charAt(i) != p.charAt(pIndex) && p.charAt(pIndex) != '.') {
                        break;
                    }
                    int result = isMatch(DP, s, p, i + 1, pIndex + 2);
                    DP[sIndex][pIndex] = result;
                    if (result == 2) {
                        return 2;
                    }
                }
                int result = isMatch(DP, s, p, sIndex, pIndex + 2);
                DP[sIndex][pIndex] = result;

                return result;

            } else {
                DP[sIndex][pIndex] = isMatch(DP, s, p, sIndex + 1, pIndex + 1);
                return DP[sIndex][pIndex];
            }
        } else if (pIndex + 1 < p.length() && p.charAt(pIndex + 1) == '*') {

            DP[sIndex][pIndex] = isMatch(DP, s, p, sIndex, pIndex + 2);
            return DP[sIndex][pIndex];
        } else {
            DP[sIndex][pIndex] = 1;
            return 1;
        }
    }
}
