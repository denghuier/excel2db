package com.hmh.excel2db.transform.all.springLocator;

import com.hmh.excel2db.db.all.DataSourceFactory;
import com.hmh.excel2db.transform.all.TransformFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TransformServiceLocator implements ApplicationContextAware  {

    /**
     * 用于保存接口实现类名及对应的类
     */
    public static Map<String, TransformFactory> transformMap;



    /**
     * 获取应用上下文并获取相应的接口实现类
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.transformMap=new HashMap<String, TransformFactory>();
        //根据接口类型返回相应的所有bean
        Map<String, TransformFactory> map1 = applicationContext.getBeansOfType(TransformFactory.class);
        for (String  name: map1.keySet()){
            String dsname=map1.get(name).getName();
            this.transformMap.put(dsname,map1.get(name));
        }
    }

    public  Map<String, TransformFactory> getMap() {
        return this.transformMap;
    }

}
