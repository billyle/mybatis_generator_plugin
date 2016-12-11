package me.libin.db.model;

import java.io.Serializable;
import java.util.Date;

public class TestBean implements Serializable {
    /** key */
    private Integer id;

    /** 名字 */
    private String name;

    private Date aa;

    private Long bb;

    private Short cc;

    private static final long serialVersionUID = -8537646657788639044L;

    /** key */
    public Integer getId() {
        return id;
    }

    /** key */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 名字 */
    public String getName() {
        return name;
    }

    /** 名字 */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getAa() {
        return aa;
    }

    public void setAa(Date aa) {
        this.aa = aa;
    }

    public Long getBb() {
        return bb;
    }

    public void setBb(Long bb) {
        this.bb = bb;
    }

    public Short getCc() {
        return cc;
    }

    public void setCc(Short cc) {
        this.cc = cc;
    }

    /** 
     * 拷贝，将对象中的字段全部拷贝到子对象中
     * @param bean 接收对象的子类
     * @return 拷贝完成后的子类
     */ 
    public  <T extends TestBean> T copy(T bean) {
        bean.setId(getId());
        bean.setName(getName());
        bean.setAa(getAa());
        bean.setBb(getBb());
        bean.setCc(getCc());
        return bean;
    }

    /** 
     * 格式化显示
     */ 
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName() + " {");
        sb.append("id=").append(getId());
        sb.append(", name=").append(getName());
        sb.append(", aa=").append(getAa());
        sb.append(", bb=").append(getBb());
        sb.append(", cc=").append(getCc());
        return sb.append("}").toString();
    }
}