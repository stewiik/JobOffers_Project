package com.joboffers.domain.offer;

import com.joboffers.domain.offer.dto.JobOfferResponseDto;
import com.joboffers.domain.offer.dto.OfferRequestDto;
import com.joboffers.domain.offer.dto.OfferResponseDto;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.Test;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

public class OfferFacadeTest {

    @Test
    public void should_fetch_jobs_from_remote_and_save_all_offers_when_repository_is_empty() {
        //given
        OfferFacade offerFacade = new OfferFacadeTestsConfig().createFacadeForTests();
        assertThat(offerFacade.findAllOffers().isEmpty());
        //when
        List<OfferResponseDto> result = offerFacade.fetchAllOffersAndSaveAllIfNotExists();
        //then
        assertThat(result).hasSize(6);
    }

    @Test
    public void should_save_only_2_offers_when_repository_had_4_added_with_offer_urls() {
        //given
        OfferFacade offerFacade = new OfferFacadeTestsConfig(List.of(
                new JobOfferResponseDto("aaa", "developer", "100", "1"),
                new JobOfferResponseDto("bbb", "junior developer", "200", "2"),
                new JobOfferResponseDto("ccc", "scrum master", "300", "3"),
                new JobOfferResponseDto("ddd", "agile coach", "400", "4"),
                new JobOfferResponseDto("new1", "Comarch", "1000", "https://someurl.pl/5"),
                new JobOfferResponseDto("new2", "Finanteq", "2000", "https://someother.pl/6")
        )
        ).createFacadeForTests();
        offerFacade.saveOffer(new OfferRequestDto("company", "position", "1000", "1"));
        offerFacade.saveOffer(new OfferRequestDto("company", "position", "1000", "2"));
        offerFacade.saveOffer(new OfferRequestDto("company", "position", "1000", "3"));
        offerFacade.saveOffer(new OfferRequestDto("company", "position", "1000", "4"));
        assertThat(offerFacade.findAllOffers()).hasSize(4);
        //when
        List<OfferResponseDto> result = offerFacade.fetchAllOffersAndSaveAllIfNotExists();
        //then
        assertThat(List.of(
                        result.get(0).offerUrl(),
                        result.get(1).offerUrl()
                )
        ).containsExactlyInAnyOrder("https://someurl.pl/5", "https://someother.pl/6");
    }

    @Test
    public void should_save_4_offers_when_there_are_no_offers_in_database() {
        //given
        OfferFacade offerFacade = new OfferFacadeTestsConfig(List.of()).createFacadeForTests();
        //when
        offerFacade.saveOffer(new OfferRequestDto("company", "position", "1000", "1"));
        offerFacade.saveOffer(new OfferRequestDto("company", "position", "1000", "2"));
        offerFacade.saveOffer(new OfferRequestDto("company", "position", "1000", "3"));
        offerFacade.saveOffer(new OfferRequestDto("company", "position", "1000", "4"));
        //then
        assertThat(offerFacade.findAllOffers()).hasSize(4);
    }

    @Test
    public void should_find_offer_by_id_when_offer_was_saved() {
        // given
        OfferFacade offerFacade = new OfferFacadeTestsConfig(List.of()).createFacadeForTests();
        OfferResponseDto offerResponseDto = offerFacade.saveOffer(new OfferRequestDto("name", "xxx", "100", "1"));
        // when
        OfferResponseDto offerById = offerFacade.findOfferById(offerResponseDto.id());

        // then
        assertThat(offerById).isEqualTo(OfferResponseDto.builder()
                .id(offerResponseDto.id())
                .companyName("name")
                .position("xxx")
                .salary("100")
                .offerUrl("1")
                .build()
        );
    }

    @Test
    public void should_throw_not_found_exception_when_offer_not_found() {
        //given
        OfferFacade offerFacade = new OfferFacadeTestsConfig(List.of()).createFacadeForTests();
        assertThat(offerFacade.findAllOffers().isEmpty());
        //when
        Throwable thrown = catchThrowable(() -> offerFacade.findOfferById("100"));
        //then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(OfferNotFoundException.class)
                .hasMessage("Offer with id 100 not found");
    }

    @Test
    public void should_throw_duplicate_key_exception_when_with_offer_url_exists() {
        //given
        OfferFacade offerFacade = new OfferFacadeTestsConfig(List.of()).createFacadeForTests();
        OfferResponseDto offerResponseDto = offerFacade.saveOffer(new OfferRequestDto("company", "position", "1000", "1"));
        String savedId = offerResponseDto.id();
        assertThat(offerFacade.findOfferById(savedId).id()).isEqualTo(savedId);
        //when
        Throwable thrown = catchThrowable(() -> offerFacade.saveOffer(
                new OfferRequestDto("company1", "position1", "10001", "1")));
        //then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(DuplicateKeyException.class)
                .hasMessage("Offer with offerUrl 1 already exists");
    }
}