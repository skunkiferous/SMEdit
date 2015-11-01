/**
 * Copyright 2014 
 * SMEdit https://github.com/StarMade/SMEdit
 * SMTools https://github.com/StarMade/SMTools
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 **/
package jo.sm.edit.util.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Auther Jo Jaquinta for SMEdit Classic - version 1.0
 **/
public class FileUtils {

    public static void copy(InputStream is, OutputStream os) throws IOException {
        if (!(is instanceof BufferedInputStream)) {
            is = new BufferedInputStream(is);
        }
        if (!(os instanceof BufferedOutputStream)) {
            os = new BufferedOutputStream(os);
        }
        for (;;) {
            int ch = is.read();
            if (ch == -1) {
                break;
            }
            os.write(ch);
        }
        os.flush();
    }

    public static byte[] readStream(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        copy(is, baos);
        return baos.toByteArray();
    }

    public static String readStreamAsString(InputStream is) throws IOException {
        return new String(readStream(is));
    }

    public static byte[] readFile(String fname) throws IOException {
        byte[] ret;
        try (FileInputStream fis = new FileInputStream(fname)) {
            ret = readStream(fis);
        }
        return ret;
    }

    public static String readFileAsString(String fname) throws IOException {
        return new String(readFile(fname));
    }
}
