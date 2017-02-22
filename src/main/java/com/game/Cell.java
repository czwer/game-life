package com.game;

import com.game.constant.CellLifeStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: chenzhiwei
 * Date: 17/2/18
 * Time: 上午11:57
 * 单个方格里的但个细胞生命
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cell {
    /**
     * 存活状态
     * @see CellLifeStatus
     */
    private int status;
    /**
     * 周围存活数量
     */
    private int aliveNumber;

    /**
     * 获取当前活着的邻居数量
     *
     * @param grid
     * @param currentX
     * @param currentY
     * @return
     */
    public int getCurrentNeighborAliveNumber(Grid grid, int currentX, int currentY) {
        int[][] allNeighborArray = builderAllNeighborArray(currentX, currentY);
        int currentAliveNumber = 0;
        for (int[] intArray : allNeighborArray) {
            int x = intArray[0];
            int y = intArray[1];
            if (x < 0 || x >=grid.getX() || y < 0 || y >= grid.getY()) {
                continue;
            }
            int status = grid.getAllCellArray()[x][y].getStatus();
            if (status == CellLifeStatus.ALIVE) {
                currentAliveNumber++;
            }
        }
        return currentAliveNumber;
    }

    private int[][] builderAllNeighborArray(int currentX, int currentY) {
        return new int[][]{
                    {currentX - 1, currentY + 1},
                    {currentX, currentY + 1},
                    {currentX + 1, currentY + 1},
                    {currentX - 1, currentY},
                    {currentX + 1, currentY},
                    {currentX - 1, currentY - 1},
                    {currentX, currentY - 1},
                    {currentX + 1, currentY - 1}};
    }

    /**
     * 获得下一次周期的生命状态---使用查表法进行判断
     * a).“人口过少”：任何活细胞如果活邻居少于2个，则死掉。
     * b).“正常”：任何活细胞如果活邻居为2个或3个，则继续活。
     * c).“人口过多”：任何活细胞如果活邻居大于3个，则死掉。
     * d).“繁殖”：任何死细胞如果活邻居正好是3个，则活过来。
     * 当前状态：0：死，1：活，x：当前状态
     * （当前状态，邻居活着数量，未来状态）
     * ===============穷尽所有情况=================
     * （1，0，0）
     * （1，1，0）
     * （1，2，1）
     * （1，3，1）
     * （1，>=4，0）
     * （0，3，1）
     * （0，!=3，0）
     * =============按邻居活的数量分组===============
     * （1，0，0），（0，!=3，0）
     * （1，1，0），（0，!=3，0）
     * （1，2，1），（0，!=3，0）
     * （1，3，1），（0，3，1）
     * （1，>=4，0），（0，!=3，0）
     * =============简化同一行的表达式===============
     * （x，0，0）
     * （x，1，0）
     * （x，2，x）
     * （x，3，1)
     * （x，>=4，0）
     * ===========================================
     * @param aliveNumber
     * @return
     */
    public int getNextCellLifeStatusByTableMethod(int status, int aliveNumber) {
        int[] allCellLifeStatus = new int[]{0, 0, status, 1, 0, 0, 0, 0, 0};
        return allCellLifeStatus[aliveNumber];
    }

    /**
     * 获得下一次周期的生命状态---通过条件判断
     * a).“人口过少”：任何活细胞如果活邻居少于2个，则死掉。
     * b).“正常”：任何活细胞如果活邻居为2个或3个，则继续活。
     * c).“人口过多”：任何活细胞如果活邻居大于3个，则死掉。
     * d).“繁殖”：任何死细胞如果活邻居正好是3个，则活过来。
     * @param status
     * @param aliveNumber
     * @return
     */
    public int getNextCellLifeStatusByConditionJudgment(int status,int aliveNumber){
        //只关心活的情况即可
        if((status== CellLifeStatus.ALIVE && (aliveNumber ==2 || aliveNumber ==3)) || (status==CellLifeStatus.DEAD && aliveNumber ==3)){
            return CellLifeStatus.ALIVE;
        }
        return CellLifeStatus.DEAD;
    }
}
