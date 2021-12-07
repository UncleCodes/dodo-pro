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
public enum ModuleButtonModel implements EnumInterface {
    ROW(1, "dodo.privilege.enums.ModuleButtonModel.name.row", "dodo.privilege.enums.ModuleButtonModel.desc.row"), MODULE(
            2, "dodo.privilege.enums.ModuleButtonModel.name.module",
            "dodo.privilege.enums.ModuleButtonModel.desc.module");
    private ModuleButtonModel(Integer seq, String nameKey, String descKey) {
        this.nameKey = nameKey;
        this.descKey = descKey;
        this.seq = seq;
    }

    private Integer seq;
    private String  name;
    private String  nameKey;
    private String  desc;
    private String  descKey;

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

    public static class Converter extends EnumAttributeConverter<ModuleButtonModel> {

        @Override
        public Class<ModuleButtonModel> getClazz() {
            return ModuleButtonModel.class;
        }
    }
}
