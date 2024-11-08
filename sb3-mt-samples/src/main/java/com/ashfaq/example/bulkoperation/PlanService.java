package com.ashfaq.example.bulkoperation;

import java.util.ArrayList;
import java.util.List;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
import java.util.Optional;

//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;

@Service
public class PlanService {

	@Autowired
	private PlanRepository planRepository;

	// Save a new plan
	public boolean savePlan(Plan plan) {
		try {
			planRepository.save(plan);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Update an existing plan with selective fields
	public boolean updatePlan(Long id, Plan updatedPlan) {
		Optional<Plan> existingPlanOpt = planRepository.findById(id);

		if (existingPlanOpt.isPresent()) {
			Plan existingPlan = existingPlanOpt.get();

			// Only update fields that are not null
			if (updatedPlan.getName() != null)
				existingPlan.setName(updatedPlan.getName());
			if (updatedPlan.getType() != null)
				existingPlan.setType(updatedPlan.getType());
			if (updatedPlan.getDescription() != null)
				existingPlan.setDescription(updatedPlan.getDescription());
			if (updatedPlan.getCost() != null)
				existingPlan.setCost(updatedPlan.getCost());

			planRepository.save(existingPlan);
			return true;
		} else {
			return false; // Plan with given ID not found
		}
	}

	// Delete a plan by ID
	public boolean deletePlan(Long id) {
		try {
			if (planRepository.existsById(id)) {
				planRepository.deleteById(id);
				return true;
			} else {
				return false; // Plan with given ID not found
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

// No Threading direct 

//	type 1 

	public List<String> processRowAction(List<PlanRequest> plans) {
		List<String> results = new ArrayList<>();
		for (PlanRequest planRequest : plans) {
			try {
				switch (planRequest.getAction()) {
				case "add":
					Plan newPlan = new Plan();
					newPlan.setName(planRequest.getName());
					newPlan.setType(planRequest.getType());
					newPlan.setDescription(planRequest.getDescription());
					newPlan.setCost(planRequest.getCost());
					planRepository.save(newPlan);
					results.add("Added Plan with name: " + planRequest.getName());
					break;
				case "update":
					Optional<Plan> existingPlan = planRepository.findById(planRequest.getId());
					if (existingPlan.isPresent()) {
						Plan planToUpdate = existingPlan.get();
						if (planRequest.getName() != null)
							planToUpdate.setName(planRequest.getName());
						if (planRequest.getType() != null)
							planToUpdate.setType(planRequest.getType());
						if (planRequest.getDescription() != null)
							planToUpdate.setDescription(planRequest.getDescription());
						if (planRequest.getCost() != null)
							planToUpdate.setCost(planRequest.getCost());
						planRepository.save(planToUpdate);
						results.add("Updated Plan with ID: " + planRequest.getId());
					} else {
						results.add("Plan not found for ID: " + planRequest.getId());
					}
					break;
				case "delete":
					Optional<Plan> planToDelete = planRepository.findById(planRequest.getId());
					if (planToDelete.isPresent()) {
						planRepository.delete(planToDelete.get());
						results.add("Deleted Plan with ID: " + planRequest.getId());
					} else {
						results.add("Plan not found for ID: " + planRequest.getId());
					}
					break;
				default:
					results.add("Invalid action for ID: " + planRequest.getId());
					break;
				}
			} catch (Exception e) {
				results.add("Error processing action for ID: " + planRequest.getId() + " - " + e.getMessage());
			}
		}
		return results;
	}

//	Threading 

	// ExecutorService with a fixed pool of 10 threads
//		private final ExecutorService executorService = Executors.newFixedThreadPool(10);

//	public void processExcelFileWithThreads(MultipartFile file) throws IOException {
//		Workbook workbook = new XSSFWorkbook(file.getInputStream());
//		Sheet sheet = workbook.getSheetAt(0);
//		List<CompletableFuture<Void>> futures = new ArrayList<>();
//
//		for (Row row : sheet) {
//			if (row.getRowNum() == 0)
//				continue; // Skip header row
//
//			// Extract values from row
//			String action = row.getCell(0).getStringCellValue();
//			Long id = row.getCell(1) != null ? (long) row.getCell(1).getNumericCellValue() : null;
//			String name = row.getCell(2) != null ? row.getCell(2).getStringCellValue() : null;
//			String type = row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null;
//			String description = row.getCell(4) != null ? row.getCell(4).getStringCellValue() : null;
//			Double cost = row.getCell(5) != null ? row.getCell(5).getNumericCellValue() : null;
//
//			// Create a task for each row based on action
//			CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//				processRowAction(action, id, name, type, description, cost);
//			}, executorService);
//
//			futures.add(future);
//		}
//
//		// Wait for all tasks to complete
//		CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
//		workbook.close();
//	}
//
//	private void processRowAction(String action, Long id, String name, String type, String description, Double cost) {
//		switch (action.toLowerCase()) {
//		case "add":
//			Plan newPlan = new Plan();
//			newPlan.setName(name);
//			newPlan.setType(type);
//			newPlan.setDescription(description);
//			newPlan.setCost(cost);
//			planRepository.save(newPlan); // Reuse save for adding
//			break;
//
//		case "update":
//			if (id != null) {
//				planRepository.findById(id).ifPresent(plan -> {
//					if (name != null)
//						plan.setName(name);
//					if (type != null)
//						plan.setType(type);
//					if (description != null)
//						plan.setDescription(description);
//					if (cost != null)
//						plan.setCost(cost);
//					planRepository.save(plan); // Reuse save for updating
//				});
//			}
//			break;
//
//		case "delete":
//			if (id != null) {
//				planRepository.deleteById(id); // Reuse deleteById for deleting
//			}
//			break;
//
//		default:
//			throw new IllegalArgumentException("Invalid action: " + action);
//		}
//	}
}
