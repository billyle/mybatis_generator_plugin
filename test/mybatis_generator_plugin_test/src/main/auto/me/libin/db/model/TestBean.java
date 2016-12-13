package me.libin.db.model;

import java.io.Serializable;
import java.util.Date;

public class TestBean implements Serializable {
    private Long id;

    private String name;

    private Date aa;

    private static final long serialVersionUID = -6924013393085649272L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getAa() {
        return aa;
    }

    public void setAa(Date aa) {
        this.aa = aa;
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
        return sb.append("}").toString();
    }
}