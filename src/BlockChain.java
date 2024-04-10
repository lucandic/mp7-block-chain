import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

public class BlockChain {

  public class Node {

    Node prev;
    Node next;
    Block contents;

    public Node(Node prev, Node next, Block contents) {
      this.prev = prev;
      this.next = next;
      this.contents = contents;
    }
  } // class Node

  // fields
  Node first;
  Node last;
  int alexisBalance;
  int blakeBalance;

  // constructor
  // creates a BlockChain that possess a single block the starts with the given initial amount. Note
  // that to create this block, the prevHash field should be ignored when calculating the block’s
  // own nonce and hash.
  public BlockChain(int initial) throws NoSuchAlgorithmException {
    this.first = new Node(null, null, new Block(0, initial, null));
    this.last = this.first;
    this.alexisBalance = 0;
    this.blakeBalance = 0;
  } // BlockChain(int initial)

  // methods
  // mines a new candidate block to be added to the end of the chain. The returned Block should be
  // valid to add onto this chain.
  public Block mine(int amount) throws NoSuchAlgorithmException {
    return new Block(this.last.contents.getNum(), amount, this.last.contents.getHash());
  } // mine(amount)

  // returns the size of the blockchain. Note that number of the blocks provides a convenient method
  // for quickly determining the size of the chain.
  public int getSize() {
    return this.last.contents.getNum();
  } // getSize()

  // adds this block to the list, throwing an IllegalArgumentException if this block cannot be added
  // to the chain (because it has an invalid hash,
  // because its hash is inappropriate for the contents, or because the previous hash is incorrect).
  public void append(Block blk) throws IllegalArgumentException, NoSuchAlgorithmException{

    // check if hash is valid
    if (!(blk.getHash().isValid())) {
      throw new IllegalArgumentException();
    }

    // check if hash is appropriate for its contents
    Hash newHash = new Hash(blk.getHash().getData());
    if (!(newHash.equals(blk.getHash()))) {
      throw new IllegalArgumentException();
    }

    // check if previous hash is correct
    Hash prevHash = this.last.contents.getHash();
    if (!(prevHash.equals(blk.getPrevHash()))) {
      throw new IllegalArgumentException();
    }

    // check if balance is valid
    int amount = blk.getAmount();
    if ((alexisBalance + amount) < 0 || (blakeBalance - amount) < 0) {
      throw new IllegalArgumentException();
    }

    // add block to blockchain and if balance is valid

    Node newLast = new Node(this.last, null, blk);
    this.last.next = newLast;
    this.last = newLast;
  } // append(blk)

  // removes the last block from the chain, returning true. If the chain only contains a single
  // block, then removeLast does nothing and returns false.
  public boolean removeLast() {
    // check if there's only one block in blockchain
    if (this.getSize() == 1) {
      return false;
    }

    // change the last block to its previous block
    this.last.prev.next = null;
    return true;
  } // removeLast()

  // returns the hash of the last block in the chain.
  public Hash getHash() {
    return this.last.contents.getHash();
  } // getHash()

  // walks the blockchain and ensures that its blocks are consistent (the balances are legal) and
  // valid (as in append).
  public boolean isValidBlockChain() throws NoSuchAlgorithmException {

    Node cur = this.first;
    Block curBlock = this.first.contents;
    int curAlexis = 0;
    int curBlake = 0;

    for (int i = 0; i < this.getSize(); i++) {

      // check if hash is valid
      if (!(this.getHash().isValid())) {
        throw new IllegalArgumentException();
      }

      // check if hash is appropriate for its contents
      Hash newHash = new Hash(curBlock.getHash().getData());
      if (!(newHash.equals(curBlock.getHash()))) {
        throw new IllegalArgumentException();
      }

      // check if previous hash is correct
      Hash prevHash = cur.prev.contents.getHash();
      if (!(prevHash.equals(curBlock.getPrevHash()))) {
        throw new IllegalArgumentException();
      }

      // check if balance is valid
      int amount = curBlock.getAmount();
      curAlexis += amount;
      curBlake -= amount;
      if (curAlexis < 0 || curBlake < 0) {
        throw new IllegalArgumentException();
      }

      cur = cur.next;
      curBlock = cur.next.contents;
    }
    return false;
  } // isValidBlockChain()

  // prints Alexis’s and Blake’s respective balances in the form Alexis: <amt>, Blake: <amt> on a
  // single line, e.g., Alexis: 300, Blake: 0.
  public void printBalances(PrintWriter pen) {
    pen.print("Alexis: " + this.alexisBalance + ", Blake: " + this.blakeBalance);
  } // printBalances(pen)

  // returns a string representation of the BlockChain which is simply the string representation of
  // each of its blocks, earliest to latest, one per line.
  public String toString() {
    Node cur = this.first;
    String output = "";
    while (cur.next != null) {
      output += cur.contents.toString();
    }
    return output;
  } // toString()

} // BlockChain
