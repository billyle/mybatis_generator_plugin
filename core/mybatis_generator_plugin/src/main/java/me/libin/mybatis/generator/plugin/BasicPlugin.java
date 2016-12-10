package me.libin.mybatis.generator.plugin;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaElement;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 作者:李斌 E-mail:libin02@17guagua.com<br/>
 * 创建时间：2016-1-7 上午10:43:35<br/>
 * 说明:所有功能皆可选用，默认开启，设置参数为false即可关闭<br/>
 * <li>备份以前生成的文件 (isBackUpXml)<br/>
 * <li>实体类实现Serializable接口 (isSerializable)<br/>
 * <li>实体类增加字段和方法的注释 (genRemarkJavaDoc)<br/>
 * <li>实体类增加copy方法 (genCopy)<br/>
 * <li>实体类增加toString方法 (genToString)
 */
public class BasicPlugin extends PluginAdapter {
	private Logger logger = LoggerFactory.getLogger(getClass());

	public String isBackUpXml = "isBackUpXml";
	public String isSerializable = "isSerializable";
	public String genRemarkJavaDoc = "genRemarkJavaDoc";
	public String genCopy = "genCopy";
	public String genToString = "genToString";

	private FullyQualifiedJavaType serializable;

	public BasicPlugin() {
		serializable = new FullyQualifiedJavaType("java.io.Serializable");
	}

	public boolean validate(List<String> warnings) {
		return true;
	}

	public void initialized(IntrospectedTable introspectedTable) {
		if (Boolean.valueOf(properties.getProperty(isBackUpXml, "true"))) {
			backUpXml(introspectedTable);
		}
		super.initialized(introspectedTable);
	}

	/**
	 * 说明：备份xml
	 */
	private void backUpXml(IntrospectedTable introspectedTable) {
		SqlMapGeneratorConfiguration sqlmapConfig = context.getSqlMapGeneratorConfiguration();
		String dir = sqlmapConfig.getTargetProject() + File.separator
				+ sqlmapConfig.getTargetPackage().replaceAll("\\.", File.separator);
		final String fileName = introspectedTable.getMyBatis3XmlMapperFileName();
		File file = new File(dir + File.separator + fileName);
		if (file.isFile()) {
			File dest = new File(dir + File.separator + "_" + fileName + "."
					+ new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date()) + ".bak");
			file.renameTo(dest);
			logger.info("备份:{}", dest.getAbsolutePath());
		}
	}

	public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		// 实体类添加统一父接口
		for (Field field : topLevelClass.getFields()) {
			field.setVisibility(JavaVisibility.PROTECTED);
		}
		makeSerializable(topLevelClass, introspectedTable);
		generateCopy(topLevelClass, introspectedTable);
		generateToString(topLevelClass, introspectedTable);
		return true;
	}

	public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		makeSerializable(topLevelClass, introspectedTable);
		generateCopy(topLevelClass, introspectedTable);
		generateToString(topLevelClass, introspectedTable);
		return super.modelRecordWithBLOBsClassGenerated(topLevelClass, introspectedTable);
	}

	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		makeSerializable(topLevelClass, introspectedTable);
		generateCopy(topLevelClass, introspectedTable);
		generateToString(topLevelClass, introspectedTable);
		return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
	}

	public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
			IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		addRemarkJavaDoc(field, introspectedColumn);
		return super.modelFieldGenerated(field, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
	}

	public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		addRemarkJavaDoc(method, introspectedColumn);
		return super.modelGetterMethodGenerated(method, topLevelClass, introspectedColumn, introspectedTable,
				modelClassType);
	}

	public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		addRemarkJavaDoc(method, introspectedColumn);
		return super.modelSetterMethodGenerated(method, topLevelClass, introspectedColumn, introspectedTable,
				modelClassType);
	}

	protected void makeSerializable(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		if (Boolean.valueOf(properties.getProperty(isSerializable, "true"))) {
			topLevelClass.addImportedType(serializable);
			topLevelClass.addSuperInterface(serializable);

			Field field = new Field();
			field.setFinal(true);
			field.setInitializationString(new Random().nextLong() + "L");
			field.setName("serialVersionUID");
			field.setStatic(true);
			field.setType(new FullyQualifiedJavaType("long"));
			field.setVisibility(JavaVisibility.PRIVATE);
			context.getCommentGenerator().addFieldComment(field, introspectedTable);

			topLevelClass.addField(field);
		}
	}

	protected void addRemarkJavaDoc(JavaElement method, IntrospectedColumn introspectedColumn) {
		if (Boolean.valueOf(properties.getProperty(genRemarkJavaDoc, "true"))) {
			addJavaDoc(method, introspectedColumn.getRemarks());
		}
	}

	protected void generateCopy(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		if (Boolean.valueOf(properties.getProperty(genCopy, "true"))) {
			Method methodCopy = new Method();
			methodCopy.setVisibility(JavaVisibility.PUBLIC);
			methodCopy.addJavaDocLine("/** ");
			methodCopy.addJavaDocLine(" * 拷贝，将对象中的字段全部拷贝到子对象中");
			methodCopy.addJavaDocLine(" * @param bean 接收对象的子类");
			methodCopy.addJavaDocLine(" * @return 拷贝完成后的子类");
			methodCopy.addJavaDocLine(" */ ");
			methodCopy.setName("<T extends " + topLevelClass.getType().getShortName() + "> T copy");
			methodCopy.setReturnType(new FullyQualifiedJavaType(""));
			methodCopy.addParameter(new Parameter(new FullyQualifiedJavaType("T"), "bean"));

			for (IntrospectedColumn column : introspectedTable.getAllColumns()) {
				String columnName = column.getJavaProperty();
				String remark = column.getRemarks();
				if (stringHasValue(remark)) {
					logger.info("column={},remark={}", columnName, column.getRemarks());
				}
				String methodName = Character.toUpperCase(columnName.charAt(0)) + columnName.substring(1);
				methodCopy.addBodyLine("bean.set" + methodName + "(get" + methodName + "());");
			}
			methodCopy.addBodyLine("return bean;");
			topLevelClass.addMethod(methodCopy);
		}
	}

	protected void generateToString(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		if (Boolean.valueOf(properties.getProperty(genToString, "true"))) {
			Method methodToString = new Method();
			methodToString.setVisibility(JavaVisibility.PUBLIC);
			methodToString.addJavaDocLine("/** ");
			methodToString.addJavaDocLine(" * 格式化显示");
			methodToString.addJavaDocLine(" */ ");
			if (introspectedTable.isJava5Targeted()) {
				methodToString.addAnnotation("@Override"); //$NON-NLS-1$
			}
			methodToString.setName("toString");
			methodToString.setReturnType(FullyQualifiedJavaType.getStringInstance());

			methodToString.addBodyLine("StringBuilder sb = new StringBuilder(getClass().getSimpleName() + \" {\");");
			boolean isFirstField = true;
			for (IntrospectedColumn column : introspectedTable.getAllColumns()) {
				String columnName = column.getJavaProperty();
				String methodName = Character.toUpperCase(columnName.charAt(0)) + columnName.substring(1);
				if (isFirstField) {
					isFirstField = false;
					methodToString.addBodyLine("sb.append(\"" + columnName + "=\").append(get" + methodName + "());");
				} else {
					methodToString.addBodyLine("sb.append(\", " + columnName + "=\").append(get" + methodName + "());");
				}
			}
			methodToString.addBodyLine("return sb.append(\"}\").toString();");
			topLevelClass.addMethod(methodToString);
		}
	}

	protected void addFieldWithGetterAndSetter(TopLevelClass topLevelClass, IntrospectedTable introspectedTable,
			String name, FullyQualifiedJavaType fieldType, String initializationString, String doc) {
		Field field = new Field();
		field.setVisibility(JavaVisibility.PROTECTED);
		field.setType(fieldType);
		field.setName(name);
		field.setInitializationString(initializationString);
		addJavaDoc(field, doc);
		topLevelClass.addField(field);
		char c = name.charAt(0);
		String camel = Character.toUpperCase(c) + name.substring(1);
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("set" + camel);
		method.addParameter(new Parameter(fieldType, name));
		method.addBodyLine("this." + name + "=" + name + ";");
		addJavaDoc(method, doc);
		topLevelClass.addMethod(method);
		method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(fieldType);
		method.setName("get" + camel);
		method.addBodyLine("return " + name + ";");
		addJavaDoc(method, doc);
		topLevelClass.addMethod(method);
	}

	protected void addJavaDoc(JavaElement method, String doc) {
		if (Boolean.valueOf(properties.getProperty(genRemarkJavaDoc, "true"))) {
			if (stringHasValue(doc)) {
				method.addJavaDocLine("/** " + doc + " */");
			}
		}
	}
}