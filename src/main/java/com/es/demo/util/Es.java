package com.es.demo.util;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 *
 */
public class Es {
    private static Logger logger = LoggerFactory.getLogger(Es.class);

    //1、集群host
    private final static String HOST1 = "mini1";
    private final static String HOST2 = "mini2";
    private final static String HOST3 = "mini3";
    //2、集群host
    private final static int PORT = 9300;

    private   static RestHighLevelClient client = null;

    static Settings settings = Settings.builder()
            .put("cluster.name", "elasticsearch")
            .put("client.transport.sniff", true)
            .build();

    static {
        try {
            getConnection();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    private static void getConnection() throws UnknownHostException {

          client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("HOST1", 9200, "http"),
                        new HttpHost("HOST2", 9200, "http"),
                        new HttpHost("HOST3", 9200, "http")));
    }

    private static void close() throws IOException {
     client.close();
    }

}
