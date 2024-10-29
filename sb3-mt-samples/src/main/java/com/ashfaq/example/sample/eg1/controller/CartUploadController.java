package com.ashfaq.example.sample.eg1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashfaq.example.sample.eg1.entity.CartUpload;
import com.ashfaq.example.sample.eg1.repo.CartUploadRepository;

@RestController
@RequestMapping("/api/uploads")
public class CartUploadController {

	@Autowired
	private CartUploadRepository cartUploadService;

	/**
	 * Get all Uploads.
	 *
	 * @return ResponseEntity with a list of all uploads
	 */
	@GetMapping
	public ResponseEntity<List<CartUpload>> getAllUploads() {
		List<CartUpload> uploads = cartUploadService.findAll();
		return ResponseEntity.ok(uploads);
	}

	/**
	 * Get an Upload by ID.
	 *
	 * @param uploadId the ID of the Upload
	 * @return ResponseEntity with the Upload or NOT_FOUND status
	 */
	@GetMapping("/{uploadId}")
	public ResponseEntity<CartUpload> getUploadById(@PathVariable String uploadId) {
		return cartUploadService.findById(uploadId).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
}
