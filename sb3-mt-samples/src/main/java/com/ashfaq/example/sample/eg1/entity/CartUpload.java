package com.ashfaq.example.sample.eg1.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "cart_uploads")
@Data
public class CartUpload {

	@Id
	private String uploadId;
	private LocalDateTime timestamp;
	private String status; // IN_PROGRESS, COMPLETED, FAILED

	public CartUpload() {
		this.uploadId = UUID.randomUUID().toString();
		this.timestamp = LocalDateTime.now();
		this.status = "IN_PROGRESS";
	}
}
