package com.ashfaq.example.bulkoperation;

import java.util.ArrayList;
import java.util.List;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executor;

//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

//	type 2

	public boolean processRowAction(PlanRequest planRequest) {
		boolean success = false;

		try {
			if ("create".equals(planRequest.getAction())) {
				// Map PlanRequest to Plan for creating new Plan
				Plan newPlan = mapPlanRequestToPlan(planRequest);
				success = savePlan(newPlan); // Save new Plan
			} else if ("update".equals(planRequest.getAction())) {
				// Directly use the PlanRequest DTO for the update action
				success = updatePlan(planRequest.getId(), planRequest); // Update Plan with the received DTO
			} else if ("delete".equals(planRequest.getAction())) {
				success = deletePlan(planRequest.getId()); // Delete Plan by ID
			} else {
				throw new RuntimeException("Invalid action: " + planRequest.getAction());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return success;
	}

	public boolean updatePlan(Long id, PlanRequest updatedPlanRequest) {
		Optional<Plan> existingPlanOpt = planRepository.findById(id);

		if (existingPlanOpt.isPresent()) {
			Plan existingPlan = existingPlanOpt.get();

			// Only update fields that are not null in the PlanRequest DTO
			if (updatedPlanRequest.getName() != null)
				existingPlan.setName(updatedPlanRequest.getName());
			if (updatedPlanRequest.getType() != null)
				existingPlan.setType(updatedPlanRequest.getType());
			if (updatedPlanRequest.getDescription() != null)
				existingPlan.setDescription(updatedPlanRequest.getDescription());
			if (updatedPlanRequest.getCost() != null)
				existingPlan.setCost(updatedPlanRequest.getCost());

			planRepository.save(existingPlan); // Save the updated Plan
			return true;
		} else {
			return false; // Plan not found
		}
	}

	public Plan mapPlanRequestToPlan(PlanRequest planRequest) {
		Plan plan = new Plan();
		plan.setName(planRequest.getName());
		plan.setType(planRequest.getType());
		plan.setDescription(planRequest.getDescription());
		plan.setCost(planRequest.getCost());
		return plan;
	}

	@Autowired
	@Qualifier("plantaskExecutor")
	private Executor executor;

	// Process the batch of PlanRequests using ExecutorService
	public void processBatch(List<PlanRequest> planRequests) {
		for (PlanRequest planRequest : planRequests) {
			executor.execute(() -> {
				boolean success = processRowAction(planRequest); // Process each planRequest
				// Handle success/failure results here, log them, etc.
				// auditing for main and child jobs will be done here
				if (success) {

					// Handle success logic (e.g., logging success, updating status, etc.)
				} else {
					// Handle failure logic (e.g., logging failure, sending failure notifications,
					// etc.)
				}
			});
		}
	}

//    The first 5 tasks will be handled by the 5 threads (since the core pool size is 5).
//    After that, 15 more tasks will be processed concurrently, utilizing up to 15 more threads (since the max pool size is 20).
//    If there are still tasks left (let's say 10), they will be placed in the queue.
//    If the queue is full (100 tasks), and additional tasks come in, the calling thread will start processing them (because of CallerRunsPolicy).

}
