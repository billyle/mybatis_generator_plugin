package me.libin.mybatis.generator.plugin;

import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * 作者:李斌 E-mail:libin02@17guagua.com<br/>
 * 创建时间：2016年5月15日 下午5:25:44<br/>
 * 说明:所有功能皆可选用，默认开启，设置参数为false即可关闭<br/>
 * 特有功能：<br/>
 * <ul>
 * <li>查询类增加分页参数 (genPage)</li>
 * <li>查询类增加不锁表开关 (genNoLock)</li>
 * </ul>
 * 继承自 {@linkplain me.libin.mybatis.generator.plugin.BasicPlugin BasicPlugin}
 * 的功能，不能和{@linkplain me.libin.mybatis.generator.plugin.BasicPlugin BasicPlugin}
 * 以及其子类同时使用<br/>
 * 如果同时使用需要配置只让一个的公共功能生效，否则可能生成重复代码或混乱代码<br/>
 */
public class SqlServerPlugin extends BasicPlugin {
	private String genNoLock = "genNoLock";
	private String genPage = "genPage";

	public boolean validate(List<String> warnings) {
		return true;
	}

	public boolean sqlMapCountByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		if (Boolean.valueOf(properties.getProperty(genNoLock, "true"))) {
			List<Element> subEl = element.getElements();
			Element et = subEl.get(0);
			Element newEt = new TextElement(
					et.getFormattedContent(0).replaceAll(introspectedTable.getTableConfiguration().getTableName(),
							introspectedTable.getTableConfiguration().getTableName() + " with(nolock) "));
			subEl.set(0, newEt);
		}
		return true;
	}

	public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		if (Boolean.valueOf(properties.getProperty(genNoLock, "true"))) {
			List<Element> subEl = element.getElements();
			Element et = subEl.get(2);
			System.err.println("et2=\n" + et.getFormattedContent(0));
			Element newEt = new TextElement(
					et.getFormattedContent(0).replaceAll(introspectedTable.getTableConfiguration().getTableName(),
							introspectedTable.getTableConfiguration().getTableName() + " with(nolock) "));
			subEl.set(2, newEt);
		}
		return true;
	}

	/**
	 * 修改 select 查询xml 对象， 添加自动分页功能
	 */
	public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {
		if (!Boolean.valueOf(properties.getProperty(genPage, "true"))) {
			return true;
		}
		List<Element> subEl = element.getElements();
		subEl.clear();
		// 当start > 0 时
		{
			XmlElement skipGreater0 = new XmlElement("if");
			subEl.add(skipGreater0);
			skipGreater0.addAttribute(new Attribute("test", "start > 0"));
			skipGreater0.addElement(new TextElement("select"));

			{
				XmlElement childEl = new XmlElement("if");
				skipGreater0.addElement(childEl);
				childEl.addAttribute(new Attribute("test", "limit >= 0"));
				childEl.addElement(new TextElement("top ${limit}"));
			}

			skipGreater0.addElement(new TextElement(" * from ( select "));

			{
				XmlElement childEl = new XmlElement("if");
				skipGreater0.addElement(childEl);
				childEl.addAttribute(new Attribute("test", "limit >= 0"));
				childEl.addElement(new TextElement("top ${endCount}"));
			}

			skipGreater0.addElement(new TextElement(" ROW_NUMBER() OVER (order by ${orderByClause}) as rownum ,"));

			{
				XmlElement childEl = new XmlElement("if");
				skipGreater0.addElement(childEl);
				childEl.addAttribute(new Attribute("test", "distinct"));
				childEl.addElement(new TextElement("distinct"));
			}

			{
				XmlElement childEl = new XmlElement("include");
				skipGreater0.addElement(childEl);
				childEl.addAttribute(new Attribute("refid", "Base_Column_List"));
			}

			skipGreater0.addElement(new TextElement(
					"from " + introspectedTable.getFullyQualifiedTableNameAtRuntime() + " with(nolock) "));

			{
				XmlElement childEl = new XmlElement("if");
				skipGreater0.addElement(childEl);
				childEl.addAttribute(new Attribute("test", "_parameter != null"));
				XmlElement childchildEl = new XmlElement("include");
				childEl.addElement(childchildEl);
				childchildEl.addAttribute(new Attribute("refid", "Example_Where_Clause"));
			}
			skipGreater0.addElement(
					new TextElement(") temp_table\r\n  where rownum > ${start}\r\n  order by ${orderByClause}"));
		}

		// 当start == 0 时
		{
			XmlElement skipEquals0 = new XmlElement("if");
			subEl.add(skipEquals0);
			skipEquals0.addAttribute(new Attribute("test", "start == 0"));
			skipEquals0.addElement(new TextElement("select"));

			{
				XmlElement childEl = new XmlElement("if");
				skipEquals0.addElement(childEl);
				childEl.addAttribute(new Attribute("test", "limit >= 0"));
				childEl.addElement(new TextElement("top ${limit}"));
			}

			{
				XmlElement childEl = new XmlElement("if");
				skipEquals0.addElement(childEl);
				childEl.addAttribute(new Attribute("test", "distinct"));
				childEl.addElement(new TextElement("distinct"));
			}

			{
				XmlElement childEl = new XmlElement("include");
				skipEquals0.addElement(childEl);
				childEl.addAttribute(new Attribute("refid", "Base_Column_List"));
			}

			skipEquals0.addElement(new TextElement(
					"from " + introspectedTable.getFullyQualifiedTableNameAtRuntime() + " with(nolock) "));

			{
				XmlElement childEl = new XmlElement("if");
				skipEquals0.addElement(childEl);
				childEl.addAttribute(new Attribute("test", "_parameter != null"));
				XmlElement childchildEl = new XmlElement("include");
				childEl.addElement(childchildEl);
				childchildEl.addAttribute(new Attribute("refid", "Example_Where_Clause"));
			}

			{
				XmlElement childEl = new XmlElement("if");
				skipEquals0.addElement(childEl);
				childEl.addAttribute(new Attribute("test", "orderByClause != null"));
				childEl.addElement(new TextElement("order by ${orderByClause}"));
			}
		}
		return super.sqlMapSelectByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
	}

	/** example 中添加分页信息 */
	@Override
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		FullyQualifiedJavaType intField = FullyQualifiedJavaType.getIntInstance();
		addFieldWithGetterAndSetter(topLevelClass, introspectedTable, "start", intField, "0", "分页参数偏移量");
		addFieldWithGetterAndSetter(topLevelClass, introspectedTable, "limit", intField, "-1", "分页参数数量");

		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(FullyQualifiedJavaType.getIntInstance());
		method.setName("getEndCount");
		method.addBodyLine("return start + limit;");
		topLevelClass.addMethod(method);

		return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
	}
}
