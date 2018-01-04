package com.example.pactera.bbchattest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button runBtn;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runBtn= (Button) findViewById(R.id.runBtn);
    }
    /**
     * 点击按钮对应的方法
     * @param v
     */
    public void runMyUiautomator(View v){
        Log.i(TAG, "runMyUiautomator: ");
        new UiautomatorThread().start();
    }
    class UiautomatorThread extends Thread {
        @Override
        public void run() {
            super.run();
            String command=generateCommand("cxq.com.testcase", "TestDemo_1", "demo");
            CMDUtils.CMD_Result rs= CMDUtils.runCMD(command,true,true);
            Log.e(TAG, "run: " + rs.error + "-------" + rs.success);
        }

        /**
         * 生成命令
         * @param pkgName 包名
         * @param clsName 类名
         * @param mtdName 方法名
         * @return
         */
        public  String generateCommand(String pkgName, String clsName, String mtdName) {
            String command = "am instrument  --user 0 -w -r   -e debug false -e class "
                    + pkgName + "." + clsName + "#" + mtdName + " "
                    + pkgName + ".test/android.support.test.runner.AndroidJUnitRunner";
            Log.e("test1: ", command);
            return command;
        }
    }

}
