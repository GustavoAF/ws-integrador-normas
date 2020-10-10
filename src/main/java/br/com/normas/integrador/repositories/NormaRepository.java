package br.com.normas.integrador.repositories;

import br.com.normas.integrador.entity.NormaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NormaRepository extends JpaRepository<NormaEntity, String> {

}
