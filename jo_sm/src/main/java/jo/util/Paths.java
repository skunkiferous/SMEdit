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

package jo.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.filechooser.FileSystemView;
import jo.sm.logic.StarMadeLogic;

/**
 *
 * @author Robert Barefoot
 */
public class Paths {


    public static final String ROOT = "." + File.separator + "resources";
    public static final String SM_VERSION_FILE = "sm_version.txt";
    public static final String VERSION = ROOT + File.separator + SM_VERSION_FILE;
    private static Map<String, File> downloadCache;
    /* file locations */

    public static String getCacheDirectory() {
        return Paths.getHomeDirectory() + File.separator + "Cache";
    }

    public static String getCollectDirectory() {
        final File dir = new File(Paths.getPluginsDirectory(), ".jar");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String path = dir.getAbsolutePath();
        try {
            path = URLDecoder.decode(path, "UTF-8");
        } catch (final UnsupportedEncodingException ignored) {
        }
        return path;
    }

    public static Map<String, File> getDownloadCaches() {
        if (downloadCache == null) {
            downloadCache = new HashMap<>(8);
            /* FILES */
            /* ICONS */
        }
        return Collections.unmodifiableMap(downloadCache);
    }
    /* folder directories */

    public static String getHomeDirectory() {
        Properties props = StarMadeLogic.getProps();
        String home = props.getProperty("starmade.home", "");
        return home + File.separator + "third-party" + File.separator + GlobalConfiguration.NAME;

    }

    public static String getIconDirectory() {
        return Paths.getHomeDirectory() + File.separator + "resources" + File.separator + "images";
    }
    
    public static String getSMEBlueprintDirectory() {
        return Paths.getHomeDirectory() + File.separator + "Blueprints";
    }

    public static String getLogsDirectory() {
        return Paths.getHomeDirectory() + File.separator + "Logs";
    }

    public static String getMenuCache() {
        return Paths.getSettingsDirectory() + File.separator + "Menu.txt";
    }

    public static String getPathCache() {
        return Paths.getSettingsDirectory() + File.separator + "path.txt";
    }

    public static String getScreenshotsDirectory() {
        return Paths.getHomeDirectory() + File.separator + "Screenshots";
    }

    public static String getPluginsDirectory() {
        return Paths.getHomeDirectory() + File.separator + "Plugins";
    }

    public static String getSettingsDirectory() {
        return Paths.getHomeDirectory() + File.separator + "Settings";
    }

    public static String getUnixHome() {
        final String home = System.getProperty("user.home");
        return home == null ? "~" : home;
    }

    public static String getVersionCache() {
        return Paths.getCacheDirectory() + File.separator + "info.dat";
    }
    
}

