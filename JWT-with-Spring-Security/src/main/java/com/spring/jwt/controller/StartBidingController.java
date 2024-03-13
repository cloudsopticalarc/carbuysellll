package com.spring.jwt.controller;


import com.spring.jwt.Interfaces.BiddingTimerService;
import com.spring.jwt.dto.BiddingTimerRequestDTO;
import com.spring.jwt.dto.ResDtos;
import com.spring.jwt.dto.ResponseSingleCarDto;
import com.spring.jwt.entity.User;
import com.spring.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("/Bidding/v1")
@RequiredArgsConstructor
public class StartBidingController {

    private final BiddingTimerService biddingTimerService;

    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(StartBidingController.class);
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();


    @PostMapping("/SetTime")
    public ResponseEntity<?> startBidding(@RequestBody BiddingTimerRequestDTO biddingTimerRequest) {
        Optional<User> user = userRepository.findById(biddingTimerRequest.getUserId());
        if(!user.isPresent()) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
        try {
            int durationMinutes = biddingTimerRequest.getDurationMinutes();
            startCountdown(durationMinutes);

            BiddingTimerRequestDTO biddingTimerRequestDTO = biddingTimerService.startTimer(biddingTimerRequest);
            return ResponseEntity.status(HttpStatus.OK).body(new ResDtos("success", biddingTimerRequestDTO));
        } catch (Exception e) {
            ResponseSingleCarDto responseSingleCarDto = new ResponseSingleCarDto("unsuccess");
            responseSingleCarDto.setException(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    private void startCountdown(int durationMinutes) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> {
            // Push notification to all users
            pushNotificationToAllUsers();
        }, durationMinutes, TimeUnit.MINUTES);
    }

    private void pushNotificationToAllUsers() {
        List<User> allUsers = userRepository.findAll();

        for (User user : allUsers) {
            try {
                sendNotification(user.getEmail(), "Your bidding timer has ended!");
                logger.info("Notification sent to user: " + user.getEmail());
            } catch (Exception e) {
                logger.error("Failed to send notification to user: " + user.getEmail(), e);
            }
        }
    }
    private void sendNotification(String recipient, String message) {
        biddingTimerService.sendNotification(recipient, message);
    }
}

