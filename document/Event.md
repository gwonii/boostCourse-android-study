## Event



### 1. 대표적인 이벤트 처리 

#### 1.1 터치 이벤트 

* 화면을 손가락으로 누를 때 발생하는 이벤트 

#### 1.2 키 이벤트 

* 키패드나 하드웨어 버튼을 누를 때 발생하는 이벤트 

#### 1.3 제스처 이벤트 

* 터치 이벤트 중에서 일정 패턴을 만들어 내는 이벤트 

#### 1.4 포커스 

* 뷰마다 순서대로 주어지는 포커스 

#### 1.5 화면 방향 변경 

* 화면의 방향이 가로/세로로 바뀜에 따라 발생하는 이벤트 

### 

### 2. 이벤트 처리 방법 

#### 2.1 클릭리스너 이용하기 

`View`를 이용한 클릭리스너 

```java
View view1 = findViewById(R.id.v_button3);

view1.setOnClickListener(new View.OnClickListener(){
   @override
   public void onClick(View v){
       Toast.makeText(MainActivity.this, "view가 클릭되었습니다.", Toast.LENGHT_LONG).show();
   }
});
```

> 이렇게 특정 위젯이 클리되었으면 작동하게 만드는 것이 클릭 리스너이다. 
>
> 하지만 단순히 클릭을 주고 받고 이외에 다른 조건을 넣을 수 없기에 다양한 표현을 하기 어렵다. 

#### 2.2 터치리스너 이용하기 

`View`를 이용한 터치리스너

```java
View view2 = findViewById(R.id.v_button2);

view1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();         // event.getAction()은 리턴 값이 int임을 알 수 있다.

                float curX = event.getX();
                float curY = event.getY();

                if(action == MotionEvent.ACTION_DOWN){
                    println("포인트가 눌렸음" +"x좌표 : "+ curX + " , y좌표 : " + curY );
                } else if ( action == MotionEvent.ACTION_MOVE){
                    println("포인트가 움직임" +"x좌표 : "+ curX + " , y좌표 : " + curY );
                } else if ( action == MotionEvent.ACTION_UP){
                    println("포인트가 떼어짐" +"x좌표 : "+ curX + " , y좌표 : " + curY );
                }
                return true;
            }
        });
```

> 클릭 리스너와 달리 터치 리스너는 매개변수로  MotionEvent도 주어진다. 그렇기 때문에 좌표 등 다양한 조건들을 사용할 수 있다. 

이외 액션 종류 

`ACTION_MASK`, `ACTION_DOWN`, `ACTION_UP`, `ACTION_MOVE`, `ACTION_CANCEL`, `ACTION_OUTSIDE`, `ACTION_POINTER_DOWN`, `ACTION_POINTER_UP`, `ACTION_POINTER_INDEX_MASK`

> 모두 int 값을 리턴한다. 

