package com.peony.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TestDAO {

	@Select("select * from t_ws_web_order where id = #{id}")
	public Map<String, Object> getById(@Param("id") int id);

}
