package com.smallworld;

import com.smallworld.data.Issue;
import com.smallworld.data.Transaction;
import com.smallworld.data.TransactionDataLoader;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionDataFetcher {
	
	private List<Transaction> transactions;
	
	public TransactionDataFetcher() {
		transactions = TransactionDataLoader.loadTransactionsFromJsonFile("transactions.json");
		
		if(this.transactions == null) {
			throw new RuntimeException("Failed to load transactions from transactions file");
		}
	}

    /**
     * Returns the sum of the amounts of all transactions
     */
    public double getTotalTransactionAmount() {
        return transactions.stream().mapToDouble(Transaction::getAmount).sum();
    }

    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     */
    public double getTotalTransactionAmountSentBy(String senderFullName) {
        return transactions.stream().filter(transaction -> senderFullName.equals(transaction.getSenderFullName())).mapToDouble(Transaction::getAmount).sum();
    }

    /**
     * Returns the highest transaction amount
     */
    public double getMaxTransactionAmount() {
        return transactions.stream().mapToDouble(Transaction::getAmount).max().orElse(0);
    }

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    public long countUniqueClients() {
        return transactions.stream().flatMap(transaction -> Stream.of(transaction.getSenderFullName(), transaction.getBeneficiaryFullName()))
                .collect(Collectors.toSet()).size();
    }

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
    public boolean hasOpenComplianceIssues(String clientFullName) {
        return transactions.stream().anyMatch(transaction -> (clientFullName.equals(transaction.getSenderFullName()) || 
        		clientFullName.equals(transaction.getBeneficiaryFullName()) 
        		&& (transaction.getIssues().stream().anyMatch(issue -> issue.getIssueId() > 0 && !issue.isIssueSolved()))));
    }

    /**
     * Returns all transactions indexed by beneficiary name
     */
    public Map<String, List<Transaction>> getTransactionsByBeneficiaryName() {
        return transactions.stream().collect(Collectors.groupingBy(Transaction::getBeneficiaryFullName));
    }

    /**
     * Returns the identifiers of all open compliance issues
     */
    public Set<Integer> getUnsolvedIssueIds() {
    	 return transactions.stream()
                 .flatMap(transaction -> transaction.getIssues().stream())  
                 .filter(issue -> !issue.isIssueSolved())
                 .map(Issue::getIssueId)
                 .collect(Collectors.toSet());
    }

    /**
     * Returns a list of all solved issue messages
     */
    public List<String> getAllSolvedIssueMessages() {
    	return transactions.stream()
                .flatMap(transaction -> transaction.getIssues().stream())  
                .filter(Issue::isIssueSolved)
                .map(Issue::getIssueMessage)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Returns the 3 transactions with the highest amount sorted by amount descending
     */
    public List<Transaction> getTop3TransactionsByAmount() {
        return transactions.stream()
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    /**
     * Returns the senderFullName of the sender with the most total sent amount
     */
    public Optional<String> getTopSender() {
    	Map<String, Double> totalSentAmountBySender = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getSenderFullName, Collectors.summingDouble(Transaction::getAmount)));

        return totalSentAmountBySender.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }

}
