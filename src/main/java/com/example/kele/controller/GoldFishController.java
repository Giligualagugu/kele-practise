package com.example.kele.controller;

import com.example.kele.entity.GoldFishEntity;
import com.example.kele.entity.GoldFishMessage;
import com.example.kele.repository.GoldFishRepository;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class GoldFishController {

    @Autowired
    private GoldFishRepository repository;

    @GetMapping("/test/001")
    public void handle() {

        long count = repository.count();
        long times = maketimes(count);

        //如果有下一页，保留最后一组不处理（最后一组不满三人的话）
        List<GoldFishEntity> groupList = new LinkedList<>();

        //当前页的最后一组的id；
        GoldFishEntity groupLastOne = null;
        String groupFirstId = null;

        for (int i = 0; i < times; i++) {
            PageRequest pageRequest = PageRequest.of(i, 1000, Sort.Direction.ASC, "groupId");
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

                    if (current.getGroupId().compareTo(groupFirstId) == -1) {
                        //异常数据；
                        continue;
                    }
                    if (current.getGroupId().compareTo(groupFirstId) == 1) {
                        groupList.add(current);
                        continue;
                    }
                    if (current.getGroupId().compareTo(groupFirstId) == 1) {

                        // 进入新组，先发送上一组的消息；
                        dealOne(groupList);
                        groupList.clear();
                        groupList.add(current);
                        groupFirstId = current.getGroupId();
                    }
                    if ((p == lenth - 1) && (i != times - 1) && groupList.size() < 3) {//已经最后一条了，且有下一页，且不满三条，保持最后一条记录；
                        groupLastOne = current;
                    } else {
                        dealOne(groupList);
                    }
                }
            }
        }


    }

    private void dealOne(List<GoldFishEntity> content) {

        Map<String, List<GoldFishEntity>> collect = content.stream().collect(Collectors.groupingBy(GoldFishEntity::getGroupId));
        collect.forEach((groupId, entities) -> {
            if (entities.size() > 2) {
                return;
            }
            GoldFishEntity captian = entities.stream().filter(e -> e.getCaptainMark().equals("1")).findFirst().get();
            entities.forEach(e -> {
                GoldFishMessage mss = GoldFishMessage.builder().openId(e.getOpenId())
                        .groupId(e.getGroupId())
                        .joinDate(e.getJoinDate())
                        .captainName(captian.getNickName())
                        .build();

                sendmessge(mss);
            });
        });
    }

    private void sendmessge(GoldFishMessage mss) {

        System.out.println("send:" + mss);

    }


    private long maketimes(long count) {
        if (count < 1000) {
            return 1L;
        }

        if (count % 1000 == 0) {
            return count / 1000;
        } else {
            return count / 1000 + 1;
        }

    }

    public static void main(String[] args) {

        System.out.println(1210 / 1000);

    }

}
