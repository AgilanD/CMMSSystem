package cmms.System.repository;

import cmms.System.entity.ProductionOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductOrderRepository extends JpaRepository<ProductionOrder,Long> {
}
