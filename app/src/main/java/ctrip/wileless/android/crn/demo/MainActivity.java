/**
 * Copyright (c) Ctrip, Inc. and its affiliates.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
package ctrip.wileless.android.crn.demo;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.react.ReactInstanceManager;
import com.facebook.soloader.SoLoader;

import ctrip.wireless.android.crn.BuildConfig;
import ctrip.wireless.android.crn.R;
import ctrip.wireless.android.crn.CRNBaseActivity;
import ctrip.wireless.android.crn.CRNURL;
import ctrip.wireless.android.crn.instance.PackageManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ReactInstanceManager.initializeSoLoaderIfNecessary(getApplicationContext());

        PackageManager.deleteWebapp();

        setContentView(R.layout.activity_main);
        requestSystemAlertWindow();

        PackageManager.installPackageForProduct("rn_common");

        ((TextView)findViewById(R.id.hermesEnable)).setText("Current Engine:" + (BuildConfig.HERMES_ENABLE ? "Hermes": "JSC"));

        findViewById(R.id.loadCRN).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageManager.installPackageForProduct("rn_crntester");
                startCRNBaseActivity("/rn_crntester/_crn_config?CRNModuleName=CtripApp&CRNType=1");
            }
        });
    }

    private void requestSystemAlertWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName())), 2);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            Toast.makeText(this, "权限失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void startCRNBaseActivity(String url) {
        if (TextUtils.isEmpty(url)) {
            Toast.makeText(MainActivity.this, "CRN URL is illegal!", Toast.LENGTH_SHORT).show();
            return;
        }

        CRNURL crnurl = new CRNURL(url);
        Intent intent = new Intent(MainActivity.this, CRNBaseActivity.class);
        intent.putExtra(CRNBaseActivity.INTENT_COMPONENT_NAME, crnurl);
        startActivity(intent);
    }

}
