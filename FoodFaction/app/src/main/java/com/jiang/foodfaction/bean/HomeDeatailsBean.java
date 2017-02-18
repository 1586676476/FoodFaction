package com.jiang.foodfaction.bean;

/**
 * Created by dllo on 17/2/18.
 */

public class HomeDeatailsBean {

    /**
     * id : 7810
     * title : 黑胡椒牛排&全麦面包
     * image_url : http://one.boohee.cn/food/2016/10/30/58A628DA-D358-4096-9C3F-7AB25988D845.jpg?
     * description :
     * food_code :
     * post_date : 2016-10-30T02:27:57.000+08:00
     * user_key : bccfb6bb-83b7-4c03-9829-9865594b08f0
     * user_name : CHENG1228
     * user_avatar : http://wx.qlogo.cn/mmopen/PiajxSqBRaELj1mEkGxiamCB8UjibfJibusEBsZO0hAGpHA4iacP9CNufO8WJda4BRtMic5rvMzGF7hJYLibhbYxJns0g/0
     * like_ct : 985
     */

    private int id;
    private String title;
    private String image_url;
    private String description;
    private String food_code;
    private String post_date;
    private String user_key;
    private String user_name;
    private String user_avatar;
    private int like_ct;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFood_code() {
        return food_code;
    }

    public void setFood_code(String food_code) {
        this.food_code = food_code;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public String getUser_key() {
        return user_key;
    }

    public void setUser_key(String user_key) {
        this.user_key = user_key;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public int getLike_ct() {
        return like_ct;
    }

    public void setLike_ct(int like_ct) {
        this.like_ct = like_ct;
    }
}
