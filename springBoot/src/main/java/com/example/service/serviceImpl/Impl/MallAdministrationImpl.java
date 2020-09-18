package com.example.service.serviceImpl.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.example.service.OutPutObject;
import com.example.service.Utils.ListUtil;
import com.example.service.mapper.BrandMapper;
import com.example.service.mapper.RegionMapper;
import com.example.service.mapper.StorageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("mallAdministrationImpl")
public class MallAdministrationImpl extends BaseServiceImpl {
    @Autowired(required = false)
    RegionMapper regionMapper;
    @Autowired(required = false)
    BrandMapper brandMapper;
    @Autowired(required = false)
    StorageMapper storageMapper;

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

    /**
     * 品牌制造商信息查询
     * @param map
     * @return
     */
    public OutPutObject queryBrand(Map map){
        OutPutObject out = getOutPutObject();
        out.setBeans(brandMapper.queryBrand(map));
        int count = brandMapper.queryBrandTotal(map);
        out.setBean(new HashMap<String, Object>(){{put("total", count);}});
        return out;
    }

    /**
     * 删除品牌制造信息（逻辑删除）
     * @param map
     * @return
     */
    public OutPutObject deleteBrand(Map map){
        OutPutObject out = getOutPutObject();
        int count = brandMapper.deleteBrand(map);
        if(count != 1){
            out.setReturnCode("9999");
        }
        return out;
    }

    /**
     * 更新数据
     * @param map
     * @return
     */
    public OutPutObject updateBrand(Map map){
        OutPutObject out = getOutPutObject();
        int count = brandMapper.updateBrand(map);
        return out;
    }

    /**
     * add Date
     * @param map
     * @return
     */
    public OutPutObject addBrand(Map map){
        OutPutObject out = getOutPutObject();
        map.put("addTime", LocalDateTime.now());
        brandMapper.addBrand(map);
        out.setBean(map);
        return out;
    }

    /**
     *
     * @param file
     * @return
     */
    public OutPutObject uploadPhoto(MultipartFile file) throws IOException {
        OutPutObject out = getOutPutObject();
        String fileName = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        long contentLength = file.getSize();
        String contentType = file.getContentType();
        //String imgFilePrefix = new SimpleDateFormat("yyyyMMddHHmmssssss").format(Calendar.getInstance().getTime());
        String key = generateKey(fileName);
        String url = generateUrl(key);
        Map<String, Object> map = new HashMap<String, Object>(){{
            put("name", fileName);
            put("size", contentLength);
            put("type", contentType);
            put("key", key);
            put("url", url);
            put("addTime", LocalDateTime.now());
        }};
        storageMapper.uploadPhoto(map);
        out.setBean(map);
        return out;
    }

    private String generateKey(String fileName){
        int index = fileName.lastIndexOf('.');
        String suffix = fileName.substring(index);
        return UUID.randomUUID()+suffix;
    }
    private String generateUrl(String key){
        return "http://localhost:8088/picture/"+key;
    }
}
