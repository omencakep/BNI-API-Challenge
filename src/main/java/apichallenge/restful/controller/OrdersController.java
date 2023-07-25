package apichallenge.restful.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import apichallenge.restful.model.CreateOrderRequest;
import apichallenge.restful.model.OrderResponse;
import apichallenge.restful.model.UpdateOrderRequest;
import apichallenge.restful.model.WebResponse;
import apichallenge.restful.service.OrderService;

@RestController
public class OrdersController {

  
  @Autowired
  private OrderService orderService;
  
  
  // Get all orders
  @GetMapping(
    path = "api/orders/",
    produces = MediaType.APPLICATION_JSON_VALUE

  )
  public WebResponse<List<OrderResponse>> getAllOrders(){
    List<OrderResponse> orderResponse = orderService.getAllOrders();
    return WebResponse.<List<OrderResponse>>builder().data(orderResponse).build();
  }
  // Get order by id  
  @GetMapping(
    path = "api/orders/{id}",
    produces = MediaType.APPLICATION_JSON_VALUE

  )
  public WebResponse<OrderResponse> getOrderById(@PathVariable("id") int id){
    OrderResponse orderResponse = orderService.getOrderById(id);
    return WebResponse.<OrderResponse>builder().data(orderResponse).build();
  }

  // create order
  @PostMapping(
    path = "/api/orders",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )

  public WebResponse<OrderResponse> createOrder(@RequestBody CreateOrderRequest request){
    OrderResponse orderResponse = orderService.createOrder(request);
    return WebResponse.<OrderResponse>builder().data(orderResponse).build();
  }

  // update order
  @PutMapping(
    path = "/api/orders/{id}",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )

  public WebResponse<OrderResponse> updateOrder(@RequestBody UpdateOrderRequest request,
                                                @PathVariable("id") int id){
    request.setId(id);
    OrderResponse orderResponse = orderService.updateOrder(request);
    return WebResponse.<OrderResponse>builder().data(orderResponse).build();
  }

  // delete order
  @DeleteMapping(
    path = "/api/orders/{id}",
    produces = MediaType.APPLICATION_JSON_VALUE
  )

  public WebResponse<String> deleteOrder(@PathVariable("id") int id){
    orderService.deleteOrder(id);
    return WebResponse.<String>builder().build();
  }

}
