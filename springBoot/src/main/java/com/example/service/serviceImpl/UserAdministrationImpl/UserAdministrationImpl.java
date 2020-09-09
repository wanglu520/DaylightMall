package com.example.service.serviceImpl.UserAdministrationImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.service.OutPutObject;
import com.example.service.Utils.ListUtil;
import com.example.service.mapper.UserAddressMapper;
import com.example.service.mapper.UserAdministrationMapper;
import com.example.service.serviceImpl.Impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAdministrationImpl")
public class UserAdministrationImpl extends BaseServiceImpl {
    @Autowired(required=false)
    UserAdministrationMapper userAdmin;
    @Autowired(required=false)
    UserAddressMapper userAddress;


    /**
     * 会员信息查询
     * @param map
     * @return
     */
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

    /**
     * 更新会员详细信息
     * @param map
     * @return
     */
    public OutPutObject updateDetail(Map map){
        OutPutObject out = getOutPutObject();
        int count = userAdmin.updateDetail(map);
        if(count != 1){
            out.setReturnCode("9999");
        }
        return out;
    }
    public OutPutObject queryUserAddress(Map map){
        OutPutObject out = getOutPutObject();
        List<Map<String, Object>> usersAddressInfo = userAddress.queryUserAddress(map);
        int count = userAddress.queryUserAddressTotal(map);
        if(ListUtil.isNotEmpty(usersAddressInfo)){
            out.setBeans(usersAddressInfo);
            out.setBean(new HashMap<String, Object>(){{put("total", count);}});
        }
        return out;
    }
}
