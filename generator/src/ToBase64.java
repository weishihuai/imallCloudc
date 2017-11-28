
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Created by IntelliJ IDEA. User: lzp Date: 11-6-8 Time: 上午11:36 To change this
 * template use File | Settings | File Templates.
 */
public class ToBase64 {
	public static void main(String[] args) {
		try {
			String[] values = new String[] {
					"2-12岁,1.1米以下等"
			};
			BASE64Encoder encoder = new BASE64Encoder();
			for(String value: values){
				String encodeStr = encoder.encode(value.getBytes("UTF-8"));
                BASE64Decoder decoder = new BASE64Decoder();
				System.out.println(encodeStr + "\n");
                System.out.println(new String(decoder.decodeBuffer(encodeStr),"UTF-8") + "\n");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
