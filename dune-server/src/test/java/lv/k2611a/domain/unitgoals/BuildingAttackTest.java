package lv.k2611a.domain.unitgoals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lv.k2611a.domain.Building;
import lv.k2611a.domain.BuildingType;
import lv.k2611a.domain.Entity;
import lv.k2611a.domain.Map;
import lv.k2611a.domain.Unit;
import lv.k2611a.domain.UnitType;
import lv.k2611a.service.game.GameServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml"})
public class BuildingAttackTest {
    @Autowired
    private GameServiceImpl gameService;

    @Before
    public void setup() {
        gameService.setTickCount(0);
    }
    // Several tanks attack a single construction yard
    // Objective is for all of the tanks to get to maximal attack range of the building.
    @Test
    public void buildingReachTest() {
        Map map = new Map(64,64);

        Building building = new Building();
        building.setType(BuildingType.CONSTRUCTIONYARD);
        building.setX(20);
        building.setY(20);
        building.setOwnerId(1);
        int building1 = map.addBuilding(building);

        Unit tank = new Unit();
        tank.setUnitType(UnitType.BATTLE_TANK);
        tank.setGoal(new Attack(Entity.BUILDING, building1));
        tank.setOwnerId(1);
        tank.setX(1);
        tank.setY(1);
        int tankVictim = map.addUnit(tank);

        gameService.setMap(map);
        gameService.tick();

        assertEquals(1,map.getUnits().size());

        // should be enough ticks to destroy the other tank
        for (int i = 0; i < 5000; i++) {
            gameService.tick();
        }

        assertEquals(0,map.getBuildings().size());
    }
}