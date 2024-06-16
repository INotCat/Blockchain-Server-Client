package project.block_chain.Test;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * The DatabaseOperator class handles operations related to interacting with a database,
 * including inserting and querying transaction data.
 * @author KJI
 * @since  May/25/2024
 */
public class DatabaseOperator {
    private static DataBaseConnector dataBaseConnector;
    private final Logger logger = Logger.getLogger(FTPServer.class.getName());
    /**
     * Constructor for DatabaseOperator. Initializes the database connector.
     */
    public DatabaseOperator() {
        try {
            dataBaseConnector = DataBaseConnector.getInstance(); // Instantiate the database connector
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
        }
    }

    // dataArray = {transactionID, userName, time, handlingFee, height}
    public void insert(String[] dataArray) {
        String sql = "INSERT INTO transaction_info (transaction_id, user_name, time, handling_fee, height) VALUES (?, ?, ?, ?, ?);";
        try {
            dataBaseConnector.insert(sql, dataArray);
            logger.info("upload to database");
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    public String[] query(String transactionID) {
        String sql = "SELECT * FROM transaction_info WHERE transaction_iD = ?";  // SQL query to search for a transactionID in the specified table
        String[] dataArray = new String[]{transactionID};
        try { //[query]transaction_id,user_name,time,handling_fee,height,transaction         
            String[] queryResult = dataBaseConnector.query(sql, dataArray);
            List<String> resultList = new ArrayList<>(Arrays.asList(queryResult));
            int height = Integer.parseInt(resultList.get(4));
            resultList.add(Chain.getInstance().getTransactionData(height, transactionID));
            // [0]=transactionId, [1]=user, [2]=time, [3]=handlingfee, [4]=height, [5]=transactionText
            // System.out.println("HEIGHT :" + height);
            // System.out.println("QUREY DATA :" + Chain.getInstance().getTransactionData(height, transactionID));
            // System.out.println("SIZE OF LIST :" + resultList.toArray(new String[0]).length);
            return resultList.toArray(new String[0]);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return new String[0];
    }
}
