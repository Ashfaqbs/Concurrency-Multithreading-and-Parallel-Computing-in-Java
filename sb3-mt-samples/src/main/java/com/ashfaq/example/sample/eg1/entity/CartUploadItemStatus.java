package com.ashfaq.example.sample.eg1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "cart_upload_item_statuses")
@Data
public class CartUploadItemStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String uploadId;
	private Long cartId;
	private String status; // SUCCESS, FAILED
	private String comment;
}
