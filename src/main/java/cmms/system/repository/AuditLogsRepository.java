package cmms.system.repository;

import cmms.system.entity.AuditLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuditLogsRepository extends JpaRepository <AuditLogs,Long> {

    @Query(value = "SELECT * FROM audit_logs WHERE id = :id", nativeQuery = true)
    Optional<AuditLogs> findByIdIncludingDeleted(Long id);

}
