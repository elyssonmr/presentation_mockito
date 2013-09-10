package br.elyssonmr.rest;

import java.util.List;

import br.elyssonmr.model.Tweet;

public class RestServiceTwitterAPI {
    private String username;
    private String password;
    
    public List<Tweet> findByTag(String tag) {
	//All logic to connect and retrieve the tweets
	return null;
    }
    
    public List<Tweet> findByMention(String mention) {
	//All logic to connect and retrieve the tweets
	return null;
    }

    public boolean authenticate(String username, String password) {
	return false;
    }
}
