package com.example.service.serviceImpl.Impl;

import java.util.List;
import java.util.Map;

import com.example.service.OutPutObject;
import com.example.service.mapper.RegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mallAdministrationImpl")
public class MallAdministrationImpl extends BaseServiceImpl {
    @Autowired(required = false)
    RegionMapper regionMapper;
    public OutPutObject queryRegion(){
        OutPutObject out = getOutPutObject();
        out.setBeans(regionMapper.queryRegion());
        return out;
    }
}
