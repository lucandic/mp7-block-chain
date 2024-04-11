import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;

/**
 * Class for the data contained in each node of the blockchain
 * 
 * @author Candice Lu
 * @author Marina Ananias
 * @author Medhashree Adhikari
 */
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
  public Block(int num, int amount, Hash prevHash) throws NoSuchAlgorithmException {
    this.index = num;
    this.data = amount;
    this.prevHash = prevHash;
    this.nonce = calcNonce(this.index, this.data, this.prevHash);
    this.hash = calHash(this.index, this.data, this.prevHash, this.nonce);
  } // Block(int num, int amount, Hash prevHash)

  // creates a new block from the specified parameters, using the provided nonce and
  // additional parameters to generate the hash for the block. Because the nonce is provided, this
  // constructor does not need to perform the mining operation; it can compute the hash directly.
  public Block(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException {

    this.index = num;
    this.data = amount;
    this.prevHash = prevHash;
    this.nonce = nonce;

    this.hash = calHash(this.index, this.data, this.prevHash, this.nonce);
  } // Block(int num, int amount, Hash prevHash, long nonce)

  // method

  // for loop that loops potential nonce values (from zero?) until we find a valid nonce
  static public long calcNonce(int num, int amount, Hash prevHash) throws NoSuchAlgorithmException {
    long non = -1;
    Hash temp = calHash(num, amount, prevHash, non);
    while (!temp.isValid()) {
      non++;
      temp = calHash(num, amount, prevHash, non);
    }
    return non;
  } // calcNonce(num, amount, prevHash)

  static public Hash calHash(int num, int amount, Hash prevHash, long nonce)
      throws NoSuchAlgorithmException {
    ByteBuffer blockData;
    // if block is the first one
    if (prevHash == null) {
      // calc without prevHash
      blockData = ByteBuffer.allocate((Integer.BYTES * 2) + Long.BYTES + Byte.BYTES);
    } else {
      // else, calc with prevHash
      blockData = ByteBuffer
          .allocate((Integer.BYTES * 2) + Long.BYTES + (prevHash.getData().length * Byte.BYTES));
    }

    blockData.putInt(num).putInt(amount);

    if (num != 0) {
      // allocate for hash
      blockData.put(prevHash.getData());
    }
    blockData.putLong(nonce);
    byte[] dataArr = blockData.array();
    // append all arrays above

    return new Hash(dataArr);
  } // calHash(num, amount, prevHash, nonce)

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
    if (this.prevHash == null) {
      return "Block " + this.index + " (Amount: " + this.data + ", Nonce: " + this.nonce
          + ", prevHash: " + null + ", hash: " + this.hash.toString() + ")\n";
    }
    return "Block " + this.index + " (Amount: " + this.data + ", Nonce: " + this.nonce
        + ", prevHash: " + this.prevHash.toString() + ", hash: " + this.hash.toString() + ")\n";
  } // toString()
} // class Block
