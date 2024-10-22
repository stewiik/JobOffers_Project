package com.joboffers.domain.offer;

import com.joboffers.domain.offer.dto.JobOfferResponseDto;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class InMemoryFetcherTestImpl implements OfferFetchable {

    private final List<JobOfferResponseDto> offers;

    @Override
    public List<JobOfferResponseDto> fetchOffers() {
        return offers;
    }
}
