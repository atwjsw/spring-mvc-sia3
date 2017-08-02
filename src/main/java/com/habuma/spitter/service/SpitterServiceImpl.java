package com.habuma.spitter.service;

import com.habuma.spitter.domain.Spitter;
import com.habuma.spitter.domain.Spittle;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wenda on 8/2/2017.
 */
@Service("spitterService")
public class SpitterServiceImpl implements SpitterService {
    @Override
    public List<Spittle> getRecentSpittles(int count) {
        return null;
    }

    @Override
    public void saveSpittle(Spittle spittle) {

    }

    @Override
    public void saveSpitter(Spitter spitter) {

    }

    @Override
    public Spitter getSpitter(long id) {
        return null;
    }

    @Override
    public void startFollowing(Spitter follower, Spitter followee) {

    }

    @Override
    public List<Spittle> getSpittlesForSpitter(Spitter spitter) {
        return null;
    }

    @Override
    public List<Spittle> getSpittlesForSpitter(String username) {
        return null;
    }

    @Override
    public Spitter getSpitter(String username) {
        return null;
    }

    @Override
    public Spittle getSpittleById(long id) {
        return null;
    }

    @Override
    public void deleteSpittle(long id) {

    }

    @Override
    public List<Spitter> getAllSpitters() {
        return null;
    }
}
