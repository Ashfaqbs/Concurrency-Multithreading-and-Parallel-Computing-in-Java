package com.ashfaq.example.bulkoperation;

import lombok.Data;

@Data
public class Result {

	private String action; // The action type (create, update, delete)
	private String status; // The status of the operation (success, failure)
	private String message; // Any error message or success message
}
