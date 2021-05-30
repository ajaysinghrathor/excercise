package com.excercise.feed.repository;

import com.excercise.feed.entity.Portfolio;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "voltage", path = "voltage")
public interface PortfolioRepository extends PagingAndSortingRepository<Portfolio, Long> {
    List<Portfolio> findAll();
}
