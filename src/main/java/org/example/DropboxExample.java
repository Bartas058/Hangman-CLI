package org.example;

import com.dropbox.core.*;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class DropboxExample {
    private static final String ACCESS_TOKEN = "YOUR_KEY";

    public void uploadDropbox(String filePath, String dropboxPath) throws DbxException, IOException {
        DbxRequestConfig config = new DbxRequestConfig("my-app");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        try (InputStream in = new FileInputStream(filePath)) {
            FileMetadata metadata = client.files().uploadBuilder(dropboxPath)
                    .uploadAndFinish(in);
            String fileUrl = client.sharing().createSharedLinkWithSettings(dropboxPath).getUrl();
            System.out.println("Link to file on Dropbox: " + fileUrl);
            System.out.println("---------------");
        } catch (UploadErrorException e) {
            e.printStackTrace();
        }
    }

    public String generateString() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("#");
        for (int i = 0; i < 3; i++) {
            int randomDigit = random.nextInt(10);
            stringBuilder.append(randomDigit);
        }

        return stringBuilder.toString();
    }
}