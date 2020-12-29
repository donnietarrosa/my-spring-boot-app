package com.dmt.mapper;

import com.dmt.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MyBatisUserMapper {

    @Select("SELECT id, first_name, last_name, email FROM users")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "email", column = "email")
    })
    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    public List<User> findAll();

    @Select("SELECT id, first_name, last_name, email FROM users WHERE id = #{userId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "email", column = "email")
    })
    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    public User findById(long userId);

    @Select("INSERT INTO users(first_name, last_name, email) values(#{firstName}, #{lastName}, #{email}) " +
            "RETURNING id, first_name, last_name, email")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "email", column = "email")
    })
    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    public User save(User user);

    @Select("UPDATE users SET first_name = #{user.firstName}, last_name = #{user.lastName}, email = #{user.email} " +
            "WHERE id = #{userId} RETURNING id, first_name, last_name, email")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "email", column = "email")
    })
    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    public User update(User user, long userId);

    @Delete("DELETE FROM users WHERE id = #{userId} RETURNING id, first_name, last_name, email")
    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    public void delete(long userId);
}
