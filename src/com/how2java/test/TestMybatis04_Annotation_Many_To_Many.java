package com.how2java.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.how2java.mapper.OrderMapper;
import com.how2java.pojo.Order;
import com.how2java.pojo.OrderItem;

public class TestMybatis04_Annotation_Many_To_Many {
	public static void main(String[] args) throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		
		listOrder(session);
		
		session.commit();
		session.close();
	}

	private static void listOrder(SqlSession session) {
		OrderMapper mapper = session.getMapper(OrderMapper.class);
		List<Order> os = mapper.list();
		for (Order order : os) {
			System.out.println(order.getCode());
			List<OrderItem> ois = order.getOrderItems();
			if(null != ois){
				for (OrderItem orderItem : ois) {
					System.out.format("\t%s\t%f\t%d%n", orderItem.getProduct().getName(),orderItem.getProduct().getPrice(),orderItem.getNumber());
				}
			}
		}
	}
	
}
