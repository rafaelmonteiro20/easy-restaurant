package com.easyrestaurant.repository.query.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.easyrestaurant.model.User;
import com.easyrestaurant.repository.filter.UserFilter;
import com.easyrestaurant.repository.query.UsersQueries;

public class UsersImpl implements UsersQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	@Transactional(readOnly = true)
	public Page<User> findAll(UserFilter filter, Pageable pageable) {
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<User> criteria = cb.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.select(root);
		
		List<User> result = manager.createQuery(criteria)
			.getResultList();
		
		return new PageImpl<>(result, pageable, result.size());
	}

	@Override
	public Long countAll(UserFilter filter) {
		return 10L;
	}

//	private void adicionarFiltro(UserFilter filtro, Criteria criteria) {
//		if (filtro != null) {
//			if (!StringUtils.isEmpty(filtro.getNome())) {
//				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
//			}
//			
//			if (filtro.hasGrupoSelecionado()) {
//				List<Criterion> subqueries = new ArrayList<>();
//				
//				for (Long codigoGrupo : getCodigosGrupos(filtro)) {
//					DetachedCriteria dc = DetachedCriteria.forClass(UsuarioGrupo.class);
//					dc.add(Restrictions.eq("id.grupo.codigo", codigoGrupo));
//					dc.setProjection(Projections.property("id.usuario"));
//					
//					subqueries.add(Subqueries.propertyIn("codigo", dc));
//				}
//				
//				Criterion[] criterions = new Criterion[subqueries.size()];
//				criteria.add(Restrictions.and(subqueries.toArray(criterions)));
//			}
//		}
//	}
	
//	@SuppressWarnings("deprecation")
//	private Long total(UserFilter filtro) {
//		Criteria criteria = manager.unwrap(Session.class).createCriteria(User.class);
//		adicionarFiltro(filtro, criteria);
//		criteria.setProjection(Projections.rowCount());
//		
//		return (Long) criteria.uniqueResult();
//	}
//	
//	private long[] getCodigosGrupos(UserFilter filtro) {
//		return filtro.getGrupos().stream().mapToLong(Grupo::getCodigo).toArray();
//	}
	
}
