package heroRepository;

import org.junit.Assert;
import org.junit.Test;

public class HeroRepositoryTests {


    @Test (expected = NullPointerException.class)
    public void testCreateHeroFirstExc() {
        Hero hero = new Hero("Bobi", 99);
        HeroRepository heroRepository = new HeroRepository();

        heroRepository.create(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateHeroSecondExc() {
        Hero hero = new Hero("Bobi", 99);
        Hero hero2 = new Hero("Bobi", 99);
        HeroRepository heroRepository = new HeroRepository();

        heroRepository.create(hero);
        heroRepository.create(hero2);
    }

    @Test
    public void testCreateHeroSuccess() {
        Hero hero = new Hero("Bobi", 99);
        HeroRepository heroRepository = new HeroRepository();
        String actual = heroRepository.create(hero);
        String expected = "Successfully added hero Bobi with level 99";

        Assert.assertEquals(expected, actual);
    }

    @Test (expected = NullPointerException.class)
    public void testRemoveHeroFirstExc() {
        HeroRepository heroRepository = new HeroRepository();
        heroRepository.remove(null);
    }

    @Test
    public void testGetHeroHighsLvl() {
        Hero hero = new Hero("Bobi", 98);
        Hero hero2 = new Hero("Aleks", 99);
        HeroRepository heroRepository = new HeroRepository();
        String actual = heroRepository.create(hero);
        String actual2 = heroRepository.create(hero2);

        Assert.assertEquals(hero2, heroRepository.getHeroWithHighestLevel());
    }

    @Test
    public void testGetHero() {
        Hero hero = new Hero("Bobi", 98);
        HeroRepository heroRepository = new HeroRepository();
        heroRepository.create(hero);
        Hero actual = heroRepository.getHero("Bobi");

        Assert.assertEquals(hero, actual);
    }
}
