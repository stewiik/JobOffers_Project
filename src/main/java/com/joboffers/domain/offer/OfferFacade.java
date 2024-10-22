package com.joboffers.domain.offer;

import com.joboffers.domain.offer.dto.OfferDto;
import com.joboffers.domain.offer.dto.OfferRequestDto;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class OfferFacade {

    private final OfferRepository offerRepository;
    private final OfferService offerService;

    public List<OfferDto> findAllOffers() {
        return  offerRepository.findAll()
                .stream()
                .map(OfferMapper::mapFromOfferToOfferResponseDto)
                .collect(Collectors.toList());
    }

    public OfferDto findOfferById(String id) {
        return offerRepository.findById(id)
                .map(OfferMapper::mapFromOfferToOfferResponseDto)
                .orElseThrow(() -> new OfferNotFoundException(id));
    }

    public OfferDto saveOffer(OfferRequestDto offerRequestDto) {
        Offer offer = OfferMapper.mapFromOfferRequestDtoToOffer(offerRequestDto);
        offerRepository.save(offer);
        return OfferMapper.mapFromOfferToOfferDto(offer);
    }

    public List<OfferDto> fetchAllOffersAndSaveAllIfNotExists() {
        return offerService.fetchAllOffersAndSaveAllIfNotExists()
                .stream()
                .map(OfferMapper::mapFromOfferToOfferDto)
                .toList();
    }
}
