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
 * 棋盘格子(生存空间)
 */
@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Grid {
    /**
     * 横轴长度：x轴
     */
    private int horizontalAxisLength;
    /**
     * 纵轴长度：y轴
     */
    private int longitudinalAxisLength;
    /**
     * 表示棋盘格子(生存空间)的二维数组
     */
    private Cell[][] allCellArray;

    /**
     * 构造函数：其中根据horizontalAxisLength,longitudinalAxisLength初始化allCellArray，转态固定
     *
     * @param horizontalAxisLength
     * @param longitudinalAxisLength
     * @param cellLifeStatus
     */
    public Grid(int horizontalAxisLength, int longitudinalAxisLength, int cellLifeStatus) {
        this.horizontalAxisLength = horizontalAxisLength;
        this.longitudinalAxisLength = longitudinalAxisLength;
        this.allCellArray = new Cell[horizontalAxisLength][longitudinalAxisLength];
        for (int i = 0; i < horizontalAxisLength; i++) {
            for (int j = 0; j < longitudinalAxisLength; j++) {
                this.allCellArray[i][j] = new Cell(cellLifeStatus, 0);
            }
        }
    }

    /**
     * 构造函数：其中根据horizontalAxisLength,longitudinalAxisLength初始化allCellArray,转态随机
     *
     * @param horizontalAxisLength
     * @param longitudinalAxisLength
     */
    public Grid(int horizontalAxisLength, int longitudinalAxisLength) {
        this.horizontalAxisLength = horizontalAxisLength;
        this.longitudinalAxisLength = longitudinalAxisLength;
        this.allCellArray = new Cell[horizontalAxisLength][longitudinalAxisLength];
        for (int i = 0; i < horizontalAxisLength; i++) {
            for (int j = 0; j < longitudinalAxisLength; j++) {
                int cellLifeStatus = Math.random() < 0.5 ? CellLifeStatus.DEAD : CellLifeStatus.ALIVE;
                this.allCellArray[i][j] = new Cell(cellLifeStatus, 0);
            }
        }
    }

    public void updateCellLifeStatus() {
        for (int i = 0; i < horizontalAxisLength; i++) {
            for (int j = 0; j < longitudinalAxisLength; j++) {
                Cell cell = allCellArray[i][j];
                int aliveNumber = cell.getCurrentNeighborAliveNumber(this, i, j);
                cell.setNeighborAliveNumber(aliveNumber);
            }
        }
        for (int i = 0; i < horizontalAxisLength; i++) {
            for (int j = 0; j < longitudinalAxisLength; j++) {
                Cell cell = allCellArray[i][j];
                int nextCellLifeStatus = cell.getNextCellLifeStatusByTableMethod(cell.getStatus(), cell.getNeighborAliveNumber());
                cell.setStatus(nextCellLifeStatus);
            }
        }
    }

    /**
     * 跟新指定周期次数
     *
     * @param cycleNumber
     */
    public void updateCellLifeStatusIsCycleNumber(int cycleNumber) {
        for (int i = 0; i < cycleNumber; i++) {
            updateCellLifeStatus();
            log.info(this.toString());
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        for (int i = 0; i < horizontalAxisLength; i++) {
            for (int j = 0; j < longitudinalAxisLength; j++) {
                stringBuilder.append(allCellArray[i][j].getStatus()).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
