package com.jowety.data.client.search;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jowety.data.client.search.Filter.MatchMode;


public class JsonTest {
	
	final static Logger logger = LoggerFactory.getLogger(JsonTest.class);
	ObjectMapper om = new ObjectMapper();
	
	@Test
	public void testoutput() throws JsonProcessingException {
		Search s = new Search()
		.select("firstName", "lastName")
		.where().function(new Length("firstName")).greaterOrEqual().literal(6)
		.where().path("lastName").notEqual().path("firstName")
		.where().path("lastName").startsWith().function(new SubStr("firstName", 1,5))
		.orderByAsc("firstName");
		String json = om.writeValueAsString(s);
		logger.debug(json);
		Search s2 = om.readValue(json, Search.class);
		logger.debug(s2.toString());
	}
//	@Test
	public void testFilter() throws JsonProcessingException {
		Filter f = new Filter();
		f.setMatchMode(MatchMode.NOT_EQUAL);
		f.setLeftSide(Exp.path("lastName"));
		f.setRightSide(Exp.path("firstName"));
		String json = om.writeValueAsString(f);
		logger.debug(json);
		logger.debug("-----------------------------------");
		Filter f2 = om.readValue(json, Filter.class);
		logger.debug(f2.toString());
	}
//	@Test
	public void testExp() throws JsonProcessingException {
		Exp a = Exp.path("firstName");
		String json = om.writeValueAsString(a);
		logger.debug(json);
		logger.debug("-----------------------------------");
		Exp a2 = om.readValue(json, Exp.class);
		logger.debug(a2.toString());
	}

}
