package lenovo.agriculture.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class SplashActivity extends BaseActivity {

    private static final String FILE_NAME="first";
    private static final int ENTER_SPLASH =0x00 ;
    private static final int ENTER_HOME = 0x01;
    private SharedPreferences mPrefrence;
    private TextView mSoftwareVersionTV;
    private Handler mHandler;
    private int waitTime = 3*1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        getPreferences();
        handleMessage();
        initView();
        setText();
        into();
        //shdshfishfoewhfowfojwfjwo
    }

    private void into() {
        if (isFirstRun()){
            mHandler.sendEmptyMessageDelayed(ENTER_SPLASH,waitTime);
        }else {
            mHandler.sendEmptyMessageDelayed(ENTER_HOME,waitTime);
        }
    }

    private boolean isFirstRun() {
        return mPrefrence.getBoolean("isFirst",true);
    }

    private void setText() {
        mSoftwareVersionTV.setText(getString(R.string.software_version)+getVersion());
    }

    private String getVersion() {
        String versionString = null;
        try {
            PackageManager manager = getPackageManager();
            PackageInfo info = manager.getPackageInfo(getPackageName(),0);
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        return versionString;
    }

    private void initView() {
        mSoftwareVersionTV = (TextView) findViewById(R.id.software_version_tv);

    }

    private void handleMessage() {
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == ENTER_HOME){
                    //进入主界面
                    Intent mHomeIntent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(mHomeIntent);
                }else {
                    //将App启动的值设为false
                    SharedPreferences.Editor mEditor = mPrefrence.edit();
                    mEditor.putBoolean("isFirst",false);
                    mEditor.commit();
                    Intent mSplashIntent = new Intent(SplashActivity.this,GuideActivity.class);
                    startActivity(mSplashIntent);
                }
                finish();
                return false;
            }
        });
    }

    private void getPreferences() {
        mPrefrence = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }
}
