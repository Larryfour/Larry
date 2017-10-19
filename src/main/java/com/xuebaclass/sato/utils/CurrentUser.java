package com.xuebaclass.sato.utils;

import com.xuebaclass.sato.config.SpringSecurityKeycloakAutditorAware;

/**
 * Created by sunhao on 2017-08-22.
 */
public class CurrentUser extends SpringSecurityKeycloakAutditorAware {
    private CurrentUser() {
    }

    public static CurrentUser getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private static enum Singleton {
        //单例实例
        INSTANCE;

        private CurrentUser singleton;

        //JVM会保证此方法绝对只调用一次
        private Singleton() {
            singleton = new CurrentUser();
        }

        public CurrentUser getInstance() {
            return singleton;
        }
    }

    private String getAutditor() {
        return super.getCurrentAuditor();
    }

    private String getAutditorName() {
        return super.getCurrentAuditorName();
    }

}
