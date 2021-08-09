package com.br.provafundacred;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class MyTestRequestFactory {

    public static MockHttpServletRequestBuilder myFactoryRequest(String url) {
        return MockMvcRequestBuilders.get(url)
                .header("Accept", "myValue")
                .header("Authorization", "myValue2");
    }
}
