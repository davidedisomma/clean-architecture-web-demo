package org.ucieffe.cleanarchitecture.library.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface BookItemRepository
        extends JpaRepository<BookItemJpaEntity, String> {
}
