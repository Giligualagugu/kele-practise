package com.example.kele.controller;

import com.example.kele.entity.GoldFishEntity;
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
        String groupLastId = null;

        for (int i = 0; i < times; i++) {

            PageRequest pageRequest = PageRequest.of(i, 1000, Sort.Direction.ASC, "groupId");
            List<GoldFishEntity> content = repository.findAll(pageRequest).getContent();

            //第一组的id；
            String groupFirstId = content.get(0).getGroupId();

            int size = content.size();

            //每页最后一条；
            GoldFishEntity lastEntity = content.get(size - 1);

            for (int n = 0; n < size; n++) {

                GoldFishEntity current = content.get(n);

                //小于则是异常数据，跳过；
                if (current.getGroupId().compareTo(groupFirstId) == -1) {
                    continue;
                }

                //如果是最后一条数据，则不做处理；
                if (n == size - 1) {
                    return;
                }


                //相同则是同一组；
                if (current.getGroupId().equals(groupFirstId)) {
                    groupList.add(current);
                }

                //满员了则跳出；
                if (groupList.size() == 3) {
                    groupList.clear();
                    groupFirstId = current.getGroupId();
                    continue;
                }

                //大于则是下一组的,处理上一组的数据；
                if (current.getGroupId().compareTo(groupFirstId) == 1) {
                    sendMessage(groupList);
                    //处理完后清楚；
                    groupList.clear();
                    groupList.add(current);

                }


            }


        }


    }

    private void sendMessage(List<GoldFishEntity> groupList) {
    }

    private long maketimes(long count) {

        if (count % 1000 == 0) {
            return count / 1000;
        } else {
            return count / 1000 + 1;
        }

    }

}
