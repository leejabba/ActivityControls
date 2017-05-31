# ActivityControls
startActivityForResult - Intent로 값 주고 받기

> 인텐트로 값을 주고 받는 것은 우체국 시스템과 비슷하다. 편지를 보내고 답장을 받고, 답장의 내용을 확인한다.

인텐트로 액티비티간에 값을 주고 받는 것은 우체국의 시스템과 비슷하다고 생각합니다. 다음의 설명은 인텐트에 String 값을 담아 보낸 상황을 가정한 것입니다.

* * *


## 1. 보내는 쪽
- 인텐트 객체를 생성한다.
- 인텐트 객체에 키 값과 내용 값을 담는다.
- startActivityForResult 메서드에 인텐트와 번지수를 포함한 값을 argument로 담아 호출한다.


**[MainActivity.java] - 답장 달라는 내용으로 편지쓰기**
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


**[SubActivity.java] - 편지 받기**
```java
// getIntent 메서드로 보내온 액티비티의 인텐트 객체를 받아온다.
Intent getIntent = getIntent();

// Extra의 값을 담은 주머니인 Bundle에서 값의 묶음을 꺼낸다.
Bundle bundle = getIntent.getExtras();

// 받아온 인텐트의 값이 null인 경우(null point exception 에러)를 대비해 if문으로 체크한다.
if (bundle != null) {
	// MainActivity.java의 putExtra("key", "안녕하세요"); 코드에서 동일한 키 값의 
	// 값을 가져와 변수에 담는다.
	String value = bundle.getString("key");
}
```

**[SubActivity.java] - 답장 보내기**
```java
/**
* 인텐트의 값 반환하기 (답장 보내기)
* 1. 결과값을 인텐트에 담는다.
* 2. 값을 setResult 메서드를 이용해 넘겨준다.
* 3. 액티비티를 종료한다.
*/

Intent intent = new Intent();
// putExtra로 키 값과 내용 값을 담는다.
intent.putExtra("result", resultValue);

// 답장을 보낼 준비가 되었다는 의미. RESULT_OK는 상수로써 의미는 상수명에서 느낄 수 있다.
setResult(RESULT_OK, intent);

// (아마) commit하면서 액티비티를 닫는 역할을 하는 듯 하다.
finish();

```

* * *


## 3. 답장의 내용 확인
- onActivityResult 메서드를 Override 한 후 어떻게 처리 할 것인지 정의한다.

**[MainActivity.java] - 답장 확인하기**
```java
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	// 답장의 setResult에서 어떤 코드를 보냈는지 체크 한다.
	if (resultCode == RESULT_OK) {

	// 원래 보냈던 편지의 요청코드 번호가 몇 번인지에 따라 행동을 다르게 설정한다.
	// 앞에서 startActivityForResult(intent, 100); 로 설정했었다.
	switch (resquestCode) {
	case 100:
	// 스트링 변수에 답장을 보낸 쪽의 putExtra 키 값의 내용을 담는다.
	String result = data.getStringExtra("result");

	Toast.makeText(this, result, Toast.LENGTH_SHORT).show();

	break;
}
}
}

```

* * *

## 링크 : 전체 소스 코드 보기
[MainActivity.java](https://github.com/leejabba/ActivityControls/blob/master/app/src/main/java/com/heythisway/activitycontrol/MainActivity.java)
[SubActivity.java](https://github.com/leejabba/ActivityControls/blob/master/app/src/main/java/com/heythisway/activitycontrol/SubActivity.java)
