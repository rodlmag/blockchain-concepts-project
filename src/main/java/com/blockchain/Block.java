package com.blockchain;

import java.util.Date;

public class Block {
    public String hash;
    public String previous_hash;
    private String data;  // for purpose will be a simple message
    private long timeStamp;  // as number of miliseconds
    private int nonce;

    //Constructor
    public Block(String data, String previous_hash){
        this.data = data;
        this.previous_hash = previous_hash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String calculatedHash = StringUtil.generateSha256(previous_hash + 
        Long.toString(timeStamp) + 
        Integer.toString(nonce) +
        data);
        return calculatedHash;
    }

    public void mineBlock(int dificulty) {
        //Create a string with difficulty * 0
        String target = new String(new char[dificulty]).replace('\0','0');
        while(!hash.substring(0, dificulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!! : "  + hash);
    }
}
