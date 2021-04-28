package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.bags.Bag;
import spaceStation.models.planets.Planet;

import java.util.Collection;
import java.util.Optional;

public class MissionImpl implements Mission{
    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {
        Collection<String> itemsOnPlanet = planet.getItems();

        for (Astronaut currentAstronaut : astronauts) {
            while (currentAstronaut.canBreath()) {


                Bag bag = currentAstronaut.getBag();
                Collection<String> items = bag.getItems();


            }


        }
    }
}
