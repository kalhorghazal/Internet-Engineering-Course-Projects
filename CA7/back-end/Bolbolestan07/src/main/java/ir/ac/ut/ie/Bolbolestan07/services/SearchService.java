package ir.ac.ut.ie.Bolbolestan07.services;

import ir.ac.ut.ie.Bolbolestan07.controllers.domain.Bolbolestan.Bolbolestan;
import ir.ac.ut.ie.Bolbolestan07.controllers.domain.Bolbolestan.Offering.Offering;
import ir.ac.ut.ie.Bolbolestan07.controllers.models.SearchData;
import ir.ac.ut.ie.Bolbolestan07.repository.BolbolestanRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchService {
    private static final ArrayList<String> types = new ArrayList<String>(
            Arrays.asList("Umumi", "Asli", "Paaye", "Takhasosi"));

    private static ArrayList<Offering> sortSearchedOfferings(List<Offering> offerings, String filter) {
        ArrayList<Offering> sortedSearchResults = new ArrayList<>();
        for (String type: types) {
            for (Offering offering : offerings) {
                if (offering.getType().equals(type))
                    sortedSearchResults.add(offering);
            }
        }
        return sortedSearchResults;
    }

    public static ArrayList<Offering> searchKeyword(SearchData searchData) throws Exception{
        System.out.println("in searchKeyword");
        Bolbolestan.getInstance().searchForCourses(searchData.getKeyword());
        ArrayList<Offering> searchResult = BolbolestanRepository.getInstance().searchOfferings(searchData);
        return sortSearchedOfferings(searchResult, searchData.getType());
    }
}
