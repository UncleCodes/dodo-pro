/*
 * <strong>File   ：</strong>FormModelUtil.java <br/>
 * <strong>Project：</strong>galaxy-app-hr <br/>
 * <strong>Date   ：</strong>2022年8月4日 下午3:01:43 <br/>
 * Copyright © 2010-2016 IKaiHuo Corporation, All Rights Reserved
 * 
 */
package com.dodo.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 注释内容<br/>
 * 
 * @author djh
 * 
 * @version
 */
public class FormModelUtil {
    public static Map<String, Object> prepareFormModelAddParams(List<Map<String, Object>> formModelFields,
            String formModelName, String submitTip, String formModelPostUrl, Map<String, Object> autoAddParams) {
        Map<String, Object> model = new HashMap<String, Object>();
        // 字段
        model.put("formModelFields", formModelFields);
        // 提示信息
        model.put("submitTip", submitTip);
        // 模型名称
        model.put("modelName", formModelName);
        // 提交地址
        model.put("formModelPostUrl", formModelPostUrl);
        model.put("formModelAddView", "formmodel_toadd");
        model.put("autoAddParams", autoAddParams);

        return model;
    }
}
