import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Class that deals with hash and includes a wrapper class that wraps up a byte array along with
 * some operations we would like to perform on it
 * 
 * @author Candice Lu
 * @author Marina Ananias
 * @author Medhashree Adhikari
 */
public class Hash {

  // fields
  byte[] hash;
  byte[] data;

  // constructor
  // constructs a new Hash object that contains the given hash (as an array of bytes).
  public Hash(byte[] data) throws NoSuchAlgorithmException {
    this.data = data;
    this.hash = getData();
  } // Hash(byte[] data)

  // methods
  // returns the hash contained in this object.
  public byte[] getData() throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("sha-256");
    md.update(this.data);

    return md.digest();
  } // getData()

  // returns true if this hash meets the criteria for validity, i.e., its first
  // three indices contain zeroes
  public boolean isValid() {
    for (int i = 0; i < 3; i++) {
      if (Byte.toUnsignedInt(this.hash[i]) != 0) {
        return false;
      } // condition check
    } // loop
    return true;
  } // isValid()

  // returns the string representation of the hash as a string of hexadecimal
  // digits, 2 digits per byte.
  public String toString() {
    String[] outputArr = new String[this.hash.length];
    for (int i = 0; i < this.hash.length; i++) {
      outputArr[i] = String.format("%02x", Byte.toUnsignedInt(this.hash[i]));
    }

    return String.join("", outputArr);
  } // toString()

  // returns true if this hash is structurally equal to the argument.
  public boolean equals(Object other) {
    // if other is not a Hash, return false
    if (!(other instanceof Hash)) {
      return false;
    }

    Hash toCompare = (Hash) other;

    if (Arrays.equals(toCompare.hash, this.hash)) {
      return true;
    }
    return false;
  } // equals(Object other)
} // class Hash
