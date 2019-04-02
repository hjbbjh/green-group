package com.hjb.utils;

import com.alibaba.fastjson.JSON;
import com.hjb.common.RoleType;

import javax.persistence.AttributeConverter;
import java.util.List;

/**
 * ClassName: RoleTypeListConvert
 * Description:
 * Created by hjb on 2019/4/2 17:40
 */
public class RoleTypeListConvert implements AttributeConverter<List<RoleType>, String> {

    @Override
    public String convertToDatabaseColumn(List<RoleType> roleTypes) {
        return JSON.toJSONString(roleTypes);
    }

    @Override
    public List<RoleType> convertToEntityAttribute(String s) {
        return JSON.parseArray(s,RoleType.class);
    }
}
