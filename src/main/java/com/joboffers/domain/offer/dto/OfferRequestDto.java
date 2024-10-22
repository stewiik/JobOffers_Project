package com.joboffers.domain.offer.dto;

public record OfferRequestDto(
        String companyName,
        String position,
        String salary,
        String offerUrl
) {
}
