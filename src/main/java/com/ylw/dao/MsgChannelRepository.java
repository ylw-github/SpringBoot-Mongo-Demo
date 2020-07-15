package com.ylw.dao;

import com.ylw.po.Comment;
import com.ylw.po.MsgChannel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MsgChannelRepository extends MongoRepository<MsgChannel, String> {


}
