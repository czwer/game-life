package com.game;

import com.game.constant.CellLifeStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: chenzhiwei
 * Date: 17/2/18
 * Time: 下午12:02
 */
public class CellTest {
    private Grid grid;
    private Cell cell;

    /**
     * 测试时以3x3的格子就能包含所有可能的情况
     */
    @Before
    public void init(){
        grid = new Grid(3,3,CellLifeStatus.DEAD);
        cell = grid.getAllCellArray()[1][1];
    }

    /**
     * 测试当前存活的数量，测试正确情况
     */
    @Test
    public void getCurrentAliveNumberIsCorrectTest(){
        grid.getAllCellArray()[0][0].setStatus(CellLifeStatus.ALIVE);
        grid.getAllCellArray()[0][1].setStatus(CellLifeStatus.ALIVE);
        int aliveNumber = cell.getCurrentNeighborAliveNumber(grid,1,1);
        Assert.assertEquals(2,aliveNumber);
    }
    /**
     * 测试当前存活的数量,测试错误情况
     */
    @Test
    public void getCurrentAliveNumberIsErrorTest(){
        grid.getAllCellArray()[0][0].setStatus(CellLifeStatus.ALIVE);
        grid.getAllCellArray()[0][1].setStatus(CellLifeStatus.ALIVE);
        int aliveNumber = cell.getCurrentNeighborAliveNumber(grid,1,1);
        Assert.assertEquals(1,aliveNumber);
    }

    /**
     * 测试当前存活的数量,测试边界
     */
    @Test
    public void getCurrentAliveNumberIsBoundaryTest(){
        grid.getAllCellArray()[1][1].setStatus(CellLifeStatus.ALIVE);
        int[][] testAllCellCoordinate = new int[][]{{0,2},{1,2},{2,2},{0,1},{2,1},{0,0},{1,0},{2,0}};
        for (int[] cellCoordinate: testAllCellCoordinate) {
            int aliveNumber = cell.getCurrentNeighborAliveNumber(grid, cellCoordinate[0], cellCoordinate[1]);
            Assert.assertEquals(1, aliveNumber);
        }
    }


    @Test
    public void getNextCellLifeStatusCurrentAliveAndNeighborZero(){
        int aliveNumber = cell.getCurrentNeighborAliveNumber(grid,1,1);
        int nextCellStatus = cell.getNextCellLifeStatusByTableMethod(CellLifeStatus.ALIVE, aliveNumber);
        Assert.assertEquals(nextCellStatus,CellLifeStatus.DEAD);
    }
    @Test
    public void getNextCellLifeStatusCurrentAliveAndNeighborOne(){
        grid.getAllCellArray()[0][0].setStatus(CellLifeStatus.ALIVE);
        int aliveNumber = cell.getCurrentNeighborAliveNumber(grid,1,1);
        int nextCellStatus = cell.getNextCellLifeStatusByTableMethod(CellLifeStatus.ALIVE, aliveNumber);
        Assert.assertEquals(nextCellStatus,CellLifeStatus.DEAD);
    }
    @Test
    public void getNextCellLifeStatusCurrentAliveAndNeighborTwo(){
        grid.getAllCellArray()[0][0].setStatus(CellLifeStatus.ALIVE);
        grid.getAllCellArray()[0][1].setStatus(CellLifeStatus.ALIVE);
        int aliveNumber = cell.getCurrentNeighborAliveNumber(grid,1,1);
        int nextCellStatus = cell.getNextCellLifeStatusByTableMethod(CellLifeStatus.ALIVE, aliveNumber);
        Assert.assertEquals(nextCellStatus,CellLifeStatus.ALIVE);
    }
    @Test
    public void getNextCellLifeStatusCurrentAliveAndNeighborThree(){
        grid.getAllCellArray()[0][0].setStatus(CellLifeStatus.ALIVE);
        grid.getAllCellArray()[0][1].setStatus(CellLifeStatus.ALIVE);
        grid.getAllCellArray()[0][2].setStatus(CellLifeStatus.ALIVE);
        int aliveNumber = cell.getCurrentNeighborAliveNumber(grid,1,1);
        int nextCellStatus = cell.getNextCellLifeStatusByTableMethod(CellLifeStatus.ALIVE, aliveNumber);
        Assert.assertEquals(nextCellStatus,CellLifeStatus.ALIVE);
    }

    @Test
    public void getNextCellLifeStatusCurrentAliveAndNeighborFour(){
        grid.getAllCellArray()[0][0].setStatus(CellLifeStatus.ALIVE);
        grid.getAllCellArray()[0][1].setStatus(CellLifeStatus.ALIVE);
        grid.getAllCellArray()[0][2].setStatus(CellLifeStatus.ALIVE);
        grid.getAllCellArray()[1][0].setStatus(CellLifeStatus.ALIVE);
        int aliveNumber = cell.getCurrentNeighborAliveNumber(grid,1,1);
        int nextCellStatus = cell.getNextCellLifeStatusByTableMethod(CellLifeStatus.ALIVE, aliveNumber);
        Assert.assertEquals(nextCellStatus,CellLifeStatus.DEAD);
    }
    @Test
    public void getNextCellLifeStatusCurrentDeadAndNeighborThree(){
        grid.getAllCellArray()[0][0].setStatus(CellLifeStatus.ALIVE);
        grid.getAllCellArray()[0][1].setStatus(CellLifeStatus.ALIVE);
        grid.getAllCellArray()[0][2].setStatus(CellLifeStatus.ALIVE);
        int aliveNumber = cell.getCurrentNeighborAliveNumber(grid,1,1);
        int nextCellStatus = cell.getNextCellLifeStatusByTableMethod(CellLifeStatus.DEAD, aliveNumber);
        Assert.assertEquals(nextCellStatus,CellLifeStatus.ALIVE);
    }
    @Test
    public void getNextCellLifeStatusCurrentDeadAndNeighborTwo(){
        grid.getAllCellArray()[0][0].setStatus(CellLifeStatus.ALIVE);
        grid.getAllCellArray()[0][1].setStatus(CellLifeStatus.ALIVE);
        int aliveNumber = cell.getCurrentNeighborAliveNumber(grid,1,1);
        int nextCellStatus = cell.getNextCellLifeStatusByTableMethod(CellLifeStatus.DEAD, aliveNumber);
        Assert.assertEquals(nextCellStatus,CellLifeStatus.DEAD);
    }

    @Test
    public void getNextCellLifeStatusCurrentAliveAndNeighborZeroByConditionJudgment(){
        int aliveNumber = cell.getCurrentNeighborAliveNumber(grid,1,1);
        int nextCellStatus = cell.getNextCellLifeStatusByConditionJudgment(CellLifeStatus.ALIVE, aliveNumber);
        Assert.assertEquals(nextCellStatus,CellLifeStatus.DEAD);
    }
    @Test
    public void getNextCellLifeStatusCurrentAliveAndNeighborOneByConditionJudgment(){
        grid.getAllCellArray()[0][0].setStatus(CellLifeStatus.ALIVE);
        int aliveNumber = cell.getCurrentNeighborAliveNumber(grid,1,1);
        int nextCellStatus = cell.getNextCellLifeStatusByConditionJudgment(CellLifeStatus.ALIVE, aliveNumber);
        Assert.assertEquals(nextCellStatus,CellLifeStatus.DEAD);
    }
    @Test
    public void getNextCellLifeStatusCurrentAliveAndNeighborTwoByConditionJudgment(){
        grid.getAllCellArray()[0][0].setStatus(CellLifeStatus.ALIVE);
        grid.getAllCellArray()[0][1].setStatus(CellLifeStatus.ALIVE);
        int aliveNumber = cell.getCurrentNeighborAliveNumber(grid,1,1);
        int nextCellStatus = cell.getNextCellLifeStatusByConditionJudgment(CellLifeStatus.ALIVE, aliveNumber);
        Assert.assertEquals(nextCellStatus,CellLifeStatus.ALIVE);
    }
    @Test
    public void getNextCellLifeStatusCurrentAliveAndNeighborThreeByConditionJudgment(){
        grid.getAllCellArray()[0][0].setStatus(CellLifeStatus.ALIVE);
        grid.getAllCellArray()[0][1].setStatus(CellLifeStatus.ALIVE);
        grid.getAllCellArray()[0][2].setStatus(CellLifeStatus.ALIVE);
        int aliveNumber = cell.getCurrentNeighborAliveNumber(grid,1,1);
        int nextCellStatus = cell.getNextCellLifeStatusByConditionJudgment(CellLifeStatus.ALIVE, aliveNumber);
        Assert.assertEquals(nextCellStatus,CellLifeStatus.ALIVE);
    }

    @Test
    public void getNextCellLifeStatusCurrentAliveAndNeighborFourByConditionJudgment(){
        grid.getAllCellArray()[0][0].setStatus(CellLifeStatus.ALIVE);
        grid.getAllCellArray()[0][1].setStatus(CellLifeStatus.ALIVE);
        grid.getAllCellArray()[0][2].setStatus(CellLifeStatus.ALIVE);
        grid.getAllCellArray()[1][0].setStatus(CellLifeStatus.ALIVE);
        int aliveNumber = cell.getCurrentNeighborAliveNumber(grid,1,1);
        int nextCellStatus = cell.getNextCellLifeStatusByConditionJudgment(CellLifeStatus.ALIVE, aliveNumber);
        Assert.assertEquals(nextCellStatus,CellLifeStatus.DEAD);
    }
    @Test
    public void getNextCellLifeStatusCurrentDeadAndNeighborThreeByConditionJudgment(){
        grid.getAllCellArray()[0][0].setStatus(CellLifeStatus.ALIVE);
        grid.getAllCellArray()[0][1].setStatus(CellLifeStatus.ALIVE);
        grid.getAllCellArray()[0][2].setStatus(CellLifeStatus.ALIVE);
        int aliveNumber = cell.getCurrentNeighborAliveNumber(grid,1,1);
        int nextCellStatus = cell.getNextCellLifeStatusByConditionJudgment(CellLifeStatus.DEAD, aliveNumber);
        Assert.assertEquals(nextCellStatus,CellLifeStatus.ALIVE);
    }
    @Test
    public void getNextCellLifeStatusCurrentDeadAndNeighborTwoByConditionJudgment(){
        grid.getAllCellArray()[0][0].setStatus(CellLifeStatus.ALIVE);
        grid.getAllCellArray()[0][1].setStatus(CellLifeStatus.ALIVE);
        int aliveNumber = cell.getCurrentNeighborAliveNumber(grid,1,1);
        int nextCellStatus = cell.getNextCellLifeStatusByConditionJudgment(CellLifeStatus.DEAD, aliveNumber);
        Assert.assertEquals(nextCellStatus,CellLifeStatus.DEAD);
    }

    /**
     * 测试结果查表法略差一点点，可能因为数组平均查找为4步，判断平均为2.5步导致
     */
    @Test
    public void comparisonAlgorithmTime(){
        for (int j=0;j<10;j++) {
            long startTime = System.currentTimeMillis();
            int nextCellStatus = 0;
            int m = 100000000;
            for (int i = 0; i < m; i++) {
                Double aliveNumber = Math.random() * 8;
                nextCellStatus = cell.getNextCellLifeStatusByConditionJudgment(CellLifeStatus.DEAD, aliveNumber.intValue());
            }
            long endTime = System.currentTimeMillis();
            System.out.println("用getNextCellLifeStatusByConditionJudgment获得下次状态,最终状态:" + nextCellStatus + "," + "循环" + m +
                    "次,计算花费时间:" + (endTime - startTime));

            startTime = System.currentTimeMillis();
            for (int i = 0; i < m; i++) {
                Double aliveNumber = Math.random() * 8;
                nextCellStatus = cell.getNextCellLifeStatusByTableMethod(CellLifeStatus.DEAD, aliveNumber.intValue());
            }
            endTime = System.currentTimeMillis();
            System.out.println("用getNextCellLifeStatusByTableMethod获得下次状态，最终状态:" + nextCellStatus + "," + "循环" + m + "次,计算花费时间:" + (endTime - startTime));
        }
    }




}
