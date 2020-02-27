package com.example.takehomechallenge.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;


public class CitySetsUtil {

	private static Logger logger= LoggerFactory.getLogger("CitySetsUtil");
	private  Resource resource;
    private  List<Set<String>> citySetList = new CopyOnWriteArrayList();

    public  HashMap<String, Integer> getCityMap(Resource resource){
    	this.resource=resource;
    	return  buildMap(resource);
	}




	private HashMap<String, Integer> buildMap(Resource resource) {
		List<String> lines;
		try {

			logger.debug(" Resource URI " + resource.getURI() );
			lines = Files.readAllLines(Paths.get(resource.getURI()), StandardCharsets.UTF_8);

			for (String line : lines)
				if (line.trim().length() != 0)
					processCities(line);
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<Set<String>> setsList=compactcitySetList();
		return buildHashMap(setsList);
	}

	private HashMap<String, Integer> buildHashMap(List<Set<String>> setsList) {
    	HashMap<String, Integer> hm= new HashMap();
		int i=0;

    	for (Set<String> citySet: setsList){
			for(String city: citySet){
				hm.put(city, i);
			}
    		i++;
		}
    	return hm;
	}

	private  List<Set<String>> compactcitySetList() {

		boolean done = false;
		Set<String> set1;
		Set<String> set2;
		boolean keepBuilding = true;
		
		while (keepBuilding) {
			for (int i = 0; i < citySetList.size(); i++) {

				set1 = citySetList.get(i);
				done = false;

				while (!done) {
					done = true;

					Iterator<Set<String>> iterator = citySetList.listIterator(i + 1);

					while (iterator.hasNext()) {
						set2 = iterator.next();

						if (citiesConnectTrue(set1, set2)) {
							done = false;
							Set<String> temp = new HashSet<String>();
							temp.addAll(set1);
							temp.addAll(set2);
							citySetList.remove(set1);
							citySetList.remove(set2);
							citySetList.add(i, temp);
							set1.addAll(temp);

						}
					}
				}
			}
			keepBuilding=isDone(citySetList);
		}

		for (Set<String> cities : citySetList) {
			logger.info("Cities : " + cities);
		}

		return new ArrayList<Set<String>>(citySetList);

	}

	private  boolean isDone(List<Set<String>> citySetList2) {

		int size = citySetList2.size();

		for (int i = 0; i < size - 1; i++) {
			Set<String> cities = citySetList2.get(i);
			for (int j = i + 1; j < size; j++) {
				Set<String> cities2 = citySetList2.get(j);
				for(String x: cities2) {
					if (cities.contains(x)) {
						return true;
					}
				}
					
			}
		}
		return false;
	}

	private  boolean citiesConnectTrue(Set<String> set1, Set<String> set2) {

		for (String city : set2) {
			if (set1.contains(city))
				return true;
		}

		return false;
	}

	private  void processCities(String line) {
		String[] cities = line.split(",");
		Set<String> citySet = new HashSet();
		for (String city : cities) {
			citySet.add(city.trim());
		}
		citySetList.add(citySet);

	}

}
