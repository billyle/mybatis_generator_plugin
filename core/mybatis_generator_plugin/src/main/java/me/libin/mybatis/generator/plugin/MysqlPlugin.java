package me.libin.mybatis.generator.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * 作者:李斌 E-mail:libin02@17guagua.com<br/>
 * 创建时间：2016-1-7 上午10:43:35<br/>
 * 说明:所有功能皆可选用，默认开启，设置参数为false即可关闭<br/>
 * 特有功能：<br/>
 * <ul>
 * <li>查询类增加分页参数 (genLimit)</li>
 * <li>查询类增加悲观锁开关 (genForUpdate)</li>
 * </ul>
 * 继承自 {@linkplain me.libin.mybatis.generator.plugin.BasicPlugin BasicPlugin}
 * 的功能，不能和{@linkplain me.libin.mybatis.generator.plugin.BasicPlugin
 * BasicPlugin} 以及其子类同时使用<br/>
 * 如果同时使用需要配置只让一个的公共功能生效，否则可能生成重复代码或混乱代码<br/>
 */
public class MysqlPlugin extends BasicPlugin {
	protected String genPage = "genPage";
	protected String genForUpdate = "genForUpdate";

	public MysqlPlugin() {
	}

	/**
	 * 给查询类加内容:<br>
	 * 分页参数<br>
	 * 悲观锁
	 */
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		if (Boolean.valueOf(properties.getProperty(genPage, "true"))) {
			FullyQualifiedJavaType intField = FullyQualifiedJavaType.getIntInstance();
			// 添加分页参数
			addFieldWithGetterAndSetter(topLevelClass, introspectedTable, "offset", intField, "0", "分页参数偏移量");
			addFieldWithGetterAndSetter(topLevelClass, introspectedTable, "rows", intField, "0", "分页参数数量");
		}
		if (Boolean.valueOf(properties.getProperty(genForUpdate, "true"))) {
			// 添加悲观锁
			addFieldWithGetterAndSetter(topLevelClass, introspectedTable, "forUpdate",
					FullyQualifiedJavaType.getBooleanPrimitiveInstance(), "false", "悲观锁");
		}
		return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
	}

	public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		addForUpdate(element);
		return super.sqlMapSelectByPrimaryKeyElementGenerated(element, introspectedTable);
	}

	public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {
		addLimit(element);
		addForUpdate(element);
		return super.sqlMapSelectByExampleWithBLOBsElementGenerated(element, introspectedTable);
	}

	public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {
		addLimit(element);
		addForUpdate(element);
		return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
	}

	/**
	 * 给xml添加内容:<br>
	 * 分页参数<br>
	 */
	protected void addLimit(XmlElement element) {
		if (Boolean.valueOf(properties.getProperty(genPage, "true"))) {
			// 在xml中加分页参数判断
			XmlElement isNotNullElement = new XmlElement("if");
			isNotNullElement.addAttribute(
					new Attribute("test", "offset != null and offset >= 0 and rows != null and rows > 0"));
			isNotNullElement.addElement(new TextElement(" limit ${offset} , ${rows}"));
			element.addElement(isNotNullElement);
		}
	}

	/**
	 * 给xml添加内容:<br>
	 * 悲观锁
	 */
	protected void addForUpdate(XmlElement element) {
		if (Boolean.valueOf(properties.getProperty(genForUpdate, "true"))) {
			// 加悲观锁判断
			XmlElement forUpdateElement1 = new XmlElement("if");
			forUpdateElement1.addAttribute(new Attribute("test", "forUpdate"));
			forUpdateElement1.addElement(new TextElement(" for update"));
			element.addElement(forUpdateElement1);
		}
	}
}