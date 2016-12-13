package me.libin.mybatis.generator.plugin.test;

import java.util.Date;

import me.libin.db.model.TestBean;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestBean bean = new TestBean();
		bean.setId(1L);
		bean.setName("aa");
		bean.setAa(new Date());
		System.out.println(bean);
		
	}
}
