package apichallenge.restful.model;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CreateOrderRequest {
  @NotBlank
  private String customer_name;

  private Double total_amount;

  private String status;

  private Date createdAt;

}
