package com.example.service.serviceImpl.GoodsImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.service.OutPutObject;
import com.example.service.mapper.GoodsMapper;
import com.example.service.serviceImpl.Impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("goodsImpl")
public class GoodsImpl extends BaseServiceImpl {
    @Autowired(required = false)
    GoodsMapper goodsMapper;

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
}
