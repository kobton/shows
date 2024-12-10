package com.radio.shows.exception

import ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(
        ex: ResourceNotFoundException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            message = ex.message ?: "Resource not found",
            details = request.getDescription(false)
        )
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(TooManyRequestsException::class)
    fun handleTooManyRequestsException(
        ex: TooManyRequestsException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            message = ex.message ?: "Resource not found",
            details = request.getDescription(false)
        )
        return ResponseEntity(errorResponse, HttpStatus.TOO_MANY_REQUESTS)
    }

    @ExceptionHandler(Exception::class)
    fun handleGlobalException(
        ex: Exception,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            message = ex.message ?: "An unexpected error occurred",
            details = request.getDescription(false)
        )
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
