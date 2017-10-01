package uk.org.tdn.finances.util.converter;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class EncryptStringConverter implements AttributeConverter<String, String> {

	private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
	private static final byte[] KEY = "MyFinanceDognini".getBytes();

	@Override
	public String convertToDatabaseColumn(String ccNumber) {
		Key key = new SecretKeySpec(KEY, "AES");
		String result = null;
		try {
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.ENCRYPT_MODE, key);
			result = Base64.getEncoder().encodeToString(c.doFinal(ccNumber.getBytes()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		Key key = new SecretKeySpec(KEY, "AES");
		String result = null;
		try {
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.DECRYPT_MODE, key);
			result = new String(c.doFinal(Base64.getDecoder().decode(dbData)));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

}