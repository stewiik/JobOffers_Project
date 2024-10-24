package com.joboffers.domain.offer;

import java.util.List;
import java.util.Optional;

public interface OfferRepository {
    List<Offer> findAll();

    Optional<Offer> findById(String id);

    Offer save(Offer offer);

    boolean existsByOfferUrl(String offerUrl);

    List<Offer> saveAll(List<Offer> offers);
}

