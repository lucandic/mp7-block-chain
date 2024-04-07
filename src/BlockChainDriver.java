import java.util.Scanner;
import java.io.PrintWriter;

public class BlockChainDriver {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    PrintWriter pen = new PrintWriter(System.out);
    int initAmount = Integer.valueOf(args[0]);

    
    while (input.hasNextLine()) {
      String command = input.next();
      if (command.equals("mine")) {

      } else if (command.equals("append")) {

      } else if (command.equals("remove")) {
        
      } else if (command.equals("check")) {
        
      } else if (command.equals("report")) {
        // STUB
      } else if (command.equals("help")) {
        pen.print("mine: discovers the nonce for a given transaction\n" + //
            "append: appends a new block onto the end of the chain\n" + //
            "remove: removes the last block from the end of the chain\n" + //
            "check: checks that the block chain is valid\n" + //
            "report: reports the balances of Alexis and Blake\n" + //
            "help: prints this list of commands\n" + //
            "quit: quits the program");
      } else if (command.equals("quit")) {
        System.exit(0);
      }
    }
  } // main(String[] args)
} // class BlockChainDriver
