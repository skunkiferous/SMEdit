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
package jo.sm.factories.ship.filter;

import java.util.Iterator;

import jo.sm.data.BlockSparseMatrix;
import jo.sm.data.SparseMatrix;
import jo.sm.data.StarMade;
import jo.sm.mods.IBlocksPlugin;
import jo.sm.mods.IPluginCallback;
import jo.sm.ship.data.Block;
import jo.vecmath.Point3i;

/**
 * @Auther Jo Jaquinta for SMEdit Classic - version 1.0
 **/
public class FilterPlugin implements IBlocksPlugin {

    private final FilterDefinition mDef;

    public FilterPlugin(FilterDefinition def) {
        mDef = def;
    }

    @Override
    public String getName() {
        return mDef.getTitle();
    }

    @Override
    public String getDescription() {
        return mDef.getDescription();
    }

    @Override
    public String getAuthor() {
        return mDef.getAuthor();
    }

    @Override
    public Object newParameterBean() {
        return null;
    }

    @Override
    public void initParameterBean(BlockSparseMatrix original, Object params,
            StarMade sm, IPluginCallback cb) {
    }

    @Override
    public int[][] getClassifications() {
        int[][] classifications = new int[][]{
            {TYPE_SHIP, SUBTYPE_VIEW, mDef.getPriority()},
            {TYPE_STATION, SUBTYPE_VIEW, mDef.getPriority()},
            {TYPE_SHOP, SUBTYPE_VIEW, mDef.getPriority()},
            {TYPE_FLOATINGROCK, SUBTYPE_VIEW, mDef.getPriority()},
            {TYPE_PLANET, SUBTYPE_VIEW, mDef.getPriority()},};
        return classifications;
    }

    @Override
    public BlockSparseMatrix modify(BlockSparseMatrix original,
            Object params, StarMade sm, IPluginCallback cb) {
        BlockSparseMatrix modified = new BlockSparseMatrix();
        for (Iterator<Point3i> i = original.iteratorNonNull(); i.hasNext();) {
            Point3i p = i.next();
            Block b = original.get(p);
            if (mDef.getBlocks().contains(b.getBlockID())) {
                modified.set(p, b);
            }
        }
        return modified;
    }

}
