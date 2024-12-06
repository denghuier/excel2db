package com.hmh.excel2db.db.all.serviceLocator;


import com.hmh.excel2db.db.all.DataSourceFactory;
import com.hmh.excel2db.transform.all.TransformFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DBServiceLocator implements ApplicationContextAware  {

    /**
     * 用于保存接口实现类名及对应的类
     */
    public static Map<String, DataSourceFactory> map;

    /**
     * 获取应用上下文并获取相应的接口实现类
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.map=new HashMap<String, DataSourceFactory>();
        //根据接口类型返回相应的所有bean
        Map<String, DataSourceFactory> map1 = applicationContext.getBeansOfType(DataSourceFactory.class);
        for (String  name: map1.keySet()){
            String dsname=map1.get(name).getName();
            this.map.put(dsname,map1.get(name));
        }

    }

    public  Map<String, DataSourceFactory> getMap() {
        return this.map;
    }

}
