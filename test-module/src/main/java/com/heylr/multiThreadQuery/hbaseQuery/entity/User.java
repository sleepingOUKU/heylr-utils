package com.heylr.multiThreadQuery.hbaseQuery.entity;


import lombok.Data;

import java.util.Date;

@Data
public class User implements Comparable {

    private String userId;

    private String userName;

    private Date timeFlag;


    public int compareTo(Object o) {
        if (o instanceof User) {
            User user = (User) o;
            if (user.getTimeFlag().before(this.timeFlag)) {
                return -1;
            } else if (user.getTimeFlag().after(this.timeFlag)) {
                return 1;
            } else {
                return 0;
            }
        }
        return 0;
    }
}
