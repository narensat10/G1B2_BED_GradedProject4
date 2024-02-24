package com.greatlearning.EmpManageApi.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {

	@RequestMapping("/error")
	public ResponseEntity<String> handleError(HttpServletRequest request) {
		Integer statusCodeObj = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (statusCodeObj != null) {
			int statusCode = statusCodeObj.intValue();
			String errorMessage;

			if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value())
				errorMessage = "Something Went Wrong.....\n Kindly Check with your inputs";
			else if (statusCode == HttpStatus.METHOD_NOT_ALLOWED.value())
				errorMessage = "Method not allowed";
			else if (statusCode == HttpStatus.NOT_FOUND.value())
				errorMessage = "Resource not found";
			else if (statusCode == HttpStatus.BAD_REQUEST.value())
				errorMessage = "Kindly check how you are entering the parameters...."
						+ "\n Use JSON format to populate employee ";
			else
				errorMessage = "An Error Occured";

			return ResponseEntity.status(statusCode).body(errorMessage);
		} else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An Error Occurred");

	}

}
