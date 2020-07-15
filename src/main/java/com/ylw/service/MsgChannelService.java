package com.ylw.service;

import com.mongodb.client.result.UpdateResult;
import com.ylw.dao.MsgChannelRepository;
import com.ylw.po.MsgChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MsgChannelService {

    @Autowired
    private MsgChannelRepository msgChannelRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    /*
     *插入
     */
    public MsgChannel save(MsgChannel channel) {
        return msgChannelRepository.save(channel);
    }


    /*
     *批量插入
     */
    public List<MsgChannel> batchSave(List<MsgChannel> channels) {
        return msgChannelRepository.saveAll(channels);
    }


    /*
     * 查询所有
     */
    public List<MsgChannel> selectAll() {
        return msgChannelRepository.findAll();
    }

    /*
     * 统计条目
     */
    public long count() {
        return msgChannelRepository.count();
    }

    /*
     * 按条件查询
     */
    public List<MsgChannel> selectByChannelName(String channelName, boolean isDelete) {

        Query query = new Query();
        //必须条件
        Criteria c = Criteria.where("channelName").is(channelName);
        c.and("isDelete").is(isDelete);
        query.addCriteria(c);

        //query.with(new Sort(properties));
        return mongoTemplate.find(query, MsgChannel.class, MsgChannel.COLLECTION_NAME);
    }

    /*
     * 范围查询
     */
    public List<MsgChannel> selectByTime(Date startTime, Date endTime) {

        Query query = new Query();
        //必须条件
        Criteria c = new Criteria();
        //对同一个属性加两次限制需这样操作
        c.andOperator(Criteria.where("createTime").lt(endTime), Criteria.where("createTime").gt(startTime));
        query.addCriteria(c);

        //query.with(new Sort(properties));
        return mongoTemplate.find(query, MsgChannel.class, MsgChannel.COLLECTION_NAME);
    }

    /*
     * 排序
     */
    public List<MsgChannel> orderBy(Sort.Direction sort, String orderValue) {

        Query query = new Query();
        query.with(new Sort(sort, orderValue));
        //query.with(new Sort(properties));
        return mongoTemplate.find(query, MsgChannel.class, MsgChannel.COLLECTION_NAME);
    }

    /**
     * 修改
     */
    public UpdateResult updateChannelName(String oldName, String newName) {
        //  查询条件
        Query query = Query.query(Criteria.where("channelName").is(oldName));
        //  更新条件
        Update update = new Update();
        update.set("channelName", newName);
        return mongoTemplate.updateFirst(query, update, MsgChannel.class);
    }

    /**
     * 删除
     */
    public void deleteByID(String channelId){
        Query query = Query.query(Criteria.where("channelId").is("channelId"));
        mongoTemplate.remove(query, MsgChannel.class);
    }
}
