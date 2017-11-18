package com.android.www.myapplication;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.navi.NaviLifecycle;

/**
 * Created by Administrator on 2017/11/17 0017.
 */

public abstract class BaseActivity extends com.trello.navi2.component.support.NaviAppCompatActivity {

    final LifecycleProvider<ActivityEvent> provider = NaviLifecycle.createActivityLifecycleProvider(this);



}
