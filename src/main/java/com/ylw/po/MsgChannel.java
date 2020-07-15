package com.ylw.po;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Data
@Document(collection="msg_channel")
public class MsgChannel {

    public static final String COLLECTION_NAME="msg_channel";

    @Id
    private String channelId;//主键

    private Integer channelType;

    private String channelName;

    private Integer channelState;

    private String appId;

    private String secretId;

    private String host;

    private String userName;

    private String passWord;

    private String classAddress;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private Boolean isDelete;
}
