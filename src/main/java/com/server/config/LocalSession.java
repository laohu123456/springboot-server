package com.server.config;

public class LocalSession {

    private final static ThreadLocal<String> userid_localThread = new ThreadLocal<>();

    public static ThreadLocal<String> getUserid_localThread() {
        return userid_localThread;
    }

    public static void set(String userid) {
        userid_localThread.set(userid);
    }

    public static String getUserId() {
        return userid_localThread.get();
    }

    public static void removeId() {
        userid_localThread.remove();
    }

}
