package com.example.entity.demo_4.base_1;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicInsert;

import com.dodo.common.annotation.action.DodoAction;
import com.dodo.common.annotation.action.DodoEntity;
import com.dodo.common.annotation.cascade.DodoCascade;
import com.dodo.common.annotation.field.DodoField;
import com.dodo.common.annotation.field.DodoFileType;
import com.dodo.common.annotation.field.FileStyle;
import com.dodo.common.annotation.menu.DodoMenu;
import com.dodo.common.framework.entity.BaseEntity;
import com.dodo.privilege.entity.admin_1.location_6.City;
import com.dodo.privilege.entity.admin_1.location_6.District;
import com.dodo.privilege.entity.admin_1.location_6.Province;
import com.example.enums.PersonKind;

/**
 * <p>
 * Dodo Framework. <a href="https://www.bydodo.com">https://www.bydodo.com</a>
 * 
 * @author uncle.code@bydodo.com
 * @author mingming@bydodo.com
 * @author dodo@bydodo.com
 * @version v 1.0
 */
@Entity
@DynamicInsert
@DodoEntity(
        name = "字段关联",
        actions = { DodoAction.ALL },
        levelOne = @DodoMenu(name = "Demo系统", sortSeq = 7),
        levelTwo = @DodoMenu(name = "基础演示", sortSeq = 1),
        levelThree = @DodoMenu(name = "字段关联演示", sortSeq = 11))
public class ShowOnDemo extends BaseEntity {

    private static final long serialVersionUID = -880789857565402658L;

    @DodoField(sortSeq = 1, name = "身份类别", isnullable = false, infoTip = "请选择一个身份，其他字段将根据选择结果自动切换")
    private PersonKind        personKind;

    @DodoField(sortSeq = 2, name = "哥哥帅吗？", showOnField = "personKind", showOnValue = "GeGe", isnullable = false)
    private Boolean           isField1;

    @DodoField(
            sortSeq = 2,
            name = "哥哥自我介绍",
            showOnField = "personKind",
            showOnValue = "GeGe",
            isnullable = false,
            isRichText = true)
    private String            desc1;

    @DodoField(sortSeq = 2, name = "妹妹漂亮吗？", showOnField = "personKind", showOnValue = "MeiMei", isnullable = false)
    private Boolean           isField2;

    @DodoField(sortSeq = 3, name = "妹妹签名", showOnField = "personKind", showOnValue = "MeiMei", isnullable = false)
    private String            isField22;

    @DodoField(
            sortSeq = 1,
            name = "妹妹喜欢的人",
            showOnField = "personKind",
            showOnValue = "MeiMei",
            isnullable = false,
            infoTip = "选择看看")
    private PersonKind        personKindMeimei;

    @DodoField(sortSeq = 3, name = "喜欢的哥哥名字", showOnField = "personKindMeimei", showOnValue = "GeGe")
    private String            name3;

    @DodoField(
            name = "喜欢的哥哥省份",
            sortSeq = 3,
            infoTip = "切换时，城市级联",
            showOnField = "personKindMeimei",
            showOnValue = "GeGe")
    @DodoCascade(group = 0)
    private Province          province;

    @DodoField(
            name = "喜欢的哥哥城市",
            sortSeq = 4,
            infoTip = "切换时，区域级联",
            showOnField = "personKindMeimei",
            showOnValue = "GeGe")
    @DodoCascade(group = 0, parentField = "province")
    private City              city;

    @DodoField(name = "喜欢的哥哥区域", sortSeq = 5, showOnField = "personKindMeimei", showOnValue = "GeGe")
    @DodoCascade(group = 0, parentField = "city")
    private District          area;

    @DodoField(sortSeq = 3, name = "喜欢的弟弟名字", showOnField = "personKindMeimei", showOnValue = "DiDi")
    private String            name4;

    @DodoField(sortSeq = 3, name = "喜欢的妹妹名字", showOnField = "personKindMeimei", showOnValue = "MeiMei")
    private String            name5;

    @DodoField(
            sortSeq = 2,
            name = "妹妹上传美照",
            showOnField = "personKind",
            showOnValue = "MeiMei",
            isnullable = false,
            isFile = true,
            fileStyle = FileStyle.OnlyPath,
            fileType = { @DodoFileType(title = "图片文件", extensions = "jpg,jpeg,gif,png,bmp") },
            maxFileSize = 1)
    private String            desc2;

    @DodoField(sortSeq = 2, name = "调皮吗？", showOnField = "personKind", showOnValue = "DiDi", isnullable = false)
    private Boolean           isField3;

    @DodoField(
            sortSeq = 2,
            name = "兴趣爱好",
            showOnField = "personKind",
            showOnValue = "DiDi",
            isnullable = false,
            isTextArea = true)
    private String            desc3;

    @Column(length = 3)
    @Convert(converter = PersonKind.Converter.class)
    public PersonKind getPersonKind() {
        return personKind;
    }

    public void setPersonKind(PersonKind personKind) {
        this.personKind = personKind;
    }

    public Boolean getIsField1() {
        return isField1;
    }

    public void setIsField1(Boolean isField1) {
        this.isField1 = isField1;
    }

    @Lob
    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    public Boolean getIsField2() {
        return isField2;
    }

    public void setIsField2(Boolean isField2) {
        this.isField2 = isField2;
    }

    @Column(length = 128)
    public String getDesc2() {
        return desc2;
    }

    public void setDesc2(String desc2) {
        this.desc2 = desc2;
    }

    public Boolean getIsField3() {
        return isField3;
    }

    public void setIsField3(Boolean isField3) {
        this.isField3 = isField3;
    }

    @Column(length = 64)
    public String getDesc3() {
        return desc3;
    }

    public void setDesc3(String desc3) {
        this.desc3 = desc3;
    }

    @Column(length = 32)
    public String getIsField22() {
        return isField22;
    }

    public void setIsField22(String isField22) {
        this.isField22 = isField22;
    }

    @Column(length = 3)
    @Convert(converter = PersonKind.Converter.class)
    public PersonKind getPersonKindMeimei() {
        return personKindMeimei;
    }

    public void setPersonKindMeimei(PersonKind personKindMeimei) {
        this.personKindMeimei = personKindMeimei;
    }

    @Column(length = 32)
    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    @OneToOne
    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    @OneToOne
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @OneToOne
    public District getArea() {
        return area;
    }

    public void setArea(District area) {
        this.area = area;
    }

    @Column(length = 32)
    public String getName4() {
        return name4;
    }

    public void setName4(String name4) {
        this.name4 = name4;
    }

    @Column(length = 32)
    public String getName5() {
        return name5;
    }

    public void setName5(String name5) {
        this.name5 = name5;
    }
}
