package com.luxoft.CJP.April16.akrzos.bankapp.database;
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
                "\tCLIENTS_NAME VARCHAR(255) NOT NULL,\n" +
                "\tCLIENTS_GENDER VARCHAR(1),\n" +
                "\tCLIENTS_CITY VARCHAR(255),\n" +
                "\tBANK_ID INT,\n" +
                "\tPRIMARY KEY(CLIENTS_ID)\n" +
                ");";
        sqlQueryExecutor(sql);

        sql = "CREATE TABLE BANK (\n" +
                "ID INT AUTO_INCREMENT NOT NULL,\n" +
                "NAME VARCHAR(255),\n" +
                "PRIMARY KEY(ID)\n" +
                ");";
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
    public void clear() {
        //TODO
    }
    public void deinitialize() {
        String sql = "DROP TABLE BANK";
        sqlQueryExecutor(sql);
        sql = "DROP TABLE CLIENTS";
        sqlQueryExecutor(sql);
        sql = "DROP TABLE ACCOUNTS";
        sqlQueryExecutor(sql);

    }

}
