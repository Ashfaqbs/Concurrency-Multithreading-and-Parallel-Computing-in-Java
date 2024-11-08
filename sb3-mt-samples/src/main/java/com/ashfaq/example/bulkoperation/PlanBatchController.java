package com.ashfaq.example.bulkoperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanBatchController {

	@Autowired
	private PlanService planService;

	@PostMapping("/batch")
	public ResponseEntity<List<String>> processBatch(@RequestBody List<PlanRequest> plans) {
		List<String> results = planService.processRowAction(plans);
		return ResponseEntity.ok(results);
	}

	@PostMapping("/threadbatch")
	public String processBatchExecutorService(@RequestBody List<PlanRequest> planRequests) {
		planService.processBatch(planRequests);
		
		return "Batch processing started";//we can return any upload entity UUId for trackng purpose as soon as i call the api the response will be returned
		//and the processing will be done in background
	}
	

}
