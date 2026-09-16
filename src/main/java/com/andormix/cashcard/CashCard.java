package com.andormix.cashcard;

// Repo import for @ID
import org.springframework.data.annotation.Id;

record CashCard(@Id Long id, Double amount) {
}
