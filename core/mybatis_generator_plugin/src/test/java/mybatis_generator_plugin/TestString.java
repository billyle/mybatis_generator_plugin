package mybatis_generator_plugin;

import java.io.File;

public class TestString {
	public static void main(String[] args) {
		String s = "tv.jufan.db";
		String a = s.replaceAll("\\.", "\\");
		System.out.println(a);
	}
}
