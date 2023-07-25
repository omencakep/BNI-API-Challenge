package apichallenge.restful.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
  
  private int id;

  private String customerName;

  private Double totalAmount;

  private String status;

  private Date createdAt;

  private Date updatedAt;
}
