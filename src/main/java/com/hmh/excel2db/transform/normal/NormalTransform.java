package com.hmh.excel2db.transform.normal;

import com.hmh.excel2db.transform.all.Transform;
import com.hmh.excel2db.transform.all.TransformDo;


public class NormalTransform implements Transform {

    private TransformDo transformDo;
    public NormalTransform(TransformDo transformDo)
    {
        this.transformDo = transformDo;
    }


    @Override
    public String transform() {
        return transformDo.getValue();
    }
}
