package cz.muni.pv239;

import cz.muni.pv239.enums.BuildingType;

public class Building {
    int xPosition;
    int yposition;
    BuildingType type;

    public Building(BuildingType type) {
        this.type = type;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getYposition() {
        return yposition;
    }

    public void setYposition(int yposition) {
        this.yposition = yposition;
    }

    public BuildingType getType() {
        return type;
    }

    public void setType(BuildingType type) {
        this.type = type;
    }
}
