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
package jo.sm.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import jo.vecmath.Point3f;

/**
 * @Auther Jo Jaquinta for SMEdit Classic - version 1.0
 **/
public class RenderSet {

    private List<RenderPoly> mAllPolys;
    private List<RenderPoly> mVisiblePolys;
    private Point3f mUnitX;
    private Point3f mUnitY;
    private Point3f mUnitZ;
    private Point3f mOrigin;

    public RenderSet() {
        mAllPolys = new ArrayList<>();
        mVisiblePolys = new ArrayList<>();
    }

    public void clearAllPolys() {
        mAllPolys.clear();
    }

    public void clearVisiblePolys() {
        mVisiblePolys.clear();
    }

    public int getAllPolysCount() {
        return mAllPolys.size();
    }

    public int getVisiblePolysCount() {
        return mVisiblePolys.size();
    }

    public Iterable<RenderPoly> getAllPolys() {
        return mAllPolys;
    }

    public Iterable<RenderPoly> getVisiblePolys() {
        return mVisiblePolys;
    }

    public Iterable<RenderPoly> getVisiblePolysReversed() {
    	return new Iterable<RenderPoly>() {
			@Override
			public Iterator<RenderPoly> iterator() {
				return new Iterator<RenderPoly>() {
					private int index = mVisiblePolys.size() - 1;
					@Override
					public boolean hasNext() {
						return index >= 0;
					}

					@Override
					public RenderPoly next() {
						return mVisiblePolys.get(index--);
					}
					
				};
			}
    		
    	};
    }

    public void addAllPolys(RenderPoly p) {
        mAllPolys.add(p);
    }

    public void addVisiblePolys(RenderPoly p) {
        mVisiblePolys.add(p);
    }

    public void sortAllPolys(Comparator<RenderPoly> cmp) {
    	Collections.sort(mAllPolys, cmp);
    }

    public void sortVisiblePolys(Comparator<RenderPoly> cmp) {
    	Collections.sort(mVisiblePolys, cmp);
    }

    public Point3f getUnitX() {
        return mUnitX;
    }

    public void setUnitX(Point3f unitX) {
        mUnitX = unitX;
    }

    public Point3f getUnitY() {
        return mUnitY;
    }

    public void setUnitY(Point3f unitY) {
        mUnitY = unitY;
    }

    public Point3f getUnitZ() {
        return mUnitZ;
    }

    public void setUnitZ(Point3f unitZ) {
        mUnitZ = unitZ;
    }

    public Point3f getOrigin() {
        return mOrigin;
    }

    public void setOrigin(Point3f origin) {
        mOrigin = origin;
    }
}
