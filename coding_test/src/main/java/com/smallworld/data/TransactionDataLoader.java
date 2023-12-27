package com.smallworld.data;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TransactionDataLoader {
	
	public static List<Transaction> loadTransactionsFromJsonFile(String jsonPath){
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Transaction[] transactionsArray = objectMapper.readValue(new File(jsonPath), Transaction[].class);
			return Arrays.asList(transactionsArray);
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
