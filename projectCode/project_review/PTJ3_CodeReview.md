# PTJ3  CodeReview



## 1. Adapter를 static으로 선언하지 말 것 

기존에 새로 리뷰를 작성하면 그 내용을 메인 액티비티에 보여주기 위하여 메인 액티비티에서 adapter를 static 변수로 설정하였다. 

**코드 변경 전**

```java
/** MainActivity.class

**/

public class MainActivity extends AppCompatActivity {
    
 // 변수 선언 
    public static CommentAdapter adapter;
   ~~~
       
}

```

> 원초적인 생각으로, 리스트뷰는 어댑터에서 관리를 하니까 어댑터의 정보를 다른 액티비티에서도 사용하게 할 수 있도록 static 변수로 지정하였다. 그런데 이러면 나중에 **동기의 문제**가 생길 수도 있고 잘못하면 **메모리 누수**가 생길 수도 있다.  

<br/>

```java
/** DoCommentActivity.class
	코멘트를 작성할 수 있는 액티비티 창 	
**/

import static com.gmail.hc.gwnoii.mymovie_11.controller.MainActivity.adapter;
    
    
private void addComment(String comment) {
        adapter.addItem(new CommentItem("anonymouse", "20분 전", comment,"1"));
    }
```

> 그런 후에 다른 액티비티에서 adater변수를 사용하기 위하여 `addComment()`의 메소드를 사용하여 `adapter.addItem(new CommentItem("anonymouse", "20분 전", comment,"1"))` `MainActivity.class`에 있는 adapter변수에 직접 접근하여 사용하였다. 
>
> 그러나~ static 변수를 이용하여 데이터를 조작하는 것은 위에서 말했듯이 굉장히 위험한 상황이 생길 수도 있음! 

<br/>

**코드 변경 후**

```java
/** MainActivity.class **/

public class MainActivity extends AppCompatActivity {
    
    // 변수 선언 
    private CommentAdapter adapter;
    
    // 작성하기 버튼
	tvWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DoCommentActivity.class);

                startActivityForResult(intent, DO_COMMENT_ACTIVITY_REQUEST_CODE);
            }
        });
   	
    // 모든 리스트 보기 버튼
    ivAllView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CommentListActivity.class);

                ArrayList<CommentItem> passDatas = adapter.getCommentItems();

                intent.putParcelableArrayListExtra("commentItems", passDatas);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_SINGLE_TOP
                        | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
}

```

> 기존의 `adapter`를 공유하는 방식에서 `DoCommentActivity`와 `CommentListAcivity` 에게 직접 데이터를 전달하는 방식으로 전체적으로 수정하였다. 
>
> 핵심적인 내용은 **adapter를 통한 리스트의 수정은 `MainActivity`에서만 실시된다는 것이다.**
>
> **MainActivity**
>
> 1. 작성하기 버튼 (`MainActivity` --> `DoCommentActivity`) 
>
>    `DoCommentActivity`에게 전달하는 데이터는 따로 있지 않고, `DoCommentActivity`에서 작성한 정보를 `MainActivity`에서 받아야 하기 때문에 `startActivityForResult()`로 액티비티를 실행시킨다.  
>
> 2. 모든 리스트 보기 버튼 (`MainActivity --> CommentListActivity`)
>
>    `CommentListActivity`에서는 모든 리스트를 보여줘야 하기 때문에 adapter의 정보가 필요하다. 그런데 adapter는 `MainActivity`에서만 수정할 수 있다면 어떻게 `CommentListActivity`에서 모든 리스트를 보여줄 수 있을까? 
>
>    `CommentListActivity`에는 `ArrayList<CommentItem> passDatas = adapter.getCommentItems()` 형태로 데이터를 전달한다. 전달할 ArrayList를 정의하고, `intent.putParcelableArrayListExtra("commentItems", passDatas)`를 통해 데이터를 전달한다.

<br/>

```java
/** CommentItem **/

public class CommentItem implements Parcelable
```

> `CommentItem`은 `Parcelable` 인터페이스를 상속받게 만들어 `Parcel`의 형태로 데이터를 전달하는 것이다.  

<br/>

```java
/** DoCommentActivity **/

public class DoCommentActivity extends AppCompatActivity {
 
    // 작성하기 버튼
    saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                String comment = commentText.getText().toString();
                CommentItem newComment = new CommentItem("anonymous", "20분 전", comment,"1");

                intent.putExtra("commentItem", newComment);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    
    
}
```

> `DoCommentActivity`에서는 `String comment = commentText.getText().toString()` 를 통해 작성한 내용을 변수에 저장시킨다. 
>
> 그 후 `CommentItem newComment = new CommentItem("anonymous", "20분 전", comment,"1")` 그 내용을 기반으로 새로운 `CommentItem` 객체를 생성한다.  
>
> 그리고 `intent.putExtra("commentItem", newComment)`와 `setResult(Activity.RESULT_OK, intent)` 을 통해 `MainActivity`에게 데이터를 다시 전달한다. 

<br/>

```java
/** MainActivity **/

public class DoCommentActivity extends AppCompatActivity {

    ~~
    
    // startActivityForResult()의 result메소드 
   	@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case DO_COMMENT_ACTIVITY_REQUEST_CODE: {
                if (data != null) {
                    CommentItem newComment = data.getParcelableExtra("commentItem");

                    addCommentItem(newComment);
                }
            }
            break;
        }
    }
    
        // 새로운 코멘트를 추가하는 메소드 
    private void addCommentItem(CommentItem commentItem) {
        adapter.addItem(commentItem);
    }
}
```

> 이제 `onActivityResult()` 메소드를 사용하여 `DoCommentActivity`에서 작성한 내용을 전달받는다. 
>
> 그리고 `addCommentItem()` 메소드를 이용해서 새로운 코멘트를 추가시킨다. 

<br/>

```java
/** CommentListActivity **/

public class CommentListActivity extends AppCompatActivity {
 
    // 변수 선언 
    private CommentAdapter adapter = new CommentAdapter(this);
    private ArrayList<CommentItem> commentItems;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent passedIntent = getIntent();
        processIntent(passedIntent);

        for (int i = 0; i < commentItems.size(); i++) {
            adapter.addItem(commentItems.get(i));
        }

        allListView.setAdapter(adapter);
    }
    
        private void processIntent(Intent passedIntent) {
        commentItems = passedIntent.getParcelableArrayListExtra("commentItems");
    }
}
```

> 마지막으로 전체 코멘트 리스트를 보기 위하여 `CommentListActivity`에서 모든 리스트를 보여준다. 모든 리스트를 보여주기 위해서는 `adapter` 있어야 하는데 `MainActivity`에게 직접 `adapter`정보를 받을 수 없기 때문에 새롭게 `private CommentAdapter adapter = new CommentAdapter(this)`와 `private ArrayList<CommentItem> commentItems` 를 선언해준다. 
>
> 그리고 `MainActivity`로부터 받은 정보를 새로 선언한  adapter에 저장시켜준다. 
>
> ```java
> Intent passedIntent = getIntent();
> processIntent(passedIntent);
> 
> for (int i = 0; i < commentItems.size(); i++) {
>      adapter.addItem(commentItems.get(i));
> }
> ```
>
> `MainActivity`에서 온 정보를 `processIntent()`메소드를 통해 ArrayList에 저장시키고 저장된 ArrayList를 `CommentListActivity`에 있는 `adapter`에 새롭게 저장시켜 주는 것이다. 

`새로운 adapter` --> `전달 받은 ArrayList<CommentItem>정보` --> `새로운 adapter에 전달받은 ArrayList정보 주입` 

<br/>

## 2. 위 변경사항의 추가적인 예상 문제점 

### 2.1 새로운 adapter생성 

위에서 볼 수 있듯이 `DoCommentActivity`가 켜질 때 마다 `private CommentAdapter adapter = new CommentAdapter(this)` 를 통해 새로운 `CommentAdapter`객체를 생성시킨다. 

위 처럼 계속 객체를 생성하는 형태가 아닌, 객체를 재활용할 수 있는 방법이 존재할 것으로 예상된다. 

<br/>

### 2.2 `putParcelableListExtra`의 문제점 

리뷰에서 리뷰어님께서 현재 `CommentItem`을 ArrayList의 형태로 전달하는 것을 추천하셨으나, 위의 방식을 이용하게 되면 List의 길이가 제한적이기 때문에 다각도로 사용할 수 없다고 하신다. 