package com.example.service.Utils;

import java.util.List;

public class ListUtil {
    public static boolean isEmpty(List list){
        if(list == null || list.isEmpty()){
            return true;
        }
        return false;
    }
    public static boolean isNotEmpty(List list){
        return !isEmpty(list);
    }
}
