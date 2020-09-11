package com.example.service.serviceImpl.Impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.service.OutPutObject;
import com.example.service.Utils.ListUtil;
import com.example.service.mapper.RegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mallAdministrationImpl")
public class MallAdministrationImpl extends BaseServiceImpl {
    @Autowired(required = false)
    RegionMapper regionMapper;

    /**
     * 查询区域地址
     * @return
     */
    public OutPutObject queryRegion(){
        OutPutObject out = getOutPutObject();
        List<Map<String, Object>> beans = regionMapper.queryRegion();
        //按类型(type)分组
        Map<Integer, List<Map<String, Object>>> collect = beans.stream().collect(Collectors.groupingBy(e -> (Integer) e.get("type")));
        int provinceType = 1;
        List<Map<String, Object>> provinceList = collect.get(provinceType);
        int cityType = 2;
        List<Map<String, Object>> cityList = collect.get(cityType);
        //按市的父id分组
        Map<Integer, List<Map<String, Object>>> cityListMap = cityList.stream().collect(Collectors.groupingBy(e -> (Integer) e.get("pid")));
        int areaType = 3;
        List<Map<String, Object>> areaList = collect.get(areaType);
        //按区县的父id分组
        Map<Integer, List<Map<String, Object>>> areaListMap = areaList.stream().collect(Collectors.groupingBy(e -> (Integer) e.get("pid")));
        //拼接省份对应的市区县
        for(Map<String, Object> map:provinceList){
            //对应省份包含的市
            List<Map<String, Object>> myCities = cityListMap.get(map.get("id"));
            for(Map<String,Object> myCity:myCities){
                //对应市包含的区县
                List<Map<String, Object>> myAreas = areaListMap.get(myCity.get("id"));
                if(ListUtil.isNotEmpty(myAreas)){
                    myCity.put("children",myAreas);
                }
            }
            map.put("children",myCities);
        }
        out.setBeans(provinceList);
        return out;
    }

}