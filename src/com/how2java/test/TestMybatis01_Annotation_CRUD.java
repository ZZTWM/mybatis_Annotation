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

/**
 * 注解：CRUD[增、删、改、查]
 * @author Administrator
 *
 */
public class TestMybatis01_Annotation_CRUD {
	public static void main(String[] args) throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		CategoryMapper mapper = session.getMapper(CategoryMapper.class);
		
		add(mapper);
		//delete(mapper);
		//get(mapper);
		//update(mapper);
		//listAll(mapper);
		
		session.commit();
		session.close();
	}

	/**
	 * 查询所有
	 * @param mapper
	 */
	private static void listAll(CategoryMapper mapper) {
		List<Category> cs = mapper.list();
		for (Category category : cs) {
			System.out.println(category.getName()); 
		}
	}
	/**
	 * 更新
	 * @param mapper
	 */
	private static void update(CategoryMapper mapper) {
		Category c = mapper.get(17);
		c.setName("OPPO");
		mapper.update(c);
		listAll(mapper);
	}
	/**
	 * 获取
	 * @param mapper
	 */
	private static void get(CategoryMapper mapper) {
		Category c = mapper.get(1);
		System.out.println(c.getName());
	}

	/**
	 * 删除
	 * @param mapper
	 */
	private static void delete(CategoryMapper mapper) {
		mapper.delete(18);
		listAll(mapper);
	}
	/**
	 * 增加
	 * @param mapper
	 */
	private static void add(CategoryMapper mapper) {
		Category c = new Category();
		c.setName("VIVO");
		mapper.add(c);
		listAll(mapper);
	}
}
