package org.example.Kurs;

import lombok.Getter;

enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

public class MoveRobot {
    public static void main(String[] args) {
        Robot robot = new Robot();
        moveRobot(robot, -123, -9);
    }

    public static void moveRobot(Robot robot, int toX, int toY) {
        Direction to = null;
        if (toX > robot.getX()) {
            to = Direction.RIGHT;
        } else if (toX < robot.getX()) {
            to = Direction.LEFT;
        }
        if(robot.getDirection() == Direction.UP){
            if(to == Direction.RIGHT){
                robot.turnRight();
            } else if (to == Direction.LEFT) {
                robot.turnLeft();
            }
        } else if (robot.getDirection() == Direction.DOWN) {
            if(to == Direction.RIGHT){
                robot.turnLeft();
            } else if (to == Direction.LEFT) {
                robot.turnRight();
            }
        } else if (robot.getDirection() == Direction.LEFT) {
            if(to == Direction.RIGHT){
                robot.turnRight();
                robot.turnRight();
            }
        }else {
            if(to == Direction.LEFT){
                robot.turnRight();
                robot.turnRight();
            }
        }
        while (robot.getX() != toX){
            robot.stepForward();
        }
        to = null;
        if (toY > robot.getY()) {
            to = Direction.UP;
        } else if (toY < robot.getY()) {
            to = Direction.DOWN;
        }
        if(robot.getDirection() == Direction.UP){
            if(to == Direction.DOWN){
                robot.turnRight();
                robot.turnRight();
            }
        } else if (robot.getDirection() == Direction.DOWN) {
            if(to == Direction.UP){
                robot.turnLeft();
                robot.turnLeft();
            }
        } else if (robot.getDirection() == Direction.LEFT) {
            if(to == Direction.UP){
                robot.turnRight();
            } else if (to == Direction.DOWN) {
                robot.turnLeft();
            }
        }else {
            if(to == Direction.UP){
                robot.turnLeft();
            } else if (to == Direction.DOWN) {
                robot.turnRight();
            }
        }
        while (robot.getY() != toY){
            robot.stepForward();
        }
        System.out.println("I am here: " + toX + ", " + toY);
    }
}

@Getter
class Robot {
    private Direction direction = Direction.UP;
    private int X = 0;
    private int Y = 0;

    public void turnLeft() {
        // повернуться на 90 градусов против часовой стрелки
        switch (direction) {
            case UP -> direction = Direction.LEFT;
            case LEFT -> direction = Direction.DOWN;
            case DOWN -> direction = Direction.RIGHT;
            case RIGHT -> direction = Direction.UP;
        }
    }

    public void turnRight() {
        // повернуться на 90 градусов по часовой стрелке
        switch (direction) {
            case UP -> direction = Direction.RIGHT;
            case RIGHT -> direction = Direction.DOWN;
            case DOWN -> direction = Direction.LEFT;
            case LEFT -> direction = Direction.UP;


        }
    }

    public void stepForward() {
        // шаг в направлении взгляда
        // за один шаг робот изменяет одну свою координату на единицу
        switch (direction) {
            case UP -> this.Y += 1;
            case DOWN -> this.Y -= 1;
            case RIGHT -> this.X += 1;
            case LEFT -> this.X -= 1;
        }
    }
}
