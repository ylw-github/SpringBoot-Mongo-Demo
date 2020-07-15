package com.ylw;

import com.mongodb.client.result.UpdateResult;
import com.ylw.po.MsgChannel;
import com.ylw.service.MsgChannelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class MsgChannelServiceTest {

    @Autowired
    private MsgChannelService msgChannelService;

    @Test
    public void save() {
        MsgChannel msgChannel = new MsgChannel();
        msgChannel.setChannelType(0);
        msgChannel.setChannelName("短信");
        msgChannel.setChannelState(0);
        msgChannel.setAppId("appid1001");
        msgChannel.setSecretId("sercretexs23csss3cceswxxf");
        msgChannel.setHost("smtp.qq.mail.com");
        msgChannel.setUserName("yanglinwei@digibms.com");
        msgChannel.setPassWord("123456");
        msgChannel.setClassAddress("com.ylw.impl.xxx");
        msgChannel.setCreateBy("yanglinwei");
        msgChannel.setCreateTime(new Date());
        msgChannel.setUpdateBy("yanglinwei");
        msgChannel.setUpdateTime(new Date());
        msgChannel.setIsDelete(false);
        MsgChannel result = msgChannelService.save(msgChannel);
        print(result.toString());
    }

    @Test
    public void batchSave() {

        List<MsgChannel> channels = new ArrayList<>();
        MsgChannel msgChannel;
        for (int i = 0; i < 10; i++) {
            msgChannel = new MsgChannel();
            msgChannel.setChannelType(10 + i);
            msgChannel.setChannelName("测试channel" + i);
            msgChannel.setChannelState(0);
            msgChannel.setAppId("appid1001");
            msgChannel.setSecretId("sercretexs23csss3cceswxxf");
            msgChannel.setHost("smtp.qq.mail.com");
            msgChannel.setUserName("yanglinwei@digibms.com");
            msgChannel.setPassWord("123456");
            msgChannel.setClassAddress("com.ylw.impl.xxx");
            msgChannel.setCreateBy("yanglinwei");
            msgChannel.setCreateTime(new Date());
            msgChannel.setUpdateBy("yanglinwei");
            msgChannel.setUpdateTime(new Date());
            msgChannel.setIsDelete(false);
            channels.add(msgChannel);
        }

        List<MsgChannel> result = msgChannelService.batchSave(channels);
        printList(result);
    }

    @Test
    public void selectAll() {
        List<MsgChannel> msgChannels = msgChannelService.selectAll();
        printList(msgChannels);
    }

    @Test
    public void count() {
        long count = msgChannelService.count();
        print(String.valueOf(count));
    }

    @Test
    public void selectByChannelName() {
        List<MsgChannel> result = msgChannelService.selectByChannelName("短信", false);
        printList(result);
    }

    @Test
    public void selectByTime() throws ParseException {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        List<MsgChannel> result = msgChannelService.selectByTime(sdf.parse("2020-07-15 00:00:00"), sdf.parse("2020-07-15 17:30:00"));
        printList(result);
    }

    @Test
    public void orderBy() {
        List<MsgChannel> result = msgChannelService.orderBy(Sort.Direction.DESC, "createTime");
        printList(result);
    }

    @Test
    public void update() {
        UpdateResult updateResult = msgChannelService.updateChannelName("测试channel0", "飞信");
        print(updateResult.toString());
    }


    private void print(String msg) {
        System.out.println("msg ---->" + msg);
    }

    private void printList(List<MsgChannel> msgChannels) {
        if (msgChannels != null && msgChannels.size() > 0) {
            for (MsgChannel channel : msgChannels) {
                print(channel.toString());
            }
        }
    }


}
