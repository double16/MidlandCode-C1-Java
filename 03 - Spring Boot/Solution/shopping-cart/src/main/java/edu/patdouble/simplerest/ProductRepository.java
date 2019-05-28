package edu.patdouble.simplerest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.List;

@RepositoryRestController
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    List<Product> findBySku(@Param("sku") String sku);
}
