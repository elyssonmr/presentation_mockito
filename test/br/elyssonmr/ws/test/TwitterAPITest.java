package br.elyssonmr.ws.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.elyssonmr.rest.RestServiceTwitterAPI;

public class TwitterAPITest {

    private RestServiceTwitterAPI ws;
    
    @Before
    public void setUp() throws Exception {
	ws = Mockito.mock(RestServiceTwitterAPI.class);
	Mockito.when(ws.authenticate("teste", "teste")).thenReturn(true);
	
    }

    @Test
    public void authenticateTest() {
	String username = "teste";
	String password = "teste";
	boolean logged = ws.authenticate(username, password);
	Mockito.verify(ws).authenticate(username, password);
	assertTrue(logged);
    }
    @Test
    public void tagTest() {
	String tag = "#workshop";
	ws.findByTag(tag);
	Mockito.verify(ws).findByTag(tag);
    }
    
    @Test
    public void mentionTest() {
	String name = "@elyssonmr";
	ws.findByMention(name);
	Mockito.verify(ws).findByMention(name);
    }

}
