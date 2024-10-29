package com.ashfaq.example.sample.eg1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashfaq.example.sample.eg1.entity.Cart;
import com.ashfaq.example.sample.eg1.repo.CartRepository;
import com.ashfaq.example.sample.eg1.service.CartService;

@RestController
@RequestMapping("/api/carts")
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private CartRepository cartRepository;

	/**
	 * Create a new Cart item.
	 *
	 * @param cart the Cart item to create
	 * @return ResponseEntity with the created Cart item
	 */
	@PostMapping
	public ResponseEntity<Cart> addCart(@RequestBody Cart cart) {
		Cart createdCart = cartService.addSingleCart(cart);
		return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
	}

	@PostMapping("/list")
	public ResponseEntity<List<Cart>> addCarts(@RequestBody List<Cart> cart) {
		List<Cart> cartList = cartRepository.saveAll(cart);
		return new ResponseEntity<>(cartList, HttpStatus.CREATED);
	}

	/**
	 * Get a Cart item by ID.
	 *
	 * @param id the ID of the Cart item
	 * @return ResponseEntity with the Cart item or NOT_FOUND status
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
		Optional<Cart> cart = cartService.getCartById(id);
		return cart.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * Get all Cart items.
	 *
	 * @return ResponseEntity with a list of all Cart items
	 */
	@GetMapping
	public ResponseEntity<List<Cart>> getAllCarts() {
		List<Cart> carts = cartService.getAllCarts();
		return ResponseEntity.ok(carts);
	}

	/**
	 * Update a Cart item.
	 *
	 * @param cart the updated Cart item
	 * @return ResponseEntity with the update status
	 */
	@PutMapping
	public ResponseEntity<Boolean> updateCart(@RequestBody Cart cart) {
		boolean isUpdated = cartService.updateSingleCart(cart);
		return isUpdated ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
	}

	/**
	 * Delete a Cart item by ID.
	 *
	 * @param id the ID of the Cart item to delete
	 * @return ResponseEntity with the deletion status
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteCart(@PathVariable Long id) {
		boolean isDeleted = cartService.deleteSingleCart(id);
		return isDeleted ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
	}

	/**
	 * Batch update of Cart items.
	 *
	 * @param cartList list of Cart items to update
	 * @return ResponseEntity with the upload ID for tracking
	 */
	@PostMapping("/batch-update")
	public ResponseEntity<String> updateCartBatch(@RequestBody List<Cart> cartList) {
		String uploadId = cartService.updateCartBatch(cartList);
		return ResponseEntity.ok(uploadId);
	}

//async

	@PostMapping("/mass-update")
	public ResponseEntity<String> massUpdateCarts(@RequestBody List<Cart> carts) {
		// Calls the service for asynchronous processing and immediately returns
		// uploadId
		String uploadId = cartService.initiateMassUpdate(carts);
		return ResponseEntity.ok(uploadId);
	}

}
