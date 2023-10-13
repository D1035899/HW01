package org.example.person;

public class PersonException extends Exception {
  protected String strInput;
  protected int intInput;
  protected double doubleInput;
  protected int flag = 0;
  protected int mode = 0;

  //根據帶入的變數型態來封裝進PersonException

  //姓名
  public PersonException(String input) {
    strInput = input;
    flag = 1;
  }

  //生日年
  public PersonException(int input) {
    intInput = input;
    flag = 2;
  }

  //身高、體重(使用num來得知是哪個)
  public PersonException(double input, int num) {
    doubleInput = input;
    this.mode = num;
    flag = 3;
  }

  // 依據 flag 來return錯誤原因
  public String personErr() {
    switch (flag) {
      case 1:
        return "姓名( \"" + strInput + "\" )不得包含特殊字元或數字!";
      case 2:
        return "年份( \"" + intInput + "\" )不得 <= 0!";
      default:
        if (mode == 1) {
          return "身高( \"" + intInput + "\" )不得 <= 0!";
        } else {
          return "體重( \"" + intInput + "\" )不得 <= 0!";
        }
    }
  }
}
