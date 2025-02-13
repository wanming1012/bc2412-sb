package com.bootcamp.sb.exercise1.exception;

public class InvalidInputException extends RuntimeException{
  public InvalidInputException() {
    super("Invalid Input.");
  }
}
