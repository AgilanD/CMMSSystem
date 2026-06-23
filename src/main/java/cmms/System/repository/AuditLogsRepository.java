package cmms.System.repository;

import cmms.System.entity.AuditLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogsRepository extends JpaRepository <AuditLogs,Long> {
}
