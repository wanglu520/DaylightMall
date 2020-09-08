package com.example.service.serviceImpl.UserAdministrationImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.service.OutPutObject;
import com.example.service.Utils.ListUtil;
import com.example.service.mapper.UserAdministrationMapper;
import com.example.service.serviceImpl.Impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAdministrationImpl")
public class UserAdministrationImpl extends BaseServiceImpl {
    @Autowired(required=false)
    UserAdministrationMapper userAdmin;
    public OutPutObject userDetail(Map map){
        OutPutObject outPutObject = getOutPutObject();
        List<Map<String, Object>> usersInfo = userAdmin.queryUserDetail(map);
        Integer count = userAdmin.queryUserDetailTotal(map);
        if(ListUtil.isEmpty(usersInfo)){
            outPutObject.setReturnMessage("没有查询到数据");
        }else {
            outPutObject.setBeans(usersInfo);
            outPutObject.setBean(new HashMap<String, Object>(){{put("total", count);}});
        }
        return outPutObject;
    }

}
