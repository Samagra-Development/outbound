package com.samagra.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.samagra.Request.UserState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "gupshup_message")
public class GupshupMessageEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(nullable = false, name = "phone_no")
  private String phoneNo;
  
  @Column(name = "msg_id")
  private String msgId;

  @Column(nullable = false, name = "message")
  private String message;

  @Column(name = "is_last_message")
  private boolean isLastResponse;
}