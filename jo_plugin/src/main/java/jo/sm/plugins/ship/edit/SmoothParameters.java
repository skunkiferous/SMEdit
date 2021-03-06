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
package jo.sm.plugins.ship.edit;

import jo.sm.ship.logic.SmoothLogic;
import jo.sm.ui.act.plugin.Description;

/**
 * @Auther Jo Jaquinta for SMEdit Classic - version 1.0
 **/
@Description(displayName = "Smooth Corners", shortDescription = "Add wedges and corners to rough edges.")
public class SmoothParameters {

    @Description(displayName = "Scope", shortDescription = "What areas to smooth")
    private int mScope;
    @Description(displayName = "Type", shortDescription = "What to smooth with")
    private int mType;

    public SmoothParameters() {
        mScope = SmoothLogic.EXTERIOR;
        mType = SmoothLogic.EVERYTHING;
    }

    public int getScope() {
        return mScope;
    }

    public void setScope(int scope) {
        mScope = scope;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }
}
