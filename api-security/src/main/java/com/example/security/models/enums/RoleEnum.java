package com.example.security.models.enums;

public enum RoleEnum {
	
	ROLE_USER(1, "ROLE_USER"),
	ROLE_ADMIN(2, "ROLE_ADMIN");
	
	private int cod;
	private String desc;
	
	private RoleEnum(int cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public static RoleEnum toEnum(String desc) {
		
		if (desc == null) {
			return null;
		}
		
		for (RoleEnum index : RoleEnum.values()) {
			
			if (desc.equals(index.getDesc())) {
				return index;
			}
		}
		
		throw new IllegalArgumentException("Enum inválido: " + desc);
	}

}
