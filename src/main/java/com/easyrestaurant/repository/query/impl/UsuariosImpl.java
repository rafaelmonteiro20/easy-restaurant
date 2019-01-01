package com.easyrestaurant.repository.query.impl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.easyrestaurant.model.User;
import com.easyrestaurant.repository.filter.UserFilter;
import com.easyrestaurant.repository.query.UsuariosQueries;

public class UsuariosImpl implements UsuariosQueries {

	@PersistenceContext
	private EntityManager manager;
	
//	@Autowired
//	private PaginationUtil paginacaoUtil;

	@SuppressWarnings({"unchecked", "deprecation"})
	@Transactional(readOnly = true)
	public Page<User> pesquisar(UserFilter filtro, Pageable pageable) {
//		Criteria criteria = manager.unwrap(Session.class).createCriteria(User.class);
//		
////		paginacaoUtil.configure(criteria, pageable);
//		adicionarFiltro(filtro, criteria);
//		
//		List<User> filtrados = criteria.list();
//		filtrados.forEach(u -> Hibernate.initialize(u.getGrupos()));
		
		return new PageImpl<>(new ArrayList<>(), pageable, 0);
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