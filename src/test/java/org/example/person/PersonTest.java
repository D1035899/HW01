package org.example.person;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {


  @org.junit.jupiter.api.Test

  // 姓名測試: 姓名不能含有數字或特殊字元
  public void nameTest() {
    assertAll("nameTest", () -> assertEquals(true, Check.checkInput("Sam")),
        () -> assertEquals(false, Check.checkInput("A1ex")),
        () -> assertEquals(false, Check.checkInput("T0m")),
        () -> assertEquals(true, Check.checkInput("Jammie"))
        );
  }
}
