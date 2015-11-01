package jo.sm.data;

import jo.sm.ship.data.Block;

public class BlockSparseMatrix extends SparseMatrix<Block> {
	
	public BlockSparseMatrix() {
		super();
	}
	
	public BlockSparseMatrix(BlockSparseMatrix other) {
		super(other);
	}
}
