package com.Team23.backend.Repository;
import com.Team23.backend.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DocumentRepositoty extends  JpaRepository<DocumentWork,Long>{
        DocumentWork findBynumberDocument(String numberDocument);
}
