package com.bappi.tripservice.config;

public class ApiPath {
    public static final String API_CORE_NAME = "/api";
    public static final String API_VERSION = "/v1";
    public static final String API_BASE_PATH = API_CORE_NAME + API_VERSION;
    public static final String PRODUCT_TITLE = "/trip";
    public static final String API_TRIP_INFO = PRODUCT_TITLE + "/tripInfo";
    public static final String API_CREATE_TRIP_INFO = "/create";
    public static final String API_GET_ALL_CUSTOMER = "/getAllCustomer";
    public static final String API_ORDER = PRODUCT_TITLE + "/order";
    public static final String API_GET_ALL_ORDER_TODAY = "/todayOrder";
    public static final String API_GET_ALL_SALE_TODAY = "/todaySale";
    public static final String API_GET_ALL_ORDER_BY_CUSTOMER = "/customersOrder";
    public static final String API_GET_MAX_SALE_A_DAY = "/maxSaleInADay";

}
