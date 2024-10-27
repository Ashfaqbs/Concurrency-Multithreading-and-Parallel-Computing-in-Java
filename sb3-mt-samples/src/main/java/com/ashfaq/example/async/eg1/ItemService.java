package com.ashfaq.example.async.eg1;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

	private final ItemRepository itemRepository;

	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	public Item createItem(Item item) {
		return itemRepository.save(item);
	}

	public Optional<Item> getItemById(Long id) {
		return itemRepository.findById(id);
	}

//	@Async // Async method that runs in a separate thread
//	public CompletableFuture<Item> updateItemAsync(Long itemId, Item newItemDetails) {
//		Optional<Item> optionalItem = itemRepository.findById(itemId);
//
//		if (optionalItem.isPresent()) {
//			Item itemToUpdate = optionalItem.get();
//			itemToUpdate.setName(newItemDetails.getName());
//			itemToUpdate.setDescription(newItemDetails.getDescription());
//			itemToUpdate.setPrice(newItemDetails.getPrice());
//
//			Item updatedItem = itemRepository.save(itemToUpdate);
//			return CompletableFuture.completedFuture(updatedItem);
//		} else {
//			return CompletableFuture.completedFuture(null); // Return null if item not found
//		}
//	}

	// defining the custom thread

	@Async("asyncExecutor") // Use custom executor defined in AsyncConfiguration
	public CompletableFuture<Item> updateItemAsync(Long itemId, Item newItemDetails) {
		Optional<Item> optionalItem = itemRepository.findById(itemId);

		if (optionalItem.isPresent()) {
			Item itemToUpdate = optionalItem.get();
			itemToUpdate.setName(newItemDetails.getName());
			itemToUpdate.setDescription(newItemDetails.getDescription());
			itemToUpdate.setPrice(newItemDetails.getPrice());

			Item updatedItem = itemRepository.save(itemToUpdate);
			return CompletableFuture.completedFuture(updatedItem);
		} else {
			return CompletableFuture.completedFuture(null);
		}
	}
	
	
	  // Normal update method
    public Item updateItem(Long id, Item item) {
        Optional<Item> existingItemOpt = itemRepository.findById(id);
        if (existingItemOpt.isPresent()) {
            Item existingItem = existingItemOpt.get();
            existingItem.setName(item.getName());
            existingItem.setPrice(item.getPrice());
            return itemRepository.save(existingItem);
        }
        return null; // Handle item not found case
    }


	public void deleteItem(Long id) {
		itemRepository.deleteById(id);
	}
}
