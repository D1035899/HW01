package org.example.person;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Check {

  public static boolean checkInput(String input) {
    // 判斷input內的文字有沒有符合regex的規則(不能包含特殊字元和數字)
    Pattern p = Pattern.compile("[^0-9~`!@#$%^&*()_+\\-={}\\[\\]\"':;?\\/<>,.]");
    Matcher m = p.matcher(input);
    return m.find();
  }
}


