package lv.k2611a.domain;

import lv.k2611a.util.Point;

public class Tile {

    public static final int TICKS_IN_SPICE_TILE = 100;

    private int x;
    private int y;
    private int usedBy;
    private TileType tileType;
    private int spiceRemainingTicks = TICKS_IN_SPICE_TILE;
    private int passableSegmentNumber = 0;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public TileType getTileType() {
        return tileType;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isPassable() {
        if (usedBy == -1) {
            return true;
        }
        return false;
    }

    public boolean isPassable(long myId) {
        if (usedBy == -1) {
            return true;
        }
        return myId == usedBy;
    }

    public boolean isUsedByUnit() {
        return usedBy >= 0;
    }

    public int getUsedBy() {
        return usedBy;
    }

    public void setUsed(int used) {
        this.usedBy = used;
    }

    public Point getPoint() {
        return new Point(x,y);
    }

    public int getSpiceRemainingTicks() {
        return spiceRemainingTicks;
    }

    public void setSpiceRemainingTicks(int spiceRemainingTicks) {
        this.spiceRemainingTicks = spiceRemainingTicks;
    }

    public int getPassableSegmentNumber() {
        return passableSegmentNumber;
    }

    public void setPassableSegmentNumber(int passableSegmentNumber) {
        this.passableSegmentNumber = passableSegmentNumber;
    }
}
