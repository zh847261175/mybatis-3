package com.haode.mybatis.builder;

import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zhuh
 * @date 2018/12/11
 **/
public class MyJavaBuilderTest {

	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void  initSqlSessionFactory(){
		Reader resourceAsReader = null;
		try {
			resourceAsReader = Resources.getResourceAsReader("com/haode/mybatis/builder/mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsReader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void xmlBuild(){
		try {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			List<HashMap> name = sqlSession.selectList("com.haode.mybatis.BlogMapper.selectAllCity",null,new RowBounds(0,20));
			System.out.println(name);
			name = sqlSession.selectList("com.haode.mybatis.BlogMapper.selectAllCity",null,new RowBounds(21,20));
			System.out.println(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void cacheSecond(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		SqlSession sqlSession2 = sqlSessionFactory.openSession();
		List<HashMap> name = sqlSession.selectList("com.haode.mybatis.BlogMapper.selectAllCity",null,new RowBounds(0,20));
		name = sqlSession2.selectList("com.haode.mybatis.BlogMapper.selectAllCity",null,new RowBounds(0,20));
	}
}
