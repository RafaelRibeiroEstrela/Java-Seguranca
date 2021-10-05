package com.example.apiautheticationserver.models;

import java.io.Serializable;

import com.example.apiautheticationserver.models.enums.RoleEnum;

public class Role implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	
	public Role() {
		
	}

	public Role(Long id, RoleEnum roleEnum) {
		super();
		this.id = id;
		this.name = roleEnum.getDesc();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleEnum getRoleEnum() {
		return RoleEnum.toEnum(name);
	}

	public void setRoleEnum(RoleEnum roleEnum) {
		this.name = roleEnum.getDesc();
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
	
	

}
