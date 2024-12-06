package com.hmh.excel2db.transform.all.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum TransformTypeEnum {

    NORMAL("NORMAL","正常"),
    ADDSTRING("ADDSTRING","新增字符");
    private String name;

	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
    public String getName() {
        return name;
    }
    public void setName(String name) {

        this.name = name;
    }
    TransformTypeEnum(String code,String name) {
		this.code = code;
        this.name = name;
    }



    public static List<HashMap<String, String>> getEnumList() {
		 List<HashMap<String, String>> list = new ArrayList<>();

    	for(int i = 0; i < TransformTypeEnum.values().length; i++) {
			 HashMap<String, String> map = new HashMap<>();
            map.put("code", TransformTypeEnum.values()[i].getCode());
			map.put("name", TransformTypeEnum.values()[i].getName());
			list.add( map);
    	}
    	return list;
    }



}
