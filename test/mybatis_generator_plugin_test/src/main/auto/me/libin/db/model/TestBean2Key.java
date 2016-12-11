package me.libin.db.model;

import java.io.Serializable;

public class TestBean2Key implements Serializable {
    /** key */
    protected Integer id;

    /** 名字 */
    protected String name;

    private static final long serialVersionUID = -8452844074800076173L;

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

    /** 
     * 拷贝，将对象中的字段全部拷贝到子对象中
     * @param bean 接收对象的子类
     * @return 拷贝完成后的子类
     */ 
    public  <T extends TestBean2Key> T copy(T bean) {
        bean.setId(getId());
        bean.setName(getName());
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
        return sb.append("}").toString();
    }
}