package com.how2java.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.how2java.CategoryDynaSqlProvider;
import com.how2java.pojo.Category;

public interface CategoryMapper {
	
	@InsertProvider(type=CategoryDynaSqlProvider.class,method="add")
	public int add(Category category);
	
	@DeleteProvider(type=CategoryDynaSqlProvider.class,method="delete")
	public void delete(int id);
	
	@SelectProvider(type=CategoryDynaSqlProvider.class,method="get")
	public Category get(int id);
	
	@SelectProvider(type=CategoryDynaSqlProvider.class,method="update")
	public int update(Category category);
	
	@SelectProvider(type=CategoryDynaSqlProvider.class,method="list")
    @Results({ 
                @Result(property = "id", column = "id"),
                @Result(property = "products", javaType = List.class, column = "id", many = 
                @Many(select = "com.how2java.mapper.ProductMapper.listByCategory") )
            })
	public List<Category> list();
	
}
