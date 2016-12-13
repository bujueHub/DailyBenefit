package com.bujue.dailybenefit.biz;

import android.content.Context;

/**
 * @author bujue
 * @date 16/12/13
 */
public abstract class BaseManager {

    public abstract void init(Context context);

    public abstract void destroy();
}
