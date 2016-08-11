package com.lhc.android.great.widget;

import android.content.Context;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;

import java.util.List;

/**
 * Created by Administrator on 2016/8/11.
 */
public class LoadingDialog extends android.app.ProgressDialog {
    public LoadingDialog(Context context){
        super(context);
    }

    @Override
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, Menu menu, int deviceId) {

    }
}
