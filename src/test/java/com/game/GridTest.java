package com.game;

import lombok.extern.slf4j.Slf4j;
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
        grid = new Grid(3,3);
    };

    @Test
    public void updateCellLifeStatusTest(){
        log.info(grid.toString());
        grid.updateCellLifeStatus();
        log.info(grid.toString());
    }

    @Test
    public void updateCellLifeStatusIsCycleNumberTest(){
        log.info(grid.toString());
        grid.updateCellLifeStatusIsCycleNumber(10);
    }

}
