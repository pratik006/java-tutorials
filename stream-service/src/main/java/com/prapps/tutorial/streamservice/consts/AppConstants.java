package com.prapps.tutorial.streamservice.consts;

public enum AppConstants {
    TOPIC_NAME(Constants.INPUT_TOPIC_NAME),
    GROUP_ID(Constants.GROUP_ID_CONST);

    private String code;

    AppConstants(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static class Constants {
        public static final String INPUT_TOPIC_NAME = "test_input";
        public static final String RESULT_TOPIC_NAME = "test_result";
        public static final String GROUP_ID_CONST = "test_group";
    }
}
