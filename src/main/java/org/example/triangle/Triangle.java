package org.example.triangle;

import java.util.Scanner;

/**
 * The type Triangle.
 */
public class Triangle {

  /**
   * Check triangle int.
   *
   * @param a the a
   * @param b the b
   * @param c the c
   * @return the int
   */
  public static int checkTriangle(int a, int b, int c) {
    boolean flag;
    int triangleType;

    // 利用assert判斷輸入的三個邊長是否 > 0 (邊長本來就不能 < 0)
    assert a > 0 && b > 0 && c > 0 : "邊長本來就不能 <= 0";

    // 利用assert判斷輸入的其中兩個邊長相加是否 > 第三邊邊長 (構成任意三角形的邊長邊長條件)
    assert (a + b > c) && (b + c > a) && (c + a > b) : "任兩邊之長總和 < 第三邊才能構成三角形";
//    利用 try catch 去判斷a, b, c是否為0，或不符合三角形邊長條件
    try {
      if (a <= 0 || b <= 0 || c <= 0) {
        flag = true;
        throw new TriangleException(flag, a, b, c);
      } else if (a + b <= c || b + c <= a || c + a <= b) {
        flag = false;
        throw new TriangleException(flag, a, b, c);
      }
    } catch (TriangleException e) {
      System.out.println("錯誤: " + e.triangleErr());
      e.printStackTrace();
      System.exit(1);
    }
    //判斷三角形類型
    if (a == b && b == c) {
      // 正三角形
      triangleType = 1;
    } else if (a == b || b == c || c == a) {
      //等腰三角形
      triangleType = 2;
    } else if (a * a + b * b == c * c || b * b + c * c == a * a || a * a + c * c == b * b) {
      //直角三角形
      triangleType = 3;
    } else {
      //不規則三角形
      triangleType = 0;
    }
    return triangleType;
  }
}



