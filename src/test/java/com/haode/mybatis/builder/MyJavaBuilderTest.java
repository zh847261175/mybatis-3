package com.haode.mybatis.builder;

import com.haode.mybatis.builder.entity.City;
import com.haode.mybatis.builder.entity.User;
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
		List<HashMap> name = sqlSession.selectList("com.haode.mybatis.BlogMapper.selectAllCity",null);
		sqlSession.commit();
		name = sqlSession2.selectList("com.haode.mybatis.BlogMapper.selectAllCity",null);
	}
	@Test
	public void lazeEnabledAndagreessiveProxyEnabledTest(){
		SqlSession session = sqlSessionFactory.openSession();
		City cites = session.selectOne("com.haode.mybatis.BlogMapper.selectCityAndCountry");
		System.out.println(cites.getId());
		//		System.out.println(objects);
	}

	@Test
	public void useGeneratedKeysTest(){
		SqlSession session = sqlSessionFactory.openSession();
		User user = new User();
		user.setName("zhuhao");
		int insert = session.insert("com.haode.mybatis.BlogMapper.addOneUser", user);
		session.commit();
		System.out.println(user.getId());
	}

}
