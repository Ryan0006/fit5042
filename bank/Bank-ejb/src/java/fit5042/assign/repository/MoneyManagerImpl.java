/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.repository;

import fit5042.assign.interfaces.AccountRepository;
import fit5042.assign.interfaces.TransactionRepository;
import fit5042.assign.interfaces.TransactionTypeManager;
import fit5042.assign.repository.entities.Account;
import fit5042.assign.repository.entities.PublicMember;
import fit5042.assign.repository.entities.Transaction;
import fit5042.assign.repository.entities.User;
import fit5042.assign.utility.TransactionType;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import fit5042.assign.interfaces.MoneyManager;

/**
 *
 * @author jerrychen
 */
@Stateless
public class MoneyManagerImpl implements MoneyManager {

    @PersistenceContext
    EntityManager entityManager;

    @EJB
    AccountRepository accountRepository;

    @EJB
    TransactionTypeManager transactionTypeManager;

    @Override
    public void deposit(Account account, double amount) throws Exception {
        List<TransactionType> types = transactionTypeManager.findAllTypes();
        for (TransactionType type : types) {
            if (type.getType().equals(TransactionType.TYPE_DEPOSIT)) {
                account.takeIn(amount);
                entityManager.merge(account);
                Transaction transaction = new Transaction(
                        TransactionType.NAME_DEPOSIT,
                        TransactionType.TYPE_DEPOSIT,
                        "Deposit $" + amount + " into card No: " + account.getCardNo(),
                        account.getOwner());
                entityManager.persist(transaction);
            }
        }
    }
    
    @Override
    public boolean withdraw(Account account, double amount) throws Exception {
        List<TransactionType> types = transactionTypeManager.findAllTypes();
        for (TransactionType type : types) {
            if (type.getType().equals(TransactionType.TYPE_WITHDRAW)) {
                if (account.getBalance() < amount) {
                    return false;
                }
                account.takeOut(amount);
                entityManager.merge(account);
                Transaction transaction = new Transaction(
                        TransactionType.NAME_WITHDRAW,
                        TransactionType.TYPE_WITHDRAW,
                        "Withdraw $" + amount + " from card No: " + account.getCardNo(),
                        account.getOwner());
                entityManager.persist(transaction);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean transferMoney(int senderNo, int receiverNo, double amount) throws Exception {
        List<TransactionType> types = transactionTypeManager.findAllTypes();
        for (TransactionType type : types) {
            if (type.getType().equals(TransactionType.TYPE_TRANSFER)) {
                Account senderAccount = accountRepository.searchAccountByNo(senderNo);
                Account receiverAccount = accountRepository.searchAccountByNo(receiverNo);
                if (senderAccount == null || receiverAccount == null) {
                    return false;
                }
                if (senderAccount.getBalance() < amount) {
                    return false;
                }
                senderAccount.takeOut(amount);
                receiverAccount.takeIn(amount);
                entityManager.merge(senderAccount);
                entityManager.merge(receiverAccount);

                Transaction transaction = new Transaction(
                        TransactionType.NAME_TRANSFER,
                        TransactionType.TYPE_TRANSFER,
                        "Card No." + senderAccount.getCardNo() + " transfers $" + amount
                        + " to card No." + receiverAccount.getCardNo(),
                        senderAccount.getOwner());
                entityManager.persist(transaction);
                return true;
            }
        }
        return false;
    }
}
