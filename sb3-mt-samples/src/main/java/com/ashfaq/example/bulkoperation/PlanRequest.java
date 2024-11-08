package com.ashfaq.example.bulkoperation;

import lombok.Data;

@Data
public class PlanRequest {

	private String action;
	private Long id;
	private String name;
	private String type;
	private String description;
	private Double cost;

}
