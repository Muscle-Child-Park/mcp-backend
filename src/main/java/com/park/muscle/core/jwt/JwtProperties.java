package com.park.muscle.core.jwt;

public class JwtProperties {

    public static final String SECRET = "MFswDQYJKoZIhvcNAQEBBQADSgAwRwJAaJd167MUifo3ZSytpDPd9z7oSS+1KXjfHJ3oMvI61Id26fdQHgWE1QMLcrhOmRzTxkCU+gesx5ANkSSIrPHNswIDAQAB";
    public static final long ACCESS_TOKEN_EXPIRATION = 1000L * 60; // 1분
    public static final long REFRESH_TOKEN_EXPIRATION = 1000L * 60 * 3; // 3분
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    private JwtProperties() {
    }
}
