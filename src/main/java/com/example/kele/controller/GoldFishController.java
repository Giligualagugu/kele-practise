package com.example.kele.controller;

import com.example.kele.entity.GoldFishEntity;
import com.example.kele.entity.GoldFishMessage;
import com.example.kele.repository.GoldFishRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class GoldFishController {

    @Autowired
    private GoldFishRepository repository;

    private List<GoldFishMessage> messages = new LinkedList<>();

    @GetMapping("/test/add100")
    public void randommsg() {

        List<GoldFishEntity> entities = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            GoldFishEntity entity = GoldFishEntity.builder().id(UUID.randomUUID().toString())
                    .openId(UUID.randomUUID().toString())
                    .groupId(UUID.randomUUID().toString())
                    .captainMark("1").joinDate(new Date())
                    .nickName("xiaoming" + i).build();
            entities.add(entity);
        }

        repository.saveAll(entities);
    }


    @GetMapping("/test/t2")
    public void stuben() {
        List<String> groups = repository.findGroupIdGroupByGroupId();

        groups.forEach(e ->
                {
                    List<GoldFishEntity> entities = repository.findByGroupId(e);
                    dealOne(entities);
                }
        );

        long total = messages.stream().distinct().count();

        System.out.println("==> all is " + total);

        messages.forEach(e -> {
            System.out.println(e);
        });

    }


    @GetMapping("/test/001")
    public void handle() {

        long count = repository.count();
        long times = maketimes(count);

        //如果有下一页，保留最后一组不处理（最后一组不满三人的话）
        List<GoldFishEntity> groupList = new LinkedList<>();

        //每页最后一条信息；
        GoldFishEntity groupLastOne = null;

        //每页第一条；
        String groupFirstId;

        for (int i = 0; i < times; i++) {
            PageRequest pageRequest = PageRequest.of(i, 10, Sort.Direction.ASC, "groupId");
            List<GoldFishEntity> content = repository.findAll(pageRequest).getContent();
            if (times == 1) {
                //只有一页时，一定顺序；
                dealOne(content);
            } else {
                int lenth = content.size();
                groupFirstId = content.get(0).getGroupId();

                for (int p = 0; p < lenth; p++) {
                    GoldFishEntity current = content.get(p);

                    if (groupLastOne != null) {
                        //有上一条。。。
                        groupFirstId = groupLastOne.getGroupId();
                    }

                    if (current.getGroupId().compareTo(groupFirstId) < 0) {
                        //异常数据；
                        continue;
                    }
                    if (current.getGroupId().equals(groupFirstId)) {
                        // 前后同一组；
                        groupList.add(current);
                        continue;
                    }
                    if (current.getGroupId().compareTo(groupFirstId) > 0) {
                        // 进入新组，先发送上一组的消息；
                        dealOne(groupList);
                        groupList.clear();
                        groupList.add(current);
                        groupFirstId = current.getGroupId();

                    }

                    //当前页的最后一条；
                    if (p == (lenth - 1)) {
                        groupLastOne = current;
                        if (i == (times - 1) && groupList.size() < 3) {
                            //最后一页的最后一条，不满组直接处理；
                            dealOne(groupList);
                        } else {
                            //不是最后一页
                            if (groupList.size() == 3) {
                                groupLastOne = null;
                                groupList.clear();
                            }
                        }
                    }
                }
            }
        }


    }

    private void dealOne(List<GoldFishEntity> content) {

        Map<String, List<GoldFishEntity>> collect = content.stream().collect(Collectors.groupingBy(GoldFishEntity::getGroupId));


        collect.forEach((groupId, entities) -> {

            if (entities.size() > 2) return;

            GoldFishEntity captian = entities.stream().filter(e -> e.getCaptainMark().trim().equals("1")).findFirst().get();

            if (ObjectUtils.isEmpty(captian)) {
                return;
            }

            entities.forEach(e -> {
                GoldFishMessage mss = GoldFishMessage.builder().openId(e.getOpenId())
                        .groupId(groupId)
                        .joinDate(e.getJoinDate())
                        .captainName(captian.getNickName())
                        .build();

                sendmessge(mss);
            });
        });
    }

    private void sendmessge(GoldFishMessage mss) {

        messages.add(mss);
    }


    private long maketimes(long count) {
        if (count < 10) {
            return 1L;
        }
        if (count % 10 == 0) {
            return count / 10;
        } else {
            return count / 10 + 1;
        }

    }


}
