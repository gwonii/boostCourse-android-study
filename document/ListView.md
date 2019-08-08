## ListView 구현하기 



### 1. 그려질 판을 만든다. 

#### 1.1 activity_main.xml에 들어갈 listView 보드를 만든다.  

```java
<ListView
        android:id="@+id/listView1"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_below="@id/linearLayout1">
</ListView>
```

> listView는 기본적으로 scroll을 지원한다. 

#### 1.2 리스트의 각 항목에 대한 xml파일을 만든다. 

**singer_item.xml**

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="horizontal"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <ImageView
        android:id="@+id/iv_profile"
        android:layout_width="84dp"
        android:layout_height="84dp"
        android:layout_marginTop="8dp"
        android:layout_marginLeft="8dp"
        android:src="@mipmap/ic_launcher"/>

    <LinearLayout
        android:id="@+id/linearLayout2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <TextView
            android:id="@+id/tv_name"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="4dp"
            android:text="이름"
            android:textSize="30sp"
            android:textColor="@android:color/darker_gray"/>


        <TextView
            android:id="@+id/tv_phoneNum"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="8dp"
            android:layout_marginBottom="4dp"
            android:text="전화번호"
            android:textColor="@android:color/darker_gray"
            android:textSize="25sp" />

    </LinearLayout>

</LinearLayout>
```

> 위는 각 리스트의 항목에 이미지, 텍스트1, 텍스트2가 들어가는 형태이다. 

### 2. inflater를 위한 class를 만든다. 

**SingerItemView**.java

```
public class SingerItemView extends LinearLayout {

    TextView textView1;
    TextView textView2;
    ImageView imageView1;

    public SingerItemView(Context context) {
        super(context);

        init(context);
    }

    public SingerItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.singer_item, this, true);

        textView1 = findViewById(R.id.tv_name);
        textView2 = findViewById(R.id.tv_phoneNum);
        imageView1 = findViewById(R.id.iv_profile);
    }

    public void setName(String string){
        textView1.setText(string);
    }
    public void setPhoneNum(String string){
        textView2.setText(string);
    }

    public void setImage(int imgResource){
        imageView1.setImageResource(imgResource);
    }
}
```

> 1. SingerItemView 클래스는 LinearLayout을 상속받는다. ( 이후에 LinearLayout으로 그려질 것이기 때문이다. 이전 singer_itme.xml파일의 최상단 레이아웃)
> 2. inflater객체를 생성하고 xml을 파일을 위 클래스로 인플레이트한다.



**기본 생성자**

```java
    public SingerItemView(Context context) {
        super(context);

        init(context);
    }

    public SingerItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }
```

> 인플레이터를 위한 클래스를 만들려고 할 때, 기본적으로 위 두 가지의 생성자를 만들어야 한다. 

### 3. 데이터를 저장할 클래스를 만든다. 

#### 3.1 데이터만이 들어갈 저장 클래스 SingerItem 클래스를 만든다. 

```java
public class SingerItem {

    private String name;
    private String phoneNum;
    private int imgResource;

    public SingerItem(String name, String phoneNum, int imgResource) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.imgResource = imgResource;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public int getImgResource() {
        return imgResource;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }

    @Override
    public String toString() {
        return "SingerItem{" +
                "name='" + name + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", imgResource=" + imgResource +
                '}';
    }
}
```

> 위 코드는 각 데이터 항목들을 ArrayList로 저장시켜놓기 위한 데이터 저장용 클래스로 사용된다. 

### 4. listView를 관리할 adapter를 만든다. 

#### 4.1 BaseAdapter를 상속받는 SingerAdapter

```java
class SingerAdapter extends BaseAdapter {

        ArrayList<SingerItem> items = new ArrayList<>();        // ArrayList에 저장해 놓을 데이터 묶음을 만든다.

        public void addItem(SingerItem item){
            items.add(item);
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SingerItemView view = new SingerItemView(getApplicationContext());

            SingerItem item = items.get(position);
            view.setName(item.getName());
            view.setPhoneNum(item.getPhoneNum());
            view.setImage(item.getImgResource());

            return view;
        }
    }
```

> 강의에서도 말했듯이 listView같은 경우 어댑터를 통해 데이터를 관리하고 데이터를 보여지게 할 수 있다. 위 형식을 잘 익혀두라 하였다. 

### 5. listView를 새로 만든 adapter에 연결시킨다. 

#### 5.1 연결시키기 전에 adapter에 필요한 정보들을 입력한다. 

```java
adapter.addItem(new SingerItem("소녀시대", "010-0000-1111",R.drawable.google));
adapter.addItem(new SingerItem("걸스데이", "010-1111-2222",R.drawable.google));
adapter.addItem(new SingerItem("다이아", "010-2222-3333", R.drawable.naver));
adapter.addItem(new SingerItem("원더걸스", "010-3333-4444",R.drawable.google));
adapter.addItem(new SingerItem("아이오아이", "010-4444-5555", R.drawable.kakao));
```

> 실질적인 데이터를 넣어준다. 

#### 5.2 listView와 SingerAdapter를 연결시킨다. 

```java
listView = findViewById(R.id.listView1);
SingerAdapter adapter = new SingerAdapter();

// ~  // 데이터를 넣는 공간 

listView.setAdpater(adapter)l
```

