package br.com.arca.commons.audit;

import br.com.arca.commons.audit.Auditoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends CrudRepository<Auditoria,Long> {
}
