package org.example.triangle;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class TriangleTest {

  //  Triangle tri;

  @BeforeAll
  public static void initAll() {
    System.out.println("測試開始");
  }

  @BeforeEach
  void init() {
    //  tri = new Triangle();
    }

  @org.junit.jupiter.api.Test
  public void checkTriangleTest() {
    System.out.println("這是 checkTriangle() 測試");
    //測試順序: 三邊長數值, 正三角, 等腰, 直角, 不規則
    /*
    回傳結果說明:
    1 : 正三角形
    2 : 等腰三角形
    3 : 直角三角形
    0 : 不規則三角形
    如果exception有跳出來則回傳結果不會準確，但會顯示自訂的錯誤原因
    */
    assertAll("checkTriangle",
        () -> assertEquals(0, Triangle.checkTriangle(-1, 0, 0)),
        () -> assertEquals(0, Triangle.checkTriangle(6, -2, 5)),
        () -> assertEquals(0, Triangle.checkTriangle(1, 3, 4)),
        () -> assertEquals(0, Triangle.checkTriangle( 7, 23, 16)),
        () -> assertEquals(1, Triangle.checkTriangle( 5, 5, 5)),
        () -> assertEquals(2, Triangle.checkTriangle( 7, 5, 5)),
        () -> assertEquals(3, Triangle.checkTriangle( 5, 12, 13)),
        () -> assertEquals(0, Triangle.checkTriangle( 10, 30, 21))
        );
  }

  @AfterAll
  static void tearDownAll() {
    System.out.println("測試結束");
  }

}
