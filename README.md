# EIMUI 一个简单，美观，易维护，易拓展的IMUI解决方案

## 致谢
从17年开始接触IM即时通讯项目开始，陆续的看了几家的即时通讯方案，作为移动端这里面包括有融云、QQ、极光、环信、网易等等。真正看了UI源码的有：

 - QQ，代码的内在精神是在的，但是不能直接用，需要大量的修改封装。
 - 融云，代码封装层次多，阅读难度较大，采取动态注入View的方式进行多类型消息支持。
 - 极光，代码简单好读，作为参考有一定价值，多类型Holder写的有点啰嗦。发出来的Aurora 渲染图很好看，不知道是不是我是安卓版的原因，安装上之后感觉有点失望。

这里对开源的前辈致敬，你们的项目给了我很多启发。

## 初衷

抛开长连接接收消息这块不谈，作为移动端的我们主要做的业务就是 **Message List消息列表** 和 **Input Bar 输入条** 相关页面以及业务的开发，尤其是很多公司会把即时通讯业务变成产品进行出售，那么友好的修改聊天页面样式，以及可以动态的支持不同协议和数据结构就成为了刚需。这也是我根据其他框架再次封装EIMUI的初衷。

[**EIMUI一个简单，美观，易维护，易拓展的IMUI解决方案。**](https://github.com/JustGank/EIMUI)


解决方案还包括：

[EMedia-支撑相册、拍照、录像、文件选取、以及Result解析功能。](https://github.com/JustGank/EMedia)

[EMpeRecord-MP3音频录制，播放工具。](https://github.com/JustGank/EMp3Record)

## 效果图

从左到右依次为：文字消息、图片消息、短视频消息、语音消息。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200527094735511.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTA0NTE5OTA=,size_16,color_FFFFFF,t_70#pic_center)

从左到右依次为：地理位置消息，文件消息（支持下载上传进度条），以及异常状态消息，Input Bar的更多操作。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200527094915852.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTA0NTE5OTA=,size_16,color_FFFFFF,t_70#pic_center)

效果上大家可以放心，以上都是真机截图，所以你看到是什么样拿到的就是什么样。

## 一、框架初始化

```java
EIMUI.INSTANCE.init(getApplicationContext(),"EIMUI");
```
这里面后面的这个参数是缓存所在的文件夹。

```java
 	private String saveFilePath = Environment.getExternalStorageDirectory().getPath();
 	//图片缓存的位置
    private String takePhotoSavePath = saveFilePath + File.separator + "Images";
    //视频缓存的位置
    private String takeVideoSavePath = saveFilePath + File.separator + "Videos";
    //音频缓存的位置
    private String cacheDirPathAudio = saveFilePath + File.separator + "Audios";
    //压缩图片的位置
    private String cacheDirPathCompress = saveFilePath + File.separator + "CompressedImages";
    //文件的位置
    private String cacheDirPathFile = saveFilePath + File.separator + "Files";

    private Context context;

    public void init(Context context, String path) {
        this.context = context;
        setSaveFolderPath(path);
    }

    private void setSaveFolderPath(String path) {
        this.saveFilePath = path;
        new File(takePhotoSavePath).mkdirs();
        new File(takePhotoSavePath).mkdirs();
        new File(cacheDirPathAudio).mkdirs();
        new File(cacheDirPathCompress).mkdirs();
        new File(cacheDirPathFile).mkdirs();
    }
```

## 二、Message List的使用

### 1.1 设计思路

一般来说一个消息是由如下内容组成的：

 1. 消息的ID和创建时间
 2. 消息的接收者和发送者信息
 3. 消息的内容
 4. 消息的状态

以上4个部分，1、2、4对于我们来说大多数时候是固定的，他们主要的主要体现就是

 - 消息头，如消息的发送时间 或提示性内容 
 - 收发双方的个人信息展示如头像和昵称  
 - 消息的状态。是发送成功还是发送失败

而3部分一般是变化最大的，因为消息类型是多样的，同时显示方式也是有多种策略的。

所以由以上条件，我们将1、2、4部分变成父类 **MessageViewHolderBase**，让他来解决这些固定的业务，然后对子类开放注入容器。

3部分我们通过继承的方式，重写父类的抽象方法 **bindDateToChild** ，然后具体实现布局操作，在完成子View和数据绑定工作后，注入到父类容器中，进而达到显示某种类型消息的目的。

### 1.2 **这样做的好处**

 1. 从设计上来说，所有的子类都是等价的只是布局不同。
 2. 子类彼此独立，业务上彻底解耦，彼此不可见，不影响，可以多人同时并行开发。
 3. 通用部分由父类统一管理，通用布局有问题，只需要修改父类即可。
 4. 所有的子类都可以高度的自定义，以及独立的制定显示策略。

### 1.3 举个例子 VideoMessage 是如何实现的

```java
public class ViewHolderVideoMessage<MESSAGE extends EMessage> extends MessageViewHolderBase {

    public ImageView item_chat_video_cover;
    public ImageView item_chat_video_player;

    private int imageWidth, imageHeight;

    public ViewHolderVideoMessage(Context context, @NonNull View itemView) {
        super(context, itemView);
        imageWidth = ScreenUtil.dip2px(context, 120);
        imageHeight = ScreenUtil.dip2px(context, 180);
    }

    @Override
    public void bindDateToChild(EMessage data, ViewGroup mineContainer, ViewGroup otherContainer) {
        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.view_message_video, null);
        item_chat_video_cover = (ImageView) relativeLayout.findViewById(R.id.item_chat_video_cover);
        Glide.with(context).load(data.getMediaFilePath()).centerCrop().into(item_chat_video_cover);
        item_chat_video_player = (ImageView) relativeLayout.findViewById(R.id.item_chat_video_player);

        item_chat_video_cover.setOnClickListener(this);
        item_chat_video_cover.setOnLongClickListener(this);

        item_chat_video_player.setOnClickListener(this);
        item_chat_video_player.setOnLongClickListener(this);

        if (MessageType.isReceivedMessage(data.getMessageType())) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
            otherContainer.addView(relativeLayout, layoutParams);
        } else {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(imageHeight, imageWidth);
            mineContainer.addView(relativeLayout, layoutParams);
        }

    }
}
```

是不是感觉代码很熟悉，好像回到了写Activity后findViewById的情形。其实目的就是让开发者，可以更专注于特定消息类型的UI开发上。在子类的实现阶段，你只需要inflate出布局，然后进行控件与数据绑定，在根据需求制定显示策略然后注入到容器就好了。


### 1.4 如何实现自定义消息
前面的好处说过了 **从设计上来说，所有的子类都是等价的只是布局不同。**
所以在拓展的时候，任何一个预置的ViewHolder都可以作为参考。只要按照他的模式开发就好了。

### 1.5 我写的自定义消息如何让框架知道
这里我为大家预置了一个MessageType与ViewHolder的映射单例工具类。只需要动态的注册一下就可以了。

```java
public enum HolderClassManager implements MessageType {

    INSTANCE;
    private static final String TAG = "HolderClassManager";
    HashMap<Integer, Class<? extends MessageViewHolderBase>> classMap;
    private HolderClassManager() {
        classMap = new HashMap<>();
        ....
        classMap.put(SEND_VIDEO, ViewHolderVideoMessage.class);
        classMap.put(RECEIVE_VIDEO, ViewHolderVideoMessage.class);
        ....
    }

	//拿到Map后自己加
    public HashMap<Integer, Class<? extends MessageViewHolderBase>> getClassMap() {
        return classMap;
    }

    public Class<? extends MessageViewHolderBase> getViewHolderClass(int messageType) {
        Class<? extends MessageViewHolderBase> holderClass = classMap.get(messageType);
        if (holderClass == null) {
            Log.e(TAG, "getViewHolderClass is null");
            holderClass = ViewHolderSendErrorMessage.class;
        }
        return holderClass;
    }
}

```

**注：目前内置的类型占用了1-14，所以使用的时候要注意覆盖的问题。**

预定义的类型：

```java
    int SEND_TEXT = 1;
    int RECEIVE_TEXT = 2;

    int SEND_IMAGE = 3;
    int RECEIVE_IMAGE = 4;

    int SEND_VOICE = 5;
    int RECEIVE_VOICE = 6;

    int SEND_VIDEO = 7;
    int RECEIVE_VIDEO = 8;

    int SEND_FILE = 9;
    int RECEIVE_FILE = 10;

    int SEND_LOCATION = 11;
    int RECEIVE_LOCATION = 12;

    int SEND_FAIL_MESSAGE = 13;
    int RECEIVE_REDOWNLOAD = 14;
```


### 1.6 预置不满足怎么办
可能有追求的小伙伴会问，哎你这个还行，但是和我的要求还是差点，这怎么办。其实很简单在效果图部分的UI展示只是预置的，方便对UI要求不大的小伙伴直接拿来用。而有追求的小伙伴，解决你的需求也很简单。

还是以 Video Message 为例，你只要继承 ViewHolderVideoMessage，后重写bindDateToChild就可以啦。想怎么设计就怎么设计，是不是感觉棒棒哒。

### 1.7 EMessageAdapter和OperationListener
EMessageAdapter是和消息列表相绑定的适配器，里面的代码很少，主要是根据消息类型反射出对应的Holder实例。然后由Adapter进行显示和管理。

一般来说：

```java
adapter = new EMessageAdapter(getContext(), new ArrayList<>(),mine,other);
recyclerView.setAdapter(adapter);
adapter.setOperationListener(operationListener);
```
这样就完成了RecyclerView和Adapter的绑定了。

OperationListener是点击事件和长按事件的接口管理类，没有采用接口的方式实现，而是采用了类方式实现，这样可以只重写有需要的回调，减少不必要的方法重写。

### 1.8 当然也支持选择模式

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200527105736629.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTA0NTE5OTA=,size_16,color_FFFFFF,t_70#pic_center)

只需要调用 **EMessageAdapter#public void setSelectedMode(boolean isSelectedMode)** 就可以了。

## 三、Input Bar的使用
### 1.1 设计思路
这部分的主要设计思想是**面向接口编程**，面向接口编程主要解决的是两个问题一个是更多操作的可自定义性，另一个是滑动录音动画的灵活性。

由于Input Bar这个部分会触发和其他页面的交互，如点击更多操作的文件快捷选取等。一般来说，一个项目或者部门公司都会有自己的工具类，来完成以上工作，如果这里写死那么显然是不利于拓展的。

所以这里先定义了两个接口分别来处理更多操作，和点击滑动动画。

更多操作的接口：
```java
public interface Operation {

    public boolean previewOperate();

    public void operate(View v, int position, Activity activity);

    public int getRequestCode();

}
```

录音时动画接口：
```java
public interface RecordStateView {

    public void Show();

    public void normalRecord();

    public void cancelRecord();

    public void dismiss();

    public int currentState();

    public void nextState();

}
```




### 1.2 这样做的好处

 1.  **面向接口编程**，高度自定义话，更多操作选择的工具类不在受限。
 2.  所有的更多操作等价，可以灵活的加减或改变位置。
 3.  对于录音动画的触发器，不在和录音动画的位置和形式产生关联，让动画模块，可以高度自定义，不用担心显示位置和显示区域。
 4. 入侵性低，即使后期改变了工具类的类型或者更换动画，依然不会对现有代码产生多大影响。



### 1.3 举个例子如何编辑操作
先定义一个我们的操作，继承Operation 
```java
public class PickFileOperation implements Operation {

    @Override
    public boolean previewOperate() {
        return true;
    }

    @Override
    public void operate(View v, int position, Activity activity) {
        if (!previewOperate()) {
            return;
        }
        IntentUtil.openFileManager(activity, EIMConstant.REQUEST_CODE_PICK_FILE);
    }

    @Override
    public int getRequestCode() {
        return EIMConstant.REQUEST_CODE_PICK_FILE;
    }

}
```
然后将操作实体变成参数和数据对象一起传给UI：

```java
//初始化inputBar的更多操作
        List<ChatMoreBean> chatMoreBeans = new ArrayList<>();
        //使用默认Operation
        chatMoreBeans.add(new ChatMoreBean(R.mipmap.chat_pick_pic, getString(R.string.chat_pick_pic), new PickPicOperation()));
        chatMoreBeans.add(new ChatMoreBean(R.mipmap.chat_take_photo, getString(R.string.chat_take_photo), new TakePhotoOperation()));
        chatMoreBeans.add(new ChatMoreBean(R.mipmap.chat_take_video, getString(R.string.chat_take_video), new TakeVideoOperation()));
        chatMoreBeans.add(new ChatMoreBean(R.mipmap.chat_pick_file, getString(R.string.chat_file), new PickFileOperation()));
        //自定义Operation
        chatMoreBeans.add(new ChatMoreBean(R.mipmap.chat_location, getString(R.string.chat_location), new LocationOperation()));

        //设置更多操作
        inputBarMoreDefaultAdapter = new InputBarMoreDefaultAdapter(chatMoreBeans, this);
        inputbar.getMorePanel().setLayoutManager(new GridLayoutManager(this, 4));
        inputbar.getMorePanel().setAdapter(inputBarMoreDefaultAdapter);
```

这样无论是我们更改顺序，还是动态的修改更换工具类，都不在会对现有代码产生多少影响。
添加或改变顺序，只需要控制 List<ChatMoreBean>就可以了。
而更换工具类，主需要自定义Operation 就可以了。

**注：这里的Operation实现是基于EMedia的，默认实现了相册、照相、录像、选择文件等操作。**

### 1.4 举个例子如何自定义录音动画

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200527112912553.gif#pic_center)
录音动画部分，帮大家完成了滑动动作监听的**RecordTouchListener**，他的内部开放了对外的UI调用接口。

所以我们的自定义动画，只需要实现

```java
public interface RecordStateView {

    public void Show();

    public void normalRecord();

    public void cancelRecord();

    public void dismiss();

    public int currentState();

    public void nextState();

}

```
接口就可以了。演示的动画可以参考：**AudioRecordStateView**

### 1.5 如何控制Input Bar 的左右按钮
这里面左右各为大家开放了3个ImageView的坑位，方便大家做拓展。不过其中左边第一个和右边第一个是保留坑位，用于实现输入法的切换，和控制更多操作。

至于其他的坑位默认是不显示的，当我们需要使用时，可以 **InputBarBuilder** 来设置资源ID。
Builder的控制逻辑是，对于没设置资源的ImageView默认是不显示的，只有设置了显示。

那么我们可以通过动态的设置对应位置的资源来控制按钮是否显示如：

```java
    //设置InputBar 子控件监听 增加一个自定义控件
    inputbar.setInputBarBuilder(InputBarBuilder.getNewInstance().setRight_img2_res(R.mipmap.chat_inputbar_template));
```

### 1.6 添加好的按钮如何监听
这里我们可以为Input Bar 设置他的内置接口管理类 **OnItemClickListener** 里面包括了常见的控制操作，他也采用类的形式，让大家可以只回调自己关心的业务接口。

```java

inputbar.setOnItemClickListener(onItemClickListener);

InputBar.OnItemClickListener onItemClickListener = new InputBar.OnItemClickListener() {
        @Override
        public void onSendClicked(String content) {
            super.onSendClicked(content);
            ToastUtils.showMessage(ChatActivity.this, "您点击了发送");
            inputbar.getEdittext().setText("");
        }

        @Override
        public void onRightImg2Clicked(ImageView img) {
            super.onRightImg2Clicked(img);
            ToastUtils.showMessage(ChatActivity.this, "为您自动装载聊天实例");
        }
    };
```

## 结语
整体来说，EIMUI只是在内部框架的基础上，为大家做了一些默认实现。当默认实现不满足时，**大家可以通过继承或者自定义的形式重新开发**来支持业务。由于代码的整体解耦，让我们可以专心的处理要处理的问题，如只是某种类型的消息不支持，那么我只改动对应的消息就可以了。

## 关于Emoji
Emoji的常规做法是，自己设计一套Emoji表情，然后发送特定的字符串，当解析发现是Emoji时，将图片设置到SpannableString 来实现。不过现在来说，Emoji已经是一套标准了，如果在和服务器端的交互中，大家统一编码如UTF-8的情况下，移动端是可以自动识别显示输入法输入的Emoji的，当然不同的手机可能显示不太一样（表达的意思是一样的），所以这部分感觉没有必要过分去追求一致性。
