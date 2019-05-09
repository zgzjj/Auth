package com.zjj.auth.utils;

import org.apache.commons.lang3.StringUtils;

public class LevelUtil {

    public final static String SEPARATOR = "_";

    public final static String ROOT = "System";

    public static String createLevel(String parentLevel,int parentId){
        if(StringUtils.isBlank(parentLevel)){
            return ROOT;
        }else{
            return StringUtils.join(parentLevel,SEPARATOR,parentId);
        }
    }
}
