package com.joboffers.domain.offer;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
class OfferService {

    private final OfferFetchable offerFetcher;
    private OfferRepository offerRepository;

    List<Offer> fetchAllOffersAndSaveAllIfNotExists() {
        List<Offer> jobOffers = fetchOffers();
  //      final List<Offer> offers = filterNotExistingOffers(jobOffers);
        try {
            return jobOffers;
  //          return offerRepository.saveAll(offers);
      } catch (OfferDuplicateException duplicateKeyException) {
            throw new OfferDuplicateException("Duplicate offer found while saving offers.", jobOffers);
        }
    }

    private List<Offer> filterNotExistingOffers(List<Offer> jobOffers) {
        return jobOffers.stream()
                .filter(offer -> !offer.offerUrl().isEmpty())
                .filter(offer -> !offerRepository.existsByOfferUrl(offer.offerUrl()))
                .collect(Collectors.toList());
    }

    private List<Offer> fetchOffers() {
        return offerFetcher.fetchOffers()
                .stream()
                .map(OfferMapper::mapFromJobOfferResponseToOffer)
                .toList();
    }
}
