package apichallenge.restful.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import apichallenge.restful.entity.Orders;
import apichallenge.restful.model.CreateOrderRequest;
import apichallenge.restful.model.OrderResponse;
import apichallenge.restful.model.UpdateOrderRequest;
import apichallenge.restful.repository.OrdersRespository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ConstraintViolationException;

@Service
public class OrderService {
  @Autowired
  private OrdersRespository ordersRepository;

  @Autowired
  private Validator validator;
  
  private OrderResponse toOrderResponse(Orders orders){
    return OrderResponse.builder()
        .id(orders.getId())
        .customerName(orders.getCustomerName())
        .totalAmount(orders.getTotalAmount())
        .status(orders.getStatus())
        .createdAt(orders.getCreatedAt())
        .updatedAt(orders.getUpdatedAt())
        .build();
  }

  //Get all orders
  public List <OrderResponse> getAllOrders(){
    List<Orders> orders = ordersRepository.findAll();
    return orders.stream().map(this::toOrderResponse).toList();
  }

  //Get order by id
  public OrderResponse getOrderById(int id){
    Orders orders = ordersRepository.findFirstById(id)
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order data is not found!"));
        return toOrderResponse(orders);
  }

  //Create order
  public OrderResponse createOrder(CreateOrderRequest request){
    Set<ConstraintViolation<CreateOrderRequest>> constraintViolations = validator.validate(request);
    if (constraintViolations.size() != 0){
      throw new ConstraintViolationException(constraintViolations);
    }
    Orders orders = new Orders();
    orders.setCustomerName(request.getCustomer_name());
    orders.setTotalAmount(request.getTotal_amount());
    orders.setStatus("pending");
    ordersRepository.save(orders);

    return toOrderResponse(orders);
    
  }

  //update order
  public OrderResponse updateOrder(UpdateOrderRequest request){
    Set<ConstraintViolation<UpdateOrderRequest>> constraintViolations = validator.validate(request);
    if (constraintViolations.size() != 0){
      throw new ConstraintViolationException(constraintViolations);
    }
    Orders orders = ordersRepository.findFirstById(request.getId())
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order data is not found!"));

    orders.setCustomerName(request.getCustomer_name());
    orders.setTotalAmount(request.getTotal_amount());
    orders.setStatus(request.getStatus());
    ordersRepository.save(orders);

    return toOrderResponse(orders);

  }

  //delete order
  public void deleteOrder(int id){
     Orders orders = ordersRepository.findFirstById(id)
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order data is not found!"));
    
     ordersRepository.delete(orders);   
  }

}