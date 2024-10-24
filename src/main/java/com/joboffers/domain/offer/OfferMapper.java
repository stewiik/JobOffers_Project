package com.joboffers.domain.offer;

import com.joboffers.domain.offer.dto.JobOfferResponseDto;
import com.joboffers.domain.offer.dto.OfferResponseDto;
import com.joboffers.domain.offer.dto.OfferRequestDto;

class OfferMapper {

    public static Offer mapFromOfferRequestDtoToOffer(OfferRequestDto offerRequestDto) {
        return Offer.builder()
                .companyName(offerRequestDto.companyName())
                .position(offerRequestDto.position())
                .salary(offerRequestDto.salary())
                .offerUrl(offerRequestDto.offerUrl())
                .build();
    }

    public static OfferResponseDto mapFromOfferToOfferResponseDto(Offer offer) {
        return OfferResponseDto.builder()
                .id(offer.id())
                .companyName(offer.companyName())
                .position(offer.position())
                .salary(offer.salary())
                .offerUrl(offer.offerUrl())
                .build();
    }

    public static Offer mapFromJobOfferResponseToOffer(JobOfferResponseDto offer) {
        return Offer.builder()
                .companyName(offer.company())
                .position(offer.title())
                .salary(offer.salary())
                .offerUrl(offer.offerUrl())
                .build();
    }
}
