package com.example.apiautheticationserver.models.enums;

public enum RoleEnum {
	
	USER(0, "USER"),
	ADMIN(1, "ADMIN");
	
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
