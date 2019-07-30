# PTJ2 CodeReview



## 1. setText 수정하기 

**요구사항**

* setText의 매개변수에는 String이 들어올 수 있도록 만든다. `"" + (unlikeNum - 1)` 사용금지 



**변경 전**

```java
bt_like.setOnClickListener(new View.OnClickListener() {
	@Override
	public void onClick(View v) {
		if (!bt_like.isActivated()) {
            if (bt_unlike.isActivated()) {
                bt_unlike.setActivated(false);
                int unlikeNum = Integer.parseInt(tv_unlikeNum.getText().toString());
                tv_unlikeNum.setText("" + (unlikeNum - 1));
            }
         }
    }
});
```

> `setText`의 매개변수가 현재 "" + ~~의 형식으로 String이 아니지만 String의 형식으로 해석하달라고 컴퓨터에게 요청되어 있는 상태이다. 
>
> 이것을 String으로 사전에 변환시켜 놓고 setText의 매개변수에는 String이 올 수 있도록 한다. 



**변경 후**

```java
bt_like.setOnClickListener(new View.OnClickListener() {
	@Override
	public void onClick(View v) {
		if (!bt_like.isActivated()) {
            if (bt_unlike.isActivated()) {
                bt_unlike.setActivated(false);
                        int unlikeNum = Integer.parseInt(tv_unlikeNum.getText().toString());
                        tv_unlikeNum.setText(substractAndToString(unlikeNum));

                        bt_like.setActivated(true);
                        int likeNum = Integer.parseInt(tv_likeNum.getText().toString());
                        tv_likeNum.setText(addAndToString(likeNum));
            }
         }
    }
});

private String substractAndToString(int num){
   String substractedNum;
   substractedNum = Integer.toString(num - 1);
   return substractedNum;
}
private String addAndToString(int num){
   String addedNum;
   addedNum = Integer.toString(num + 1);
   return addedNum;
}
```

> 먼저 setText의 매개변수에 String을 직접 넣어주기위하여 String으로 변환해주는 메소드를 새로 만들었다. 그리고 String으로 변환해주면서 값도 + 또는 - 하도록 만들어 코드의 중복을 없앴다. 



## 2. src로 이미지파일 변경하기 

**요구사항** 

* setBackground를 사용해서 이미지를 변경하지 말아라 

**현재 상황**

현재는 새롭게 xml파일을 만들어서 이미지를 변경하였다. 그렇지만 java코드에서 이미지가 변경하고 싶다면 밑의 다른 방법들을 사용해 볼 것!

### 2.1 대안 1

**setBackgroundSource 사용하기 **

```java
// 변경 전 
ImageView imageView;
imageView.setBackground(@drawable/ic_15);

// 변경 후 
ImageView imageView;
imageView.setBackgroundResource(@drawable/ic_15);

// xml에서도 마찬가지로 직접 변경이 가능하다. 
```



### 2.2 대안 2 

**src를 이용해서 이미지 변경하기**

```java
// ImageView의 경우 
<ImageView 
	android:layout_width="wrap_content"
	android:layout_height="wrap_content"
	android:src="@drawable/ic_15">
	
// Button의 경우 
<Button
	android:layout_width="wrap_content"
	android:layout_height="warp_conent"
	android:src="@drawable/ic_15">
	
// java파일에서도 setResource를 통해 바꿀 수 있다. 
```

## 

## 3. text처리하기 

**요구사항**

* text로 지정되는 내용들은 values의 strings.xml을 통해서 관리할 것 
* 그러면 나중에 유지/보수에 용이하다.

**변경 전** 

```java
<TextView
	android:id="@+id/tvFirstPage"
	android:layout_width="wrap_content"
	android:layout_height="wrap_content"
	android:text="긴 말이 들어갈 수도 있어요"/>
```

**변경 후**

```java
<TextView
	android:id="@+id/tvFirstPage"
	android:layout_width="wrap_content"
	android:layout_height="wrap_content"
	android:text="@drawable/story"/>
	
// values > strings.xml 
<resource>
	<string name="story">긴 말이 들어갈 수도 있어요</string>
</resource>
```

> 이런식으로 strings.xml에서 모든 text들을 관리하도록 한다. 



