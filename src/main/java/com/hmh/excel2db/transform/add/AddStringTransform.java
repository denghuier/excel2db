package com.hmh.excel2db.transform.add;

import com.hmh.excel2db.transform.all.Transform;
import com.hmh.excel2db.transform.all.TransformDo;

public class AddStringTransform implements Transform {



    private TransformDo transformDo;


    public AddStringTransform(TransformDo transformDo){
        this.transformDo= transformDo;

    }

    @Override
    public String transform() {
        return transformDo.getConfigValue();
    }
}
