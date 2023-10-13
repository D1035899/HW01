package org.example.person;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) {
    String name = "";
    int birthYear = 0;
    int age = 0;
    double weight = 0;
    double height = 0;
    double bmi = 0;
    boolean check;
    Scanner scn = new Scanner(System.in);
    // 詢問共有幾組測資
    System.out.print("請輸入要登記的總人數: ");
    int amount = scn.nextInt();

    // 詢問有沒有需要預先設定的person
    System.out.print("請輸入預先登記人數: ");
    int preAmount = scn.nextInt();

    //建立 person array
    Person[] person = new Person[amount];

    for (int i = 0; i < amount; i++) {
      if (i < preAmount) {
        System.out.println("------------------------");
        System.out.println("請輸入 person " + (i + 1) + "的預設資料");

        // 設定預設姓名
        System.out.print("預設姓名: ");
        scn.nextLine();
        name = scn.nextLine();
        try {
          check = Check.checkInput(name);
          if (!check) {
            throw new PersonException(name);
          }
        } catch (PersonException e) {
          System.out.println("錯誤: " + e.personErr());
          e.printStackTrace();
          System.exit(1);
        }
        // 設定預設生日年
        System.out.print("預設生日年: ");
        birthYear = scn.nextInt();
        try {
          if (birthYear <= 0) {
            throw new PersonException(birthYear);
          }
        } catch (PersonException e) {
          System.out.println("錯誤: " + e.personErr());
          e.printStackTrace();
          System.exit(1);
        }

        person[i] = new Person(name, birthYear);
      } else {
        person[i] = new Person();
      }
    }
    System.out.println("\n-----預設資料設定完畢-----");

    for (int i = 0; i < amount; i++) {
      System.out.println("------------------------");
      System.out.println("請輸入 person " + (i + 1) + " 的資料");

      // 設定姓名 (如果已經預設好就不用輸入)
      if (person[i].getName().equals("")) {
        System.out.print("請輸入姓名: ");
        scn.nextLine();
        name = scn.nextLine();
        try {
          check = Check.checkInput(name);
          if (!check) {
            throw new PersonException(name);
          }
        } catch (PersonException e) {
          System.out.println("錯誤: " + e.personErr());
          e.printStackTrace();
          System.exit(1);
        }
        person[i].setName(name);
      } else {
        System.out.println("姓名已預設");
      }

      // 設定生日年 (如果已經預設好就不用輸入)
      if (person[i].getBirthYear() == 0) {
        System.out.print("請輸入生日年: ");
        birthYear = scn.nextInt();
        try {
          if (birthYear <= 0) {
            throw new PersonException(birthYear);
          }
        } catch (PersonException e) {
          System.out.println("錯誤: " + e.personErr());
          e.printStackTrace();
          System.exit(1);
        }
        person[i].setBirthYear(birthYear);
      } else {
        System.out.println("生日年已預設");
      }

      // 設定年齡 (如果person[i]沒有預設好生日年，需要帶入birthYear)
      if (person[i].getBirthYear() == 0) {
        person[i].setAge(birthYear);
      } else {
        person[i].setAge();
      }

      // 設定身高
      if (person[i].getHeight() == 0) {
        System.out.print("請輸入身高(cm): ");
        height = scn.nextDouble();
        try {
          if (height <= 0) {
            throw new PersonException(height, 1);
          }
        } catch (PersonException e) {
          System.out.println("錯誤: " + e.personErr());
          e.printStackTrace();
          System.exit(1);
        }
        person[i].setHeight(height);
      }

      // 設定體重
      if (person[i].getWeight() == 0) {
        System.out.print("請輸入體重(kg): ");
        weight = scn.nextDouble();
        try {
          if (height <= 0) {
            throw new PersonException(height, 2);
          }
        } catch (PersonException e) {
          System.out.println("錯誤: " + e.personErr());
          e.printStackTrace();
          System.exit(1);
        }
        person[i].setWeight(weight);
      }
      person[i].setBmi(height, weight);
    }
    System.out.println("\n-----全部資料設定完畢-----");

    //印出每組person的資料
    for (int i = 0; i < amount; i++) {
      name = person[i].getName();
      birthYear = person[i].getBirthYear();
      age = person[i].getAge();
      height = person[i].getHeight();
      weight = person[i].getWeight();
      bmi = person[i].getBmi();
      System.out.println("------------------------");
      System.out.println("person " + (i + 1) + " 的資料");
      System.out.println("姓名: " + name);
      System.out.println("生日年: " + birthYear);
      System.out.println("年齡: " + age);
      System.out.println("身高: " + height);
      System.out.println("體重: " + weight);
      System.out.printf("BMI: %.1f%n", bmi);
    }
  }
}
