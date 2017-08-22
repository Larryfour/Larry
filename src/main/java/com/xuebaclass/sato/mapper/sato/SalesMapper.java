package com.xuebaclass.sato.mapper.sato;

import com.xuebaclass.sato.model.Sales;
import org.apache.ibatis.annotations.Select;

public interface SalesMapper {

	@Select("SELECT * FROM SALES WHERE USERNAME = #{userName} AND STATUS = 0 LIMIT 1")
	Sales getSalesByUserName(String userName);

}
