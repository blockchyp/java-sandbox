package com.blockchyp.sandbox.history;

import java.util.Date;
import java.util.HashSet;

import org.junit.Test;

import com.blockchyp.client.BlockChypClient;
import com.blockchyp.client.dto.AuthorizationResponse;
import com.blockchyp.client.dto.BatchHistoryRequest;
import com.blockchyp.client.dto.BatchHistoryResponse;
import com.blockchyp.client.dto.BatchSummary;
import com.blockchyp.client.dto.TransactionHistoryRequest;
import com.blockchyp.client.dto.TransactionHistoryResponse;
import com.blockchyp.sandbox.util.IntegrationTestConfiguration;

public class TransactionHistoryTest {
	
	
	@Test
	public void testTransactionHistoryPaging() throws Exception {
		
		BlockChypClient client = IntegrationTestConfiguration.getTestClient();

		TransactionHistoryRequest request = new TransactionHistoryRequest();
		request.setMaxResults(50);
		
		
		HashSet<String> processedIds = new HashSet<String>();
		int processed = 0;
		
		TransactionHistoryResponse response = null;
	
		
		do {
			
			response = client.transactionHistory(request);
			processed += response.getTransactions().size();
			
			for (AuthorizationResponse authResponse : response.getTransactions()) {
				if (processedIds.contains(authResponse.getTransactionId())) {
					throw new IllegalStateException("duplicate transaction id");
				}
				processedIds.add(authResponse.getTransactionId());
			}
			
			request.setStartIndex(processed);
			
			
		} while (processed < response.getTotalResultCount());
		
	}
	
	@Test
	public void testDateHistoryPaging() throws Exception {
		
		BlockChypClient client = IntegrationTestConfiguration.getTestClient();
		
		TransactionHistoryRequest request = new TransactionHistoryRequest();
		request.setMaxResults(50);
		request.setStartDate(new Date(0));
		request.setEndDate(new Date());
		
		
		HashSet<String> processedIds = new HashSet<String>();
		int processed = 0;
		
		TransactionHistoryResponse response = null;
	
		
		do {
			
			response = client.transactionHistory(request);
			processed += response.getTransactions().size();
			
			for (AuthorizationResponse authResponse : response.getTransactions()) {
				if (processedIds.contains(authResponse.getTransactionId())) {
					throw new IllegalStateException("duplicate transaction id");
				}
				processedIds.add(authResponse.getTransactionId());
			}
			
			request.setStartIndex(processed);
			
			
		} while (processed < response.getTotalResultCount());
		
		
	}
	
	@Test
	public void testBatchTerminalHistoryPaging() throws Exception {
		
		BlockChypClient client = IntegrationTestConfiguration.getTestClient();
		
		BatchHistoryResponse batchHistory = client.batchHistory(new BatchHistoryRequest());
		
		for (BatchSummary batch : batchHistory.getBatches()) {
			
			TransactionHistoryRequest request = new TransactionHistoryRequest();
			request.setBatchId(batch.getBatchId());
			request.setMaxResults(50);
			request.setTerminalName("Register 1");
			
			
			HashSet<String> processedIds = new HashSet<String>();
			int processed = 0;
			
			TransactionHistoryResponse response = null;
		
			
			do {
				
				response = client.transactionHistory(request);
				processed += response.getTransactions().size();
				
				for (AuthorizationResponse authResponse : response.getTransactions()) {
					if (processedIds.contains(authResponse.getTransactionId())) {
						throw new IllegalStateException("duplicate transaction id");
					}
					processedIds.add(authResponse.getTransactionId());
				}
				
				request.setStartIndex(processed);
				
				
			} while (processed < response.getTotalResultCount());
			
			
		}

		
	}
	
	
	@Test
	public void testBatchHistoryPaging() throws Exception {
		
		BlockChypClient client = IntegrationTestConfiguration.getTestClient();
		
		BatchHistoryResponse batchHistory = client.batchHistory(new BatchHistoryRequest());
		
		for (BatchSummary batch : batchHistory.getBatches()) {
			
			TransactionHistoryRequest request = new TransactionHistoryRequest();
			request.setBatchId(batch.getBatchId());
			request.setMaxResults(50);
			
			
			HashSet<String> processedIds = new HashSet<String>();
			int processed = 0;
			
			TransactionHistoryResponse response = null;
		
			
			do {
				
				response = client.transactionHistory(request);
				processed += response.getTransactions().size();
				
				for (AuthorizationResponse authResponse : response.getTransactions()) {
					if (processedIds.contains(authResponse.getTransactionId())) {
						throw new IllegalStateException("duplicate transaction id");
					}
					processedIds.add(authResponse.getTransactionId());
				}
				
				request.setStartIndex(processed);
				
				
			} while (processed < response.getTotalResultCount());
			
			
		}

		
	}
	
	@Test
	public void testTerminalHistoryPaging() throws Exception {
		
		BlockChypClient client = IntegrationTestConfiguration.getTestClient();
		

		TransactionHistoryRequest request = new TransactionHistoryRequest();
		request.setMaxResults(50);
		request.setTerminalName("Register 1");
	
		
		HashSet<String> processedIds = new HashSet<String>();
		int processed = 0;
		
		TransactionHistoryResponse response = null;
	
		
		do {
			
			response = client.transactionHistory(request);
			processed += response.getTransactions().size();
			
			for (AuthorizationResponse authResponse : response.getTransactions()) {
				if (processedIds.contains(authResponse.getTransactionId())) {
					throw new IllegalStateException("duplicate transaction id");
				}
				processedIds.add(authResponse.getTransactionId());
			}
			
			request.setStartIndex(processed);
			
			
		} while (processed < response.getTotalResultCount());
		
	}

}
