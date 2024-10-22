package com.joboffers.domain.offer;

import com.joboffers.domain.offer.dto.JobOfferResponseDto;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class OfferFacadeTestsConfig {

    private final InMemoryFetcherTestImpl inMemoryFetcherTest;
    private final InMemoryOfferRepository offerRepository;

    OfferFacadeTestsConfig() {
        this.inMemoryFetcherTest = new InMemoryFetcherTestImpl(
                List.of(
                        new JobOfferResponseDto("aaa", "developer", "100", "1"),
                        new JobOfferResponseDto("bbb", "junior developer", "200", "2"),
                        new JobOfferResponseDto("ccc", "scrum master", "300", "3"),
                        new JobOfferResponseDto("ddd", "agile coach", "400", "4"),
                        new JobOfferResponseDto("eee", "IT director", "500", "5"),
                        new JobOfferResponseDto("fff", "data analyst", "600", "6")
                )
        );
        this.offerRepository = new InMemoryOfferRepository();
    }

    OfferFacadeTestsConfig(List<JobOfferResponseDto> remoteClientOffers) {
        this.inMemoryFetcherTest = new InMemoryFetcherTestImpl(remoteClientOffers);
        this.offerRepository = new InMemoryOfferRepository();
    }

    OfferFacade createFacadeForTests() {
        return new OfferFacade(offerRepository, new OfferService(inMemoryFetcherTest, offerRepository));
    }
}
