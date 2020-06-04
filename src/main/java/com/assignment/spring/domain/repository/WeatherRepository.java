package com.assignment.spring.domain.repository;

import com.assignment.spring.domain.entity.WeatherEntity;
import org.springframework.data.repository.CrudRepository;

public interface WeatherRepository extends CrudRepository<WeatherEntity, Integer> {
}
