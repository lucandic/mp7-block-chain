import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Hash {

  // fields
  byte[] hash;
  byte[] data;

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
    char[] outputArr = new char[this.hash.length * 2];
    for (int i = 0; i < this.hash.length; i++) {

    }
    return String.format("%02d", outputArr);
  } // toString()

  // returns true if this hash is structurally equal to the argument.
  public boolean equals(Object other) {
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
