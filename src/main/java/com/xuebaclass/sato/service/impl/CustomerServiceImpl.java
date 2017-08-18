package com.xuebaclass.sato.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuebaclass.sato.common.SatoSort;
import com.xuebaclass.sato.mapper.crm.CustomerMapper;
import com.xuebaclass.sato.model.Customer;
import com.xuebaclass.sato.model.request.DistributionRequest;
import com.xuebaclass.sato.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {
	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public void create(Customer customer) {
		customerMapper.create(customer);
	}

	@Override
	public void update(Customer customer) {
		customerMapper.update(customer);
	}

	@Override
	public Customer getById(String id) {
		return customerMapper.getById(id);
	}

	@Override
	public Page<Customer> getCustomers(Pageable pageable, Customer customer) {
		PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize());
		String sort = SatoSort.getSort(pageable, "CREATED_DATE");
		PageInfo<Customer> pageInfo = new PageInfo<>(customerMapper.getCustomers(sort, customer));
		return new PageImpl<Customer>(pageInfo.getList(), pageable, pageInfo.getTotal());
	}

	@Override
	public Page<Customer> getMyselfCustomers(Pageable pageable, Customer customer) {
		PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize());
		String sort = SatoSort.getSort(pageable, "CREATED_DATE");
		PageInfo<Customer> pageInfo = new PageInfo<>(customerMapper.getMyselfCustomers(sort, customer));
		return new PageImpl<Customer>(pageInfo.getList(), pageable, pageInfo.getTotal());
	}

	@Override
	public void distribution(DistributionRequest request){
		customerMapper.distribution(request);
	}
}
