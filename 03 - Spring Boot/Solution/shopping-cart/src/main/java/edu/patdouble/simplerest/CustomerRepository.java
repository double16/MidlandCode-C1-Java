package edu.patdouble.simplerest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

}
