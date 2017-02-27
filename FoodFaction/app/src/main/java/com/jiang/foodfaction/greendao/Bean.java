package com.jiang.foodfaction.greendao;

import android.support.annotation.IdRes;

import com.google.gson.annotations.Expose;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dllo on 17/2/27.
 */

@Entity
public class Bean {
    @Id
    private Long id;
    private String name;
    private String content;
    @Generated(hash = 823518727)
    public Bean(Long id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }
    @Generated(hash = 80546095)
    public Bean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }


}
