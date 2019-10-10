package com.lhsystems.module.datageneratorancillary.service.data;

public enum BookingChannel {
    LH_GROUP_DIRECT_ONLI("LH GROUP DIRECT ONLI"),
    LH_GROUP_DIRECT_OFFL("LH GROUP DIRECT OFFL"),
    TOUR_OPERATOR("TOUR OPERATOR"),
    ONLINE_AGENTS("ONLINE AGENTS"),
    LOCAL_CHAINS("LOCAL CHAINS"),
    CONSOLIDATOR("CONSOLIDATOR"),
    RETAILER("RETAILER"),
    GLOBAL_CHAINS("GLOBAL CHAINS"),
    SPECIAL("SPECIAL"),
    UNKNOWN("Unknown"),
    OAL_SALES("OAL SALES");

    private final String channelName;

    BookingChannel(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelName() {
        return channelName;
    }
}
