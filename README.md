# ActivityControls
startActivityForResult - Intent로 값 주고 받기

> 인텐트로 값을 주고 받는 것은 우체국 시스템과 비슷하다. 편지를 보내고 답장을 받고, 답장의 내용을 실행한다.

인텐트로 액티비티간에 값을 주고 받는 것은 우체국의 시스템과 비슷하다고 생각합니다.

다음의 설명은 인텐트에 String 값을 담아 보낸 상황을 가정한 것입니다.

* * *
## 1. 보내는 쪽
- 인텐트 객체를 생성한다.
- 인텐트 객체에 키 값과 내용 값을 담는다.
- startActivityForResult 메서드에 인텐트와 번지수를 포함한 값을 argument로 담아 호출한다.

**[MainActivity.java]**
```java
// Context와 액티비티 클래스 값을 담아 Intent 생성자를 호출한다.
Intent intent = new Intent(this, DetailActivity.class);

// putExtra() 메서드에 키 값과 내용을 담아둔다.
intent.putExtra("key", "안녕하세요");

// 결과를 받길 원한다며 번지수를 담아 보낸다.
// 100은 임의의 번지
startActivityForResult(intent, 100);
```
* * *
## 2. 받는 쪽
- 보내온 인텐트 객체를 받아온다.
- 값의 묶음을 꺼낸다.
- 인텐트 객체를 생성하고 결과를 보낼 키 값과 값을 담아냅니다.
- 답장을 보낼 준비를 하고 액티비티를 닫으면서 답장을 보냅니다.

**[SubActivity.java]**
```java
// getIntent 메서드로 보내온 액티비티의 인텐트 객체를 받아온다.
Intent getIntent = getIntent();

// Extra의 값을 담은 주머니인 Bundle에서 값의 묶음을 꺼낸다.
Bundle bundle = getIntent.getExtras();

// 받아온 인텐트의 값이 null인 경우(null point exception 에러)를 대비해 if문으로 체크한다.
```

