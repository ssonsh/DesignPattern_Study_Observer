# DesignPattern_Study_Observer

# Notion Link
https://five-cosmos-fb9.notion.site/Observer-06b020a8b07441a0a13f55a7e4f2f958


# 감시자 (Observer)

### 의도

객체 사이에 일 대 다의 의존관계를 정의해 두어, 어떤 객체의 상태가 변할 때 그 객체의 의존성을 가진 다른 객체들이 그 변화를 통지받고 자동으로 갱신될 수 있게 만든다.

- *주로 분산 이벤트 핸들링 시스템을 구현하는데 사용된다. (발행/구독 모델)*

<aside>
🎈 다른이름 : 종속자(Dependent), 게시-구독 (Publish-Subscribe)

</aside>

### 대표적 사례

- 외부에서 발생한 이벤트(사용자 입력같은)에 대한 응답. (이벤트 기반 프로그래밍)
- 객체의 속성 값 변화에 따른 응답. 종종 콜백은 속성 값 변화를 처리하기 위해 호출될 뿐 아니라 속성 값 또한 바꾼다.
- 때때로 이벤트 연쇄의 원인이 될 수 있다.

<aside>
🎈 모델-뷰-컨트롤러(MVC) 패러다임과 자주 결합된다.

옵저버 패턴은 MVC에서 모델과 뷰 사이를 느슨히 연결하기 위해 사용된다. 

</aside>

### 활용성

- 어떤 추상 개념이 두 가지 양상을 갖고 하나가 다른 하나에 종속적일 때, 각 양상을 별도의 객체로 캡슐화하여 이들 각각을 재사용할 수 있다.
- 한 객체에 가해진 변경으로 다른 객체를 변경해야 하고, 프로그래머들은 얼마나 많은 객체들이 변경되어야 하는지 몰라도 될 때
- 어떤 객체가 다른 객체에 자신의 변화를 통보할 수 있는데, 그 변화에 관심있어 하는 객체들이 누구인지에 대한 가정 없이도 그러한 통보가 될 때

### 구조

![image](https://user-images.githubusercontent.com/18654358/157772494-4d321ab1-4730-4ff4-a6a3-f481acd2ea46.png)

- Observer는 구독하고자 하는 Subject로부터 이벤트를 수신받을 수 있는 경로인 notify를 제공
- Subject는 Observer들을 등록하고, 해제할 수 있으며 등록된 모든 Observer들에게 이벤트를 발행할 수 있는 notifyObservers() 제공

### 참여자

**Subject**

- 감시자들을 알고 있는 주체
- 임의 개수의 감시자 객체는 주체를 감시할 수 있다.
- 주체는 감시자 객체를 붙이거나 떼는데 필요한 인터페이스를 제공한다.

**Observer**

- 주체에 생긴 변화에 관심 있는 객체를 갱신하는데 필요한 인터페이스를 정의한다.
- 이로써 주체의 변경에 따라 변화되어야 하는 객체들의 일관성을 유지한다.

**ConcreteSubject**

- ConcreteObserver 객체에게 알려주어야 하는 상태를 저장한다.
- 또한 이 상태가 변경될 때 감시자에게 변경을 통보한다.

**ConcreteObserver**

- ConcreteSubject객체에 대한 참조자를 괸라한다.
- 주체의 상태와 일관성을 유지해야 하는 상태를 저장한다.
- 주체의 상태와 감시자의 상태를 일관되게 유지하는데 사용하는 갱신 인터페이스를 구현한다.

---

![image](https://user-images.githubusercontent.com/18654358/157772524-33aaabf8-1f4b-4511-95da-d4ee49f05fb8.png)

**만약 Observer Pattern이 적용되어 있지 않다면?**

- Polling 방식
- 만약 Polling Interval(1초, 1분, 1시간 등)에 발생된 Event가 사라진다면?
    - 이벤트를 처리하고자 하는 Client는 Event에 대해 처리하지 못하는 현상이 발생될 수 있다.

![image](https://user-images.githubusercontent.com/18654358/157772542-73281b1f-b351-4503-8496-8def8db431bc.png)

<aside>
🎈 이와 반대로, Observer Pattern 으로 구현되게 된다면

이벤트가 일어나는 순간, 이를 바라보던 Observer들이 바로 반응할 수 있도록 할 수 있다.

</aside>

![image](https://user-images.githubusercontent.com/18654358/157772576-d58085cf-4dbf-4f0a-a0a5-c1b0d1718edc.png)

**Observer Pattern의 핵심은,**

Event 발생 주체 Object에서 Observer들을 관리하는 것

- Observer들의 등록
- Observer들의 함수 호출

상황에 따라 ConcreteObserver를 바라보는 또 다른 Observer와 같이 확장되어 활용될 수 있다.

Observer.java

```java
public interface Observer {
    void update();
}
```

Cat, Dog

```java
public class Cat implements Observer{
    @Override
    public void update() {
        System.out.println("냥");
    }
}

///////////////////////////////////////////////////////
public class Dog implements Observer{
    @Override
    public void update() {
        System.out.println("멍");
    }
}
```

Owner.java

```java
import java.util.ArrayList;
import java.util.List;

public class Owner {

    private List<Observer> animals;

    public void register(Observer observer){
        if(animals == null){
            animals = new ArrayList<>();
        }

        animals.add(observer);
    }

    public void noti(){
        for (Observer animal : animals) {
            animal.update();
        }
    }
}
```

Main

```java
public class Main {
    public static void main(String[] args) {

        Owner owner = new Owner();

        Cat cat = new Cat();
        Dog dog = new Dog();

        owner.register(cat);
        owner.register(dog);

        owner.noti();
    }
}
```

```java
냥
멍
```

---

---

ClickListener.java

```java
public interface ClickListener {
    void click();
}
```

ButtonClickListener.java

```java
public class ButtonClickListener implements ClickListener{
    @Override
    public void click() {
        System.out.println("clicked TextButton !!");
    }
}
```

Main
