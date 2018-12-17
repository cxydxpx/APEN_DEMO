package com.apen.simple.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.apen.simple.R;
import com.apen.simple.fragment.ProguardFragment;
import com.apen.simple.tool.ToolNative;
import com.apen.simple.tool.ToolProguard;

import org.litepal.tablemanager.Connector;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2017-11-01.
 * GitHub：https://github.com/cxydxpx
 */

public class ProguardActivity extends AppCompatActivity {
    private String toastTip = "toast in ProguardActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proguard);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment, new ProguardFragment()).commit();
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                methodWithGlobalVariable();
                methodWithLocalVariable();
                ToolProguard utils = new ToolProguard();
                utils.methodNormal();
                ToolNative.methodNative();
                ToolNative.methodNotNative();
                Connector.getDatabase();
            }
        });
    }

    public void methodWithGlobalVariable() {
        Toast.makeText(this, toastTip, Toast.LENGTH_SHORT).show();
    }

    public void methodWithLocalVariable() {
        String logMessage = "log in MainActivity";
        logMessage = logMessage.toLowerCase();
        System.out.println(logMessage);
    }
}
