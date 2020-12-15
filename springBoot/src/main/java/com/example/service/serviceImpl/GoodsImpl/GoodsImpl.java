package com.example.service.serviceImpl.GoodsImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.service.OutPutObject;
import com.example.service.Utils.StringUtils;
import com.example.service.mapper.BrandMapper;
import com.example.service.mapper.CategoryMapper;
import com.example.service.mapper.GoodsMapper;
import com.example.service.serviceImpl.Impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("goodsImpl")
public class GoodsImpl extends BaseServiceImpl {
    @Autowired(required = false)
    GoodsMapper goodsMapper;
    @Autowired(required = false)
    CategoryMapper categoryMapper;
    @Autowired(required = false)
    BrandMapper brandMapper;

    /**
     * 商品查询
     * @param body
     * @return
     */
    public OutPutObject queryGoods(Map body){
        OutPutObject out = getOutPutObject();
        List<Map<String, Object>> listMap = goodsMapper.queryGoods(body);
        Integer toltal = goodsMapper.queryGoodsTotal(body);
        out.setBean(new HashMap<String, Object>(){{put("total", toltal);}});
        out.setBeans(listMap);
        return out;
    }

    /**
     * 所属类型查询
     * @param body
     * @return
     */
    public OutPutObject queryCategory(Map body){
        OutPutObject out = getOutPutObject();
        List<Map<String, Object>> list = categoryMapper.queryCategory(body);
        Map<String, Object>tempMap = new HashMap();
        for(Map<String, Object> map : list){
            tempMap.put("pid",map.get("id"));
            List<Map<String,Object>> listChildren = categoryMapper.queryCategory(tempMap);
            map.put("children", listChildren);
        }
        out.setBeans(list);
        List<Map<String,Object>> listB = brandMapper.queryBrandAll(new HashMap());
        out.setBean(new HashMap<String, Object>(){{put("brandList",listB);}});
        return out;
    }
}
