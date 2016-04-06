
# BaseImageDownloader
有时候需要获取ImageLoader这个请求中头部的一些信息，可以自定义一个BaseImageDownloader。
如下，可以获取cookie
```java
public class AuthImageDownloader extends BaseImageDownloader {

    private static final int MAX_REDIRECT_COUNT = 5;

    public AuthImageDownloader(Context context) {
        super(context);
    }

    public AuthImageDownloader(Context context, int connectTimeout, int readTimeout) {
        super(context, connectTimeout, readTimeout);
    }

    @Override
    protected InputStream getStreamFromNetwork(String imageUri, Object extra) throws IOException {
        HttpURLConnection conn = createConnection(imageUri, extra);
        int redirectCount = 0;
        while (conn.getResponseCode() / 100 == 3 && redirectCount < MAX_REDIRECT_COUNT) {
            conn = createConnection(conn.getHeaderField("Location"), extra);
            redirectCount++;
        }
        HashMap<String, String> heads = new HashMap<>();
        String headerField = conn.getHeaderField("Set-Cookie");
        String[] split = headerField.split(";");
        for (String aSplit : split) {
            String[] split1 = aSplit.split("=");
            if (split1.length > 1) {
                heads.put(split1[0], split1[1]);
            }
        }
        System.out.println("lvsessionid" + heads.get("lvsessionid"));
        ((QRApplication) context.getApplicationContext()).setLvsessionid(heads.get("lvsessionid"));
        InputStream imageStream;
        try {
            imageStream = conn.getInputStream();
        } catch (IOException e) {
            // Read all data to allow reuse connection (http://bit.ly/1ad35PY)
            IoUtils.readAndCloseStream(conn.getErrorStream());
            throw e;
        }
        if (!shouldBeProcessed(conn)) {
            IoUtils.closeSilently(imageStream);
            throw new IOException("Image request failed with response code " + conn.getResponseCode());
        }
        return new ContentLengthInputStream(new BufferedInputStream(imageStream, BUFFER_SIZE), conn.getContentLength());
    }

}
```