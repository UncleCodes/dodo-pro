package com.example.entity.demo_4.base_1;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.DynamicInsert;

import com.dodo.common.annotation.action.DodoAction;
import com.dodo.common.annotation.action.DodoEntity;
import com.dodo.common.annotation.field.DodoField;
import com.dodo.common.annotation.field.DodoFileType;
import com.dodo.common.annotation.field.FileStyle;
import com.dodo.common.annotation.menu.DodoMenu;
import com.dodo.common.framework.entity.BaseEntity;

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
        name = "单记录测试(一般用于系统配置等场景)",
        actions = { DodoAction.ALL },
        levelOne = @DodoMenu(name = "Demo系统", sortSeq = 7),
        levelTwo = @DodoMenu(name = "基础演示", sortSeq = 1),
        levelThree = @DodoMenu(name = "单记录测试", sortSeq = 11),
        singleRecord = true)
public class SingleRecord extends BaseEntity {

    private static final long serialVersionUID = 3130356442497463407L;

    @DodoField(name = "站点标题", sortSeq = 0, maxLength = 32, isnullable = false)
    private String            title;

    @DodoField(name = "联系邮箱", sortSeq = 1, maxLength = 32, isnullable = false, isEmail = true)
    private String            email;

    @DodoField(name = "联系电话", sortSeq = 2, maxLength = 32, isnullable = false)
    private String            phone;

    @DodoField(name = "全国服务热线", sortSeq = 3, maxLength = 32, isnullable = false)
    private String            hotLine;
    @DodoField(
            name = "公众号二维码",
            sortSeq = 1,
            infoTip = "图片尺寸：282px * 284px",
            isFile = true,
            isnullable = false,
            fileStyle = FileStyle.OnlyPath,
            fileType = { @DodoFileType(title = "请选择图片", extensions = "jpg,jpeg,gif,png,bmp") })
    private String            gzhPic;

    @DodoField(
            name = "网站logo",
            sortSeq = 1,
            infoTip = "图片尺寸：160px * 32px",
            isFile = true,
            fileStyle = FileStyle.OnlyPath,
            fileType = { @DodoFileType(title = "请选择图片", extensions = "jpg,jpeg,gif,png,bmp") })
    private String            webLogo;

    @Column(length = 32)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(length = 32)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(length = 32)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(length = 32)
    public String getHotLine() {
        return hotLine;
    }

    public void setHotLine(String hotLine) {
        this.hotLine = hotLine;
    }

    @Column(length = 128)
    public String getGzhPic() {
        return gzhPic;
    }

    public void setGzhPic(String gzhPic) {
        this.gzhPic = gzhPic;
    }

    @Column(length = 128)
    public String getWebLogo() {
        return webLogo;
    }

    public void setWebLogo(String webLogo) {
        this.webLogo = webLogo;
    }
}
