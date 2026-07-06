package cmms.system.common.entity.repository;

import cmms.system.common.entity.VehicleInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleInventoryRepository extends JpaRepository<VehicleInventory,Long> {
}
