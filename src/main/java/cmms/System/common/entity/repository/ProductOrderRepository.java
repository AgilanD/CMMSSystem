package cmms.System.common.entity.repository;

import cmms.System.common.entity.ProductionOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductOrderRepository extends JpaRepository<ProductionOrder,Long> {
}
