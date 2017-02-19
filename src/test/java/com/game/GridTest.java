package com.game;

import com.game.constant.CellLifeStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: chenzhiwei
 * Date: 17/2/19
 * Time: 下午9:07
 */
@Slf4j
public class GridTest {

    private Grid grid;

    @Before
    public void init(){
        grid = new Grid(3,3, CellLifeStatus.ALIVE);
    };

    @Test
    public void updateCellLifeStatusTest(){
        log.info(grid.toString());
        grid.updateCellLifeStatus();
        Cell[][] allCellArray = grid.getAllCellArray();
        Assert.assertTrue(allCellArray[0][2].getStatus() ==CellLifeStatus.ALIVE);
        Assert.assertTrue(allCellArray[1][2].getStatus() ==CellLifeStatus.DEAD);
        Assert.assertTrue(allCellArray[2][2].getStatus() ==CellLifeStatus.ALIVE);
        Assert.assertTrue(allCellArray[0][1].getStatus() ==CellLifeStatus.DEAD);
        Assert.assertTrue(allCellArray[1][1].getStatus() ==CellLifeStatus.DEAD);
        Assert.assertTrue(allCellArray[2][1].getStatus() ==CellLifeStatus.DEAD);
        Assert.assertTrue(allCellArray[0][0].getStatus() ==CellLifeStatus.ALIVE);
        Assert.assertTrue(allCellArray[1][0].getStatus() ==CellLifeStatus.DEAD);
        Assert.assertTrue(allCellArray[2][0].getStatus() ==CellLifeStatus.ALIVE);
        log.info(grid.toString());
    }

    @Test
    public void updateCellLifeStatusIsCycleNumberTest(){
        log.info(grid.toString());
        grid.updateCellLifeStatusIsCycleNumber(10);
        Cell[][] allCellArray = grid.getAllCellArray();
        Assert.assertTrue(allCellArray[0][2].getStatus() ==CellLifeStatus.DEAD);
        Assert.assertTrue(allCellArray[1][2].getStatus() ==CellLifeStatus.DEAD);
        Assert.assertTrue(allCellArray[2][2].getStatus() ==CellLifeStatus.DEAD);
        Assert.assertTrue(allCellArray[0][1].getStatus() ==CellLifeStatus.DEAD);
        Assert.assertTrue(allCellArray[1][1].getStatus() ==CellLifeStatus.DEAD);
        Assert.assertTrue(allCellArray[2][1].getStatus() ==CellLifeStatus.DEAD);
        Assert.assertTrue(allCellArray[0][0].getStatus() ==CellLifeStatus.DEAD);
        Assert.assertTrue(allCellArray[1][0].getStatus() ==CellLifeStatus.DEAD);
        Assert.assertTrue(allCellArray[2][0].getStatus() ==CellLifeStatus.DEAD);
    }

}
