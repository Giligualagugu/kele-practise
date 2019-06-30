package com.example.kele.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoldFishMessage implements Serializable {

    private static final long serialVersionUID = 3248492662273857278L;

    private String captainName;

    private String openId;

    private String groupId;

    private Date joinDate;

}
