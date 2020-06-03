package com.odin.push.demo.utils;

import java.io.Serializable;

/**
 * 推送任务明细，web端推送的接口数据实例
 */
public class PushData implements Serializable {

    /**
     * 主键 , 批次编号(odin平台产生)
     */
    protected String batchId;

    /**
     * 创建时间
     */
    protected Long createAt;

    /**
     * 更新时间
     */
    protected Long updateAt;

    /**
     * 推送任务标识，对接用户服务端唯一ID，传入后原样返回（用户服务端自有）
     */
    protected String workno;

    /**
     * 推送任务来源：webapi 、developerPlatform WorkSourceEnum
     */
    protected String source;

    /**
     * 应用APPKEY
     */
    protected String appkey;

    /**
     * 可使用平台，1  android;2 ios;3 winphone(暂不使用) ;
     * 如果多选则使用组合按小到大排，如12表示推送android和ios
     */
    protected Integer[] plats;

    /**
     * plat = 2下，0测试环境，1生产环境
     */
    protected Integer iosProduction;

    /**
     * 推送范围:0 全部；1广播；2别名；3标签；4regid；5地理位置；6用户分群
     */
    protected Integer target;

    /**
     * 设置推送标签集合["tag1","tag2"]
     */
    protected String[] tags;

    /**
     * 标签组合方式：1并集；2交集；3补集(3暂不考虑)
     */
    protected Integer tagsType;

    /**
     * 设置推送别名集合["alias1","alias2"]
     */
    protected String[] alias;

    /**
     * 设置推送Registration Id集合["Id1","id2"]
     */
    protected String[] registrationIds;

    /**
     * 用户分群ID
     */
    protected String block;

    /**
     * 推送地理位置
     */
//	protected String region;
    protected String city;
    protected String country;
    protected String province;

    /**
     * 推送内容
     */
    protected String content;

    /**
     * 离线保留时间，0表示不发离线， 可选择1天，10天
     */
    protected Integer offlineTime;

    /**
     * 推送类型：1通知；2自定义
     */
    protected Integer type;

    /**
     * 是否是定时任务：0否，1是，默认0
     */
    protected Integer workType;

    /**
     * 定时消息 发送时间
     */
    protected Long taskTime;

    /**
     * 推送任务状态：0删除；1创建中；2正在发送；3部分发送完成；4发送完成；5发送失败；6停止发送； WorkDetailStatusEnum
     */
    protected Integer status;

    /**
     * 是否重新发送,true是，默认false否
     */
    protected Boolean repeat;

    /**
     * 附加字段键值对的方式，扩展数据 json
     */
    protected String extras;

    /**
     * 自定义内容 CustomMessage.class
     *//*
	protected String customMessage;*/

    /**
     * 自定义消息类型：text 文本消息
     * CustomTypeEnum
     */
    protected String customType;

    /**
     * 自定义类型标题
     */
    protected String customTitle;


    /**
     * android通知消息 AndroidNotify.class
     *//*
	protected String androidNotify;*/

    /**
     * 通知标题
     */
    protected String androidTitle;

    /**
     * 显示样式标识
     */
    protected Integer androidstyle;

    /**
     * content:样式具体内容： 0、默认通知无； 1、长内容则为内容数据； 2、大图则为图片地址； 3、横幅则为多行内容
     */
    protected String[] androidContent;


    /**
     * warn:  提醒类型： 提示音；
     */
    protected Boolean androidVoice;

    /**
     * androidShake:震动
     */
    protected Boolean androidShake;

    /**
     * androidLight:指示灯
     */
    protected Boolean androidLight;

    /**
     * 标题- 不填写则为应用名称
     */
    protected String title;

    /**
     * 副标题
     */
    protected String subtitle;

    /**
     * APNs通知，通过这个字段指定声音。默认为default，即系统默认声音。 如果设置为空值，则为静音。
     * 如果设置为特殊的名称，则需要你的App里配置了该声音才可以正常。
     */
    protected String sound = "default";

    /**
     * 可直接指定 APNs 推送通知的 badge，直接展示在桌面应用图标的右上角，含义是应用未读的消息数。也支持如 +12，-3 等运算操作，
     * 会针对每一个用户当前的 badge 做单独的运算，例：接受者 A，B 的角标分别为 1 和 2，那么推送 +2 后 A的角标变为 3，B 的角标变为 4。默认值为 1。
     */
    protected Integer badge = 1;

    /**
     * 只有IOS8及以上系统才支持此参数推送
     */
    protected String category;

    /**
     * 如果只携带content-available: 1,不携带任何badge，sound 和消息内容等参数，
     * 则可以不打扰用户的情况下进行内容更新等操作即为“Silent Remote Notifications”。
     */
    protected Integer silentPush;

    protected Integer contentAvailable;

    /**
     * 需要在附加字段中配置相应参数
     */
    protected Integer mutableContent;

    protected String attachment;

    /**
     * 状态变更类型 ： 0删除，1重新发送，2停止发送
     */
    protected Integer opType;

    /**
     * 智能标签，格式{"agebin":[1,2],"gender":[-1,0]}
     */
    protected String label;

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public Long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }

    public String getWorkno() {
        return workno;
    }

    public void setWorkno(String workno) {
        this.workno = workno;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public Integer[] getPlats() {
        return plats;
    }

    public void setPlats(Integer[] plats) {
        this.plats = plats;
    }

    public Integer getIosProduction() {
        return iosProduction;
    }

    public void setIosProduction(Integer iosProduction) {
        this.iosProduction = iosProduction;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public Integer getTagsType() {
        return tagsType;
    }

    public void setTagsType(Integer tagsType) {
        this.tagsType = tagsType;
    }

    public String[] getAlias() {
        return alias;
    }

    public void setAlias(String[] alias) {
        this.alias = alias;
    }

    public String[] getRegistrationIds() {
        return registrationIds;
    }

    public void setRegistrationIds(String[] registrationIds) {
        this.registrationIds = registrationIds;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(Integer offlineTime) {
        this.offlineTime = offlineTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getWorkType() {
        return workType;
    }

    public void setWorkType(Integer workType) {
        this.workType = workType;
    }

    public Long getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Long taskTime) {
        this.taskTime = taskTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getRepeat() {
        return repeat;
    }

    public void setRepeat(Boolean repeat) {
        this.repeat = repeat;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public String getCustomType() {
        return customType;
    }

    public void setCustomType(String customType) {
        this.customType = customType;
    }

    public String getCustomTitle() {
        return customTitle;
    }

    public void setCustomTitle(String customTitle) {
        this.customTitle = customTitle;
    }

    public String getAndroidTitle() {
        return androidTitle;
    }

    public void setAndroidTitle(String androidTitle) {
        this.androidTitle = androidTitle;
    }

    public Integer getAndroidstyle() {
        return androidstyle;
    }

    public void setAndroidstyle(Integer androidstyle) {
        this.androidstyle = androidstyle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public Integer getBadge() {
        return badge;
    }

    public void setBadge(Integer badge) {
        this.badge = badge;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getSilentPush() {
        return silentPush;
    }

    public void setSilentPush(Integer silentPush) {
        this.silentPush = silentPush;
    }

    public Integer getContentAvailable() {
        return contentAvailable;
    }

    public void setContentAvailable(Integer contentAvailable) {
        this.contentAvailable = contentAvailable;
    }

    public Integer getMutableContent() {
        return mutableContent;
    }

    public void setMutableContent(Integer mutableContent) {
        this.mutableContent = mutableContent;
    }

    public Integer getOpType() {
        return opType;
    }

    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    public String[] getAndroidContent() {
        return androidContent;
    }

    public void setAndroidContent(String[] androidContent) {
        this.androidContent = androidContent;
    }

    public Boolean getAndroidVoice() {
        return androidVoice;
    }

    public void setAndroidVoice(Boolean androidVoice) {
        this.androidVoice = androidVoice;
    }

    public Boolean getAndroidShake() {
        return androidShake;
    }

    public void setAndroidShake(Boolean androidShake) {
        this.androidShake = androidShake;
    }

    public Boolean getAndroidLight() {
        return androidLight;
    }

    public void setAndroidLight(Boolean androidLight) {
        this.androidLight = androidLight;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


}
