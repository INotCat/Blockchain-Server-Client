package project.block_chain.Test;

public interface BlockChain{
    void addTransaction(String transaction);
    String getTransactionData(int height, String TXID);
    Block getHead();
    //showing the head's height represent the long of the chain
    int getChainLatestHeight();
    int getTransactionCount();
    //showing the operating status of the chain
    boolean isRunning();
    void stopChain();
    void restoreBlock(String filePath, int BlockchainLength);
}