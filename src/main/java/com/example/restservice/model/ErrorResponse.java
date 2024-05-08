package com.example.restservice.model;

public record ErrorResponse (int status, String errorCode, String errorMessage, String path) {}
