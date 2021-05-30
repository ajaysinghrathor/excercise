package com.excercise.feed.repository;

import com.excercise.feed.entity.FeedMetaData;
import com.excercise.feed.entity.Portfolio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "metadata", path = "metadata")
public interface FeedMetaDataRepository extends CrudRepository<FeedMetaData, Long> {

    FeedMetaData findByFeed(String feed);
}
