package com.example.kele.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoldFishMessage implements Serializable {

    private static final long serialVersionUID = 3248492662273857278L;

    private String captainName;

    private String openId;

    private String groupId;

    private LocalDateTime joinDate;

}
