package com.hmh.excel2db.transform.normal;


import com.hmh.excel2db.transform.all.TransformDo;

import com.hmh.excel2db.transform.all.TransformFactory;
import com.hmh.excel2db.transform.all.Transform;
import com.hmh.excel2db.transform.all.enums.TransformTypeEnum;
import org.springframework.stereotype.Component;

@Component
public class NormalTransformFactory implements TransformFactory{

    @Override
    public String getName() {
        return TransformTypeEnum.NORMAL.getCode();
    }

    @Override
    public Transform createTransform(TransformDo transformDo) {
        return new NormalTransform(transformDo);
    }


}
