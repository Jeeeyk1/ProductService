package com.example.Microservices.ProductService.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long productId;

	@Column(name ="PRICE")
	private long price;
	@Column(name = "PRODUCT_NAME")
	private String productName;
	@Column(name = "QUANTITY")
	private long quantity;
}
