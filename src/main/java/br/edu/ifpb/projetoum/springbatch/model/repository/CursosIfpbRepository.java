package br.edu.ifpb.projetoum.springbatch.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.projetoum.springbatch.model.entity.CursoIfpb;

@Repository
public interface CursosIfpbRepository extends JpaRepository<CursoIfpb, Long> {
	

}
