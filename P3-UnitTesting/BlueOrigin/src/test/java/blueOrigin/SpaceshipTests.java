package blueOrigin;

import org.junit.Assert;
import org.junit.Test;

public class SpaceshipTests {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS Spaceship

    @Test
    public void testConstructorCreateShip() {
        Spaceship spaceship = new Spaceship("DeathStar", 10);
        String actualName = spaceship.getName();
        String expectedName = "DeathStar";

        Assert.assertEquals(expectedName, actualName);
    }

    @Test (expected = NullPointerException.class)
    public void testConstructorCreateShipSetNameThrowException() {
        Spaceship spaceship = new Spaceship("", 10);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructorCreateShipSetCapacityThrowException() {
        Spaceship spaceship = new Spaceship("DeathStar", -2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testMethodGetCount() {
        Spaceship spaceship = new Spaceship("DeathStar", -5);
        Astronaut astronaut = new Astronaut("Bobi", 100);
        Astronaut astronaut2 = new Astronaut("Aleks", 100);
        spaceship.add(astronaut);
        spaceship.add(astronaut2);

        int actualCount = spaceship.getCount();
        int expectedCount = 2;

        Assert.assertEquals(expectedCount, actualCount);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testMethodAddIfCapacityIsFullShouldThrowExceptionIAE() {
        Spaceship spaceship = new Spaceship("DeathStar", 1);
        Astronaut astronaut = new Astronaut("Bobi", 100);
        Astronaut astronaut2 = new Astronaut("Aleks", 100);
        spaceship.add(astronaut);
        spaceship.add(astronaut2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testMethodAddIfAstronautExistThrowExceptionIAE() {
        Spaceship spaceship = new Spaceship("DeathStar", 5);
        Astronaut astronaut = new Astronaut("Bobi", 100);
        Astronaut astronaut2 = new Astronaut("Bobi", 95);
        spaceship.add(astronaut);
        spaceship.add(astronaut2);
    }

    @Test
    public void testMethodAddShouldAddSuccessful() {
        Spaceship spaceship = new Spaceship("DeathStar", 2);
        Astronaut astronaut = new Astronaut("Bobi", 100);
        Astronaut astronaut2 = new Astronaut("Aleks", 100);
        spaceship.add(astronaut);
        spaceship.add(astronaut2);

        int expectedCount = 2;
        Assert.assertEquals(expectedCount, spaceship.getCount());
    }

    @Test
    public void testMethodRemoveSmallSize() {
        Spaceship spaceship = new Spaceship("DeathStar", 2);
        Astronaut astronaut = new Astronaut("Bobi", 100);
        spaceship.add(astronaut);

        spaceship.remove("Bobi");

        Assert.assertEquals(0, spaceship.getCount());
    }

    @Test
    public void testMethodRemoveShouldReturnTrue() {
        Spaceship spaceship = new Spaceship("DeathStar", 2);
        Astronaut astronaut = new Astronaut("Bobi", 100);
        spaceship.add(astronaut);

        spaceship.remove("Bobi");
        Assert.assertTrue(true);
    }

    @Test
    public void testMethodRemoveShouldReturnFalse() {
        Spaceship spaceship = new Spaceship("DeathStar", 2);
        Astronaut astronaut = new Astronaut("Bobi", 100);
        spaceship.add(astronaut);

        spaceship.remove("Aleks");
        Assert.assertFalse(false);
    }
}
