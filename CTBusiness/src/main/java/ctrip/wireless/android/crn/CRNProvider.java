package ctrip.wireless.android.crn;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ctrip.wireless.android.crn.plugin.CRNPlugin;

/**
 * Created by neo on 26/05/2017.
 */

public class CRNProvider {

    //provide NativeModules
    public static List<NativeModule> provideNativeModules(ReactApplicationContext reactContext) {
        return Collections.EMPTY_LIST;
    }

    //provide ViewManager
    public static List<ViewManager> provideViewManagers(ReactApplicationContext reactContext) {
        return Collections.EMPTY_LIST;
    }

    //provide CRNPlugin
    public static List<CRNPlugin> providePlugins() {
        return Collections.EMPTY_LIST;
    }

    //provide React Packages
    public static List<ReactPackage> provideReactPackages() {
        List list = new ArrayList();
        list.addAll(Arrays.asList(
                new MainReactPackage()
        ));
        return list;
    }

}
