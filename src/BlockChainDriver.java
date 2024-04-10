import java.util.Scanner;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

public class BlockChainDriver {
  public static void main(String[] args) throws NoSuchAlgorithmException {
    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner input = new Scanner(System.in);
    
    int initAmount = Integer.valueOf(args[0]);
    BlockChain bc = new BlockChain(initAmount);
    pen.println(bc.toString());

    pen.println("Command? ");
    String command = input.next();
    

    while (input.hasNextLine()) {
      if (command.equals("mine")) {
        // prints the amt being transferred and the nonce
        pen.println("Amount transferred? ");
        int newAmt = input.nextInt();
        pen.println("amount = " + newAmt + ", nonce = " + Block.calcNonce(bc.last.contents.getNum() + 1, newAmt, bc.last.contents.getHash()));
      } else if (command.equals("append")) {
        pen.println("Amount transferred? ");
        int newAmt = input.nextInt();
        pen.println("Nonce? ");
        long newNonce = input.nextLong();

        // append new block
        bc.append(new Block(bc.last.contents.getNum() + 1, newAmt, bc.last.contents.getHash(), newNonce));
      } else if (command.equals("remove")) {
        bc.removeLast(); // removes the last block
      } else if (command.equals("check")) {
        // check if valid
        if(bc.isValidBlockChain()) {
          pen.println("Chain is valid!");
        }
      } else if (command.equals("report")) {
        bc.printBalances(pen);
      } else if (command.equals("help")) {
        pen.println("mine: discovers the nonce for a given transaction\n" + //
            "append: appends a new block onto the end of the chain\n" + //
            "remove: removes the last block from the end of the chain\n" + //
            "check: checks that the block chain is valid\n" + //
            "report: reports the balances of Alexis and Blake\n" + //
            "help: prints this list of commands\n" + //
            "quit: quits the program");
      } else if (command.equals("quit")) {
        System.exit(0);
      } // cond checks
      pen.println(bc.toString());// print block(s)
      
      pen.println("Command? ");
      command = input.next();
    } // while loop
    input.close();
  } // main(String[] args)
} // class BlockChainDriver
