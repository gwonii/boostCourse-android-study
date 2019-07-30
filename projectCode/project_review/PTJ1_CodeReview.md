# PTJ1 CodeReview

## 1. app/src/main/AndroidManifest.xml

```java
 <application
        android:allowBackup="true"
        android:icon="@drawable/ic_app"
        android:label="씨네마천국"
            // 컨텐츠 내용을 넣을 때는 strings.xml을 이용할 것 
        android:roundIcon="@drawable/ic_app"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity" android:label ="씨네마천국">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
```

> 위 처럼 label등에 이름을 넣을 때는 strings.xml을 이용하여 참조할 것 ! 
>
> 그래야 나중에 변경사항이 생길 때도 쉽게 변경이 가능하다. 

## 2. app/src/main/res/layout/activity_main.xml

### 2.1  레이어별 주석처리 

* 여러 가지 레이어를 사용할 때에는 의미있는 레이어를 구분하여 주석을 추가해놓을 것! 그래야 가독성이 훨씬 높아진다. 

### 2. 2 id의 naming

```java
<TextView
    android:id="@+id/textView4"
        // id를 표기할 땐 헝가리언 표기법과 snake case를 혼용할 것 
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginStart="16dp"
    android:layout_marginLeft="16dp"
    android:layout_marginTop="40dp"
    android:layout_marginBottom="16dp"
    android:text="군도"
    android:textColor="@android:color/white"
    android:textSize="24sp"
    android:textStyle="bold"
    app:layout_constraintBottom_toTopOf="@+id/textView5"
    app:layout_constraintStart_toEndOf="@+id/img_poster"
    app:layout_constraintTop_toTopOf="@+id/img_poster" />
```

> 위 처럼 id를 설정할 때에는 
>
> ex) 
>
> 1. tv_title
> 2. iv_age 등등
>
> 처럼 헝가리언 표기법과 snake case를 혼용해야 한다. 그래야 나중에 java 코드를 작성할 때에도 가독성에 큰 영향을 끼친다.  

### 2.3 strings.xml의 사용 

```java
android:text="군도, 백성을 구하라 \n양반과 탐관오리들의 착취가 극에 달했던 조선 철종 13년.\n힘 없는 백성의 편이 되어 세상을 바로잡고자 하는 의적떼인 군도(群盜), 지리산 추설이 있었다."
```

> 위 처럼 텍스트 내용을 사용할 때에는 strings.xml을 이용하여 참조하라! 
>
> 그래야 나중에 수정, 유지보수가 훨씬 수월해진다. 