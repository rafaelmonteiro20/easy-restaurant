package com.rm.easyrestaurant.controller.page;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
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
	
}