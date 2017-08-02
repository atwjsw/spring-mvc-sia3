package com.habuma.spitter.client;

import com.habuma.spitter.domain.Spittle;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * Created by wenda on 8/2/2017.
 */
public class RestSpittleClientTest {

    @Test
    public void shouldGetSpittle() {
        RestTemplate rest = new RestTemplate();
        Long spittleId = 1L;
        Spittle spittle = rest.getForObject("http://localhost:8080/spittles/{spittleId}", Spittle.class, spittleId);
        System.out.println(spittle);
    }
    @Test
    public void shouldGetEntitySpittle() {
        RestTemplate rest = new RestTemplate();
        Long spittleId = 1L;
        ResponseEntity<Spittle> response = rest.getForEntity("http://localhost:8080/spittles/{spitter}",
                Spittle.class, spittleId);
        Spittle spittle = response.getBody();
        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getHeaders().getAccept());
        System.out.println(spittle);
//        assertExpectedSpitter(spitter);
    }

    @Test
    public void shouldUpdateSpittle() {
        RestTemplate rest = new RestTemplate();
        Long spittleId = 1L;
        Spittle spittle = new Spittle();
        spittle.setId(spittleId);
        spittle.setText("update text");
        spittle.setWhen(new Date());
        rest.put("http://localhost:8080/spittles/{spittleUd}", spittle, spittle.getId());
    }

    @Test
    public void shouldDeleteSpittle() {
        RestTemplate rest = new RestTemplate();
        Long spittleId = 1L;
        rest.delete("http://localhost:8080/spittles/{spittleId}", spittleId);
    }
}
