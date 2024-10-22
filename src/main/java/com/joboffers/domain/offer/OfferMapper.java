package com.joboffers.domain.offer;

import com.joboffers.domain.offer.dto.JobOfferResponseDto;
import com.joboffers.domain.offer.dto.OfferDto;
import com.joboffers.domain.offer.dto.OfferRequestDto;

class OfferMapper {

    public static OfferDto mapFromOfferToOfferResponseDto(Offer offer) {
        return OfferDto.builder()
                .id(offer.id())
                .companyName(offer.companyName())
                .position(offer.position())
                .salary(offer.salary())
                .offerUrl(offer.offerUrl())
                .build();
    }

    public static Offer mapFromOfferRequestDtoToOffer(OfferRequestDto offerRequestDto) {
        return Offer.builder()
                .companyName(offerRequestDto.companyName())
                .position(offerRequestDto.position())
                .salary(offerRequestDto.salary())
                .offerUrl(offerRequestDto.offerUrl())
                .build();
    }

    public static OfferDto mapFromOfferToOfferDto(Offer offer) {
        return OfferDto.builder()
                .id(offer.id())
                .companyName(offer.companyName())
                .position(offer.position())
                .salary(offer.salary())
                .offerUrl(offer.offerUrl())
                .build();
    }

    public static Offer mapFromJobOfferResponseToOffer(JobOfferResponseDto offer) {
        return Offer.builder()
                .companyName(offer.companyName())
                .position(offer.position())
                .salary(offer.salary())
                .offerUrl(offer.offerUrl())
                .build();
    }
}
