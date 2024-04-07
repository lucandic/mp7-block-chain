public class Block {
  // fields
  // number of the block in the blockchain
  private int index;

  // amount transferred between the two parties represented as a single integer
  private int data;

  // hash of the previous block in the chain
  private Hash prevHash;

  // the nonce
  private long nonce;

  // hash of this block
  private Hash hash;

  // constructor
  // creates a new block from the specified parameters, performing the mining
  // operation to discover the nonce and hash for this block given these parameters.
  public Block(int num, int amount, Hash prevHash) {
    this.index = num;
    this.data = amount;
    this.prevHash = prevHash;
    this.nonce = 0; // come back later
  } // Block(int num, int amount, Hash prevHash)

  // creates a new block from the specified parameters, using the provided nonce and
  // additional parameters to generate the hash for the block. Because the nonce is provided, this
  // constructor does not need to perform the mining operation; it can compute the hash directly.
  public Block(int num, int amount, Hash prevHash, long nonce) {
    this.index = num;
    this.data = amount;
    this.prevHash = prevHash;
    this.nonce = nonce;
  } // Block(int num, int amount, Hash prevHash, long nonce)

  // method
  // returns the number of this block
  public int getNum() {
    return this.index;
  } // getNum()

  // returns the amount transferred that is recorded in this block.
  public int getAmount() {
    return this.data;
  } // getAmount()

  // returns the nonce of this block.
  public long getNonce() {
    return this.nonce;
  } // getNonce()

  // returns the hash of the previous block in the blockchain.
  public Hash getPrevHash() {
    return this.prevHash;
  } // getPrevHash()

  // returns the hash of this block
  public Hash getHash() {
    return this.hash;
  } // getHash()

  // returns a string representation of the block
  public String toString() {
    return "Block " + this.index + " (Amount: " + this.data + ", Nonce: " + this.nonce
        + ", prevHash: " + this.prevHash + ", hash: " + this.hash + ")";
  } // toString()
} // class Block
