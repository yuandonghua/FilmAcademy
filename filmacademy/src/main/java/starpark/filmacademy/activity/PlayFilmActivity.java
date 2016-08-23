package starpark.filmacademy.activity;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import starpark.filmacademy.R;

/**
 * @description:播放视频界面
 * @author:袁东华 created at 2016/8/23 0023 下午 5:59
 */
@ContentView(R.layout.activity_playfilm)
public class PlayFilmActivity extends BaseActivity {
    @ViewInject(R.id.webView)
    private WebView webView;
    private String url="";
    private String title="";

    @Override
    public void receiveIntentData() {
        url=getIntent().getStringExtra("url");
        title=getIntent().getStringExtra("title");

    }

    @Override
    public void initTopView() {
        hideBar(false);
        TextView title_tv= (TextView) findViewById(R.id.title_tv);
        title_tv.setText(title);
    }

    @Override
    public void initData() {
//        webView.setScrollBarStyle(View.SCROLL_AXIS_NONE);
        WebSettings mWebSettings = webView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setSupportZoom(true);
        mWebSettings.setDomStorageEnabled(true);

        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // WebView自适应屏幕大小
        mWebSettings.setDefaultTextEncodingName("UTF-8");
        mWebSettings.setLoadsImagesAutomatically(true);
        // 设置可以支持缩放

        mWebSettings.setSupportZoom(true);

        // 设置默认缩放方式尺寸是far

        mWebSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);

        // 设置出现缩放工具

        mWebSettings.setBuiltInZoomControls(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO Auto-generated method stub
                super.onPageStarted(view, url, favicon);
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);
                return false;
            }

            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                Toast.makeText(activity, "加载失败", Toast.LENGTH_LONG).show();
            }

            // 在加载页面结束时响应
            public void onPageFinished(WebView view, String url) {
            }
        });
    }

    @Override
    public void initListener() {
        ImageView back_iv= (ImageView) findViewById(R.id.back_iv);
        back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void finish() {
        webView.destroy();
        super.finish();

    }
}