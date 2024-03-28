package com.spring.jwt.Interfaces;

import com.spring.jwt.dto.BookingDtos.PendingBookingDto;
import com.spring.jwt.dto.BookingDtos.PendingBookingRequestDto;
import com.spring.jwt.dto.PendingBookingDTO;

import java.util.List;

public interface PendingBookingService {

    public PendingBookingRequestDto savePendingBooking(PendingBookingDto pendingBookingDTO);


    public void deleteBooking(int id);

    public void statusUpdate(PendingBookingDTO pendingBookingDTO);
    public List<PendingBookingDto> getAllPendingBookingWithPage(int PageNo);


    public List<PendingBookingDto>getAllPendingBookingByUserId(int pageNo, int userId);
    public PendingBookingDto getPendingBookingId(int bookingId);

    public List<PendingBookingDto> getPendingBookingsByDealerId(int pageNo, int dealerId);

    public Object getPendingBookingsByCarId(Integer pageNo, Integer carId);
}
