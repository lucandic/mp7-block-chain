import java.io.PrintWriter;

public class BlockChain {

  public class Node {

    Block prev;
    Block next;
    Block contents;

    public Node(Block prev, Block next, Block contents) {
      this.prev = prev;
      this.next = next;
      this.contents = contents;
    }
  }

  // fields
  Node first;
  Node last;
  int alexisBalance;
  int blakeBalance;

  // constructor
  // creates a BlockChain that possess a single block the starts with the given initial amount. Note
  // that to create this block, the prevHash field should be ignored when calculating the block’s
  // own nonce and hash.
  public BlockChain(int initial) {
    this.first = new Node(null, null, new Block(0, initial, null));
    this.last = this.first;
    this.alexisBalance = 0;
    this.blakeBalance = 0;
  } // BlockChain(int initial)

  // methods
  // mines a new candidate block to be added to the end of the chain. The returned Block should be
  // valid to add onto this chain.
  public Block mine(int amount) {
    // STUB
    return null;
  }

  // returns the size of the blockchain. Note that number of the blocks provides a convenient method
  // for quickly determining the size of the chain.
  public int getSize() {
    return this.last.contents.getNum();
  } // getSize()

  // adds this block to the list, throwing an IllegalArgumentException if this block cannot be added
  // to the chain (because it has an invalid hash,
  // because its hash is inappropriate for the contents, or because the previous hash is incorrect).
  public void append(Block blk) throws IllegalArgumentException {

    // check if hash is valid
    if (!(blk.getHash().isValid())) {
      throw new IllegalArgumentException();
    }

    // check if hash is appropriate for its contents

    // check if previous hash is correct

    // add block to blockchain and see if balance is valid
    this.last.next = blk;
    this.last = new Node(this.last.contents, null, blk);
    if (!this.isValidBlockChain()) {

    }
  }

  // removes the last block from the chain, returning true. If the chain only contains a single
  // block, then removeLast does nothing and returns false.
  public boolean removeLast() {
    // check if there's only one block in blockchain
    if (this.getSize() == 1) {
      return false;
    }

    // change the last block to its previous block
    // this.last.prev.next = null;
    return true;
  }

  // returns the hash of the last block in the chain.
  public Hash getHash() {
    return this.last.contents.getHash();
  }

  // walks the blockchain and ensures that its blocks are consistent (the balances are legal) and
  // valid (as in append).
  public boolean isValidBlockChain() {
    // STUB
    return false;
  }

  // prints Alexis’s and Blake’s respective balances in the form Alexis: <amt>, Blake: <amt> on a
  // single line, e.g., Alexis: 300, Blake: 0.
  public void printBalances(PrintWriter pen) {
    pen.print("Alexis: " + this.alexisBalance + ", Blake: " + this.blakeBalance);
  }

  // returns a string representation of the BlockChain which is simply the string representation of
  // each of its blocks, earliest to latest, one per line.
  public String toString() {
    Node cur = this.first;
    String output = "";
    while (cur.next != null) {
      output += cur.contents.toString();
    }
    return output;
  }

}
