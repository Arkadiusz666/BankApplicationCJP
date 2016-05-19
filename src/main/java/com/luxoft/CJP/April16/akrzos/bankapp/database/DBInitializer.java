package com.luxoft.CJP.April16.akrzos.bankapp.database;
import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.accounts.Account;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Client;
import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.DAOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by akrzos on 2016-05-17.
 */
public class DBInitializer extends BaseDAOImplementation{

    public void sqlQueryExecutor(String sql) {
        PreparedStatement stmt;
        try {
            openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
    public void initialize() {
        String sql = "CREATE TABLE CLIENTS (\n" +
                "\tCLIENTS_ID INT AUTO_INCREMENT,\n" +
                "\tCLIENTS_NAME VARCHAR(255) NOT NULL UNIQUE,\n" +
                "\tCLIENTS_GENDER VARCHAR(1),\n" +
                "\tCLIENTS_CITY VARCHAR(255),\n" +
                "\tCLIENTS_BANKS_ID INT,\n" +
                "\tPRIMARY KEY(CLIENTS_ID)\n" +
                ");";
        sqlQueryExecutor(sql);

        sql = "CREATE TABLE BANKS (\n" +
                "BANKS_ID INT AUTO_INCREMENT NOT NULL,\n" +
                "BANKS_NAME VARCHAR(255) UNIQUE,\n" +
                "PRIMARY KEY(BANKS_ID)\n" +
                ")";
        sqlQueryExecutor(sql);

        sql = "CREATE TABLE ACCOUNTS (\n" +
                "\tACCOUNTS_ID INT AUTO_INCREMENT,\n" +
                "\tACCOUNTS_CLIENT_ID INT, --TU MAJA BYC ID CLIENTOW POSIADACZY\n" +
                "\tACCOUNTS_TYPE VARCHAR(1),\n" +
                "\tACCOUNTS_BALANCE FLOAT,\n" +
                "\tACCOUNTS_OVERDRAFT FLOAT,\t\n" +
                "\tPRIMARY KEY(ACCOUNTS_ID)\n" +
                ");";
        sqlQueryExecutor(sql);
    }
    public void fill(Bank bank) {
        AccountDAOImplementation accountDAO = new AccountDAOImplementation();
        BankDAOImplementation bankDAO = new BankDAOImplementation();
        ClientDAOImplementation clientDAO = new ClientDAOImplementation();

        try {
            bankDAO.save(bank);
            for (Client client : bank.getClients()) {
                clientDAO.save(client,bank);
                for (Account account : client.getAccounts()) {
                    accountDAO.save(account,client);
                }
            }


        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
    public void clear() {
        //TODO
    }
    public void deinitialize() {
        String sql = "DROP TABLE BANKS";
        sqlQueryExecutor(sql);
        sql = "DROP TABLE CLIENTS";
        sqlQueryExecutor(sql);
        sql = "DROP TABLE ACCOUNTS";
        sqlQueryExecutor(sql);

    }

}
