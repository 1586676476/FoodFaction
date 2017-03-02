package com.jiang.foodfaction.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dllo on 17/3/2.
 */
@Entity
public class Bean {
    @Id
    private String url;
    private String title;
    @Generated(hash = 2139430122)
    public Bean(String url, String title) {
        this.url = url;
        this.title = title;
    }
    @Generated(hash = 80546095)
    public Bean() {
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
