package com.gpagers.cn.handle.util;

/**
 * Created by nali on 16/9/7.
 */
public class Constants {
    //根据算法,非人为操作的默认userId
    public static Long DEFAULT_USER_ID = -1L;

    //资源形势
    public enum RESOURCE_TYPE{
        ALBUM,TRACK
    }

    //资源投放形势APT(按天收费),APM(按次数收费)
    public enum RESOURCE_LAUNCH{
        APT,APM;
    }
}
