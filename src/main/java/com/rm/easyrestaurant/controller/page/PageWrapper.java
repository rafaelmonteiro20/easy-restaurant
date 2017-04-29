package com.rm.easyrestaurant.controller.page;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

public class PageWrapper<T> {

	private Page<T> page;
	
	private UriComponentsBuilder uriBuilder;

	public PageWrapper(Page<T> page, HttpServletRequest request) {
		this.uriBuilder = ServletUriComponentsBuilder.fromRequest(request);
		this.page = page;
	}
	
	public List<T> getContent() {
		return page.getContent();
	}
	
	public boolean isEmpty() {
		return page.getContent().isEmpty();
	}
	
	public int getCurrent() {
		return page.getNumber();
	}
	
	public boolean isFirst() {
		return page.isFirst();
	}
	
	public boolean isLast() {
		return page.isLast();
	}
	
	public int getTotal() {
		return page.getTotalPages();
	}
	
	public String uriToPage(int pagina) {
		return uriBuilder.replaceQueryParam("page", pagina)
						 .build(true).encode().toUriString();
	}
	
	public String uriOrder(String property) {
		UriComponentsBuilder uriBuilderOrder = UriComponentsBuilder
				.fromUriString(uriBuilder.build(true).encode().toUriString());
		
		String valorSort = String.format("%s,%s", property, reverseOrder(property));
		
		return uriBuilderOrder.replaceQueryParam("sort", valorSort).build(true).encode().toUriString();
	}
	
	public String reverseOrder(String property) {
		String direction = "asc";
		
		Order order = page.getSort() != null ? page.getSort().getOrderFor(property) : null;
		if (order != null)
			direction = Sort.Direction.ASC.equals(order.getDirection()) ? "desc" : "asc";
		
		return direction;
	}
	
	public boolean isDesc(String propriedade) {
		return reverseOrder(propriedade).equals("asc");
	}
	
	public boolean isOrdered(String property) {
		Sort sort = page.getSort();
		
		Order order = sort != null ? sort.getOrderFor(property) : null; 
		
		if (order == null)
			return false;
		
		return sort.getOrderFor(property) != null ? true : false;
	}
	
}