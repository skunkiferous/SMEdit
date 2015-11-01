/**
 * Copyright 2014 SMEdit
 * https://github.com/StarMade/SMEdit SMTools
 * https://github.com/StarMade/SMTools
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
 *
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Properties;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Boot {

    private static Properties mProps;

    public static void loadProps() {
        File home = new File(System.getProperty("user.home"));
        // XXX Where the user settings are stored
        File props = new File(home, ".josm");
        if (props.exists()) {
            mProps = new Properties();
            try {
                try (FileInputStream fis = new FileInputStream(props)) {
                    mProps.load(fis);
                }
            } catch (IOException e) {

            }
        } else {
            mProps = new Properties();
        }
    }

    public static void main(final String[] args) throws IOException {
        loadProps();
        String location = Boot.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        location = URLDecoder.decode(location, "UTF-8").replaceAll("\\\\", "/");
        System.out.println("Running from: '"+location+"'");
        final String os = System.getProperty("os.name").toLowerCase();
        String flags = "-Xmx" + mProps.getProperty("memory", "2") + "g";
        if ("true".equalsIgnoreCase(mProps.getProperty("debug", "")) || "true".equalsIgnoreCase(System.getProperty("debug", ""))) {
        	// 8000 is "default" Java debugging port
        	System.out.println("DEBUGGING! The Application will wait for the debugger at port 8000 ...");
        	flags = "-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000 "+flags;
        }

        if (os.contains("windows")) {
            Runtime.getRuntime().exec("javaw " + flags + " -classpath \""
                    + location + "\" jo.sm.ui.RenderFrame");
        } else if (os.contains("mac")) {
            Runtime.getRuntime().exec(new String[]{"/bin/sh",
                "-c", "java " + flags + " -Xdock:name=\"SMEdit\""
                + " -Xdock:icon=resources/images/icon.png"
                + " -classpath \"" + location + "\" jo.sm.ui.RenderFrame"});
        } else {
            Runtime.getRuntime().exec(new String[]{"/bin/sh",
                "-c", "java " + flags + " -classpath \"" + location + "\" jo.sm.ui.RenderFrame"});
        }
    }
}
