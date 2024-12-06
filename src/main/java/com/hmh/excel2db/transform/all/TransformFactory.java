package com.hmh.excel2db.transform.all;




public interface TransformFactory {

    String getName();


    public Transform createTransform(TransformDo transformDo );
}
