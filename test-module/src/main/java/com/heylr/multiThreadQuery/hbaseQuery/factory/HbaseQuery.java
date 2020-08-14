package com.heylr.multiThreadQuery.hbaseQuery.factory;

import com.heylr.entity.Tuples;
import com.heylr.factory.AbstractBaseQuery;
import com.heylr.multiThreadQuery.hbaseQuery.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HbaseQuery extends AbstractBaseQuery<String,User> {

    public List<Tuples<String>> splitKeysList(String startKey, String endKey) {

        List<Tuples<String>> splitList = new ArrayList<Tuples<String>>();

        Integer startNum = new Integer(startKey);
        Integer endNum = new Integer(endKey);

        Integer step = (endNum - startNum)/5;
        while (startNum + step < endNum){
            Tuples<String> temp = new Tuples<String>(String.valueOf(startNum), String.valueOf(startNum + step));
            splitList.add(temp);
            startNum = startNum + step;
        }

        Tuples<String> last = new Tuples<String>(String.valueOf(startNum), String.valueOf(endNum));
        splitList.add(last);

        return splitList;
    }

    public List<User> query(Tuples tuples) {

        List<User> userList = new ArrayList<User>();

        for(int i =0 ;i<3 ;i++){
            User user = new User();
            user.setUserId(i+"");
            user.setUserName("Name_"+i);
            user.setTimeFlag(new Date());
            userList.add(user);
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return userList;
    }
}
