package com.swi.swiprotocollibrary;

import com.swi.swiprotocollibrary.controlcomponent.ControlComponentManager;

/**
 * Copyright (C), 2020-2030
 * <p>
 * 功能描述:组件管理类
 * <p>
 * 创建时间: 2022/8/5 17:30
 *
 * @author yuhao
 */
public class ComponentManager extends ShowErrorCode {

    private static final ComponentManager componentManager = new ComponentManager();
    private CacheMsgTimeoutCheck cacheMsgTimeoutCheck;
    private ControlComponentManager controlComponentManager;

    public static ComponentManager getInstance() {
        return componentManager;
    }

    public ComponentManager() {
        cacheMsgTimeoutCheck = new CacheMsgTimeoutCheck();
        cacheMsgTimeoutCheck.start();
    }


    public void destroyComponentManager() {
        if (cacheMsgTimeoutCheck != null) {
            cacheMsgTimeoutCheck.destroyCache();
            cacheMsgTimeoutCheck = null;
        }
    }


    /**
     * 获取控制组件
     */
    public ControlComponentManager getControlComponent() {
        if (controlComponentManager == null) {
            controlComponentManager = new ControlComponentManager(cacheMsgTimeoutCheck);
        }
        return controlComponentManager;
    }

}
