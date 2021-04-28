package spaceStation.core;

import spaceStation.common.ConstantMessages;
import spaceStation.common.ExceptionMessages;
import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private AstronautRepository allAstronauts;
    private PlanetRepository allPlanets;

    public ControllerImpl() {
        this.allAstronauts = new AstronautRepository();
        this.allPlanets = new PlanetRepository();
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        Astronaut astronaut;

        if (type.equals("Biologist")) {
            astronaut = new Biologist(astronautName);
        } else if (type.equals("Geodesist")) {
            astronaut = new Geodesist(astronautName);
        } else if (type.equals("Meteorologist")) {
            astronaut = new Meteorologist(astronautName);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_INVALID_TYPE);
        }
        this.allAstronauts.add(astronaut);

        return String.format(ConstantMessages.ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        PlanetImpl planet = new PlanetImpl(planetName);
        planet.addItems(Arrays.asList(items));

        this.allPlanets.add(planet);

        return String.format(ConstantMessages.PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        Astronaut byName = this.allAstronauts.findByName(astronautName);

        if (byName == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.ASTRONAUT_DOES_NOT_EXIST, astronautName));
        }

        return String.format(ConstantMessages.ASTRONAUT_RETIRED, astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {
        //is all austo under 60
        Collection<Astronaut> models = this.allAstronauts.getModels();
        boolean allUnder60oxy = true;
        for (Astronaut astronautCurrent : models) {
            if (astronautCurrent.getOxygen() > 60) {
                allUnder60oxy = false;
            }
        }
        if (allUnder60oxy) {
            throw new IllegalArgumentException(ExceptionMessages.PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }

        Collection<Astronaut> astroAbove60Oxy = this.allAstronauts.getModels().stream().filter(a -> a.getOxygen() > 60).collect(Collectors.toList());
        int astroNumberBeforeMission = astroAbove60Oxy.size();

        Planet planetByName = this.allPlanets.findByName(planetName);
        MissionImpl mission = new MissionImpl();
        mission.explore(planetByName, astroAbove60Oxy);
        int astroNumberAfterMission = models.size();

        return String.format(ConstantMessages.PLANET_EXPLORED ,planetByName, astroNumberBeforeMission - astroNumberAfterMission);
    }

    @Override
    public String report() {
        StringBuilder stringBuilder = new StringBuilder();

        Collection<Astronaut> astro = this.allAstronauts.getModels();
        astro.stream().filter(a -> a.getBag() != null);


        return null;
    }
}
