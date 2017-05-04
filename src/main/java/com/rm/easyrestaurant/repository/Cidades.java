package com.rm.easyrestaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rm.easyrestaurant.model.Cidade;
import com.rm.easyrestaurant.model.Estado;

public interface Cidades extends JpaRepository<Cidade, Long> {

	public List<Cidade> findByEstado(Estado estado);
	
}