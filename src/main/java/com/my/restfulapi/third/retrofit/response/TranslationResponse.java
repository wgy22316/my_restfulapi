package com.my.restfulapi.third.retrofit.response;

import lombok.Data;

@Data
public class TranslationResponse {
    private int status;
    private content content;

    @Data
    private static class content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;
    }
}
