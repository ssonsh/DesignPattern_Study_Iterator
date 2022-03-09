# DesignPattern_Study_Iterator

# Notion Link
https://five-cosmos-fb9.notion.site/Iterator-afa9ccf35ff1456b84b9625308a56115

# 반복자 (Iterator)

### 의도

내부 표현부를 노출하지 않고 어떤 집합 객체에 속한 원소들을 순차적으로 접근할 수 있는 방법을 제공

<aside>
🎈 다른 이름 : 커서 (Cursor)

</aside>

### 활용성

- 객체 내부 표현 방식을 모르고도 집합 객체의 각 원소들에 접근하고 싶을 때
- 집합 객체를 순회하는 다양한 방법을 지원하고 싶을 때
- 서로 다른 집합 객체 구조에 대해서도 동일한 방법으로 순회하고 싶을 때

<aside>
🎈 사실 반복자 패턴은 중요하지 않다!

개념만 알고 있다면, 각 언어별로 제공해주는 컨테이너와 그와 같이 제공되는 Iterator를 활용하면된다.

만약, 컨테이너 자체를 직접 만들어야 한다면 처음부터 만들어나갈 필요 없이 언어별로 제공되는 Iterator 패턴을 활용하면 된다.

</aside>

### 구조

![image](https://user-images.githubusercontent.com/18654358/157388510-2078368f-07ce-4917-880a-94219c54682e.png)

**Iterator**

- 원소를 접근하고 순회하는 데 필요한 인터페이스를 제공

**ConcreteIterator**

- Iterator에 정의된 인터페이스를 구현하는 클래스로, 순회 과정 중 집합 객체 내에서 현재 위치를 기억한다.

**Aggregate**

- Iterator 객체를 생성하는 인터페이스를 정의

**ConcreteAggregate**

- 해당하는 ConcreteIterator 의 인스턴스를 반환하는 Iterator 생성 인터페이스를 구현

---

### 흔히 사용하는 Java Collection 중 ArrayList

![image](https://user-images.githubusercontent.com/18654358/157388534-d1298c23-62a0-4dda-8e60-1a441c60546b.png)

**Iterable.java**

```java
package java.lang;

import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

public interface Iterable<T> {

    Iterator<T> iterator();

    default void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T t : this) {
            action.accept(t);
        }
    }

    default Spliterator<T> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), 0);
    }
}
```

---

### 다시 살펴보는 Iterator Pattern

여러 타입의 컨테이너들에 엑세스를 가능하게 해주는 공통된 인터페이스를 제공해준다.

**Example.**

**Forward Iterator** → 컨테이너 안의 데이터에서 다음 데이터를 가리킬 수 있게 도와준다.

- Tree 같은 경우 Iterator를 어떻게 정의하느냐에 따라 Right Child로 반복하느냐, Left Child 로 반복하느냐 방법을 정의할 수 있다.

여러 종료의 데이터 컨테이너들 → Array, Tree, HashSet 등 컨테이너 타입에 관계 없이 Iterator의 인터페이스만 가지고도 같은 알고리즘을 적용해낼 수 있는 것이다.

<aside>
🎈 위에서도 언급했듯이 Iterator도 다양한 Iterator들이 존재한다. (정방향, 역방향 등)

</aside>

![image](https://user-images.githubusercontent.com/18654358/157388569-3a851791-3d3b-4b64-b094-581054590159.png)

위와 같이 ArrayContainer를 정의하고 이를 처리하는 Iterator를 만들게 되면

클라이언트는 Iterator 오브젝트만 가지고 Container 안의 데이터를 순차적으로 처리할 수 있게 된다.

**Iterator.java**

```java
public interface Iterator {
    boolean hasNext();
    int nextVal();
}
```

[**ArrayIterator.java](http://ArrayIterator.java) (implements Iterator)**

```java
public class ArrayIterator implements Iterator{

    private int pos = -1;
    private final ArrayContainer container;

    public ArrayIterator(ArrayContainer container) {this.container = container;}

    @Override
    public boolean hasNext() {
        if(this.pos < this.container.size() - 1){
            return true;
        }
        return false;
    }

    @Override
    public int nextVal() {
        if(this.hasNext()){
            pos += 1;
            return this.container.get(pos);
        }
        return 0;
    }
}
```

**ArrayContainer.java**

```java
import java.util.Arrays;

public class ArrayContainer {

    private int pos = 0;
    private int[] arr = new int[pos + 1];

    public void add(int val){
        arr[pos] = val;
        pos++;

        int[] expand = Arrays.copyOf(arr, pos + 1);
        arr = expand;
    }

    public Iterator getIterator(){
        return new ArrayIterator(this);
    }

    public int size() {
        return this.pos;
    }

    public int get(int idx) {
        return arr[idx];
    }
}
```

테스트
