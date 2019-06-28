package com.example.kele.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "goldfish_info")
@Entity
public class GoldFishEntity implements Serializable {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column
    private String openId;

    @Column
    private String groupId;

    @Column
    private String captainMark;

    private String nickName;

    @Column
    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime joinDate;

}
