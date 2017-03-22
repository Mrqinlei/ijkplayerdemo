最终效果:

![app 最终效果图](http://upload-images.jianshu.io/upload_images/2235135-1fba8f0c9fe632e6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

###有两种方法集成ijkplayer

第一种 编译源码(第一种没有尝试)
第二种 直接添加官方的依赖(本文采用)

###以下介绍的是第二种方法:

####第一步

	//必须导入的,足够大部分设备  
	compile 'tv.danmaku.ijk.media:ijkplayer-java:0.5.1'  
    compile 'tv.danmaku.ijk.media:ijkplayer-armv7a:0.5.1'  
	//不同cpu平台,可选(这里在导入arm64和x86_64出错了就没有导入)  
	compile 'tv.danmaku.ijk.media:ijkplayer-armv5:0.5.1'  
    compile 'tv.danmaku.ijk.media:ijkplayer-arm64:0.5.1'  
    compile 'tv.danmaku.ijk.media:ijkplayer-x86:0.5.1'  
    compile 'tv.danmaku.ijk.media:ijkplayer-x86_64:0.5.1'  
	//ExoPlayer as IMediaPlayer: optional, experimental(还不知道有啥用)  
    compile 'tv.danmaku.ijk.media:ijkplayer-exo:0.7.8.1'

最终我的 gradle 配置:
![gradle 配置](http://upload-images.jianshu.io/upload_images/2235135-e2815aef06ad29d9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

####第二步

下载 ijkplayer 源码,添加 ijkplayer 的源码.添加目录如下

     ijkplayer\android\ijkplayer\ijkplayer-example 
红色框中的在application文件夹中 
![ijkplayer 源码](http://upload-images.jianshu.io/upload_images/2235135-86b933c6de65a833.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

####第三步
添加 layout string 资源,消除代码中的错误

![layout资源](http://upload-images.jianshu.io/upload_images/2235135-124903bc0de4ce54.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![string资源](http://upload-images.jianshu.io/upload_images/2235135-19e7d3839c335b38.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

####第四步

最后layout如下中:

	<?xml version="1.0" encoding="utf-8"?>
	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
	    android:orientation="vertical">
	
	    <com.qinlei.myapplication.media.IjkVideoView
	        android:id="@+id/video_view"
	        android:layout_width="match_parent"
	        android:layout_height="match_parent"
	        android:layout_gravity="center" />
	
	    <TableLayout
	        android:id="@+id/hud_view"
	        android:layout_width="wrap_content"
	        android:layout_height="wrap_content"
	        android:layout_gravity="right|center_vertical"
	        android:padding="8dp" />
	</LinearLayout>

Activity中

	public class MainActivity extends AppCompatActivity {
	    private String mVideoPath = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
	
	    private TableLayout mHudView;
	    private IjkVideoView mVideoView;
	
	
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        mHudView = (TableLayout) findViewById(R.id.hud_view);
	
	        mVideoView = (IjkVideoView) findViewById(R.id.video_view);
	        mVideoView.setHudView(mHudView);
	        // init player
	        IjkMediaPlayer.loadLibrariesOnce(null);
	        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
	
	        mVideoView.setVideoURI(Uri.parse(mVideoPath));
	
	        mVideoView.start();
	
	    }
	}

参考: http://www.jianshu.com/p/a4eea7ea4664#