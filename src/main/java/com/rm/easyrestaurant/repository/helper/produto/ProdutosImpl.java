package com.rm.easyrestaurant.repository.helper.produto;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.rm.easyrestaurant.model.Produto;
import com.rm.easyrestaurant.repository.filter.ProdutoFilter;

public class ProdutosImpl implements ProdutosQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override @Transactional(readOnly = true)
	public List<Produto> filtrar(ProdutoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Produto.class);
		
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

		return criteria.list();
	}

}