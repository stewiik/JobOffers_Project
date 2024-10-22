package com.joboffers.domain.offer;

import com.joboffers.domain.offer.dto.JobOfferResponseDto;

import java.util.List;

public interface OfferFetchable {
    List<JobOfferResponseDto> fetchOffers();
}
