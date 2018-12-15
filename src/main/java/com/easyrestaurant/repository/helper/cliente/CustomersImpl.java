package com.easyrestaurant.repository.helper.cliente;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.easyrestaurant.model.Customer;
import com.easyrestaurant.repository.filter.CustomerFilter;
import com.easyrestaurant.repository.pagination.PaginationUtil;

@Repository
public class CustomersImpl implements CustomersQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginationUtil paginacao;

	@Override 
	@Transactional(readOnly = true)
	public Page<Customer> findAll(CustomerFilter filter, Pageable pageable) {
		return new PageImpl<>(new ArrayList<>(), pageable, count(filter));
	}

	@SuppressWarnings("deprecation")
	private Long count(CustomerFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cliente.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(CustomerFilter filtro, Criteria criteria) {
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}

			if (!StringUtils.isEmpty(filtro.getDocumento())) {
				criteria.add(Restrictions.ilike("documento", filtro.getDocumento(), MatchMode.ANYWHERE));
			}
		}
	}
	
}
