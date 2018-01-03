package com.xuebaclass.sato.mapper.sato;

import com.xuebaclass.sato.model.Sales;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SalesMapper {

	/**
	 * @param userName
	 * @return
	 */
	@Select("SELECT * FROM SALES WHERE USERNAME = #{userName} AND STATUS = 0 LIMIT 1")
	Sales getSalesByUserName(String userName);

	/**
	 * @param id
	 * @return
	 */
	@Select("SELECT * FROM SALES WHERE ID = #{id} AND STATUS = 0 LIMIT 1")
	Sales get(String id);

	/**
	 * @param
	 * @return
	 */
	@Select("SELECT * FROM SALES WHERE STATUS = 0")
	List<Sales> getSales();

}
