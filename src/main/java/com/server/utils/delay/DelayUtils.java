package com.server.utils.delay;

import com.server.entity.User;
import com.server.utils.OtherUtils;

import java.util.concurrent.DelayQueue;

public class DelayUtils {


    public static void main(String[] args) {
        DelayQueue<DelayElement<User>> delayQueue = new DelayQueue<DelayElement<User>>();
        new provider(delayQueue).start();
        new consumer(delayQueue).start();
    }
    public static class consumer  extends Thread{
        private DelayQueue<DelayElement<User>>  delayQueue;

        public consumer(DelayQueue<DelayElement<User>> delayQueue) {
            this.delayQueue = delayQueue;
        }

        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String id = OtherUtils.getUUID();
                User user = new User();
                user.setUserId(id);
                DelayElement<User> element = new DelayElement<User>(user,2000L);
                delayQueue.offer(element);
                System.out.println("放入一个元素" + System.currentTimeMillis() + "标识ID: " + id);
            }
        }
    }

    public static class provider extends Thread{
        private DelayQueue<DelayElement<User>>  delayQueue;

        public provider(DelayQueue<DelayElement<User>> delayQueue) {
            this.delayQueue = delayQueue;
        }

        @Override
        public void run() {
            while (true){
                try {
                    DelayElement<User> take = delayQueue.take();
                    System.out.println("到期时间 :" + System.currentTimeMillis() + "到期元素 : " + take.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}


