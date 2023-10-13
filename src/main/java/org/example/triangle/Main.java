package org.example.triangle;

import java.util.Scanner;

/**
 * The type Main.
 */
public class Main {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    // 輸入三個邊長 a, b, c
    System.out.println("請輸入要測試的三個邊長(a, b, c)");
    System.out.print("a: ");
    int a = scn.nextInt();
    System.out.print("b: ");
    int b = scn.nextInt();
    System.out.print("c: ");
    int c = scn.nextInt();

    // 取得checkTriangle()回傳結果，並根據結果輸出三角形類型
    int result = Triangle.checkTriangle(a, b, c);
    switch (result) {
      case 1:
        System.out.println("正三角形");
        break;
      case 2:
        System.out.println("等腰三角形");
        break;
      case 3:
        System.out.println("直角三角形");
        break;
      case 0:
        System.out.println("不規則三角形");
        break;
      default:
        System.out.println("此三邊長不能形成三角形");
        break;
    }
  }
}
