package com.easyrestaurant.repository.query.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.easyrestaurant.model.Customer;
import com.easyrestaurant.repository.filter.CustomerFilter;
import com.easyrestaurant.repository.query.CustomersQueries;

@Repository
public class CustomersImpl implements CustomersQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Override 
	@Transactional(readOnly = true)
	public Page<Customer> findAll(CustomerFilter filter, Pageable pageable) {
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Customer> criteria = cb.createQuery(Customer.class);
		Root<Customer> root = criteria.from(Customer.class);
		
		criteria.select(root);
		criteria.where(restrictions(cb, criteria, root, filter));
		
		int pageSize = pageable.getPageSize();
		int firstResult = pageable.getPageNumber() * pageSize;
		
		TypedQuery<Customer> query = manager.createQuery(criteria)
				.setFirstResult(firstResult)
				.setMaxResults(pageSize);
		
		return new PageImpl<>(query.getResultList(), pageable, countAll(filter));
	}

	@Override
	public Long countAll(CustomerFilter filter) {
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
		Root<Customer> root = criteria.from(Customer.class);
		
		criteria.select(cb.count(root));
		criteria.where(restrictions(cb, criteria, root, filter));
		
		return manager.createQuery(criteria).getSingleResult();
	}

	private Predicate[] restrictions(CriteriaBuilder cb, CriteriaQuery<?> criteria, Root<Customer> root,
			CustomerFilter filter) {
		
		List<Predicate> restrictions = new ArrayList<>();
		
		if(!StringUtils.isEmpty(filter.getName())) {
			restrictions.add(cb.like(cb.upper(root.get("name")), "%" + filter.getName().toUpperCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(filter.getDocument())) {
			restrictions.add(cb.equal(root.get("document"), filter.getDocument()));
		}
		
		return restrictions.toArray(new Predicate[0]);
	}
	
}
