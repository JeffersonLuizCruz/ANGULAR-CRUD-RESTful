package com.angular.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ProductRequestDto {
	
    @NotBlank
    private String name;
    @Min(0)
    private long price;

    public ProductRequestDto() {
    }

    public ProductRequestDto(@NotBlank String name, @Min(0) long price) {
        this.name = name;
        this.price = price;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}
    
    

}
