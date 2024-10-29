package com.ashfaq.example.sample.eg1.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ashfaq.example.sample.eg1.entity.Cart;
import com.ashfaq.example.sample.eg1.entity.CartUpload;
import com.ashfaq.example.sample.eg1.entity.CartUploadItemStatus;
import com.ashfaq.example.sample.eg1.repo.CartRepository;
import com.ashfaq.example.sample.eg1.repo.CartUploadItemStatusRepository;
import com.ashfaq.example.sample.eg1.repo.CartUploadRepository;

@Service
public class CartService {

	/*
	 * Mass update is created with 2 different ways 1. Async annotation and 2.
	 * ExecutorService and completable future
	 */

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ExecutorService executorService;
	// using ExecutorService and completable future to create massupdate

	@Autowired
	private CartUploadRepository cartUploadRepository;

	@Autowired
	private CartUploadItemStatusRepository cartUploadItemStatusRepository;

	/**
	 * Adds a single Cart item to the repository.
	 *
	 * @param cart the Cart item to add
	 * @return the saved Cart item
	 */
	public Cart addSingleCart(Cart cart) {
		return cartRepository.save(cart);
	}

	/**
	 * Retrieves a single Cart item by ID.
	 *
	 * @param id the ID of the Cart item
	 * @return an Optional containing the Cart if found, otherwise empty
	 */
	public Optional<Cart> getCartById(Long id) {
		return cartRepository.findById(id);
	}

	/**
	 * Retrieves all Cart items.
	 *
	 * @return list of all Cart items
	 */
	public List<Cart> getAllCarts() {
		return cartRepository.findAll();
	}

	/**
	 * Updates a single Cart item and returns whether the update was successful.
	 *
	 * @param cart the Cart item to update
	 * @return true if update was successful, false otherwise
	 */
	public boolean updateSingleCart(Cart cart) {
		Optional<Cart> existingCart = cartRepository.findById(cart.getId());

		if (existingCart.isPresent()) {
			Cart updatedCart = existingCart.get();

			// Update fields if they are not null
			if (cart.getItemName() != null)
				updatedCart.setItemName(cart.getItemName());

			if (cart.getPrice() != null)
				updatedCart.setPrice(cart.getPrice());

			if (cart.getCategory() != null)
				updatedCart.setCategory(cart.getCategory());

			cartRepository.save(updatedCart);

			return true;
		}

		return false;
	}

	/**
	 * Deletes a single Cart item by ID.
	 *
	 * @param id the ID of the Cart item to delete
	 * @return true if deletion was successful, false otherwise
	 */
	public boolean deleteSingleCart(Long id) {
		if (cartRepository.existsById(id)) {
			cartRepository.deleteById(id);
			return true;
		}
		return false;
	}

	/**
	 * Processes a batch update of Cart items and tracks the operation.
	 *
	 * @param cartList List of Cart items to be updated
	 * @return the upload ID to track the batch update
	 */
	@Transactional
	public String updateCartBatch(List<Cart> cartList) {
		CartUpload cartUpload = new CartUpload(); // This will auto-generate an upload ID and set initial status
		cartUploadRepository.save(cartUpload);

		cartList.forEach(cart -> {
			boolean isSuccess = updateSingleCart(cart);

			// Track status for each Cart item in the batch
			CartUploadItemStatus status = new CartUploadItemStatus();
			status.setUploadId(cartUpload.getUploadId());
			status.setCartId(cart.getId());
			status.setStatus(isSuccess ? "SUCCESS" : "FAILED");
			status.setComment(isSuccess ? "Update successful" : "Item not found or update failed");

			cartUploadItemStatusRepository.save(status);
		});

		// Update main upload status to completed
		cartUpload.setStatus("COMPLETED");
		cartUploadRepository.save(cartUpload);

		return cartUpload.getUploadId(); // Return the unique ID for tracking
	}

	public String initiateMassUpdate(List<Cart> carts) {
		// Create a new tracking record for this upload
		CartUpload uploadTracking = new CartUpload();
		uploadTracking.setUploadId("ASYNC - " + UUID.randomUUID().toString());
		cartUploadRepository.save(uploadTracking);

		String uploadId = uploadTracking.getUploadId();

		// For each cart item, initiate an asynchronous update
		carts.forEach(cart -> updateCartAsync(cart, uploadId));

		// Return the unique uploadId immediately
		return uploadId;
	}

	@Async("asyncExecutor")
	public CompletableFuture<Boolean> updateCartAsync(Cart cart, String uploadId) {
		try {
			// Perform the update operation for a single cart item
			Cart updatedCart = cartRepository.save(cart);

			boolean updateSingleCart = updateSingleCart(cart);

			if (updateSingleCart) {
				// Track individual item update status
				CartUploadItemStatus itemStatus = new CartUploadItemStatus();
				itemStatus.setUploadId(uploadId);
				itemStatus.setCartId(updatedCart.getId());
				itemStatus.setStatus("SUCCESS");
				itemStatus.setComment("Update successful");
				cartUploadItemStatusRepository.save(itemStatus);
				return CompletableFuture.completedFuture(true);
			} else {
				CartUploadItemStatus itemStatus = new CartUploadItemStatus();
				itemStatus.setUploadId(uploadId);
				itemStatus.setCartId(cart.getId());
				itemStatus.setStatus("FAILED");
				itemStatus.setComment("Item not found or update failed");
				cartUploadItemStatusRepository.save(itemStatus);
				return CompletableFuture.completedFuture(false);

			}
		} catch (Exception e) {
			// Handle failure and save item status as "FAILED"
			CartUploadItemStatus itemStatus = new CartUploadItemStatus();
			itemStatus.setUploadId(uploadId);
			itemStatus.setCartId(cart.getId());
			itemStatus.setStatus("FAILED");
			itemStatus.setComment(e.getMessage());
			cartUploadItemStatusRepository.save(itemStatus);
			return CompletableFuture.completedFuture(false);
		}
	}

	
//	2. Mass update using ExecutorService and completable future

//	 public String initiateMassUpdate(List<Cart> carts) {
//	        String uploadId = UUID.randomUUID().toString();
//
//	        // Create the upload tracking entry
//	        CartUploadTracking uploadTracking = new CartUploadTracking();
//	        uploadTracking.setUploadId(uploadId);
//	        uploadTracking.setStatus("IN_PROGRESS");
//	        uploadTracking.setTimestamp(LocalDateTime.now());
//	        cartUploadTrackingRepository.save(uploadTracking);
//
//	        // Initiate the asynchronous update for each cart item
//	        for (Cart cart : carts) {
//	            CompletableFuture.runAsync(() -> updateCart(cart, uploadId), executorService);
//	        }
//
//	        return uploadId; // Return the upload ID immediately
//	    }
//
//	    private void updateCart(Cart cart, String uploadId) {
//	        try {
//	            // Perform the update operation for a single cart item
//	            // Implement your logic to update the cart
//	            boolean updateSuccess = updateSingleCart(cart);
//
//	            // Track individual item update status
//	            CartUploadItemStatus itemStatus = new CartUploadItemStatus();
//	            itemStatus.setUploadId(uploadId);
//	            itemStatus.setCartId(cart.getId());
//	            itemStatus.setStatus(updateSuccess ? "SUCCESS" : "FAILED");
//	            cartUploadItemStatusRepository.save(itemStatus);
//
//	        } catch (Exception e) {
//	            // Handle failure and save item status as "FAILED"
//	            CartUploadItemStatus itemStatus = new CartUploadItemStatus();
//	            itemStatus.setUploadId(uploadId);
//	            itemStatus.setCartId(cart.getId());
//	            itemStatus.setStatus("FAILED");
//	            itemStatus.setComment(e.getMessage());
//	            cartUploadItemStatusRepository.save(itemStatus);
//	        }
//	    }

}
