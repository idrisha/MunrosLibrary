package com.xdesign.munro.library.repository;

import com.xdesign.munro.library.models.Munro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MunroRepository extends JpaRepository<Munro, Long> {
    Page<Munro> findByName( String name, Pageable pagingSort );
}
