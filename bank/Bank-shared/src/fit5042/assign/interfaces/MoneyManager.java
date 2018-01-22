/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assign.interfaces;

import fit5042.assign.repository.entities.Account;

/**
 *
 * @author jerrychen
 */
public interface MoneyManager {

    public boolean transferMoney(int senderNo, int receiverNo, double amount) throws Exception;

    public void deposit(Account account, double amount) throws Exception;

    public boolean withdraw(Account account, double amount) throws Exception;
}
