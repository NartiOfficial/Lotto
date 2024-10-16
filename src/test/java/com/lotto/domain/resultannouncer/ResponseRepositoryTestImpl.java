package com.lotto.domain.resultannouncer;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class ResponseRepositoryTestImpl implements ResponseRepository {

    private final Map<String, ResultResponse> responseList = new ConcurrentHashMap<>();

    @Override
    public ResultResponse save(ResultResponse resultResponse) {
        return responseList.put(resultResponse.hash(), resultResponse);
    }

    @Override
    public boolean existsById(String hash) {
        return responseList.containsKey(hash);
    }

    @Override
    public Optional<ResultResponse> findById(String hash) {
        return Optional.ofNullable(responseList.get(hash));
    }

    @Override
    public <S extends ResultResponse> S insert(final S entity) {
        return null;
    }

    @Override
    public <S extends ResultResponse> List<S> insert(final Iterable<S> entities) {
        return List.of();
    }

    @Override
    public <S extends ResultResponse> Optional<S> findOne(final Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends ResultResponse> List<S> findAll(final Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends ResultResponse> List<S> findAll(final Example<S> example, final Sort sort) {
        return List.of();
    }

    @Override
    public <S extends ResultResponse> Page<S> findAll(final Example<S> example, final Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ResultResponse> long count(final Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ResultResponse> boolean exists(final Example<S> example) {
        return false;
    }

    @Override
    public <S extends ResultResponse, R> R findBy(final Example<S> example, final Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends ResultResponse> List<S> saveAll(final Iterable<S> entities) {
        return List.of();
    }

    @Override
    public List<ResultResponse> findAll() {
        return List.of();
    }

    @Override
    public List<ResultResponse> findAllById(final Iterable<String> strings) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(final String s) {

    }

    @Override
    public void delete(final ResultResponse entity) {

    }

    @Override
    public void deleteAllById(final Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(final Iterable<? extends ResultResponse> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<ResultResponse> findAll(final Sort sort) {
        return List.of();
    }

    @Override
    public Page<ResultResponse> findAll(final Pageable pageable) {
        return null;
    }
}
