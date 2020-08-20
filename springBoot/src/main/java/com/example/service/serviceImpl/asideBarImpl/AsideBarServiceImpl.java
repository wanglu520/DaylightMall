package com.example.service.serviceImpl.asideBarImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.service.OutPutObject;
import com.example.service.mapper.AutoMeunMapper;
import com.example.service.serviceImpl.Impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class AsideBarServiceImpl extends BaseServiceImpl {
    @Autowired(required=false)
    private AutoMeunMapper autoMeunMapper;
    @RequestMapping(value = "/queryMenu", method = RequestMethod.POST)
    public OutPutObject queryMenu(@RequestBody Map body){
        System.out.println(body.get("userId"));
        String id = (String) body.get("userId");
        OutPutObject out = getOutPutObject();
        //查询菜单权限
        List<Map<String, Object>> beans = autoMeunMapper.queryAutoMenu(id);
        if(beans != null && beans.size() > 0){
            //查询主菜单
            String mainId =(String) beans.get(0).get("main_id");
            List<Map<String, Object>> mainBeans = autoMeunMapper.queryMainMenu(mainId);
            if(mainBeans !=null && mainBeans.size() > 0){
                //查询子菜单
                String childId =(String) beans.get(0).get("child_id");
                List<Map<String, Object>> childBeans = autoMeunMapper.queryChildMenu(childId);
                //把子节点拼装到对应的父节点下
                if(childBeans != null && childBeans.size() > 0){
                    for(Map<String, Object> bean:mainBeans){
                        List<Map<String,Object>> child = new ArrayList<>();
                        for(Map<String,Object> tempBean : childBeans){
                            String beanId = String.valueOf(bean.getOrDefault("id",""));
                            String tempId = String.valueOf(tempBean.get("main_id"));
                            if(beanId.equals(tempId)){
                                child.add(tempBean);
                            }
                        }
                        bean.put("child",child);
                    }
                }
                out.setBeans(mainBeans);
            }else {
                out.setReturnCode("-9999");
                out.setReturnMessage("没有查询到数据");
            }
        }else {
            out.setReturnCode("-9999");
            out.setReturnMessage("没有查询到数据");
        }
        return out;
    }
}
