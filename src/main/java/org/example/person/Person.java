package org.example.person;

public class Person {
  protected String name = "";
  protected int birthYear = 0;
  protected int age = 0;
  protected double weight = 0;
  protected double height = 0;
  protected double bmi = 0;


  // Person預設物件
  public Person() {

  }

  //Person 物件 (可設定姓名和生日年)
  public Person(String name, int birthYear) {
    this.name = name;
    this.birthYear = birthYear;
  }


  // 姓名、生日年、年齡、身高、體重、BMI等變數的getter & setter
  public void setBmi(double height, double weight) {
    bmi = weight / (height * height / 10000);
  }

  // bmi getter and setter
  public double getBmi() {
    return bmi;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getBirthYear() {
    return birthYear;
  }

  public void setBirthYear(int birthYear) {
    this.birthYear = birthYear;
  }

  public int getAge() {
    return age;
  }

  // 利用生日年取得年齡
  public void setAge() {
    this.age = 2023 - birthYear;
  }

  public void setAge(int birthYear) {
    this.age = 2023 - birthYear;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public double getHeight() {
    return height;
  }

  public void setHeight(double height) {
    this.height = height;
  }
}
