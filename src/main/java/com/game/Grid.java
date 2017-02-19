package com.game;

import com.game.constant.CellLifeStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created with IntelliJ IDEA.
 * User: chenzhiwei
 * Date: 17/2/19
 * Time: 上午10:15
 * 生命游戏的方格
 */
@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Grid {
    private int x;
    private int y;
    private Cell[][] allCellArray;

    /**
     * 构造函数：其中根据x,y初始化allCellArray，转态固定
     *
     * @param x
     * @param y
     * @param cellLifeStatus
     */
    public Grid(int x, int y, int cellLifeStatus) {
        this.x = x;
        this.y = y;
        this.allCellArray = new Cell[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                this.allCellArray[i][j] = new Cell(cellLifeStatus, 0);
            }
        }
    }

    /**
     * 构造函数：其中根据x,y初始化allCellArray,转态随机
     *
     * @param x
     * @param y
     */
    public Grid(int x, int y) {
        this.x = x;
        this.y = y;
        this.allCellArray = new Cell[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                int cellLifeStatus = Math.random() < 0.5 ? CellLifeStatus.DEAD : CellLifeStatus.ALIVE;
                this.allCellArray[i][j] = new Cell(cellLifeStatus, 0);
            }
        }
    }

    public void updateCellLifeStatus() {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Cell cell = allCellArray[i][j];
                int aliveNumber = cell.getCurrentNeighborAliveNumber(this, i, j);
                cell.setAliveNumber(aliveNumber);
            }
        }
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Cell cell = allCellArray[i][j];
                int nextCellLifeStatus = cell.getNextCellLifeStatusByTableMethod(cell.getStatus(), cell.getAliveNumber());
                cell.setStatus(nextCellLifeStatus);
            }
        }
    }

    /**
     * 跟新指定周期次数
     *
     * @param cycleumber
     */
    public void updateCellLifeStatusIsCycleNumber(int cycleumber) {
        for (int i = 0; i < cycleumber; i++) {
            updateCellLifeStatus();
            log.info(this.toString());
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                stringBuilder.append(allCellArray[i][j].getStatus()).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
