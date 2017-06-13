/**
 * Copyright(c) 2012 Dialog-University of Moratuwa Mobile Communications
 * Research Laboratory. All Rights Reserved. This software is the proprietary
 * information of Dialog-University of Moratuwa Mobile Communications Research
 * Laboratory(Dialog-UOM Lab).
 *
 * Dialog-UOM Lab or Dialog Axiata PLC reserves to right to modify, update
 * and/or enhance the software as it sees fit. and .
 */
package uom.dialog.click2call.utils;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.util.encoders.UrlBase64;

import sun.misc.*;

/**
 * C2CEncryptor.java (UTF-8)- To provide encrypt/decrypt mechanism for a String using a predefined secret key
 * Mar 20, 2013, 2:16:57 AM
 *
 * @author Dewmini
 */
public class C2CEncryptor {
private static final String ALGO = "AES";
    private static final byte[] keyValue = 
        new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };


public static String encrypt(String Data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        //below BASE64Encoder() cannot be used as it is not safe with url parameters.as it contains +/= characters
        //String encryptedValue = new BASE64Encoder().encode(encVal);
        byte[] encrypted = UrlBase64.encode(encVal);
        String encryptedValue=new String(encrypted);
        return encryptedValue;
    }

    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        //As encription cannot use BASE64Decoder() to encrypt as indicated in encrypt() method comments
        //byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decordedValue = UrlBase64.decode(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
}

}
