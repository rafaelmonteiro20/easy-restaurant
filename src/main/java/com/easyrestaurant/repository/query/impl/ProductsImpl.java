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

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.easyrestaurant.dto.ProductDTO;
import com.easyrestaurant.model.Product;
import com.easyrestaurant.repository.filter.ProductFilter;
import com.easyrestaurant.repository.pagination.PaginationUtil;
import com.easyrestaurant.repository.query.ProductsQueries;

public class ProductsImpl implements ProductsQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginationUtil paginationUtil;

	@Override
	@Transactional(readOnly = true)
	public Page<Product> findAll(ProductFilter filter, Pageable pageable) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
		Root<Product> root = criteria.from(Product.class);
		
		root.fetch("category");
		
		criteria.select(root);
		
		criteria.where(restrictions(builder, criteria, root, filter));
		
		int pageSize = pageable.getPageSize();
		int firstResult = pageable.getPageNumber() * pageSize;
		
		TypedQuery<Product> query = manager.createQuery(criteria)
				.setFirstResult(firstResult)
				.setMaxResults(pageSize);
		
		
		
		List<Product> result = query.getResultList();
		
//		paginationUtil.configure(criteria, pageable);

		return new PageImpl<>(result, pageable, count(filter));
	}

	private Long count(ProductFilter filter) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Product> root = criteria.from(Product.class);
		
		criteria.select(builder.count(root));
		criteria.where(restrictions(builder, criteria, root, filter));
		
		return manager.createQuery(criteria).getSingleResult();
	}

	private Predicate[] restrictions(CriteriaBuilder builder, CriteriaQuery<?> criteria,
			Root<Product> root, ProductFilter filter) {
		
		List<Predicate> restrictions = new ArrayList<>();

		if(!StringUtils.isEmpty(filter.getSku())) {
			restrictions.add(builder.equal(builder.upper(root.get("sku")), filter.getSku()));
		}
		
		if(!StringUtils.isEmpty(filter.getName())) {
			restrictions.add(builder.like(builder.upper(root.get("name")), "%" + filter.getName().toUpperCase() + "%"));
		}

		if(filter.getCategory() != null) {
			restrictions.add(builder.equal(root.get("category"), filter.getCategory()));
		}

		return restrictions.toArray(new Predicate[0]);
	}

	@Override
	public List<ProductDTO> findBySkuOrName(String skuOuNome) {
		String jpql = "select new com.easyrestaurant.dto.ProductDTO("
					+ "id, sku, name, category.name, price, image) from Produto "
					+ "where lower(sku) like lower(:skuOuNome) or lower(nome) like lower(:skuOuNome)";
		
		return manager.createQuery(jpql, ProductDTO.class)
					  .setParameter("skuOuNome", skuOuNome + "%")
					  .getResultList();
	}
	
}
