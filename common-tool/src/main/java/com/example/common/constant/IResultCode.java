package com.example.common.constant;

public abstract interface IResultCode
{
    public static abstract interface Commons
    {
        public static final int HTTP_OK = 200;
        
        public static final String HTTP_ERROR = "000500";
        
        public static final String SUCCESS = "000000";
        
        public static final String ERROR_UNKNOWN = "000001";
        
        public static final String ERROR_DATABASE_OP = "000002";
        
        public static final String ERROR_PARAMETER = "000003";
        
        public static final String ERROR_UPLOAD_IO = "000004";
        
        public static final String ERROR_NO_SUPPORT_OP = "000005";
        
        public static final String ERROR_MAX_UPLOAD_SIZE = "000006";
        
        public static final String ERROR_VER_CODE = "000007";
        
        public static final String ERROR_GET_VER_CODE_OUT_LIMIT = "000008";
        
        public static final String ERROR_AUTHORIZATION = "000009";
    }
}
