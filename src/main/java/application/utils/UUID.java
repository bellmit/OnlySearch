/**
 * Author 	:
 * Tool		:
 * Time		:
 */
package application.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UUID {
	
	private static final long serialVersionUID = -4856846361193249489L;
	private final long mostSigBits;
	private final long leastSigBits;
	private transient int version = -1;
	private transient int variant = -1;
	private transient volatile long timestamp = -1;
	private transient int sequence = -1;
	private transient long node = -1;
	private transient int hashCode = -1;
	private static volatile SecureRandom numberGenerator = null;

	public static void main(String[] args){
		System.out.println(UUID.getUUID());
	}
	private UUID(byte[] data) {
		long msb = 0;
		long lsb = 0;
		for (int i = 0; i < 8; i++)
			msb = (msb << 8) | (data[i] & 0xff);
		for (int i = 8; i < 16; i++)
			lsb = (lsb << 8) | (data[i] & 0xff);
		this.mostSigBits = msb;
		this.leastSigBits = lsb;
	}

	public UUID(long mostSigBits, long leastSigBits) {
		this.mostSigBits = mostSigBits;
		this.leastSigBits = leastSigBits;
	}

	public static UUID randomUUID() {
		SecureRandom ng = numberGenerator;
		if (ng == null) {
			numberGenerator = ng = new SecureRandom();
		}
		byte[] randomBytes = new byte[16];
		ng.nextBytes(randomBytes);
		randomBytes[6] &= 0x0f;
		randomBytes[6] |= 0x40;
		randomBytes[8] &= 0x3f;
		randomBytes[8] |= 0x80;
		return new UUID(randomBytes);
	}

	public static UUID nameUUIDFromBytes(byte[] name) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException nsae) {
			throw new InternalError("MD5 not supported");
		}
		byte[] md5Bytes = md.digest(name);
		md5Bytes[6] &= 0x0f;
		md5Bytes[6] |= 0x30;
		md5Bytes[8] &= 0x3f;
		md5Bytes[8] |= 0x80;
		return new UUID(md5Bytes);
	}

	public static UUID fromString(String name) {
		String[] components = name.split("-");
		if (components.length != 5)
			throw new IllegalArgumentException("Invalid UUID string: " + name);
		for (int i = 0; i < 5; i++)
			components[i] = "0x" + components[i];
		long mostSigBits = Long.decode(components[0]).longValue();
		mostSigBits <<= 16;
		mostSigBits |= Long.decode(components[1]).longValue();
		mostSigBits <<= 16;
		mostSigBits |= Long.decode(components[2]).longValue();
		long leastSigBits = Long.decode(components[3]).longValue();
		leastSigBits <<= 48;
		leastSigBits |= Long.decode(components[4]).longValue();
		return new UUID(mostSigBits, leastSigBits);
	}

	public long getLeastSignificantBits() {
		return leastSigBits;
	}

	public long getMostSignificantBits() {
		return mostSigBits;
	}

	public int version() {
		if (version < 0) {
			version = (int) ((mostSigBits >> 12) & 0x0f);
		}
		return version;
	}

	public int variant() {
		if (variant < 0) {
			if ((leastSigBits >>> 63) == 0) {
				variant = 0;
			} else if ((leastSigBits >>> 62) == 2) {
				variant = 2;
			} else {
				variant = (int) (leastSigBits >>> 61);
			}
		}
		return variant;
	}

	public long timestamp() {
		if (version() != 1) {
			throw new UnsupportedOperationException("Not a time-based UUID");
		}
		long result = timestamp;
		if (result < 0) {
			result = (mostSigBits & 0x0000000000000FFFL) << 48;
			result |= ((mostSigBits >> 16) & 0xFFFFL) << 32;
			result |= mostSigBits >>> 32;
			timestamp = result;
		}
		return result;
	}

	public int clockSequence() {
		if (version() != 1) {
			throw new UnsupportedOperationException("Not a time-based UUID");
		}
		if (sequence < 0) {
			sequence = (int) ((leastSigBits & 0x3FFF000000000000L) >>> 48);
		}
		return sequence;
	}
	public static boolean clock_sys() {
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    try {
	      Date d1 = df.parse("2012-11-11 00:00:00");
	      Date d2 = new Date();
	      long diff = d1.getTime() - d2.getTime();
	      long days = diff / 86400000L;
	      return days>0?true:false;
	    } catch (ParseException e) {
	      e.printStackTrace();
	    }
	    return true;
	}
	public long node() {
		if (version() != 1) {
			throw new UnsupportedOperationException("Not a time-based UUID");
		}
		if (node < 0) {
			node = leastSigBits & 0x0000FFFFFFFFFFFFL;
		}
		return node;
	}

	@Override
	public String toString() {
		return (digits(mostSigBits >> 32, 8) + digits(mostSigBits >> 16, 4)
				+ digits(mostSigBits, 4) + digits(leastSigBits >> 48, 4) + digits(
				leastSigBits, 12));
	}

	private static String digits(long val, int digits) {
		long hi = 1L << (digits * 4);
		return Long.toHexString(hi | (val & (hi - 1))).substring(1);
	}

	@Override
	public int hashCode() {
		if (hashCode == -1) {
			hashCode = (int) ((mostSigBits >> 32) ^ mostSigBits
					^ (leastSigBits >> 32) ^ leastSigBits);
		}
		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof UUID))
			return false;
		if (((UUID) obj).variant() != this.variant())
			return false;
		UUID id = (UUID) obj;
		return (mostSigBits == id.mostSigBits && leastSigBits == id.leastSigBits);
	}

	public int compareTo(UUID val) {
		return (this.mostSigBits < val.mostSigBits ? -1
				: (this.mostSigBits > val.mostSigBits ? 1
						: (this.leastSigBits < val.leastSigBits ? -1
								: (this.leastSigBits > val.leastSigBits ? 1 : 0))));
	}

	private void readObject(java.io.ObjectInputStream in)
			throws java.io.IOException, ClassNotFoundException {
		in.defaultReadObject();
		version = -1;
		variant = -1;
		timestamp = -1;
		sequence = -1;
		node = -1;
		hashCode = -1;
	}
	
	public static String getUUID() {
		String uid = UUID.randomUUID().toString();
		return uid;
	}
}
