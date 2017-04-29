package com.rm.easyrestaurant.repository.pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PaginacaoUtil {

	public void configurar(Criteria criteria, Pageable pageable) {
		int pageSize = pageable.getPageSize();
		int firstResult = pageable.getPageNumber() * pageSize;

		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(pageSize);

		Sort sort = pageable.getSort();
		
		if (sort != null) {
			Sort.Order order = sort.iterator().next();
			String property = order.getProperty();
			criteria.addOrder(order.isAscending() ? Order.asc(property) : Order.desc(property));
		}
	}
}