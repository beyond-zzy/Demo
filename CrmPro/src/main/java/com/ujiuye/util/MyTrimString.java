package com.ujiuye.util;

import org.springframework.core.convert.converter.Converter;

/*
 * 去除表单数据前后空格的转换器
 * */
public class MyTrimString implements Converter<String,String> {

	@Override
	public String convert(String arg0) {
		return arg0.trim();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
