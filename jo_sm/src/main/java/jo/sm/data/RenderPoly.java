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

import jo.sm.ship.data.Block;
import jo.vecmath.Point3i;

/**
 * @Auther Jo Jaquinta for SMEdit Classic - version 1.0
 **/
public class RenderPoly {

    public static final int XP = 0;
    public static final int XM = 1;
    public static final int YP = 2;
    public static final int YM = 3;
    public static final int ZP = 4;
    public static final int ZM = 5;
    public static final int XPYP = 6;
    public static final int XPYM = 7;
    public static final int XMYP = 8;
    public static final int XMYM = 9;
    public static final int YPZP = 10;
    public static final int YPZM = 11;
    public static final int YMZP = 12;
    public static final int YMZM = 13;
    public static final int ZPXP = 14;
    public static final int ZPXM = 15;
    public static final int ZMXP = 16;
    public static final int ZMXM = 17;

    public static final int SQUARE = 0;
    public static final int TRI1 = 1;
    public static final int TRI2 = 2;
    public static final int TRI3 = 3;
    public static final int TRI4 = 4;
    public static final int RECTANGLE = 5;

    private static final Point3i[] EMPTY = new Point3i[0];
    
    private int mType;				// int[0]
    private int mNormal;			// int[1]
    private Block mBlock;			// int[2]
    private Point3i mPosition;		// int[3-5]
    private Point3i[] mModelPoints = EMPTY; // int[6-?]

    public Block getBlock() {
        return mBlock;
    }

    public void setBlock(Block block) {
        mBlock = block;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    public Point3i[] getModelPoints() {
        return mModelPoints;
    }

    public void setModelPoints(Point3i[] modelPoints) {
        mModelPoints = (modelPoints == null) ? EMPTY : modelPoints;
    }

    public int getNormal() {
        return mNormal;
    }

    public void setNormal(int normal) {
        mNormal = normal;
    }

    public Point3i getPosition() {
        return mPosition;
    }

    public void setPosition(Point3i position) {
        mPosition = position;
    }
    
    public int slotsRequired() {
    	return 7 + mModelPoints.length*3;
    }
    
    public int export(int offset, int[] data) {
    	data[offset++] = mType;
    	data[offset++] = mNormal;
    	data[offset++] = mBlock.toInt();
    	offset = mPosition.export(offset, data);
    	data[offset++] = mModelPoints.length;
    	for (Point3i p : mModelPoints) {
        	offset = p.export(offset, data);
    	}
    	return offset;
    }
    
    public int _import(int offset, int[] data) {
    	mType = data[offset++];
    	mNormal = data[offset++];
    	if (mBlock == null) {
    		mBlock = new Block();
    	}
    	mBlock.fromInt(data[offset++]);
    	if (mPosition == null) {
    		mPosition = new Point3i();
    	}
    	offset = mPosition._import(offset, data);
    	final int count = data[offset++];
    	if (count != mModelPoints.length) {
    		final int min = (count < mModelPoints.length) ? count : mModelPoints.length;
    		final Point3i[] old = mModelPoints;
    		mModelPoints = new Point3i[count];
    		for (int i = 0; i < min; i++) {
    			mModelPoints[i] = old[i];
			}
    		for (int i = min; i < count; i++) {
    			mModelPoints[i] = new Point3i();
			}
    	}
    	for (Point3i p : mModelPoints) {
        	offset = p._import(offset, data);
    	}
    	return offset;
    }
}
