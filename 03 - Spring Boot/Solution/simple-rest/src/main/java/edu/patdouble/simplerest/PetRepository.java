package edu.patdouble.simplerest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface PetRepository extends PagingAndSortingRepository<Pet, Long> {

    List<Pet> findByName(@Param("name") String name);

}
