package com.joboffers.domain.offer;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.StreamSupport;

public class InMemoryOfferRepository implements OfferRepository {

    private final Map<String, Offer> offers = new ConcurrentHashMap<>();

    @Override
    public boolean existsByOfferUrl(String offerUrl) {
        long count = offers.values()
                .stream()
                .filter(offer -> offer.offerUrl().equals(offerUrl))
                .count();
        return count == 1;
    }

    @Override
    public Optional<Offer> findById(String id) {
        return Optional.ofNullable(offers.get(id));
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public <S extends Offer> S save(S entity) {
        if (offers.values().stream().anyMatch(offer -> offer.offerUrl().equals(entity.offerUrl()))) {
            throw new DuplicateKeyException(String.format("Offer with offerUrl [%s] already exists", entity.offerUrl()));
        }
        UUID id = UUID.randomUUID();
        Offer offer = new Offer(
                id.toString(),
                entity.companyName(),
                entity.position(),
                entity.salary(),
                entity.offerUrl()
        );
        offers.put(id.toString(), offer);
        return (S) offer;
    }


    @Override
    public <S extends Offer> List<S> saveAll(Iterable<S> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(this::save)
                .toList();
    }

    @Override
    public List<Offer> findAll() {
        return offers.values().stream().toList();
    }

    @Override
    public List<Offer> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Offer entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Offer> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Offer> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Offer> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Offer> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Offer> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends Offer> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Offer> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Offer> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Offer> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Offer> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Offer> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Offer, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
