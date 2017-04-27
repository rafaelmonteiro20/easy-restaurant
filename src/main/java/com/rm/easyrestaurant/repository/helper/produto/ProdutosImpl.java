package com.rm.easyrestaurant.repository.helper.produto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.rm.easyrestaurant.model.Produto;
import com.rm.easyrestaurant.repository.filter.ProdutoFilter;

public class ProdutosImpl implements ProdutosQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override @Transactional(readOnly = true)
	public Page<Produto> filtrar(ProdutoFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Produto.class);
		
		int pageSize = pageable.getPageSize();
		int firstResult = pageable.getPageNumber() * pageSize;
		
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(pageSize);
		
		adicionarFiltro(filtro, criteria);

		return new PageImpl<>(criteria.list(), pageable, count(filtro)) ;
	}
	
	@SuppressWarnings("deprecation")
	private Long count(ProdutoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Produto.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private void adicionarFiltro(ProdutoFilter filtro, Criteria criteria) {
		if(filtro != null) {
			if(!StringUtils.isEmpty(filtro.getSku())) {
				criteria.add(Restrictions.eq("sku", filtro.getSku()));
			}
			
			if(!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}
			
			if(filtro.getCategoria() != null) {
				criteria.add(Restrictions.eq("categoria", filtro.getCategoria()));
			}
		}
	}
}