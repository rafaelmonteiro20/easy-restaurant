package com.easyrestaurant.repository.pagination;

import javax.persistence.Query;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class PaginationUtil {

	public void configure(Query query, Pageable pageable) {
		
		int pageSize = pageable.getPageSize();
		int firstResult = pageable.getPageNumber() * pageSize;

		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
//
//		Sort sort = pageable.getSort();
//		
//		if (sort != null) {
//			Sort.Order order = sort.iterator().next();
//			String property = order.getProperty();
//			
//			
//			query.addOrder(order.isAscending() ? Order.asc(property) : Order.desc(property));
//		}
	}
	
}
