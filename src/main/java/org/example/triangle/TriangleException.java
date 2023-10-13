package org.example.triangle;

public class TriangleException extends Exception {
  boolean flag;
  int aa;
  int bb;
  int cc;

  // 將checkTriangle() 所得到的三個邊長以及錯誤類型封裝進TriangleException()
  // 錯誤類型:
  // flag = true : a || b || c <= 0
  // flag = false : 任兩邊長 <= 第三邊
  public TriangleException(boolean flag, int a, int b, int c) {
    this.flag = flag;
    this.aa = a;
    this.bb = b;
    this.cc = c;
  }

  // 根據錯誤類型回傳錯誤原因
  public String triangleErr() {
    if (flag) {
      return ("輸入的三邊長( a: " + aa + ", b: " + bb + ", c: " + cc + " )數值不得小於0!");
    } else {
      return ("輸入的三邊長( a: " + aa + ", b: " + bb + ", c: " + cc + " )任兩邊相加<=第三邊!");
    }
  }
}
