package com.ashfaq.example.bulkoperation;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/plans")
public class PlanController {

	@Autowired
	private PlanService planService;

	@Autowired
	private PlanRepository planRepository;

	// Get all plans
	@GetMapping
	public Iterable<Plan> getAllPlans() {
		return planRepository.findAll();
	}

	// Create a new plan
	@PostMapping("/create")
	public ResponseEntity<String> createPlan(@RequestBody Plan plan) {
		boolean isSaved = planService.savePlan(plan);
		return isSaved ? ResponseEntity.ok("Plan created successfully")
				: ResponseEntity.status(500).body("Failed to create plan");
	}

	// Update an existing plan
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updatePlan(@PathVariable Long id, @RequestBody Plan plan) {
		boolean isUpdated = planService.updatePlan(id, plan);
		return isUpdated ? ResponseEntity.ok("Plan updated successfully")
				: ResponseEntity.status(404).body("Plan not found");
	}

	// Delete a plan by ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePlan(@PathVariable Long id) {
		boolean isDeleted = planService.deletePlan(id);
		return isDeleted ? ResponseEntity.ok("Plan deleted successfully")
				: ResponseEntity.status(404).body("Plan not found");
	}

}
