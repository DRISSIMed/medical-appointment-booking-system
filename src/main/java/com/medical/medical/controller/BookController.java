package com.medical.medical.controller;

import com.medical.medical.dto.request.BookingRequest;
import com.medical.medical.dto.response.BookingResponse;
import com.medical.medical.dto.response.TimeSlotResponse;
import com.medical.medical.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/doctors/{doctorId}/slots")
    public ResponseEntity<List<TimeSlotResponse>> getAvailableSlots(@PathVariable Long doctorId) {
        return ResponseEntity.ok(bookService.getAvailableSlots(doctorId));
    }

    @PostMapping("/book")
    public ResponseEntity<BookingResponse> bookAppointment(@Valid @RequestBody BookingRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.bookSlot(request));
    }
}