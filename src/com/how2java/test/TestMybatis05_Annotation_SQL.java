package com.how2java.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.how2java.mapper.CategoryMapper;
import com.how2java.pojo.Category;

public class TestMybatis05_Annotation_SQL {
	public static void main(String[] args) throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		CategoryMapper mapper = session.getMapper(CategoryMapper.class);
		
		//add(mapper);
		//delete(mapper);
		//get(mapper);
		//update(mapper);
		
		listAll(mapper);
		
		session.commit();
		session.close();
	}

	private static void listAll(CategoryMapper mapper) {
		List<Category> cs = mapper.list();
		for (Category category : cs) {
			System.out.println(category.getName());
		}
	}

	private static void update(CategoryMapper mapper) {
		Category c = mapper.get(17);
		c.setName(c.getName() + "ÐÞ¸Äºó");
		mapper.update(c);
		listAll(mapper);
	}

	private static void get(CategoryMapper mapper) {
		Category c = mapper.get(17);
		System.out.println(c.getName());
	}

	private static void delete(CategoryMapper mapper) {
		mapper.delete(17);
		listAll(mapper);
	}

	private static void add(CategoryMapper mapper) {
		List<Category> cs = mapper.list();
		for (Category category : cs) {
			System.out.println(category.getName());
		}
	}
}
