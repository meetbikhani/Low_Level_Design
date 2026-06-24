package entities;
import enums.*;

public class request {
    public Integer targetFloor;
    public direction direction;
    public requestType requestType;

    public request(Integer targetFloor, direction direction, requestType requestType) {
        this.requestType = requestType;
        this.targetFloor = targetFloor;
        this.direction = direction;
    }

    public Integer getTargetFloor() {
        return targetFloor;
    }

    public direction getDirection() {
        return direction;
    }

    public requestType getRequestType() {
        return requestType;
    }
}
