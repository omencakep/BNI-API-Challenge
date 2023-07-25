package apichallenge.restful.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apichallenge.restful.entity.Orders;

@Repository
public interface OrdersRespository extends JpaRepository<Orders, String>{
  //Get order by id
  Optional<Orders> findFirstById(int id);

  //Get all orders
  List<Orders> findAll();
}
