package apichallenge.restful.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Orders {
  @Id
  private int id;

  @Column(name = "customer_name")
  private String customerName;

  @Column(name = "total_amount")
  private Double totalAmount;

  private String status;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="created_at", nullable = false)
  private Date createdAt;

  @PrePersist
  private void onCreate(){
    createdAt = new Date();
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="updated_at", nullable = false)
  private Date updatedAt;

  @PreUpdate
  private void onUpdate(){
    updatedAt = new Date();
  }
}
