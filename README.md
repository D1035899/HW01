# 軟體測試 HW01

學號: D1035899  姓名: 張牧翔

所有的檔案包括此文件皆已上傳至gitgub
連結: 

[https://github.com/D1035899/HW01](https://github.com/D1035899/HW01)

---

---

## 題目1: Triangle

### 設計說明:

- 依照題目要求，在`Triangle.java` 中製作了`checkTriangle()` method，並且會判斷三邊長任一邊是否`≤`0 or 符不符合構成三角形的條件，如果測資是錯的就會throw自製的`TriangleException` ，反之則回傳三角形的類型給`Main.java`
- 在`Main.java` 裡輸入三邊長，並呼叫`checkTriangle()` ，接著取得回傳結果，輸出三角形的類型
- 在`TriangleTest.java` 使用Junit測試`checkTriangle()` 的判斷以及Exception的結果

### 節錄程式碼

![Untitled](images\Untitled.png)

![Untitled](images\Untitled1.png)

- Main.java

```java
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
```

- Triangle.java

```java
public static int checkTriangle(int a, int b, int c) {
    boolean flag;
    int triangleType;

    // 利用assert判斷輸入的三個邊長是否 > 0 (邊長本來就不能 < 0)
    assert a > 0 && b > 0 && c > 0 : "邊長本來就不能 <= 0";

    // 利用assert判斷輸入的其中兩個邊長相加是否 > 第三邊邊長 (構成任意三角形的邊長邊長條件)
    assert (a + b > c) && (b + c > a) && (c + a > b) : "任兩邊之長總和 < 第三邊才能構成三角形";
    //利用 try catch 去判斷a, b, c是否為0，或不符合三角形邊長條件
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
```

- TriangleException.java

```java
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
```

- TriangleTest.java

```java
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
        () -> assertEquals(4, Triangle.checkTriangle( 8, 14, 22))
        );
  }
```

### 執行結果

- 正常輸入執行結果
    - 測資: `(3, 4, 5)`
    - 正常輸出: 直角三角形

![Untitled](images\Untitled2.png)

- 錯誤輸入執行結果(三邊長不得小於0)
    - 測資: `(2, -1, 7)`
    - 錯誤輸出: throw 出`TriangleException()`的例外處理

![Untitled](images\Untitled3.png)

- Junit執行結果
    - 利用`assertAll()`一次執行所有測資
    - 測試順序為: 三邊長數值以及組成條件, 正三角, 等腰, 直角, 不規則
    - 測資為:
    
    ```java
    (-1, 0, 0)    // 三邊長不得為0
    (6, -2, 5)    // 三邊長不得為0
    (1, 3, 4)     // 任兩邊小於第三邊
    (7, 23, 16)   // 任兩邊小於第三邊
    (5, 5, 5)     // 正確測資
    (7, 5, 5)     // 正確測資
    (5, 12, 13)   // 正確測資
    (10, 30, 21)  // 正確測資
    ```
    
    - *回傳結果說明:*
        - *1 : 正三角形*
        - *2 : 等腰三角形*
        - *3 : 直角三角形*
        - *0 : 不規則三角形*
        - ***如果exception有跳出來則回傳結果不會準確，但會顯示自訂的錯誤原因***
    
    ![Untitled](images\Untitled4.png)
    

---

## 題目2: Person

### 設計說明:

- 依照題目要求，在`Person.java` 中製作了`Person class` ，當中有兩個Person物件，一個是預設的空物件，另一個則是題目所需的Person物件，可以初始化姓名和生日年
- 建立年齡的`getter & setter`並透過生日年來取得年齡，且也有兩種method，來應付不同狀況
- 建立了`setBmi()`來計算BMI，`getBmi()`回傳BMI
- 使用`PersonException`來判斷各項資料是否輸入正確
    - 姓名: 不得有特殊字元和數字
    - 生日年、身高、體重不能 ≤ 0
- 於`Person.java` 使用Junit測試`checkInput()`的結果

### 節錄程式碼

![Untitled](images\Untitled5.png)

![Untitled](images\Untitled6.png)

- Main.java

```java
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
```

- Person.java

```java
// Person預設物件
public Person() {

}

//Person 物件 (可設定姓名和生日年)
public Person(String name, int birthYear) {
  this.name = name;
  this.birthYear = birthYear;
}

// bmi getter and setter
public void setBmi(double height, double weight) {
  bmi = weight / (height * height / 10000);
}

public double getBmi() {
  return bmi;
}

// 利用生日年取得年齡
public void setAge() {
  this.age = 2023 - birthYear;
}

public void setAge(int birthYear) {
  this.age = 2023 - birthYear;
}
```

- Check.java

```java
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Check {

  public static boolean checkInput(String input) {
    // 判斷input內的文字有沒有符合regex的規則(不能包含特殊字元和數字)
    Pattern p = Pattern.compile("^[0-9~`!@#$%^&*()_+\\-={}\\[\\]\"':;?/<>,.]");
    Matcher m = p.matcher(input);
    return m.find();
  }
}
```

- PersonException.java

```java
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
```

- PersonTest.java

```java
@org.junit.jupiter.api.Test
  // 姓名測試: 姓名不能含有數字或特殊字元
  public void nameTest() {
    assertAll("nameTest", 
				() -> assertEquals(true, Check.checkInput("Sam")),
        () -> assertEquals(false, Check.checkInput("A1ex")),
        () -> assertEquals(false, Check.checkInput("T0m")),
        () -> assertEquals(true, Check.checkInput("Jammie"))
        );
  }
```

### 執行結果

- 正常輸入執行結果

![Untitled](images\Untitled7.png)

- 錯誤輸入執行結果(以輸入錯誤姓名為例)
    - 測資: 以輸入錯誤姓名為例
    - 錯誤輸出: throw 出`PersonException()`的例外處理

![Untitled](images\Untitled8.png)

- Junit執行結果
    - 利用`assertAll()`一次執行所有測
    - 測資為:
    
    ```java
    "Sam"   // 正確測資
    "A1ex"    // 錯誤測資
    "T0m"     // 錯誤測資
    "Jammie"  // 正確測資
    ```
    
    ![Untitled](images\Untitled9.png)
    

---

## 作業心得

這次的作業讓我了解到如何使用Junit來測試我的程式碼，另外也更了解如何使用`Exception class` 來自製例外處理，不過因為是第一次做程式碼測試的編寫，所以並不是很熟悉。
在還沒修這堂課之前，我只會用debugger逐行偵錯，我也沒想過還有別的方法可以來查找程式碼的錯誤，希望之後可以更加了解軟體測試的手段。