package com.angular.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ProductRequestDto implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotBlank
    private String name;
	
    @Min(0) // Valor não pode ser menor do que 0
    private Long price;

    public ProductRequestDto() {
    }

    public ProductRequestDto(@NotBlank String name, @Min(0) Long price) {
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

	public void setPrice(Long price) {
		this.price = price;
	}
    
    

}
