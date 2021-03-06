package com.dodo.privilege.enums;

import com.dodo.common.enums.EnumAttributeConverter;
import com.dodo.common.enums.EnumInterface;

/**
 * <p>
 * Dodo Framework. <a href="https://www.bydodo.com">https://www.bydodo.com</a>
 * 
 * @author uncle.code@bydodo.com
 * @author mingming@bydodo.com
 * @author dodo@bydodo.com
 * @version v 1.0
 */
public enum ExcelUpdateStatus implements EnumInterface {
    NEW(1,"dodo.privilege.enums.ExcelUpdateStatus.name.new", "dodo.privilege.enums.ExcelUpdateStatus.desc.new"), 
    ING(2,"dodo.privilege.enums.ExcelUpdateStatus.name.ing", "dodo.privilege.enums.ExcelUpdateStatus.desc.ing"), 
    OK(3,"dodo.privilege.enums.ExcelUpdateStatus.name.ok", "dodo.privilege.enums.ExcelUpdateStatus.desc.ok"), 
    ERROR(4,"dodo.privilege.enums.ExcelUpdateStatus.name.error", "dodo.privilege.enums.ExcelUpdateStatus.desc.error"), 
    EXCEPTION(5,"dodo.privilege.enums.ExcelUpdateStatus.name.exception","dodo.privilege.enums.ExcelUpdateStatus.desc.exception");
    private ExcelUpdateStatus(Integer seq,String nameKey, String descKey) {
        this.nameKey = nameKey;
        this.descKey = descKey;
        this.seq = seq;
    }

    private Integer seq;
    private String name;
    private String nameKey;
    private String desc;
    private String descKey;

    public String getName() {
        return name;
    }

    public String getNameKey() {
        return nameKey;
    }

    public String getDesc() {
        return desc;
    }

    public String getDescKey() {
        return descKey;
    }

    @Override
    public Integer getSeq() {
        return seq;
    }
    
    public static class Converter extends EnumAttributeConverter<ExcelUpdateStatus> {

        @Override
        public Class<ExcelUpdateStatus> getClazz() {
            return ExcelUpdateStatus.class;
        }
    }
}
