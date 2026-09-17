package com.andormix.cashcard;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

interface CashCardRepository  extends CrudRepository<CashCard, Long>, PagingAndSortingRepository<CashCard, Long>
{
    // TO READ https://docs.spring.io/spring-data/relational/reference/repositories/query-methods-details.html
    CashCard findByIdAndOwner(Long id, String owner);
    Page<CashCard> findByOwner(String owner, PageRequest pageRequest);
}
