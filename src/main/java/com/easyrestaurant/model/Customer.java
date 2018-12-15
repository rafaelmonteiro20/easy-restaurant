package com.easyrestaurant.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import com.easyrestaurant.core.Identifiable;
import com.easyrestaurant.model.validation.CustomerGroupSequenceProvider;
import com.easyrestaurant.model.validation.group.CNPJGroup;
import com.easyrestaurant.model.validation.group.CPFGroup;

@Entity
@Table(name = "customer")
@GroupSequenceProvider(CustomerGroupSequenceProvider.class)
public class Customer implements Identifiable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	@CPF(groups = CPFGroup.class)
	@CNPJ(groups = CNPJGroup.class)
	private String document;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TypePerson type;

	private String phone;

	@Email
	private String mail;


	@Override
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public TypePerson getType() {
		return type;
	}

	public void setType(TypePerson type) {
		this.type = type;
	}

	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	@PrePersist @PreUpdate
	public void preSaveOrUpdate() {
		this.document = TypePerson.removeFormatting(document);
	}
	
	@PostLoad
	public void postLoad() {
		this.document = this.type.format(this.document);
	}
	
	@Override
	public String toString() {
		return "[" + name + ", " + type + ", " + document + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Customer))
			return false;
		Customer other = (Customer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
