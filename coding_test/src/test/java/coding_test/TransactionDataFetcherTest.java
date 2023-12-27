package coding_test;

import org.junit.jupiter.api.Test;

import com.smallworld.TransactionDataFetcher;
import com.smallworld.data.Transaction;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class TransactionDataFetcherTest {
	 @Test
	    public void testGetTotalTransactionAmount() {
	        TransactionDataFetcher dataFetcher = new TransactionDataFetcher();
	        double totalAmount = dataFetcher.getTotalTransactionAmount();
	        assertTrue(totalAmount > 0, "Total transaction amount should be greater than 0");
	    }

	    @Test
	    public void testGetTotalTransactionAmountSentBy() {
	        TransactionDataFetcher dataFetcher = new TransactionDataFetcher();
	        double totalAmount = dataFetcher.getTotalTransactionAmountSentBy("SenderName");
	        assertTrue(totalAmount >= 0, "Total transaction amount sent by a specific sender should be greater than or equal to 0");
	    }

	    @Test
	    public void testGetMaxTransactionAmount() {
	        TransactionDataFetcher dataFetcher = new TransactionDataFetcher();
	        double maxAmount = dataFetcher.getMaxTransactionAmount();
	        assertTrue(maxAmount >= 0, "Max transaction amount should be greater than or equal to 0");
	    }

	    @Test
	    public void testCountUniqueClients() {
	        TransactionDataFetcher dataFetcher = new TransactionDataFetcher();
	        long uniqueClients = dataFetcher.countUniqueClients();
	        assertTrue(uniqueClients >= 0, "Number of unique clients should be greater than or equal to 0");
	    }

	    @Test
	    public void testHasOpenComplianceIssues() {
	        TransactionDataFetcher dataFetcher = new TransactionDataFetcher();
	        assertTrue(dataFetcher.hasOpenComplianceIssues("Arthur Shelby"), "There should be open compliance issues for the specified client");
	    }

	    @Test
	    public void testGetTransactionsByBeneficiaryName() {
	        TransactionDataFetcher dataFetcher = new TransactionDataFetcher();
	        // Replace "BeneficiaryName" with an actual beneficiary name from your test data
	        List<Transaction> transactions = dataFetcher.getTransactionsByBeneficiaryName().get("Arthur Shelby");
	        assertNotNull(transactions, "Transactions by beneficiary name should not be null");
	        assertTrue(transactions.size() > 0, "There should be transactions for the specified beneficiary");
	    }

	    @Test
	    public void testGetUnsolvedIssueIds() {
	        TransactionDataFetcher dataFetcher = new TransactionDataFetcher();
	        Set<Integer> unsolvedIssueIds = dataFetcher.getUnsolvedIssueIds();
	        assertNotNull(unsolvedIssueIds, "Unsolved issue IDs should not be null");
	    }

	    @Test
	    public void testGetAllSolvedIssueMessages() {
	        TransactionDataFetcher dataFetcher = new TransactionDataFetcher();
	        List<String> solvedIssueMessages = dataFetcher.getAllSolvedIssueMessages();
	        assertNotNull(solvedIssueMessages, "Solved issue messages should not be null");
	    }

	    @Test
	    public void testGetTop3TransactionsByAmount() {
	        TransactionDataFetcher dataFetcher = new TransactionDataFetcher();
	        List<Transaction> top3Transactions = dataFetcher.getTop3TransactionsByAmount();
	        assertNotNull(top3Transactions, "Top 3 transactions by amount should not be null");
	        assertTrue(top3Transactions.size() <= 3, "There should be at most 3 transactions");
	    }

	    @Test
	    public void testGetTopSender() {
	        TransactionDataFetcher dataFetcher = new TransactionDataFetcher();
	        Optional<String> topSender = dataFetcher.getTopSender();
	        assertTrue(topSender.isPresent(), "Top sender should be present");
	    }
}