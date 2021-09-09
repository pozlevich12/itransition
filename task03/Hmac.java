package by.itransition.training.task03;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Mac;

public class Hmac {

	private SecretKeySpec key;
	private Mac hmac;

	public Hmac(String move) throws InvalidKeyException, NoSuchAlgorithmException {
		keyGeneration();
		hmacGeneration(move);
	}

	private void hmacGeneration(String move) throws NoSuchAlgorithmException, InvalidKeyException {
		hmac = Mac.getInstance("HmacSHA3-256");
		hmac.init(key);
		hmac.update(move.getBytes());
	}

	private void keyGeneration() {
		SecureRandom random = new SecureRandom();
		byte[] bytes = new byte[16];
		random.nextBytes(bytes);
		this.key = new SecretKeySpec(bytes, "HmacSHA3-256");
	}

	public SecretKeySpec getKey() {
		return key;
	}

	public Mac getHmac() {
		return hmac;
	}

	public void printHmac() {
		System.out.println("HMAC: " + DatatypeConverter.printHexBinary(hmac.doFinal()));
	}

	public void printKey() {
		System.out.println("HMAC key: " + DatatypeConverter.printHexBinary(key.getEncoded()));
	}
}
