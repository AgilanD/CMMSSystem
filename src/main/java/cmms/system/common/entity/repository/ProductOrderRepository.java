package cmms.system.common.entity.repository;

import cmms.system.common.entity.ProductionOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductOrderRepository extends JpaRepository<ProductionOrder,Long> {
}
