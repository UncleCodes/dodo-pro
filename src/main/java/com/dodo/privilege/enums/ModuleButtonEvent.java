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
public enum ModuleButtonEvent implements EnumInterface {
    AJAX(1, "dodo.privilege.enums.ModuleButtonEvent.name.ajax", "dodo.privilege.enums.ModuleButtonEvent.desc.ajax"), WINDOW(
            2, "dodo.privilege.enums.ModuleButtonEvent.name.window",
            "dodo.privilege.enums.ModuleButtonEvent.desc.window");
    private ModuleButtonEvent(Integer seq, String nameKey, String descKey) {
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

    public static class Converter extends EnumAttributeConverter<ModuleButtonEvent> {

        @Override
        public Class<ModuleButtonEvent> getClazz() {
            return ModuleButtonEvent.class;
        }
    }
}
