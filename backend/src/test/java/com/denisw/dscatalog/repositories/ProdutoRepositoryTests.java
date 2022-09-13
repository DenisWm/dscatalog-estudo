package com.denisw.dscatalog.repositories;

import com.denisw.dscatalog.entities.Product;
import com.denisw.dscatalog.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

@DataJpaTest
public class ProdutoRepositoryTests {
        private long existingId;
        private long nonExistingId;
        private long countTotalProducts;
    @Autowired
    private ProductRepository repository;

    @BeforeEach
    void setUp() throws Exception{
        existingId =  1L;
        nonExistingId = 1000L;
        countTotalProducts = 25L;
    }

    @Test
    public void saveShouldPersistWithAutoincrementWheIdIsNull(){
        Product product = Factory.createProduct();
        product.setId(null);
        product = repository.save(product);
        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(countTotalProducts + 1, product.getId() );

    }

    @Test
    public void findByIdShouldRetrieveDataWhenIdExists(){
        Optional<Product> product = repository.findById(existingId);
        Assertions.assertEquals(true, product.isPresent());
    }

    @Test
    public void findByIdShouldThrowIllegalArgumentExceptionWhenIdDoesNotExists(){
        Optional<Product> product = repository.findById(nonExistingId);
        Assertions.assertEquals(true, product.isEmpty());
    }
    @Test
    public void deleteShouldDeleteObjectWhenIdExists(){

        repository.deleteById(existingId);

        Optional<Product> result = repository.findById(existingId);

        Assertions.assertFalse(result.isPresent());

    }

    @Test
    private void deleteShouldThrowEmptyResultDataAcessExceptionWhenIdDoesNotExists() {

        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            repository.deleteById(nonExistingId);

        });
    }
}
